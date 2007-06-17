//SearchWord.java
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


public class SearchWord extends Task
{
	private final static int SEARCH_HARD_LIMIT=100;
	WordQuery query;
	List<Word> dic;
	public SearchWord(WordQuery wq, List<Word> d)
	{
		query=wq;
		dic=d;
	}

	public void run()
	{
		List<Word> list= new LinkedList<Word>();
		String taskName="looking for "+query+"  in "+dic.size()+" items";
		setTaskName(taskName);
		
		for(Word w:dic)
			if(query.accept(w))
			{
				list.add(w);
			}
		
		dataStore.currentWordSearch=list;
		
		int counter=0;
		String res="";
		for(Word w : list)
		{
			counter++;
			res+=w.simpl+"###";
			if(counter>=SEARCH_HARD_LIMIT)
			{
				dataStore.wordSearchReady.emit(res);
				setTaskName(taskName+"==> too many matches! Change your request.");
				return;
			}
			
		}
		
		dataStore.wordSearchReady.emit(res);
		setTaskName(taskName+"==>"+ list.size()+ "matche(s)");
		QApplication.invokeLater(new CleanUI());
	}
	
	public class CleanUI implements Runnable
	{
		public void run()
		{
			mainUI.main.wordsListView.resizeColumnsToContents();
		}
	}
}
