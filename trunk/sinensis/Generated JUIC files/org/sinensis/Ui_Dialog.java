/********************************************************************************
** Form generated from reading ui file 'configDialog.jui'
**
** Created: lun. juin 18 01:05:44 2007
**      by: Qt User Interface Compiler version 4.3.0
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

package org.sinensis;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_Dialog
{
    public QVBoxLayout vboxLayout;
    public QHBoxLayout hboxLayout;
    public QListWidget listWidget;
    public QStackedWidget stackedWidget;
    public QWidget page;
    public QWidget page_2;
    public QDialogButtonBox buttonBox;
    public QHBoxLayout hboxLayout1;
    public QSpacerItem spacerItem;
    public QPushButton pushButton;
    public QPushButton pushButton_2;

    public Ui_Dialog() { super(); }

    public void setupUi(QDialog Dialog)
    {
        Dialog.setObjectName("Dialog");
        Dialog.resize(new QSize(627, 435).expandedTo(Dialog.minimumSizeHint()));
        vboxLayout = new QVBoxLayout(Dialog);
        vboxLayout.setObjectName("vboxLayout");
        hboxLayout = new QHBoxLayout();
        hboxLayout.setObjectName("hboxLayout");
        hboxLayout.setContentsMargins(0, 0, 0, 0);
        listWidget = new QListWidget(Dialog);
        listWidget.setObjectName("listWidget");

        hboxLayout.addWidget(listWidget);

        stackedWidget = new QStackedWidget(Dialog);
        stackedWidget.setObjectName("stackedWidget");
        stackedWidget.setMinimumSize(new QSize(100, 100));
        page = new QWidget();
        page.setObjectName("page");
        stackedWidget.addWidget(page);
        page_2 = new QWidget();
        page_2.setObjectName("page_2");
        stackedWidget.addWidget(page_2);

        hboxLayout.addWidget(stackedWidget);


        vboxLayout.addLayout(hboxLayout);

        buttonBox = new QDialogButtonBox(Dialog);
        buttonBox.setObjectName("buttonBox");
        buttonBox.setOrientation(com.trolltech.qt.core.Qt.Orientation.Horizontal);
        buttonBox.setStandardButtons(com.trolltech.qt.gui.QDialogButtonBox.StandardButton.createQFlags(com.trolltech.qt.gui.QDialogButtonBox.StandardButton.NoButton));

        vboxLayout.addWidget(buttonBox);

        hboxLayout1 = new QHBoxLayout();
        hboxLayout1.setObjectName("hboxLayout1");
        hboxLayout1.setContentsMargins(0, 0, 0, 0);
        spacerItem = new QSpacerItem(40, 20, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum);

        hboxLayout1.addItem(spacerItem);

        pushButton = new QPushButton(Dialog);
        pushButton.setObjectName("pushButton");

        hboxLayout1.addWidget(pushButton);

        pushButton_2 = new QPushButton(Dialog);
        pushButton_2.setObjectName("pushButton_2");

        hboxLayout1.addWidget(pushButton_2);


        vboxLayout.addLayout(hboxLayout1);

        retranslateUi(Dialog);

        Dialog.connectSlotsByName();
    } // setupUi

    void retranslateUi(QDialog Dialog)
    {
        Dialog.setWindowTitle("Dialog");
        pushButton.setText("Contextual help");
        pushButton_2.setText("Close this dialog");
    } // retranslateUi

}

