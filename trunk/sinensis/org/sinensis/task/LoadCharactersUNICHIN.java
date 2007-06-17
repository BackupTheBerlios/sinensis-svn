//LoadCharacters.java
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

public class LoadCharactersUNICHIN extends Task
{
	final static String filename="data/UNICHIN.TXT";
	
// 	For debugging purpose mainly
	final static int maxCount=10000000;
	
// 	The number of characters we are expecting to load
	final static int totalCount=16000;

	public void run()
	{
		setTaskName("Loading characters...");
	
	        QFile file=new QFile(filename);
		if (!file.open(QIODevice.OpenModeFlag.ReadOnly))
		{
			setTaskName("Loading characters... error in loading file");
			return;
		}
		QTextStream in=new QTextStream(file);
		int count=0;
		while(!in.atEnd()&&count<maxCount)
		{
			count++;
			String line=in.readLine();
			String [] t=line.split("\t");
			final String uni=t[0];
			final String[] py0=t[1].split("\\.");
			final String key=py0[0];
			final String remStrokes=py0[1];
			final String strokes=t[2];
			final String py=t[3];
			final String def=t[4];
			final String cant=t[5];
//			final String alt=t[6];
			final String freq=t[7];
			final String big5=t[8];
			final String gb=t[9];
			final String comps=t[10];
			
			CCharacter cchar=new CCharacter(uni);
			try{
			if(key.indexOf("-")<0)
				cchar.put("KEY",key);
			if(remStrokes.indexOf("-")<0)
				cchar.put("STROKESREM",Integer.parseInt(remStrokes));
			if(strokes.indexOf("-")<0)
				cchar.put("STROKES",Integer.parseInt(strokes));
			if(py.indexOf("-")<0)
				cchar.put("PY",py.toLowerCase());
			if(cant.indexOf("-")<0)
				cchar.put("CANT",cant);
			if(def.indexOf("-")<0)
				cchar.put("EN",def);
			if(freq.indexOf("-")<0)
				cchar.put("FREQ",Integer.parseInt(freq));
			if(big5.indexOf("-")<0)
				cchar.put("BIG5",big5);
			if(gb.indexOf("-")<0)
				cchar.put("GB",gb);
			if(comps.indexOf("-")<0)
				cchar.put("COMPS",comps);
			dataStore.charMap.put(cchar.getInt("UNI"),cchar);
			}catch(Exception e){}
			
			setCounter(100*count/totalCount);
		}
		setTaskName(dataStore.charMap.size()+" characters loaded");
	}
}