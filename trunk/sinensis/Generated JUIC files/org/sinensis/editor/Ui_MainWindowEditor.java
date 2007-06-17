/********************************************************************************
** Form generated from reading ui file 'TTXSinEditor.jui'
**
** Created: ven. juin 15 02:31:22 2007
**      by: Qt User Interface Compiler version 4.3.0
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

package org.sinensis.editor;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_MainWindowEditor
{
    public QWidget centralwidget;
    public QVBoxLayout vboxLayout;
    public QSplitter splitter;
    public QListWidget listWidget;
    public QGraphicsView graphicsView;
    public QHBoxLayout hboxLayout;
    public QLineEdit nameEdit;
    public QSpinBox spinBox;
    public QPushButton scanDBButton;
    public QPushButton openDBButton;
    public QPushButton ttxButton;
    public QPushButton saveDBButton;
    public QMenuBar menubar;
    public QStatusBar statusbar;

    public Ui_MainWindowEditor() { super(); }

    public void setupUi(QMainWindow MainWindow)
    {
        MainWindow.setObjectName("MainWindow");
        MainWindow.resize(new QSize(800, 600).expandedTo(MainWindow.minimumSizeHint()));
        centralwidget = new QWidget(MainWindow);
        centralwidget.setObjectName("centralwidget");
        vboxLayout = new QVBoxLayout(centralwidget);
        vboxLayout.setObjectName("vboxLayout");
        splitter = new QSplitter(centralwidget);
        splitter.setObjectName("splitter");
        splitter.setOrientation(com.trolltech.qt.core.Qt.Orientation.Horizontal);
        listWidget = new QListWidget(splitter);
        listWidget.setObjectName("listWidget");
        QSizePolicy sizePolicy = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Preferred, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);
        sizePolicy.setHorizontalStretch((byte)0);
        sizePolicy.setVerticalStretch((byte)0);
        sizePolicy.setHeightForWidth(listWidget.sizePolicy().hasHeightForWidth());
        listWidget.setSizePolicy(sizePolicy);
        listWidget.setMaximumSize(new QSize(150, 16777215));
        splitter.addWidget(listWidget);
        graphicsView = new QGraphicsView(splitter);
        graphicsView.setObjectName("graphicsView");
        splitter.addWidget(graphicsView);

        vboxLayout.addWidget(splitter);

        hboxLayout = new QHBoxLayout();
        hboxLayout.setObjectName("hboxLayout");
        nameEdit = new QLineEdit(centralwidget);
        nameEdit.setObjectName("nameEdit");
        nameEdit.setFocusPolicy(com.trolltech.qt.core.Qt.FocusPolicy.StrongFocus);

        hboxLayout.addWidget(nameEdit);

        spinBox = new QSpinBox(centralwidget);
        spinBox.setObjectName("spinBox");
        spinBox.setFocusPolicy(com.trolltech.qt.core.Qt.FocusPolicy.WheelFocus);
        spinBox.setMinimum(1);
        spinBox.setMaximum(1000);
        spinBox.setSingleStep(10);
        spinBox.setValue(10);

        hboxLayout.addWidget(spinBox);

        scanDBButton = new QPushButton(centralwidget);
        scanDBButton.setObjectName("scanDBButton");

        hboxLayout.addWidget(scanDBButton);

        openDBButton = new QPushButton(centralwidget);
        openDBButton.setObjectName("openDBButton");

        hboxLayout.addWidget(openDBButton);

        ttxButton = new QPushButton(centralwidget);
        ttxButton.setObjectName("ttxButton");

        hboxLayout.addWidget(ttxButton);

        saveDBButton = new QPushButton(centralwidget);
        saveDBButton.setObjectName("saveDBButton");

        hboxLayout.addWidget(saveDBButton);


        vboxLayout.addLayout(hboxLayout);

        MainWindow.setCentralWidget(centralwidget);
        menubar = new QMenuBar(MainWindow);
        menubar.setObjectName("menubar");
        menubar.setGeometry(new QRect(0, 0, 800, 27));
        MainWindow.setMenuBar(menubar);
        statusbar = new QStatusBar(MainWindow);
        statusbar.setObjectName("statusbar");
        MainWindow.setStatusBar(statusbar);
        retranslateUi(MainWindow);

        MainWindow.connectSlotsByName();
    } // setupUi

    void retranslateUi(QMainWindow MainWindow)
    {
        MainWindow.setWindowTitle("MainWindow");
        scanDBButton.setText("scan next");
        openDBButton.setText("Open database...");
        ttxButton.setText("open TTX file...");
        saveDBButton.setText("Dump database");
    } // retranslateUi

}

