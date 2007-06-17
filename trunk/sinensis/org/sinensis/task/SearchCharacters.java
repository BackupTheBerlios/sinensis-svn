//SearchCharacters.java
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
import com.trolltech.qt.gui.*;
import java.util.*;

public class SearchCharacters extends Task
{
	private final static int SEARCH_HARD_LIMIT=100;
	CCharacterRequest query;
	Collection<CCharacter> dic;
	
	public SearchCharacters(CCharacterRequest req, Collection<CCharacter> d)
	{
		query=req;
		dic=d;
	}
	
	public void run()
	{
		List<CCharacter> list= new LinkedList<CCharacter>();
		query.prepare();
		
		for(CCharacter c:dic)
			if(query.accepts(c)>=1)
				list.add(c);
		
		dataStore.currentSearch=list;
		
		int counter=0;
		String res="";
		for(CCharacter c : list)
		{
			counter++;
			res+=(char)c.getInt("UNI")+" ";
			if(counter>=SEARCH_HARD_LIMIT)
			{
				dataStore.charSearchReady.emit(res);
				return;
			}
			
		}
		
		dataStore.charSearchReady.emit(res);
		QApplication.invokeLater(new CleanUI());
	}
	
	public class CleanUI implements Runnable
	{
		public void run()
		{
// 			mainUI.main.selectionView.resizeColumnsToContents();
		}
	}
}

