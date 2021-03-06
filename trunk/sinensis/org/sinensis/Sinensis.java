//Sinensis.java
//COPYRIGHT (C) 2007 Timothee HUNTER
//Contact: sinensis-dictionary@gmail.com
//This file is part of Sinensis.
//
//    Sinensis is free software; you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation; either version 3 of the License, or
//    (at your option) any later version.
//
//    Sinensis is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with Sinensis; if not, write to the Free Software
//    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA

package org.sinensis;

import org.sinensis.data.*;
import org.sinensis.task.*;


import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;
import com.trolltech.qt.*;
import java.util.*;

public class Sinensis extends QMainWindow
{
	public final static String versionStr="0.2 SVN ALPHA";
	
//	The font used to render the character selection list
	public static QFont charViewFont;
//	The font used to render the key list
	public static QFont keyFont;
//	The font used to render the word selection list
	public static QFont wordViewFont;
	
	
	
	public static void main(String[] args) 
	{
		QApplication.initialize(args);
		Sinensis mainw = new Sinensis();
		mainw.show();
		QApplication.exec();
		
//		The store object (which lives in another thread) watches this variable in order to exit its main loop
//		TODO : cleverer mecanism using signals/slots
//		The current mechanism activate the program every 0.1sec, which is useless most of the time, and bad for a laptop configuration		
		Sinensis.alive=false;
	}

//	The store object (which lives in another thread) watches this variable in order to exit its main loop
//	TODO : cleverer mecanism using signals/slots
	public static boolean alive=true;


//	The backend which contains all the data
	public DataStore store;
//	The model used for storing radicals/keys
	public RadicalModel radicalModel;
//	The model used for storing result words
	public WordsModel wordsModel;
//	The model used for storing characters
	private CharModel charModel;
//	The glyph manager
//	TODO
	private GlyphViewManager gvManager;
	
//	Different icons which have to be loaded manually
	private QIcon sinIcon=new QIcon("classpath:ui/logo32.png");
	
//	Use the system tray
	private QSystemTrayIcon tray=new QSystemTrayIcon(sinIcon,this);
//	Use the clipboard for interaction with the desktop
	private QClipboard clipboard=QApplication.clipboard(); 
	
//	Settings
//	TODO : switch to the QSettings mechanism
//	TODO : rename it to IOInterface
	static public Settings settings=new Settings();

//	This signal is sent when the UI is created
//	It is used to update the models
	public QObject.Signal0 readyToFillUI = new QObject.Signal0();
	
	public QObject.Signal1<CCharacterRequest> charQueryReadyToProcess = new QObject.Signal1<CCharacterRequest>();
	
//	QtDesigner file
	public Ui_SinMainW main=new Ui_SinMainW();
	
	public Sinensis()
	{
		QCoreApplication.setOrganizationName("Sinensis");
		QCoreApplication.setOrganizationDomain("sinensis.berlios.de");
		QCoreApplication.setApplicationName("Sinensis Dictionary");
		QSettings qsettings=new QSettings(this);
		qsettings.setValue("GUI/guiVisible", true);

//		GUI creation
		main.setupUi(this);
		this.setWindowIcon(sinIcon);
		this.setWindowIconText(tr("Sinensis dictionary"));
		this.setWindowTitle(tr("Sinensis dictionary"));
		createActions();
		
		main.clearSearchBtn.clicked.connect(this,"clearCharSearch()");
		loadConfigurableData();
		
		tray.setVisible((Boolean)qsettings.value("GUI/showTray",true));
		tray.activated.connect(this,"changeMainUiStatus(QSystemTrayIcon$ActivationReason)");
		
		Task.mainUI=this;
		store=new DataStore();
		QThread thread=new QThread(store);
		store.moveToThread(thread);
		thread.setDaemon(true);
		thread.start();
		gvManager=new GlyphViewManager(this,main.displayView,store);
		store.progress.connect(main.progressBar,"setValue(int)",Qt.ConnectionType.QueuedConnection);
		store.status.connect(statusBar(),"showMessage(String)",Qt.ConnectionType.QueuedConnection);
		
		clipboard.changed.connect(this,"displayInfoInline()");
		clipboard.dataChanged.connect(this,"displayInfoInline()");
		clipboard.selectionChanged.connect(this,"displayInfoInline()");

		main.keysView.setItemDelegate(new RadicalDelegate(main.keysView));
		main.keysView.verticalHeader().setDefaultSectionSize(RadicalDelegate.sizeHint().height());
		main.keysView.horizontalHeader().setDefaultSectionSize(RadicalDelegate.sizeHint().width());
		main.keysView.verticalHeader().setVisible(false);
		main.keysView.horizontalHeader().setVisible(false);
		radicalModel=new RadicalModel(this,store);
//		main.keysView.setModel(radicalModel);
		main.keysView.clicked.connect(radicalModel,"increaseCount(QModelIndex)");
		main.keysView.doubleClicked.connect(radicalModel,"decreaseCount(QModelIndex)");
//		radicalModel.dataChanged.connect(main.keysView,"repaint()");
		radicalModel.dataChanged.connect(this,"buildCharQuery()");
//		main.keysView.activated.connect(this,"clickedKey(QModelIndex)");
		
		wordsModel=new WordsModel(this,store,this);
		main.wordsListView.verticalHeader().setVisible(false);
		main.wordsListView.horizontalHeader().setVisible(true);
		main.wordsListView.setModel(wordsModel);
		store.wordSearchReady.connect(wordsModel,"setContent(String)");

		main.wordFilter.textChanged.connect(store,"lookForWord(String)");
		main.wordsListView.clicked.connect(this,"displayWord(QModelIndex)");

		main.pinyinEdit.textChanged.connect(this,"buildCharQuery()");
		main.englishEdit.textChanged.connect(this,"buildCharQuery()");
		main.pinyinEdit.textChanged.connect(this,"buildCharQuery()");
		main.strokeNbrEdit.textChanged.connect(this,"buildCharQuery()");
		main.unicodeEdit.textChanged.connect(this,"buildCharQuery()");
		
		charModel=new CharModel(this,this);
		store.charSearchReady.connect(charModel,"setContent(String)");
		
		main.selectionView.setModel(charModel);
		main.selectionView.setViewMode(QListView.ViewMode.IconMode);
		main.selectionView.setWrapping(true);
		main.selectionView.clicked.connect(this,"displayChar(QModelIndex)");
		
		
//		store.loadData();
		loadConfigurableData();
		
		readyToFillUI.connect(store,"loadData()");
//		store.radicalsLoaded.connect(this,"fillModel()");
		store.radicalsLoaded.connect(this,"fillRadicalModel()");
		charQueryReadyToProcess.connect(store,"lookForChar(CCharacterRequest)");
		readyToFillUI.emit();

	}
	
//	Sets all the data that can be changed through the config dialog
//	We want it to have a very dynamic behaviour
//	A bit ugly sometimes since the qsettings has trouble making a difference between String and Integer
	public void loadConfigurableData()
	{
		QSettings set=new QSettings();
		int i=10;
		Object o=null;
		o=set.value("GUI/charViewFontSize",10);
		if(o instanceof Integer)
			i=(Integer)o;
		if(o instanceof String)
			i=Integer.parseInt((String)o);
		charViewFont=(QFont)set.value("GUI/charViewFont",new QFont("Arial Unicode MS", i));
		
		o=set.value("GUI/keyViewFontSize",10);
		if(o instanceof Integer)
			i=(Integer)o;
		if(o instanceof String)
			i=Integer.parseInt((String)o);
		keyFont=(QFont)set.value("GUI/keyViewFont",new QFont("Arial Unicode MS", i));

		o=set.value("GUI/wordViewFontSize",10);
		if(o instanceof Integer)
			i=(Integer)o;
		if(o instanceof String)
			i=Integer.parseInt((String)o);
		wordViewFont=(QFont)set.value("GUI/wordViewFont",new QFont("Arial Unicode MS", i));
		if(main==null)
			return;
//System.out.println("##"+keyFont+" "+keyFont.pointSize());
		main.selectionView.setFont(charViewFont);
		main.selectionView.repaint();
		main.keysView.setFont(keyFont);
		main.keysView.repaint();
		main.wordsListView.setFont(wordViewFont);
		main.wordsListView.repaint();
	}
	
//	Populate the actions, assuming the designer UI has been created
	private void createActions()
	{
		main.aboutQt.triggered.connect(QApplication.instance(),"aboutQt()");
		main.aboutSinensis.triggered.connect(this,"aboutSinensis()");
		main.actionCloseW.triggered.connect(this,"changeMainUiStatus()");
		main.actionQuit.triggered.connect(this,"quit()");
		main.actionWhatsThis.triggered.connect(this,"whatIsThis()");
		main.actionConfigure_interface.setEnabled(true);
		main.actionConfigure_interface.triggered.connect(this,"openConfigDialog()");
		
	}
	
	private void whatIsThis()
	{
		QWhatsThis.enterWhatsThisMode();
	}
	
	private void quit()
	{
System.out.println(tr("Bye bye"));
		QCoreApplication.quit();
	}
	
	private String aboutStr=String.format(tr("<h1>Sinensis %1$s</h1><p>Copyright 2007 (C) Timothee Hunter (thunter@developer.berlios.de) All rights reserved. </p> <p>This software is provided \"as is\" with the hope it will be of any use to you, and without any warranty. It is released under the GNU General Public License.</p> <p>More information on the <a href=\"http://sinensis.berlios.de\"> Sinensis website</a></p>."),versionStr);
	private void aboutSinensis()
	{
		QMessageBox.about(this,tr("About Sinensis"), aboutStr);
	}
	
//	When we clean the UI to prepare for a new search, the cleaning does not need to trigger a new (useless) query 
	private boolean charSearchEnabled=true;
	
	private void clearCharSearch()
	{
//		No query while we are cleaning the interface
		charSearchEnabled=false;
		main.pinyinEdit.clear();
		main.characterEdit.clear();
		main.unicodeEdit.clear();
		main.englishEdit.clear();
		main.strokeNbrEdit.clear();
		radicalModel.clearSelection();
		charModel.setContent("");
		charSearchEnabled=true;
	}
	
// building a CCharacterSearch and passing it to the datastore
	public void buildCharQuery()
	{
		if(!charSearchEnabled)
			return;
		CCharacterRequest req=new CCharacterRequest();
		req.addPinyin(main.pinyinEdit.text());
		req.addTranslation(main.englishEdit.text());
		req.addStrokeNbr(main.strokeNbrEdit.text());
		req.addUnicode(main.unicodeEdit.text());
		req.addChinese(main.characterEdit.text());
		List<CCharacter> list=radicalModel.activatedRadicals();
		for(CCharacter c: list)
		{
//			System.out.println("###"+c.getInt("KEY_ID"));
			req.tokensInt.put("KEY_ID", c.getInt("KEY_ID"));
		}
		charQueryReadyToProcess.emit(req);
//		store.lookFor(req);
	}
	
//	Giving a word query to the datastore
//	The inout here is only one line => we pass it directly to the datastore
//	public void lookForWord(String u)
//	{	
//		store.lookForWord(u);
//	}
	
//	public void fillModel()
//	{
//		System.out.println(store.foo());
//	}
	
	public void displayWord(QModelIndex index)
	{
		if(index==null)
			return;
		QModelIndex i=wordsModel.index(index.row(),0);
		if(i==null)
			return;
		String s=(String)(i.data());
		
		displayWord(s);
	}
	
	public void fillRadicalModel()
	{
		radicalModel.fillModel();
		main.keysView.setModel(radicalModel);
		
	}
	
	public void displayWord(String word)
	{
		Word w=store.getWord(word);
		main.wordBrowser.setText(settings.formatWord(w));
		tray.showMessage(word,w.trans);
	}
	
//	Main function to interact with the desktop
//	It is called when the user selects some text around the interface
	private void displayInfoInline()
	{
		String text=clipboard.text(QClipboard.Mode.Selection);
		if(text==null||text.equals(""))
			text=clipboard.text(QClipboard.Mode.Clipboard);
		if(text==null||text.equals(""))
			return;
//		Ok, let's see what the user wants us to understand
//		First, is it a character?
		if(text.length()==1&&CCharacter.isChineseChar(text.charAt(0)))
		{
			CCharacter cchar=store.getChar(text.charAt(0));
			if(cchar!=null)
				tray.showMessage(String.format(tr("Translation of %1$s"),text), cchar.translation());
			return;
		}
//		So then, it might be a word.
		main.wordFilter.setText(text);
	}
	
//	Hides or display the main user interface, depending on the tray actions
	private void changeMainUiStatus(QSystemTrayIcon.ActivationReason reason)
	{
		if(reason==QSystemTrayIcon.ActivationReason.Trigger)
		{
			this.setVisible(this.isHidden());
		}
	}
	
	private void changeMainUiStatus()
	{
		this.setVisible(this.isHidden());
	}
	
	public void displayChar(QModelIndex index)
	{
		try
		{
			if(charModel.index(index.row(),0)==null||charModel.index(index.row(),0).equals(""))
				return;
			
		String s=(String)(charModel.index(index.row(),0).data());
		if(s.length()<=0)
			return;
		char c=s.charAt(0);
		
		displayChar(c);
		}catch(Exception e){e.printStackTrace();}
	}
	
	public void displayChar(char uni)
	{
// 		gvManager.loadGlyph(uni);
		CCharacter c=store.getChar(uni);
		main.infosBrowser.setText(settings.formatChar(c));
	}

	private Ui_ConfigUIWidget ui_configUiWidget;
	private void configSaveApplyChanges()
	{
		if(ui_configUiWidget==null)
			return;
		QSettings settings=new QSettings();
		settings.setValue("GUI/SysTrayEnabled", ui_configUiWidget.trayEnabledCheck.isChecked());
		settings.setValue("GUI/StartMinimized", ui_configUiWidget.startMinimizedCheck.isChecked());
		
		settings.setValue("GUI/charViewFont", ui_configUiWidget.charFontCombo.currentFont());
		settings.setValue("GUI/wordViewFont", ui_configUiWidget.wordFontCombo.currentFont());
		settings.setValue("GUI/keyViewFont", ui_configUiWidget.keyFontCombo.currentFont());
		
		settings.setValue("GUI/charViewFontSize", ui_configUiWidget.charFontSpin.value());
		settings.setValue("GUI/wordViewFontSize", ui_configUiWidget.wordFontSpin.value());
		settings.setValue("GUI/keyViewFontSize", ui_configUiWidget.keyFontSpin.value());
		
		settings.sync();
		
		loadConfigurableData();
	}
	
	private void openConfigDialog()
	{
		QSettings settings=new QSettings();
		
		QDialog dialog=new QDialog(this);
		Ui_Dialog ui_dialog=new Ui_Dialog();
		ui_dialog.setupUi(dialog);
		
		QWidget widgetUi=new QWidget(dialog);
		ui_configUiWidget=new Ui_ConfigUIWidget();
		ui_configUiWidget.setupUi(widgetUi);
		
		ui_dialog.stackedWidget.addWidget(widgetUi);
		ui_dialog.stackedWidget.setCurrentWidget(widgetUi);
		
		ui_configUiWidget.charFontCombo.setWritingSystem(QFontDatabase.WritingSystem.SimplifiedChinese);
		ui_configUiWidget.keyFontCombo.setWritingSystem(QFontDatabase.WritingSystem.SimplifiedChinese);
		ui_configUiWidget.wordFontCombo.setWritingSystem(QFontDatabase.WritingSystem.SimplifiedChinese);
		
		ui_configUiWidget.charFontCombo.currentFontChanged.connect(this,"configSaveApplyChanges()");
		ui_configUiWidget.wordFontCombo.currentFontChanged.connect(this,"configSaveApplyChanges()");
		ui_configUiWidget.keyFontCombo.currentFontChanged.connect(this,"configSaveApplyChanges()");
		
		ui_configUiWidget.charFontCombo.setCurrentFont(charViewFont);
		ui_configUiWidget.keyFontCombo.setCurrentFont(keyFont);
		ui_configUiWidget.wordFontCombo.setCurrentFont(wordViewFont);
		
		ui_configUiWidget.charFontSpin.setValue(Integer.parseInt((String)settings.value("GUI/charViewFontSize")));
		ui_configUiWidget.wordFontSpin.setValue(Integer.parseInt((String)settings.value("GUI/wordViewFontSize")));
		ui_configUiWidget.keyFontSpin.setValue(Integer.parseInt((String)settings.value("GUI/keyViewFontSize")));

		ui_configUiWidget.charFontSpin.valueChanged.connect(this,"configSaveApplyChanges()");
		ui_configUiWidget.keyFontSpin.valueChanged.connect(this,"configSaveApplyChanges()");
		ui_configUiWidget.wordFontSpin.valueChanged.connect(this,"configSaveApplyChanges()");
		
		dialog.setVisible(true);

	}

}
