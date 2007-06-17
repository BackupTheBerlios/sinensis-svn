/********************************************************************************
** Form generated from reading ui file 'config.jui'
**
** Created: ven. juin 15 02:21:59 2007
**      by: Qt User Interface Compiler version 4.3.0
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

package org.sinensis;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_Dialog
{
    public QGridLayout gridLayout;
    public QVBoxLayout vboxLayout;
    public QToolBox toolBox;
    public QWidget page;
    public QGridLayout gridLayout1;
    public QGridLayout gridLayout2;
    public QLabel label;
    public QFontComboBox fontComboBox;
    public QSpinBox spinBox;
    public QLabel label_2;
    public QFontComboBox fontComboBox_2;
    public QSpinBox spinBox_2;
    public QLabel label_3;
    public QFontComboBox fontComboBox_3;
    public QSpinBox spinBox_3;
    public QWidget page_3;
    public QGridLayout gridLayout3;
    public QVBoxLayout vboxLayout1;
    public QRadioButton radioButton;
    public QRadioButton radioButton_2;
    public QRadioButton radioButton_3;
    public QRadioButton radioButton_4;
    public QWidget page_2;
    public QWidget widget;
    public QGridLayout gridLayout4;
    public QCheckBox checkBox;
    public QCheckBox checkBox_2;
    public QHBoxLayout hboxLayout;
    public QSpacerItem spacerItem;
    public QDialogButtonBox buttonBox_2;

    public Ui_Dialog() { super(); }

    public void setupUi(QDialog ConfigDialog)
    {
        ConfigDialog.setObjectName("ConfigDialog");
        ConfigDialog.resize(new QSize(608, 398).expandedTo(ConfigDialog.minimumSizeHint()));
        gridLayout = new QGridLayout(ConfigDialog);
        gridLayout.setObjectName("gridLayout");
        vboxLayout = new QVBoxLayout();
        vboxLayout.setObjectName("vboxLayout");
        vboxLayout.setContentsMargins(0, 0, 0, 0);
        toolBox = new QToolBox(ConfigDialog);
        toolBox.setObjectName("toolBox");
        page = new QWidget();
        page.setObjectName("page");
        page.setGeometry(new QRect(0, 0, 572, 244));
        gridLayout1 = new QGridLayout(page);
        gridLayout1.setObjectName("gridLayout1");
        gridLayout2 = new QGridLayout();
        gridLayout2.setObjectName("gridLayout2");
        gridLayout2.setContentsMargins(0, 0, 0, 0);
        label = new QLabel(page);
        label.setObjectName("label");

        gridLayout2.addWidget(label, 0, 0, 1, 1);

        fontComboBox = new QFontComboBox(page);
        fontComboBox.setObjectName("fontComboBox");
        fontComboBox.setFocusPolicy(com.trolltech.qt.core.Qt.FocusPolicy.WheelFocus);

        gridLayout2.addWidget(fontComboBox, 0, 1, 1, 1);

        spinBox = new QSpinBox(page);
        spinBox.setObjectName("spinBox");
        spinBox.setFocusPolicy(com.trolltech.qt.core.Qt.FocusPolicy.WheelFocus);

        gridLayout2.addWidget(spinBox, 0, 2, 1, 1);

        label_2 = new QLabel(page);
        label_2.setObjectName("label_2");

        gridLayout2.addWidget(label_2, 1, 0, 1, 1);

        fontComboBox_2 = new QFontComboBox(page);
        fontComboBox_2.setObjectName("fontComboBox_2");
        fontComboBox_2.setFocusPolicy(com.trolltech.qt.core.Qt.FocusPolicy.WheelFocus);

        gridLayout2.addWidget(fontComboBox_2, 1, 1, 1, 1);

        spinBox_2 = new QSpinBox(page);
        spinBox_2.setObjectName("spinBox_2");
        spinBox_2.setFocusPolicy(com.trolltech.qt.core.Qt.FocusPolicy.WheelFocus);

        gridLayout2.addWidget(spinBox_2, 1, 2, 1, 1);

        label_3 = new QLabel(page);
        label_3.setObjectName("label_3");

        gridLayout2.addWidget(label_3, 2, 0, 1, 1);

        fontComboBox_3 = new QFontComboBox(page);
        fontComboBox_3.setObjectName("fontComboBox_3");
        fontComboBox_3.setFocusPolicy(com.trolltech.qt.core.Qt.FocusPolicy.WheelFocus);

        gridLayout2.addWidget(fontComboBox_3, 2, 1, 1, 1);

        spinBox_3 = new QSpinBox(page);
        spinBox_3.setObjectName("spinBox_3");
        spinBox_3.setFocusPolicy(com.trolltech.qt.core.Qt.FocusPolicy.WheelFocus);

        gridLayout2.addWidget(spinBox_3, 2, 2, 1, 1);


        gridLayout1.addLayout(gridLayout2, 0, 0, 1, 1);

        toolBox.addItem(page, "Page 1");
        page_3 = new QWidget();
        page_3.setObjectName("page_3");
        gridLayout3 = new QGridLayout(page_3);
        gridLayout3.setObjectName("gridLayout3");
        vboxLayout1 = new QVBoxLayout();
        vboxLayout1.setObjectName("vboxLayout1");
        vboxLayout1.setContentsMargins(0, 0, 0, 0);
        radioButton = new QRadioButton(page_3);
        radioButton.setObjectName("radioButton");

        vboxLayout1.addWidget(radioButton);

        radioButton_2 = new QRadioButton(page_3);
        radioButton_2.setObjectName("radioButton_2");

        vboxLayout1.addWidget(radioButton_2);

        radioButton_3 = new QRadioButton(page_3);
        radioButton_3.setObjectName("radioButton_3");

        vboxLayout1.addWidget(radioButton_3);

        radioButton_4 = new QRadioButton(page_3);
        radioButton_4.setObjectName("radioButton_4");

        vboxLayout1.addWidget(radioButton_4);


        gridLayout3.addLayout(vboxLayout1, 0, 0, 1, 1);

        toolBox.addItem(page_3, "Page");
        page_2 = new QWidget();
        page_2.setObjectName("page_2");
        page_2.setGeometry(new QRect(0, 0, 423, 196));
        widget = new QWidget(page_2);
        widget.setObjectName("widget");
        widget.setGeometry(new QRect(130, 30, 69, 42));
        gridLayout4 = new QGridLayout(widget);
        gridLayout4.setObjectName("gridLayout4");
        gridLayout4.setContentsMargins(0, 0, 0, 0);
        checkBox = new QCheckBox(widget);
        checkBox.setObjectName("checkBox");

        gridLayout4.addWidget(checkBox, 0, 0, 1, 1);

        checkBox_2 = new QCheckBox(widget);
        checkBox_2.setObjectName("checkBox_2");

        gridLayout4.addWidget(checkBox_2, 1, 0, 1, 1);

        toolBox.addItem(page_2, "Page 2");

        vboxLayout.addWidget(toolBox);

        hboxLayout = new QHBoxLayout();
        hboxLayout.setObjectName("hboxLayout");
        hboxLayout.setContentsMargins(0, 0, 0, 0);
        spacerItem = new QSpacerItem(40, 20, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum);

        hboxLayout.addItem(spacerItem);

        buttonBox_2 = new QDialogButtonBox(ConfigDialog);
        buttonBox_2.setObjectName("buttonBox_2");
        buttonBox_2.setStandardButtons(com.trolltech.qt.gui.QDialogButtonBox.StandardButton.createQFlags(com.trolltech.qt.gui.QDialogButtonBox.StandardButton.Cancel,com.trolltech.qt.gui.QDialogButtonBox.StandardButton.Ok));

        hboxLayout.addWidget(buttonBox_2);


        vboxLayout.addLayout(hboxLayout);


        gridLayout.addLayout(vboxLayout, 0, 0, 1, 1);

        retranslateUi(ConfigDialog);

        toolBox.setCurrentIndex(0);


        ConfigDialog.connectSlotsByName();
    } // setupUi

    void retranslateUi(QDialog ConfigDialog)
    {
        ConfigDialog.setWindowTitle("Dialog");
        label.setText("TextLabel");
        label_2.setText("TextLabel");
        label_3.setText("TextLabel");
        toolBox.setItemText(toolBox.indexOf(page), "Page 1");
        radioButton.setText("RadioButton");
        radioButton_2.setText("RadioButton");
        radioButton_3.setText("RadioButton");
        radioButton_4.setText("RadioButton");
        toolBox.setItemText(toolBox.indexOf(page_3), "Page");
        checkBox.setText("CheckBox");
        checkBox_2.setText("CheckBox");
        toolBox.setItemText(toolBox.indexOf(page_2), "Page 2");
    } // retranslateUi

}

