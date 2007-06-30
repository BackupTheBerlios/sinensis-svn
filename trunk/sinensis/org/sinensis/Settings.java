//Settings.java
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

package org.sinensis;

import org.sinensis.data.*;

import com.trolltech.qt.*;
import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

// This class holds all the things the user can change 
// Since we want it to be dynamic, this class handles all the dirty stuff (formatting, etc)
public class Settings
{
	
	public QFont viewFont;
	public String dicLang="EN";
// 	public QSize mainWSize=null;
// 	The html skeleton of the word view. We provide a basic skeleton, but you are expected to change it!
// 	What do we want to display? 
// 		+The Chinese glyphs of the word. It can exist in bot simplied and traditional for: <hzx:tradi/> and <hzx:simpl/>
// 		+The list of pinyins <hzx:pinyin/>
// 		+The translation <hzx:trans/>
// 	This is all for the moment
	private String wordSkel="<html><h1><hzx:simpl/> (<i><hzx:tradi/></i>)</h1><hzx:pinyin/><hzx:trans/></html>";
// 	The html skeleton of the characters view
// 	For the character, there is much more flexibility about what we can print due to the key system
// 	We use a regular XML parser for this one, so you can put whatever you want 
	private String charSkel="<html>Character:<h1><hzx type=\"string\" name=\"UNI\"/></h1></html>";
//	private Set<QDomNode> changingNodes=new HashSet<QDomNode>();
	public String charSkelHtmlPath="data/template-char.html";

	public Settings()
	{
		loadHtmlSkel(charSkelHtmlPath);
// 		setFormatChar(charSkel);
		setFormatWord(wordSkel);
	}
	
	public void triggerChanges()
	{
		paramChanged.emit();
	}
	
	public void loadSettings()
	{
		paramChanged.emit();
	}
	
	public QSignalEmitter.Signal0 paramChanged;
// 	=new QSignalEmitter.Signal0();
	
	public void saveSettings()
	{
		
	}
	
	public String formatWord(Word w)
	{
		String skel="<html><h1><hzx:simpl/> (<i><hzx:tradi/></i>)</h1><hzx:pinyin/><hzx:trans/></html>";
		String[] pinyin=w.pinyin.split(" ");
		String py="<div id=\"py\">";
		for(int i=0;i<pinyin.length;i++)
		{
//			System.out.println(pinyin[i]);
			py+=" "+pinyin[i];
// 			py+="<div id=\"py_"+(i+1)+"\">"+pinyin[i]+"</div>";
		}
		py+="</div>";
		
		String[] defs=w.trans.split("/");
		String def="<div id=\"trans\">";
		for(int i=0;i<defs.length;i++)
		{
//			System.out.println(defs[i]);
			def+="<div id=\"trans_"+(i+1)+"\">"+defs[i]+"</div>";
		}
		def+="</div>";
		
		skel=skel.replaceAll("<hzx:simpl/>",w.simpl);
		skel=skel.replaceAll("<hzx:tradi/>",w.tradi);
		skel=skel.replaceAll("<hzx:pinyin/>",py);
		skel=skel.replaceAll("<hzx:trans/>",def);
		return skel;
	}

	public void setFormatWord(String html)
	{
	}
	
	
	public String formatChar(CCharacter w)
	{
		String html=charSkel;
		for(String key:w.keys())
		{
			String res=w.get(key);
			String type="string";
			String s1="<hzx type=\""+type+"\" name=\""+key+"\"/>";
//			System.out.println(s1+"===>"+res);
			html=html.replaceAll(s1,res);
		}
		
		for(String key:w.keysInt())
		{
			String res=w.getInt(key)+"";
			String type="int";
			String s1="<hzx type=\""+type+"\" name=\""+key+"\"/>";
//			System.out.println(s1+"===>"+res);
			html=html.replaceAll(s1,res);
		}
		
/*		for(QDomNode node : changingNodes)
		{
			QDomElement el=node.toElement();
			String type=el.attribute("type","string");
			String key=el.attribute("key","");
			String res="";
			if(type.equals("int"))
			{
				try
				{
					res=w.getInt(key)+"";
				}catch(Exception e){System.out.println("Failed to convert "+key+" to an int");}
			}
			else res=w.get(key);
			String s1="<hzx type=\""+type+"\" key=\""+key+"\"/>";
			System.out.println(s1+"===>"+res);
			html=html.replaceAll(s1,res);
		}*/
		
		return html;
	}
	
	public void loadHtmlSkel(String filename)
	{
		QFile file=new QFile(filename);
		if (!file.open(QIODevice.OpenModeFlag.ReadOnly))
			return;
		QTextStream in=new QTextStream(file);
		String res="";
		while (!in.atEnd()) 
		{
			res+=in.readLine()+"\n";
		}
		file.close();
		
		setFormatChar(res);
		
	}

	public boolean setFormatChar(String html)
	{
// 		QDomDocument doc=new QDomDocument();
// 		If we cannot parse the new file, we keep the old one and return
// 		if(!doc.setContent(html).success)
// 			return false;
// 		charSkelDom=doc;
//		System.out.println("!!!!!!!!!!!!!!!");
		charSkel=html;
		return true;
	}	
	
}