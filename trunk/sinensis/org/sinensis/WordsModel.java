//WordsModel.java
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

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

class WordsModel extends QStandardItemModel
{
	private DataStore data;
	private Sinensis mainSin;
	public WordsModel(QObject parent, DataStore ds, Sinensis main)
	{
		super(0,4,parent);
		data=ds;
		mainSin=main;
		setHeaderData(0, Qt.Orientation.Horizontal, tr("Simplified"));
		setHeaderData(1, Qt.Orientation.Horizontal, tr("Traditional"));
		setHeaderData(2, Qt.Orientation.Horizontal, tr("Pinyin"));
		setHeaderData(3, Qt.Orientation.Horizontal, tr("Translation"));
	}
	
	public Qt.ItemFlag flags()
	{
		return Qt.ItemFlag.ItemIsSelectable;
	}
	
	public void setContent(String s)
	{
// System.out.println("###"+s);
		String [] res=s.split("###");
		removeRows(0,rowCount(null),null);
// 		setRowCount(res.length);
		for(int i=0;i<res.length;i++)
		{
			
			
			Word w=data.getWord(res[i]);
			if(w!=null)
			{
			insertRow(0);
			setData(index(0,0),w.simpl);
			setData(index(0,1),w.tradi);
			setData(index(0,2),w.pinyin);
			setData(index(0,3),w.trans);
			}
		}
		if(res.length>0)
			mainSin.displayWord(index(0,0));
	}
	
	
}
