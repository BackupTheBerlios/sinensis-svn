//Task.java
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
import org.sinensis.Sinensis;

public abstract class Task implements Runnable
{
	public static DataStore dataStore;
	public static Sinensis mainUI;
	
	abstract public void run();
	
	protected void setTaskName(String s)
	{
//		dataStore.status.emit(s);
		mycounter=0;
	}
	
	protected void setCounter(int u)
	{
		if(u<=mycounter+10)
			return;
		mycounter=u;
// 		dataStore.progress.emit(u);
	}
	
	private int mycounter=0;

	public class SetName implements Runnable
	{
		String s;
		public SetName(String s1)
		{
			s1=s;
		}
		
		public void run()
		{
	//	mainUI.statusBar().showMessage(s);
		}
	}
		
	public class SetCounter implements Runnable
	{
		int u;
		public SetCounter(int i)
		{
			u=i;
		}
		
		public void run()
		{
// 		mainUI.main.progressBar.setValue(u);
		}
	}
}
