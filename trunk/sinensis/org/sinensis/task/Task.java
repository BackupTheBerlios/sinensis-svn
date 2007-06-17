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
		dataStore.status.emit(s);
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
		{mainUI.statusBar().showMessage(s);}
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
