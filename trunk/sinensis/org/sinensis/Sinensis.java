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
	public static void main(String[] args) 
	{
		QApplication.initialize(args);
		Sinensis mainw = new Sinensis();
		mainw.setStyle(new QPlastiqueStyle());
		mainw.setStyleSheet(".QWidget{font-size: 16px;font-family: \"Arial Unicode MS\"}");
		mainw.show();
		QApplication.exec();
		Sinensis.alive=false;
	}

	public static boolean alive=true;


	public DataStore store;
	public QStandardItemModel radicalModel;
	public WordsModel wordsModel;
	private CharModel charModel;
	private GlyphViewManager gvManager;
	
	private QIcon sinIcon=new QIcon("classpath:data/acroread.png");
	private QSystemTrayIcon tray=new QSystemTrayIcon(sinIcon,this);
	private QClipboard clipboard=QApplication.clipboard(); 
	
	static public Settings settings=new Settings();

	public Ui_SinMainW main=new Ui_SinMainW();
	
	public Sinensis()
	{
		QCoreApplication.setOrganizationName("Sinensis");
		QCoreApplication.setOrganizationDomain("sinensis.berlios.de");
		QCoreApplication.setApplicationName("Sinensis Dictionary");
		QSettings qsettings=new QSettings(this);
		qsettings.setValue("GUI/guiVisible", true);

		main.setupUi(this);
		
		tray.setVisible((Boolean)qsettings.value("GUI/showTray",true));
		tray.activated.connect(this,"changeMainUiStatus(QSystemTrayIcon$ActivationReason)");
		
		Task.mainUI=this;
		store=new DataStore(this);
		gvManager=new GlyphViewManager(main.displayView,store);
		store.progress.connect(main.progressBar,"setValue(int)",Qt.ConnectionType.QueuedConnection);
		store.status.connect(statusBar(),"showMessage(String)",Qt.ConnectionType.QueuedConnection);
		QThread thread=new QThread(store);
		store.moveToThread(thread);
		thread.start();
		
		clipboard.changed.connect(this,"displayInfoInline()");
		clipboard.selectionChanged.connect(this,"displayInfoInline()");
// 		radicalModel=new RadicalModel();
// 		main.keysView.
		
// 		main.keysView.clicked.connect(this,"clickedKey(QModelIndex)");
// 		main.keysView.activated.connect(this,"clickedKey(QModelIndex)");
// 		main.keysView.viewportEntered.connect(this,"clickedKey()");
		wordsModel=new WordsModel(this,store,this);
		main.wordsListView.verticalHeader().setVisible(false);
		main.wordsListView.horizontalHeader().setVisible(true);
		main.wordsListView.setModel(wordsModel);
		store.wordSearchReady.connect(wordsModel,"setContent(String)");
// 		store.wordSearchReady.connect(main.wordsListView,"resizeColumnsToContents()");
		main.wordFilter.textChanged.connect(this,"lookForWord(String)");
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
		
		store.loadData();
	}

	public void buildCharQuery()
	{
		CCharacterRequest req=new CCharacterRequest();
		req.addPinyin(main.pinyinEdit.text());
		req.addTranslation(main.englishEdit.text());
		req.addStrokeNbr(main.strokeNbrEdit.text());
		req.addUnicode(main.unicodeEdit.text());
		req.addChinese(main.characterEdit.text());
System.out.println(req);
		store.lookFor(req);
	}
	
	public void lookForWord(String u)
	{	
// System.out.println("u");
		store.lookForWord(u);
	}
	
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
		System.out.println(">>>"+text);
//		Ok, let's see what the user wants us to understand
//		First, is it a character?
		if(text.length()==1&&CCharacter.isChineseChar(text.charAt(0)))
		{
			CCharacter cchar=store.getChar(text.charAt(0));
			if(cchar!=null)
				tray.showMessage("Translation of "+text, cchar.translation());
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
	
	public void displayChar(QModelIndex index)
	{
		try
		{
		char c=((String)(charModel.index(index.row(),0).data())).charAt(0);
		
		displayChar(c);
		}catch(Exception e){}
	}
	
	public void displayChar(char uni)
	{
// 		gvManager.loadGlyph(uni);
		CCharacter c=store.getChar(uni);
		System.out.println(">>>"+c+"\n>>>"+settings.formatChar(c));
		main.infosBrowser.setText(settings.formatChar(c));
	}
	
	
	
	public void setupWordsModel()
	{
		
// 		wordsModel=new QStandardItemModel(0,4,this);
// 		wProxyModel=new WordsProxyModel(this);
// 		wProxyModel.setSourceModel(wordsModel);
// 		wProxyModel.setDynamicSortFilter(true);
// 		main.wordsListView.setModel(wProxyModel);
	}
	
// 	private void wordsFilterChanged()
// 	{
// 		wProxyModel.setTokens(main.wordFilter.text());
// System.out.println(main.wordFilter.text());
// 	}
	
// 	public void addWords(List<Word> l)
// 	{
// // 		for(Word w : l)
// 		{
// 			wordsModel.insertRow(0);
// 			wordsModel.setData(wordsModel.index(0, 0), w.simpl);
// // 			wordsModel.setData(wordsModel.index(0, 1), w.tradi);
// 			wordsModel.setData(wordsModel.index(0, 2), w.pinyin);
// 			wordsModel.setData(wordsModel.index(0, 3), w.trans);
// 		}
// 		wordsFilterChanged();
// 
// 	}
	
	public void setupRadicalModel(List<CCharacter> chars)
	{
		main.keysView.setStyleSheet("#keysView{font-size: 20px;font-family: \"Arial Unicode MS\"}");
// 		main.keysView.installEventFilter(new MyEventFilter(main.keysView));
		main.keysView.setItemDelegate(new RadicalDelegate(main.keysView));
		main.keysView.verticalHeader().setDefaultSectionSize(RadicalDelegate.sizeHint().height());
		main.keysView.horizontalHeader().setDefaultSectionSize(RadicalDelegate.sizeHint().width());
		main.keysView.verticalHeader().setVisible(false);
		main.keysView.horizontalHeader().setVisible(false);

		final int cols=10;
		Vector< Vector<CCharacter> > myData=new Vector< Vector<CCharacter> >(200);
		for(int i=0;i<200;i++)
			myData.add(i,new Vector<CCharacter>());
			
		for(CCharacter c : chars)
		{
			myData.get(c.getInt("Strokes")).add(c);
			c.put("SelectStroke",0);
		}

		radicalModel=new QStandardItemModel(1,cols,this);
		
		int i=0,j=0;
			for(CCharacter c : chars)
			{
				radicalModel.setData(radicalModel.index(j,i,null),c);
				i++;
				if(i>=cols)
				{
					j++;
					i=0;
					radicalModel.insertRows(j,1,null);
				}
			}
		main.keysView.setModel(radicalModel);
		main.keysView.clicked.connect(this,"clickedKey(QModelIndex)");
		main.keysView.doubleClicked.connect(this,"doubleclickedKey(QModelIndex)");
		main.keysView.activated.connect(this,"clickedKey(QModelIndex)");
	}
	
	public void clickedKey(QModelIndex index)
	{
System.out.println("+"+index.data());	
		int u=((CCharacter)index.data()).getInt("SelectStroke");
		((CCharacter)index.data()).put("SelectStroke",u+1);
		radicalModel.dataChanged.emit(index,index);
	}

	public void doubleclickedKey(QModelIndex index)
	{
System.out.println("-"+index.data());	
		int u=((CCharacter)index.data()).getInt("SelectStroke")-4;
// 		if(u<0)
// 			u=0;
		((CCharacter)index.data()).put("SelectStroke",u);
		radicalModel.dataChanged.emit(index,index);
	}
	
	public void selectedWord(QModelIndex index)
	{
		
	}
}
/*
class WordsProxyModel extends QSortFilterProxyModel
{
	String [] tokens=null;
	public WordsProxyModel(QObject parent)
	{
		super(parent);
	}
	
	public void setTokens(String u)
	{
		tokens=u.split(" ");
// System.out.println(";");
		filterChanged();
	}
	
	protected boolean filterAcceptsRow(int sourceRow, QModelIndex sourceParent)
	{
// System.out.print(";");
		if(tokens==null)
			return true;
		String zhSim = (String)(sourceModel().index(sourceRow, 0, sourceParent).data());
		String zhTrad = (String)(sourceModel().index(sourceRow, 1, sourceParent).data());
		String py = (String)(sourceModel().index(sourceRow, 2, sourceParent).data());
		String def =(String)(sourceModel().index(sourceRow, 3, sourceParent).data());
// System.out.print(zhSim+""+zhTrad+py+def);
		for(int i=0;i<tokens.length;i++)
			if((zhSim!=null && zhSim.indexOf(tokens[i])>=0) || (zhSim!=null && zhTrad.indexOf(tokens[i])>=0)
			 || (zhSim!=null && py.indexOf(tokens[i])>=0) || (zhSim!=null && def.indexOf(tokens[i])>=0) )
				return true;

		return false;
	}
}*/


// class MyEventFilter extends QObject
// {
// 	private QTableView view;
// 	public MyEventFilter(QTableView v)
// 	{
// 		view=v;
// 	}
// 	
// 	public boolean eventFilter(QObject object,QEvent e)
// 	{
// 		if(e!=null && (e instanceof QHelpEvent))
// 		{
// 			CCharacter c=(CCharacter)view.indexAt(((QHelpEvent)e).pos()).data();
// // System.out.println(";"+c.get("ZH"));			
// 		}
// 		return super.eventFilter(object,e);
// 	}
// 	
// }