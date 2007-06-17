//DataStore.java
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

package org.sinensis.data;

import org.sinensis.task.*;
import org.sinensis.*;

import com.trolltech.qt.core.*;
import java.util.concurrent.*;
import java.util.*;

public class DataStore extends QObject implements Runnable
{
	public Map<Integer,CCharacter> charMap=new HashMap<Integer,CCharacter>();
	public List<Word> words;
	
	public List<CCharacter> currentSearch;
	public List<Word> currentWordSearch;
	private WordQuery lastWordSearch=null;
	
	private Queue<Task> tasks; 
//	private CCharacterRequest currentCharQuery=null;
	
	Sinensis mainUI;
	
	public QObject.Signal1<String> status = new QObject.Signal1<String>();
	public QObject.Signal1<Integer> progress = new QObject.Signal1<Integer>();
	
	public QObject.Signal1<String> wordSearchReady = new QObject.Signal1<String>();
	public QObject.Signal1<String> charSearchReady = new QObject.Signal1<String>();
	
	public DataStore(Sinensis main)
	{
// 		super(main);
		mainUI=main;
		currentSearch=new LinkedList<CCharacter>();
		currentWordSearch=new LinkedList<Word>();
		words=new LinkedList<Word>();
		Task.dataStore=this;
		tasks=new LinkedBlockingQueue<Task>();
	}

	public void lookFor(CCharacterRequest req)
	{
// 	TODO : speed optimization, if it is worth it
// 		if(currentCharQuery!=null)
// 			addTask(new SearchCharacters(req,currentSearch));
// 		else 
		addTask(new SearchCharacters(req,charMap.values()));
	}
	
	public void lookForWord(String s)
	{
System.out.println("..."+s);
		WordQuery query=new WordQuery(s);
		System.out.println(query);
		if(query.derivesFrom(lastWordSearch))
			addTask(new SearchWord(query,currentWordSearch));
		else addTask(new SearchWord(query,words));
	}
	
/*	private boolean isSubsetOf(String search1)
	{
		if(currentWordSearch.isEmpty())
			return false;
		if(lastWordSearch.indexOf(search1)>=0)
			return true;
		return false;
	}*/
	
	public void getRadicals()
	{
	}
	
/*	public CCharacter getCharacter(String unicode)
	{
		return charMap.get(unicode);
	}*/
	
	public CCharacter getChar(int unicode)
	{
		return charMap.get(unicode);
	}

	public CCharacter getChar(char c)
	{
		return charMap.get((int)c);
	}
	
	public Word getWord(String simp)
	{
// System.out.println(simp);
		for(Word w:words)
			if(w.simpl.equals(simp))
				return w;
		return null;
// 		throw new Error(simp);
	}
	
	public void loadData()
	{
		addTask(new LoadKeys());
		addTask(new LoadWords());
		addTask(new LoadCharactersUNICHIN());
// 		addTask(new LoadCharactersUNICHIN());
	}
	
	public void addTask(Task t)
	{
		tasks.offer(t);
	}
	
	public void run()
	{
		while(true&&Sinensis.alive)
		{
			Task t=tasks.poll();
			if(t!=null)
				t.run();
			
		try
		{
			Thread.sleep(100);
		}catch(Exception e){}
		}
		dispose();
	}
}


