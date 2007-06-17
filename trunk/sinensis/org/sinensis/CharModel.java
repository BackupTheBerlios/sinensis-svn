package org.sinensis;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

// This model displays the characters after a query
class CharModel extends QStandardItemModel
{
//	private DataStore data;

//	public CharModel(QObject parent,DataStore store)
	Sinensis sin;
	public CharModel(QObject parent,Sinensis mainApp)
	{
		super(0,1,parent);
		sin=mainApp;
	}
	public CharModel(QObject parent)
	{
		super(0,1,parent);
//		data=store;
	}
	
	public boolean isEditable()
	{
		return false;
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
	}
}
