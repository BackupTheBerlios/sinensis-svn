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
