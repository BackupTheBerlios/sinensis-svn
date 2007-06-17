//CCharacterRequest.java
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

import java.util.*;

public class CCharacterRequest
{
	public Map<String,String> tokens=new HashMap<String,String>();
	public Map<String,Integer> tokensInt=new HashMap<String,Integer>();
// 	Min and max number of strokes, key excluded
	public int maxStrokes=256,minStrokes=0;
// 	A Chinese character may have multiple pinyins.
// 	If the user knows them, he/she/it can input them, with or without tones
// 	A match will need all the pinyins
	public Vector<String> pinyins=new Vector<String>();
// 	Translation tokens
	public Vector<String> translation=new Vector<String>();

	public void prepare()
	{
		for(String s:tokens.keySet())
			tokens.put(s,tokens.get(s).toLowerCase());
	}

	public boolean isSubsetOf(CCharacterRequest request)
	{
		return false;
	}

	public int accepts(CCharacter c)
	{
// 		Bad number of strokes
		if(!c.hasInt("STROKES")||c.getInt("STROKES")<minStrokes || c.getInt("STROKES")>maxStrokes)
			return 0;
// 		Check for the pinyin
		final String py=c.get("PY");
// 		DEBUG : no pinyin? What the heck?
		if(py==null)
		{
// 			System.out.println("Warning: no pinyin found for character"+c);
			return 0;
		}
		
		else for(String s:pinyins)
			if(py.indexOf(s)<0)
				return 0;
		
		
// 		TODO: make it possible to change the language (use the Settings class)
		final String trans=c.get("EN");
		if(trans==null)
		{
// 			System.out.println("Warning: no translation found for character"+c);
			return 0;
		}
		else for(String s:translation)
			if(trans.indexOf(s)<0)
				return 0;
		
		for(String s:tokens.keySet())
			if(c.get(s)==null)
				return 0;
			else if(c.get(s).toLowerCase().indexOf(tokens.get(s))<0)
				return 0;

		for(String s:tokensInt.keySet())
			if(!c.hasInt(s))
				return 0;
			else if(s.equals("UNI")&&c.getInt(s)!=tokensInt.get(s))
				return 0;		
			else if(c.getInt(s)<tokensInt.get(s))
				return 0;
// 		Good
		return 1;
	}
	
	public void addPinyin(String s)
	{
		parsePinyin(this,s);
	}
	
	public void addTranslation(String s)
	{
		parseTranslation(this,s);
	}

	public void addStrokeNbr(String s)
	{
		parseStrokeNbr(this,s);
	}

	public void addUnicode(String s)
	{
		parseUnicode(this,s);
	}
	
	public void addChinese(String s)
	{
		parseChinese(this,s);
	}

	public static boolean parsePinyin(CCharacterRequest request,String s)
	{
		String[] pys=s.split(" ");
		for(int i=0;i<pys.length;i++)
			request.pinyins.add(pys[i]);
//		Vector<String> vec=new Vector<String>();
//		if(!WordQuery.heuristicPinYin(vec,s))
//			return false;
//		if(request!=null)
//		{
//			request.pinyins.addAll(vec);
//		}
		return true;
	}
	
	public boolean parseStrokeNbr(CCharacterRequest request, String s)
	{
		
		s=s.replaceAll("\t"," ");
		try
		{
			Vector<Object> tokens=parseStrokes(s);
			if(tokens.size()==0)
				return true;
			int i=(Integer)tokens.get(0);
			int min=0,max=256;
			if(tokens.size()==1)
			{
				min=max=i;
			}
			else if( tokens.get(1) instanceof Integer )
			{
				min=i;
				max=(Integer) tokens.get(1);
			}
			else if( tokens.get(1) instanceof String && ((String)tokens.get(1)).equals("-"))
			{
				max=i;
			}
			else if( tokens.get(1) instanceof String && ((String)tokens.get(1)).equals("+"))
			{
				min=i;
			}
			else return false;
			if(request!=null)
			{
				request.maxStrokes=max;
				request.minStrokes=min;
			}

		}catch(Exception e){return false;}
		return true;
	}
	
	private static Vector<Object> parseStrokes(String s) throws Exception
	{
		Vector<Object> res=new Vector<Object>(3);
		int index=0;
		final int length=s.length();
		while(index<s.length()&&Character.isWhitespace(s.charAt(index)))
			index++;
		if(index==length)
			return res;
		String s1="";
		while(index<s.length()&&Character.isDigit(s.charAt(index)))
		{
			s1=s1+s.charAt(index);
			index++;
		}
		res.add(Integer.parseInt(s1));
		if(index==length)
			return res;
		while(index<s.length()&&Character.isWhitespace(s.charAt(index)))
			index++;
		if(index==length)
			return res;
		if(s.charAt(index)=='+')
		{
			res.add("+");
			return res;
		}
		if(s.charAt(index)=='-')
		{
			res.add("-");
			return res;
		}
		while(index<s.length()&&Character.isWhitespace(s.charAt(index)))
			index++;
		if(index==length)
			return res;
		String s2="";
		while(index<s.length()&&Character.isDigit(s.charAt(index)))
		{
			s2=s2+s.charAt(index);
			index++;
		}
		res.add(Integer.parseInt(s2));
		return res;
	}

	public static boolean parseUnicode(CCharacterRequest request, String s)
	{
		if(s.equals(""))
			return true;
		int u=CCharacter.toUniInt(s);
		if(!CCharacter.isChineseChar((char)u))
			return false;
		if(request!=null)
			request.tokensInt.put("UNI",u);
System.out.println(">>>"+u);
		return true;
	}

	public static boolean parseChinese(CCharacterRequest request, String s)
	{
		if(s.equals(""))
			return true;
		int index=0;
		while(index<s.length()&&Character.isWhitespace(s.charAt(index)))
			index++;
		if(!CCharacter.isChineseChar(s.charAt(index)))
			return false;
		if(request!=null)
			request.tokensInt.put("UNI",(int)s.charAt(index));
		return true;
	}
	
	public static boolean parseTranslation(CCharacterRequest request, String s)
	{
		String[] a=s.split(" ");
		if(request!=null)
			for(int i=0;i<a.length;i++)
				request.translation.add(a[i]);
		return true;
	}
	
	public String toString()
	{
		String res="CharacterQuery: ";
		res+=minStrokes+" strokes min, ";
		res+=maxStrokes+" strokes max, PYs:";
		for(String s:pinyins)
			res+=s+" ";
		res+="trans:";
		for(String s:translation)
			res+=s+" ";
		
		res+="Additional: ";
		for(String s:tokens.keySet())
			res+=s+"=>"+tokens.get(s)+" ";
		for(String s:tokensInt.keySet())
			res+=s+"=>"+tokensInt.get(s)+" ";
		return res;
	}
}