/********************************************************************************
** Form generated from reading ui file 'main.jui'
**
** Created: sam. juin 16 22:36:11 2007
**      by: Qt User Interface Compiler version 4.3.0
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

package org.sinensis;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_SinMainW
{
    public QAction actionLoad_new_dictionary;
    public QAction actionQuit;
    public QAction actionPrint;
    public QAction actionExport_to_PDF;
    public QAction actionExport_to_text;
    public QAction aboutSinensis;
    public QAction aboutQt;
    public QAction actionNew_search;
    public QAction actionConfigure_interface;
    public QAction actionDatabases;
    public QAction actionCloseW;
    public QAction actionWhatsThis;
    public QWidget centralwidget;
    public QHBoxLayout hboxLayout;
    public QSplitter splitter_4;
    public QWidget layoutWidget;
    public QVBoxLayout vboxLayout;
    public QSplitter splitter_3;
    public QTabWidget searchTab;
    public QWidget strokeTab;
    public QVBoxLayout vboxLayout1;
    public QTableView strokeView;
    public QWidget keysTab;
    public QVBoxLayout vboxLayout2;
    public QHBoxLayout hboxLayout1;
    public QTableView keysView;
    public QWidget otherTab;
    public QGridLayout gridLayout;
    public QGridLayout gridLayout1;
    public QLabel label;
    public QPushButton clearPYbut;
    public QLineEdit pinyinEdit;
    public QLabel label_2;
    public QPushButton clearTransbut;
    public QLineEdit englishEdit;
    public QLabel label_3;
    public QPushButton clearStrokesbut;
    public QLineEdit strokeNbrEdit;
    public QLabel label_4;
    public QPushButton clearUNIbut;
    public QLineEdit unicodeEdit;
    public QLabel label_5;
    public QPushButton clearZHbut;
    public QLineEdit characterEdit;
    public QPushButton clearSearchBtn;
    public QSpacerItem spacerItem;
    public QListView selectionView;
    public QProgressBar progressBar;
    public QSplitter splitter_2;
    public QTabWidget tabWidget;
    public QWidget defTab;
    public QVBoxLayout vboxLayout3;
    public QTextBrowser infosBrowser;
    public QWidget displayTab;
    public QVBoxLayout vboxLayout4;
    public QHBoxLayout hboxLayout2;
    public QGraphicsView displayView;
    public QGridLayout gridLayout2;
    public QPushButton pushButton_2;
    public QPushButton pushButton;
    public QPushButton pushButton_3;
    public QPushButton pushButton_5;
    public QWidget tab_2;
    public QWidget layoutWidget1;
    public QVBoxLayout vboxLayout5;
    public QHBoxLayout hboxLayout3;
    public QPushButton clearButton;
    public QLineEdit wordFilter;
    public QSplitter splitter;
    public QTableView wordsListView;
    public QTextBrowser wordBrowser;
    public QMenuBar menubar;
    public QMenu menuFile;
    public QMenu menuHelp;
    public QMenu menuConfigure;
    public QStatusBar statusbar;

    public Ui_SinMainW() { super(); }

    public void setupUi(QMainWindow SinMainW)
    {
        SinMainW.setObjectName("SinMainW");
        SinMainW.resize(new QSize(800, 600).expandedTo(SinMainW.minimumSizeHint()));
        actionLoad_new_dictionary = new QAction(SinMainW);
        actionLoad_new_dictionary.setObjectName("actionLoad_new_dictionary");
        actionQuit = new QAction(SinMainW);
        actionQuit.setObjectName("actionQuit");
        actionPrint = new QAction(SinMainW);
        actionPrint.setObjectName("actionPrint");
        actionExport_to_PDF = new QAction(SinMainW);
        actionExport_to_PDF.setObjectName("actionExport_to_PDF");
        actionExport_to_text = new QAction(SinMainW);
        actionExport_to_text.setObjectName("actionExport_to_text");
        aboutSinensis = new QAction(SinMainW);
        aboutSinensis.setObjectName("aboutSinensis");
        aboutSinensis.setIcon(new QIcon());
        aboutQt = new QAction(SinMainW);
        aboutQt.setObjectName("aboutQt");
        actionNew_search = new QAction(SinMainW);
        actionNew_search.setObjectName("actionNew_search");
        actionConfigure_interface = new QAction(SinMainW);
        actionConfigure_interface.setObjectName("actionConfigure_interface");
        actionConfigure_interface.setEnabled(false);
        actionDatabases = new QAction(SinMainW);
        actionDatabases.setObjectName("actionDatabases");
        actionDatabases.setEnabled(false);
        actionCloseW = new QAction(SinMainW);
        actionCloseW.setObjectName("actionCloseW");
        actionWhatsThis = new QAction(SinMainW);
        actionWhatsThis.setObjectName("actionWhatsThis");
        centralwidget = new QWidget(SinMainW);
        centralwidget.setObjectName("centralwidget");
        hboxLayout = new QHBoxLayout(centralwidget);
        hboxLayout.setObjectName("hboxLayout");
        splitter_4 = new QSplitter(centralwidget);
        splitter_4.setObjectName("splitter_4");
        splitter_4.setOrientation(com.trolltech.qt.core.Qt.Orientation.Horizontal);
        layoutWidget = new QWidget(splitter_4);
        layoutWidget.setObjectName("layoutWidget");
        vboxLayout = new QVBoxLayout(layoutWidget);
        vboxLayout.setObjectName("vboxLayout");
//        vboxLayout.setContentsMargins(0, 0, 0, 0);
        splitter_3 = new QSplitter(layoutWidget);
        splitter_3.setObjectName("splitter_3");
        splitter_3.setOrientation(com.trolltech.qt.core.Qt.Orientation.Vertical);
        searchTab = new QTabWidget(splitter_3);
        searchTab.setObjectName("searchTab");
        strokeTab = new QWidget();
        strokeTab.setObjectName("strokeTab");
        vboxLayout1 = new QVBoxLayout(strokeTab);
        vboxLayout1.setObjectName("vboxLayout1");
        strokeView = new QTableView(strokeTab);
        strokeView.setObjectName("strokeView");
        strokeView.setFrameShape(com.trolltech.qt.gui.QFrame.Shape.Box);
        strokeView.setFrameStyle(49);

        vboxLayout1.addWidget(strokeView);

        searchTab.addTab(strokeTab, "Strokes");
        keysTab = new QWidget();
        keysTab.setObjectName("keysTab");
        vboxLayout2 = new QVBoxLayout(keysTab);
        vboxLayout2.setObjectName("vboxLayout2");
        hboxLayout1 = new QHBoxLayout();
        hboxLayout1.setObjectName("hboxLayout1");
//        hboxLayout1.setContentsMargins(0, 0, 0, 0);

        vboxLayout2.addLayout(hboxLayout1);

        keysView = new QTableView(keysTab);
        keysView.setObjectName("keysView");
        keysView.setAutoFillBackground(true);
        keysView.setFrameStyle(22);
        keysView.setFrameShadow(com.trolltech.qt.gui.QFrame.Shadow.Plain);
        keysView.setLineWidth(0);
        keysView.setEditTriggers(com.trolltech.qt.gui.QAbstractItemView.EditTrigger.createQFlags(com.trolltech.qt.gui.QAbstractItemView.EditTrigger.NoEditTriggers));
        keysView.setAlternatingRowColors(true);
        keysView.setSelectionMode(com.trolltech.qt.gui.QAbstractItemView.SelectionMode.SingleSelection);

        vboxLayout2.addWidget(keysView);

        searchTab.addTab(keysTab, "Keys/Parts");
        otherTab = new QWidget();
        otherTab.setObjectName("otherTab");
        gridLayout = new QGridLayout(otherTab);
        gridLayout.setObjectName("gridLayout");
        gridLayout1 = new QGridLayout();
        gridLayout1.setObjectName("gridLayout1");
//        gridLayout1.setContentsMargins(0, 0, 0, 0);
        label = new QLabel(otherTab);
        label.setObjectName("label");
        label.setAlignment(com.trolltech.qt.core.Qt.AlignmentFlag.createQFlags(com.trolltech.qt.core.Qt.AlignmentFlag.AlignAbsolute,com.trolltech.qt.core.Qt.AlignmentFlag.AlignBottom,com.trolltech.qt.core.Qt.AlignmentFlag.AlignCenter,com.trolltech.qt.core.Qt.AlignmentFlag.AlignHCenter,com.trolltech.qt.core.Qt.AlignmentFlag.AlignHorizontal_Mask,com.trolltech.qt.core.Qt.AlignmentFlag.AlignJustify,com.trolltech.qt.core.Qt.AlignmentFlag.AlignLeft,com.trolltech.qt.core.Qt.AlignmentFlag.AlignRight,com.trolltech.qt.core.Qt.AlignmentFlag.AlignTop,com.trolltech.qt.core.Qt.AlignmentFlag.AlignVCenter,com.trolltech.qt.core.Qt.AlignmentFlag.AlignVertical_Mask));

        gridLayout1.addWidget(label, 0, 0, 1, 1);

        clearPYbut = new QPushButton(otherTab);
        clearPYbut.setObjectName("clearPYbut");
        clearPYbut.setIcon(new QIcon(new QPixmap("classpath:ui/editclear.png")));

        gridLayout1.addWidget(clearPYbut, 0, 1, 1, 1);

        pinyinEdit = new QLineEdit(otherTab);
        pinyinEdit.setObjectName("pinyinEdit");
        pinyinEdit.setFocusPolicy(com.trolltech.qt.core.Qt.FocusPolicy.StrongFocus);

        gridLayout1.addWidget(pinyinEdit, 0, 2, 1, 1);

        label_2 = new QLabel(otherTab);
        label_2.setObjectName("label_2");
        label_2.setAlignment(com.trolltech.qt.core.Qt.AlignmentFlag.createQFlags(com.trolltech.qt.core.Qt.AlignmentFlag.AlignAbsolute,com.trolltech.qt.core.Qt.AlignmentFlag.AlignBottom,com.trolltech.qt.core.Qt.AlignmentFlag.AlignCenter,com.trolltech.qt.core.Qt.AlignmentFlag.AlignHCenter,com.trolltech.qt.core.Qt.AlignmentFlag.AlignHorizontal_Mask,com.trolltech.qt.core.Qt.AlignmentFlag.AlignJustify,com.trolltech.qt.core.Qt.AlignmentFlag.AlignLeft,com.trolltech.qt.core.Qt.AlignmentFlag.AlignRight,com.trolltech.qt.core.Qt.AlignmentFlag.AlignTop,com.trolltech.qt.core.Qt.AlignmentFlag.AlignVCenter,com.trolltech.qt.core.Qt.AlignmentFlag.AlignVertical_Mask));

        gridLayout1.addWidget(label_2, 1, 0, 1, 1);

        clearTransbut = new QPushButton(otherTab);
        clearTransbut.setObjectName("clearTransbut");
        clearTransbut.setIcon(new QIcon(new QPixmap("classpath:ui/editclear.png")));

        gridLayout1.addWidget(clearTransbut, 1, 1, 1, 1);

        englishEdit = new QLineEdit(otherTab);
        englishEdit.setObjectName("englishEdit");
        englishEdit.setFocusPolicy(com.trolltech.qt.core.Qt.FocusPolicy.StrongFocus);

        gridLayout1.addWidget(englishEdit, 1, 2, 1, 1);

        label_3 = new QLabel(otherTab);
        label_3.setObjectName("label_3");

        gridLayout1.addWidget(label_3, 2, 0, 1, 1);

        clearStrokesbut = new QPushButton(otherTab);
        clearStrokesbut.setObjectName("clearStrokesbut");
        clearStrokesbut.setIcon(new QIcon(new QPixmap("classpath:ui/editclear.png")));

        gridLayout1.addWidget(clearStrokesbut, 2, 1, 1, 1);

        strokeNbrEdit = new QLineEdit(otherTab);
        strokeNbrEdit.setObjectName("strokeNbrEdit");
        strokeNbrEdit.setFocusPolicy(com.trolltech.qt.core.Qt.FocusPolicy.StrongFocus);

        gridLayout1.addWidget(strokeNbrEdit, 2, 2, 1, 1);

        label_4 = new QLabel(otherTab);
        label_4.setObjectName("label_4");
        label_4.setAlignment(com.trolltech.qt.core.Qt.AlignmentFlag.createQFlags(com.trolltech.qt.core.Qt.AlignmentFlag.AlignAbsolute,com.trolltech.qt.core.Qt.AlignmentFlag.AlignBottom,com.trolltech.qt.core.Qt.AlignmentFlag.AlignCenter,com.trolltech.qt.core.Qt.AlignmentFlag.AlignHCenter,com.trolltech.qt.core.Qt.AlignmentFlag.AlignHorizontal_Mask,com.trolltech.qt.core.Qt.AlignmentFlag.AlignJustify,com.trolltech.qt.core.Qt.AlignmentFlag.AlignLeft,com.trolltech.qt.core.Qt.AlignmentFlag.AlignRight,com.trolltech.qt.core.Qt.AlignmentFlag.AlignTop,com.trolltech.qt.core.Qt.AlignmentFlag.AlignVCenter,com.trolltech.qt.core.Qt.AlignmentFlag.AlignVertical_Mask));

        gridLayout1.addWidget(label_4, 3, 0, 1, 1);

        clearUNIbut = new QPushButton(otherTab);
        clearUNIbut.setObjectName("clearUNIbut");
        clearUNIbut.setIcon(new QIcon(new QPixmap("classpath:ui/editclear.png")));

        gridLayout1.addWidget(clearUNIbut, 3, 1, 1, 1);

        unicodeEdit = new QLineEdit(otherTab);
        unicodeEdit.setObjectName("unicodeEdit");
        unicodeEdit.setFocusPolicy(com.trolltech.qt.core.Qt.FocusPolicy.StrongFocus);

        gridLayout1.addWidget(unicodeEdit, 3, 2, 1, 1);

        label_5 = new QLabel(otherTab);
        label_5.setObjectName("label_5");

        gridLayout1.addWidget(label_5, 4, 0, 1, 1);

        clearZHbut = new QPushButton(otherTab);
        clearZHbut.setObjectName("clearZHbut");
        clearZHbut.setIcon(new QIcon(new QPixmap("classpath:ui/editclear.png")));

        gridLayout1.addWidget(clearZHbut, 4, 1, 1, 1);

        characterEdit = new QLineEdit(otherTab);
        characterEdit.setObjectName("characterEdit");
        characterEdit.setFocusPolicy(com.trolltech.qt.core.Qt.FocusPolicy.StrongFocus);

        gridLayout1.addWidget(characterEdit, 4, 2, 1, 1);

        clearSearchBtn = new QPushButton(otherTab);
        clearSearchBtn.setObjectName("clearSearchBtn");
        clearSearchBtn.setIcon(new QIcon(new QPixmap("classpath:ui/editclear.png")));

        gridLayout1.addWidget(clearSearchBtn, 6, 0, 1, 3);

        spacerItem = new QSpacerItem(20, 40, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);

        gridLayout1.addItem(spacerItem, 5, 1, 1, 1);


        gridLayout.addLayout(gridLayout1, 0, 0, 1, 1);

        searchTab.addTab(otherTab, "Page");
        splitter_3.addWidget(searchTab);
        selectionView = new QListView(splitter_3);
        selectionView.setObjectName("selectionView");
        selectionView.setEditTriggers(com.trolltech.qt.gui.QAbstractItemView.EditTrigger.createQFlags(com.trolltech.qt.gui.QAbstractItemView.EditTrigger.NoEditTriggers));
        selectionView.setDragEnabled(true);
        selectionView.setAlternatingRowColors(true);
        selectionView.setFlow(com.trolltech.qt.gui.QListView.Flow.LeftToRight);
//        selectionView.setProperty("isWrapping", true);
        selectionView.setResizeMode(com.trolltech.qt.gui.QListView.ResizeMode.Adjust);
        selectionView.setLayoutMode(com.trolltech.qt.gui.QListView.LayoutMode.Batched);
        selectionView.setWordWrap(true);
        splitter_3.addWidget(selectionView);

        vboxLayout.addWidget(splitter_3);

        progressBar = new QProgressBar(layoutWidget);
        progressBar.setObjectName("progressBar");
        progressBar.setMaximumSize(new QSize(16777215, 15));
        progressBar.setValue(24);
        progressBar.setTextVisible(false);

        vboxLayout.addWidget(progressBar);

        splitter_4.addWidget(layoutWidget);
        splitter_2 = new QSplitter(splitter_4);
        splitter_2.setObjectName("splitter_2");
        splitter_2.setOrientation(com.trolltech.qt.core.Qt.Orientation.Vertical);
        tabWidget = new QTabWidget(splitter_2);
        tabWidget.setObjectName("tabWidget");
        defTab = new QWidget();
        defTab.setObjectName("defTab");
        vboxLayout3 = new QVBoxLayout(defTab);
        vboxLayout3.setObjectName("vboxLayout3");
        infosBrowser = new QTextBrowser(defTab);
        infosBrowser.setObjectName("infosBrowser");

        vboxLayout3.addWidget(infosBrowser);

        tabWidget.addTab(defTab, "Tab 1");
        displayTab = new QWidget();
        displayTab.setObjectName("displayTab");
        vboxLayout4 = new QVBoxLayout(displayTab);
        vboxLayout4.setObjectName("vboxLayout4");
        hboxLayout2 = new QHBoxLayout();
        hboxLayout2.setObjectName("hboxLayout2");
//        hboxLayout2.setContentsMargins(0, 0, 0, 0);
        displayView = new QGraphicsView(displayTab);
        displayView.setObjectName("displayView");

        hboxLayout2.addWidget(displayView);

        gridLayout2 = new QGridLayout();
        gridLayout2.setObjectName("gridLayout2");
//        gridLayout2.setContentsMargins(0, 0, 0, 0);
        pushButton_2 = new QPushButton(displayTab);
        pushButton_2.setObjectName("pushButton_2");

        gridLayout2.addWidget(pushButton_2, 0, 0, 1, 1);

        pushButton = new QPushButton(displayTab);
        pushButton.setObjectName("pushButton");

        gridLayout2.addWidget(pushButton, 0, 1, 1, 1);

        pushButton_3 = new QPushButton(displayTab);
        pushButton_3.setObjectName("pushButton_3");

        gridLayout2.addWidget(pushButton_3, 1, 0, 1, 1);

        pushButton_5 = new QPushButton(displayTab);
        pushButton_5.setObjectName("pushButton_5");

        gridLayout2.addWidget(pushButton_5, 1, 1, 1, 1);


        hboxLayout2.addLayout(gridLayout2);


        vboxLayout4.addLayout(hboxLayout2);

        tabWidget.addTab(displayTab, "Page");
        tab_2 = new QWidget();
        tab_2.setObjectName("tab_2");
        tabWidget.addTab(tab_2, "Tab 2");
        splitter_2.addWidget(tabWidget);
        layoutWidget1 = new QWidget(splitter_2);
        layoutWidget1.setObjectName("layoutWidget1");
        vboxLayout5 = new QVBoxLayout(layoutWidget1);
        vboxLayout5.setObjectName("vboxLayout5");
//        vboxLayout5.setContentsMargins(0, 0, 0, 0);
        hboxLayout3 = new QHBoxLayout();
        hboxLayout3.setObjectName("hboxLayout3");
//        hboxLayout3.setContentsMargins(0, 0, 0, 0);
        clearButton = new QPushButton(layoutWidget1);
        clearButton.setObjectName("clearButton");
        clearButton.setIcon(new QIcon(new QPixmap("classpath:ui/editclear.png")));

        hboxLayout3.addWidget(clearButton);

        wordFilter = new QLineEdit(layoutWidget1);
        wordFilter.setObjectName("wordFilter");
        wordFilter.setFocusPolicy(com.trolltech.qt.core.Qt.FocusPolicy.StrongFocus);

        hboxLayout3.addWidget(wordFilter);


        vboxLayout5.addLayout(hboxLayout3);

        splitter = new QSplitter(layoutWidget1);
        splitter.setObjectName("splitter");
        splitter.setOrientation(com.trolltech.qt.core.Qt.Orientation.Vertical);
        wordsListView = new QTableView(splitter);
        wordsListView.setObjectName("wordsListView");
        wordsListView.setEditTriggers(com.trolltech.qt.gui.QAbstractItemView.EditTrigger.createQFlags(com.trolltech.qt.gui.QAbstractItemView.EditTrigger.NoEditTriggers));
        wordsListView.setDragDropOverwriteMode(false);
        wordsListView.setAlternatingRowColors(true);
        wordsListView.setSelectionBehavior(com.trolltech.qt.gui.QAbstractItemView.SelectionBehavior.SelectRows);
        wordsListView.setSortingEnabled(true);
        splitter.addWidget(wordsListView);
        wordBrowser = new QTextBrowser(splitter);
        wordBrowser.setObjectName("wordBrowser");
        splitter.addWidget(wordBrowser);

        vboxLayout5.addWidget(splitter);

        splitter_2.addWidget(layoutWidget1);
        splitter_4.addWidget(splitter_2);

        hboxLayout.addWidget(splitter_4);

        SinMainW.setCentralWidget(centralwidget);
        menubar = new QMenuBar(SinMainW);
        menubar.setObjectName("menubar");
        menubar.setGeometry(new QRect(0, 0, 800, 27));
        menuFile = new QMenu(menubar);
        menuFile.setObjectName("menuFile");
        menuHelp = new QMenu(menubar);
        menuHelp.setObjectName("menuHelp");
        menuConfigure = new QMenu(menubar);
        menuConfigure.setObjectName("menuConfigure");
        SinMainW.setMenuBar(menubar);
        statusbar = new QStatusBar(SinMainW);
        statusbar.setObjectName("statusbar");
        SinMainW.setStatusBar(statusbar);

        menubar.addAction(menuFile.menuAction());
        menubar.addAction(menuConfigure.menuAction());
        menubar.addAction(menuHelp.menuAction());
        menuFile.addSeparator();
        menuFile.addAction(actionCloseW);
        menuFile.addAction(actionQuit);
        menuFile.addAction(actionNew_search);
        menuHelp.addAction(aboutSinensis);
        menuHelp.addAction(aboutQt);
        menuHelp.addAction(actionWhatsThis);
        menuConfigure.addAction(actionConfigure_interface);
        menuConfigure.addAction(actionDatabases);
        retranslateUi(SinMainW);
        clearPYbut.pressed.connect(pinyinEdit, "clear()");
        clearTransbut.pressed.connect(englishEdit, "clear()");
        clearStrokesbut.pressed.connect(strokeNbrEdit, "clear()");
        clearUNIbut.pressed.connect(unicodeEdit, "clear()");
        clearZHbut.pressed.connect(characterEdit, "clear()");
        clearButton.pressed.connect(wordFilter, "clear()");

        searchTab.setCurrentIndex(2);
        tabWidget.setCurrentIndex(0);

    } // setupUi

    void retranslateUi(QMainWindow SinMainW)
    {
        SinMainW.setWindowTitle("MainWindow");
        actionLoad_new_dictionary.setText("Load new dictionary");
        actionQuit.setText("Quit");
        actionPrint.setText("Print...");
        actionExport_to_PDF.setText("Export to PDF...");
        actionExport_to_text.setText("Export to text...");
        aboutSinensis.setText("About Sinensis...");
        aboutQt.setText("About Qt...");
        actionNew_search.setText("New search");
        actionConfigure_interface.setText("Configure interface...");
        actionDatabases.setText("Add/edit databases...");
        actionCloseW.setText("Close window");
        actionWhatsThis.setText("Contextual help");
        searchTab.setStyleSheet("");
        searchTab.setTabText(searchTab.indexOf(strokeTab), "Strokes");
        keysView.setStyleSheet("");
        searchTab.setTabText(searchTab.indexOf(keysTab), "Keys/Parts");
        label.setText("Pinyin");
        clearPYbut.setWhatsThis("<html><head><meta name=\"qrichtext\" content=\"1\" /><style type=\"text/css\">\n"+
"p, li { white-space: pre-wrap; }\n"+
"</style></head><body style=\" font-family:'Sans Serif'; font-size:9pt; font-weight:400; font-style:normal;\">\n"+
"<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\">Use this button to clear the pinyin line edit only.</p></body></html>");
        clearPYbut.setText("");
        pinyinEdit.setWhatsThis("<html><head><meta name=\"qrichtext\" content=\"1\" /><style type=\"text/css\">\n"+
"p, li { white-space: pre-wrap; }\n"+
"</style></head><body style=\" font-family:'Sans Serif'; font-size:9pt; font-weight:400; font-style:normal;\">\n"+
"<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\">Type the pinyin of the character.</p>\n"+
"<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\"><span style=\" font-weight:600;\">If you have some doubts</span>, here is what you can type:</p>\n"+
"<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\"><span style=\" font-weight:600;\">you3</span> : I will look for all characters that pronounce \"you3\"</p>\n"+
"<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\"><span style=\" font-weight:600;\">you</span> : all characters that pronounce \"you1\", \"you2\" , etc.</p>\n"+
"<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\"><span style=\" font-weight:600;\">you yang4</span> : all characters that pronounce \"yang4\" <span style=\" font-style:italic;\">or </span>\"you1\" <span style=\" font-style:italic;\">or</span> \"you2\" , etc.</p>\n"+
"<p style=\"-qt-paragraph-type:empty; margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\"></p></body></html>");
        label_2.setText("Translation");
        clearTransbut.setWhatsThis("<html><head><meta name=\"qrichtext\" content=\"1\" /><style type=\"text/css\">\n"+
"p, li { white-space: pre-wrap; }\n"+
"</style></head><body style=\" font-family:'Sans Serif'; font-size:9pt; font-weight:400; font-style:normal;\">\n"+
"<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\">Use this button to clear the translation line edit only.</p></body></html>");
        clearTransbut.setText("");
        englishEdit.setWhatsThis("<html><head><meta name=\"qrichtext\" content=\"1\" /><style type=\"text/css\">\n"+
"p, li { white-space: pre-wrap; }\n"+
"</style></head><body style=\" font-family:'Sans Serif'; font-size:9pt; font-weight:400; font-style:normal;\">\n"+
"<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\">Input one or several english keywords. I will look first for all your keywords, then for any of your keywords.</p>\n"+
"<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\"><span style=\" font-weight:600;\">Note English keywords are case-sensitive</span>. Make sure you are not using capital letters if you do not find any word.</p></body></html>");
        label_3.setText("Number of strokes");
        clearStrokesbut.setWhatsThis("<html><head><meta name=\"qrichtext\" content=\"1\" /><style type=\"text/css\">\n"+
"p, li { white-space: pre-wrap; }\n"+
"</style></head><body style=\" font-family:'Sans Serif'; font-size:9pt; font-weight:400; font-style:normal;\">\n"+
"<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\">Use this button to clear the number of strokes only.</p></body></html>");
        clearStrokesbut.setText("");
        strokeNbrEdit.setWhatsThis("<html><head><meta name=\"qrichtext\" content=\"1\" /><style type=\"text/css\">\n"+
"p, li { white-space: pre-wrap; }\n"+
"</style></head><body style=\" font-family:'Sans Serif'; font-size:9pt; font-weight:400; font-style:normal;\">\n"+
"<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\">Type the number of strokes of the character</p>\n"+
"<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\">If you specified a key do not include the strokes in the key in your count.</p>\n"+
"<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\"><span style=\" font-weight:600;\">You are not sure?</span> No problem. This is what you can type:</p>\n"+
"<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\"><span style=\" font-weight:600;\">15 </span>:  I will look for exaclty 15 strokes</p>\n"+
"<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\"><span style=\" font-weight:600;\">15+</span> : I will look for 15 strokes and above</p>\n"+
"<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\"><span style=\" font-weight:600;\">15-</span> :  I will look for 15 strokes and below</p>\n"+
"<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\"><span style=\" font-weight:600;\">13-15</span> or <span style=\" font-weight:600;\">13 15</span> :  I will look between 13 and 15 strokes</p>\n"+
"<p style=\"-qt-paragraph-type:empty; margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\"></p></body></html>");
        label_4.setText("Unicode");
        clearUNIbut.setWhatsThis("<html><head><meta name=\"qrichtext\" content=\"1\" /><style type=\"text/css\">\n"+
"p, li { white-space: pre-wrap; }\n"+
"</style></head><body style=\" font-family:'Sans Serif'; font-size:9pt; font-weight:400; font-style:normal;\">\n"+
"<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\">Use this button to clear the number of strokes only.</p></body></html>");
        clearUNIbut.setText("");
        unicodeEdit.setWhatsThis("<html><head><meta name=\"qrichtext\" content=\"1\" /><style type=\"text/css\">\n"+
"p, li { white-space: pre-wrap; }\n"+
"</style></head><body style=\" font-family:'Sans Serif'; font-size:9pt; font-weight:400; font-style:normal;\">\n"+
"<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\">Type the unicode character. The following formats are allowed:</p>\n"+
"<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\"><span style=\" font-weight:600;\">UNIXXXX</span></p>\n"+
"<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px; font-weight:600;\">uniXXXX</p>\n"+
"<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px; font-weight:600;\">XXXX</p>\n"+
"<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\">where XXXX is the hexadecimal unicode representation.</p></body></html>");
        label_5.setText("Chinese character");
        clearZHbut.setWhatsThis("<html><head><meta name=\"qrichtext\" content=\"1\" /><style type=\"text/css\">\n"+
"p, li { white-space: pre-wrap; }\n"+
"</style></head><body style=\" font-family:'Sans Serif'; font-size:9pt; font-weight:400; font-style:normal;\">\n"+
"<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\">Use this button to clear the number of strokes only.</p></body></html>");
        clearZHbut.setText("");
        characterEdit.setWhatsThis("<html><head><meta name=\"qrichtext\" content=\"1\" /><style type=\"text/css\">\n"+
"p, li { white-space: pre-wrap; }\n"+
"</style></head><body style=\" font-family:'Sans Serif'; font-size:9pt; font-weight:400; font-style:normal;\">\n"+
"<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\">Enter the Chinese character.</p></body></html>");
        clearSearchBtn.setText("Clear search and start new search");
        searchTab.setTabText(searchTab.indexOf(otherTab), "Page");
        tabWidget.setStyleSheet("");
        infosBrowser.setToolTip("<html><head><meta name=\"qrichtext\" content=\"1\" /><style type=\"text/css\">\n"+
"p, li { white-space: pre-wrap; }\n"+
"</style></head><body style=\" font-family:'Sans Serif'; font-size:9pt; font-weight:400; font-style:normal;\">\n"+
"<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\">You can customize this display. Look at the options.</p></body></html>");
        tabWidget.setTabText(tabWidget.indexOf(defTab), "Tab 1");
        pushButton_2.setText("+");
        pushButton.setText("-");
        pushButton_3.setText("PushButton");
        pushButton_5.setText("PushButton");
        tabWidget.setTabText(tabWidget.indexOf(displayTab), "Page");
        tabWidget.setTabText(tabWidget.indexOf(tab_2), "Tab 2");
        clearButton.setText("");
        menuFile.setTitle("File");
        menuHelp.setTitle("Help");
        menuConfigure.setTitle("Configure");
    } // retranslateUi

}

