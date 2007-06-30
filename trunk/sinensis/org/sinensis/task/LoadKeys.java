//LoadKeys.java
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
import com.trolltech.qt.gui.*;
import java.util.*;


public class LoadKeys extends Task
{
	final static String filename="classpath:/db/keys.txt";
	final static int keyCount=214;
	List<CCharacter> chars;
	
	public LoadKeys()
	{
		chars=new Vector<CCharacter>();
	}
	
	public void run()
	{
//System.out.println("XXXX");
		setTaskName("reading keys...");
	        QFile file=new QFile(filename);
		if (!file.open(QIODevice.OpenModeFlag.ReadOnly))
			return;
//		This setting is quite memory agressive, but this is what we want in the end
		QTextStream in=new QTextStream(file.readAll());
//		Not forget to specify the file locale
		in.setCodec("UTF-8");
		int count=1;
		while(!in.atEnd())
		{
			String line=in.readLine();
			String [] t=line.split("\t");
			String id=t[0];
			String zh=t[1];
			String fr=t[3];
			String strokes=""+(count/6+1);
			CCharacter c=new CCharacter();
			
			c.put("KEY_ID",count);
			c.put("ZH",zh);
			c.put("FR",fr);
			c.put("STROKES",Integer.parseInt(strokes));
			chars.add(c);
			if(id.indexOf(".")<0)
				count++;
// System.out.println(c);
			setCounter(100*count/keyCount);
		}
		QApplication.invokeLater(new AddRadical());
	}
	
	public class AddRadical implements Runnable
	{
// 		private CCharacter cchar;
// 		public AddRadical(CCharacter c)
// 		{
// 			cchar=c;
// 		}
		
		public void run()
		{
			mainUI.setupRadicalModel(chars);
// 			mainUI.radicalModel.setRadicals(chars);
// 			mainUI.setStyleSheet("{color: yellow;font-size: 16px;font-family: \"Arial Unicode MS\"}");
		}
	}
}
