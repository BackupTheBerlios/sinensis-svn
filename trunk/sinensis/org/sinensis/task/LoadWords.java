package org.sinensis.task;

import org.sinensis.data.*;
import com.trolltech.qt.core.*;
import java.util.*;


public class LoadWords extends Task
{
	final static String filename="data/cedict.txt";
	final static int wordsCount=40000;
// 	Vector<Word> chars;
	
	public LoadWords()
	{
		dataStore.words=new Vector<Word>();
	}
	
	public void run()
	{
		setTaskName("Reading words...");
// 		QApplication.invokeLater(new InitWords());
	        QFile file=new QFile(filename);
		if (!file.open(QIODevice.OpenModeFlag.ReadOnly))
			return;
		QTextStream in=new QTextStream(file);
// 		int count=0;
		
// 		We skip the first line
		in.readLine();
		int count=0;
		while(!in.atEnd()&&count<100000)
		{
			count++;
			String line=in.readLine();
// System.out.println(line);
			String [] t=line.split("\\[");
			String [] zhs=t[0].split(" ");
			String zhTrad=zhs[0];
// System.out.println(zhSim);
			String zhSim=zhs[1];
// System.out.println(zhTrad);
			String [] t2=t[1].split("\\]");
			String py=t2[0];
// System.out.println(py);
			String def=t2[1];
// System.out.println(zhSim+"|"+zhTrad+"|"+py+"|"+def);

			dataStore.words.add(new Word(zhSim,zhTrad,py,def));
			
			setCounter(100*count/wordsCount);
// 			if(count%400==0)
// 			{
// 				QApplication.invokeLater(new AddWords((Vector<Word>)chars.clone()));
// 				chars=new Vector<Word>();
// 			}
// System.out.print(zhTrad);
		}
// System.out.println("f");
// 		QApplication.invokeLater(new AddWords(chars));
	}
	
// 	public class AddWords implements Runnable
// 	{
// 		private List<Word> list;
	/*	
		public AddWords(List<Word> l)
		{
			list=l;
		}
		
		public void run()
		{
			mainUI.addWords(list);
		}
	}*/

/*	public class InitWords implements Runnable
	{
		public void run()
		{
			mainUI.setupWordsModel();
		
		}
	}*/
}
