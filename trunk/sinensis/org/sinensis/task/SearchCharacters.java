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

