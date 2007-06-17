//CharModel.java
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

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

// This model displays the characters after a query
class CharModel extends QStandardItemModel
{
	Sinensis sin;
	public CharModel(QObject parent,Sinensis mainApp)
	{
		super(0,1,parent);
		sin=mainApp;
	}
	public CharModel(QObject parent)
	{
		super(0,1,parent);
	}

	public void setContent(String s)
	{
		removeRows(0,rowCount(null),null);
		String[] list=s.split(" ");
		for(int i=0;i<list.length;i++)
		{
			insertRow(0);
			setData(index(0,0),list[i]);
		}
		if(sin!=null && list.length==1)
			sin.displayChar(index(0,0));
//		Probably a QtJambi bug: I must specify the font again
		if(Sinensis.charViewFont!=null)
			sin.main.selectionView.setFont(Sinensis.charViewFont);
	}
}
