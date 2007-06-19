//LoadRepresentation.java
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
package org.sinensis.task;

import org.sinensis.data.*;
import com.trolltech.qt.core.*;


public class LoadRepresentation extends Task
{
	public static String fileName="classpath:/db/1.db";
	private final static int countLimit=50000;
	
	public LoadRepresentation()
	{
		
	}
	
	public void run()
	{
		setTaskName("Reading representation...");
		int count=0;
		QFile file=new QFile(fileName);
		if (!file.open(QIODevice.OpenModeFlag.ReadOnly))
			return;
		QDataStream in=new QDataStream(file);
		while (!in.atEnd()&&count<countLimit) 
		{
			GlyphNode glyph=new GlyphNode(in);
			if(dataStore.getChar(glyph.hashCode())!=null)
			{
				dataStore.getChar(glyph.hashCode()).representation=glyph;
				count++;
			}
			else
				System.out.println("Could not find character id="+glyph.name+" in the loaded characters");
		}
		setTaskName(count+" characters loaded");
	}
}