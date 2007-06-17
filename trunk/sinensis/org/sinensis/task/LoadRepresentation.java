package org.sinensis.task;

import org.sinensis.data.*;
import com.trolltech.qt.core.*;


public class LoadRepresentation extends Task
{
	public static String fileName="db/1.db";
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