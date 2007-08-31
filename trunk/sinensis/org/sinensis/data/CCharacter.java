//CCharacter.java
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
// import com.trolltech.qt.core.*;
// import com.trolltech.qt.gui.*;
import java.util.*;

public class CCharacter
{
// 	A storage map
// 	The following keys are used:
// 	UNI : the unicode value in 4-character string representation
// 	PY : pinyin list separated by a blank space. There is no distinction of the meaning of each pronunciation so far
// 	FR : French translation if it exists
// 	EN : English translation if it exists
// 	DE : German translation
// 	CANT : cantonese romanization
// 	BIG5 : Big5 encoding scheme exadecimal value
// 	GB : GB2312 encoding scheme exadecimal value
// 	COMPS : components of the character
// 	KEY : the character that makes the key
// 	ZH : the character itself embedded in a string object
	private HashMap<String,String> infos=new HashMap<String,String>();

// 	A storage map for integer-based values
// 	KEY : unicode 32-bit value of the key of this character
// 	UNI : 32-bit value of the unicode mapping for this character
// 	STROKES : total number of strokes
//	STROKESREM : number of strokes without the number of strokes in the key
// 	FREQ : statistical information on the character
// 	keys related to character shape (used to speed up research)
// 	COUNT_UNIXXXX : the number of occurences of character XXXX in this character
// 	KEY_ID : gives the # of the key (1-214)
// 	Printable: this character is printable
//	SELECTION: if this character was selected as a key or a stroke, the number of times it was selected

	private HashMap<String,Integer> infosInt=new HashMap<String,Integer>();
	public GlyphNode representation=null;
	
//	The score when doing q chqrqcter query
//	Not thqt useful becquse also stored in the infosInt
//	TODO : get rid of it
	public int queryScore=0;
	
	public CCharacter(String uni)
	{
		put("UNI",uni);
		put("UNI",toUniInt(uni));
		put("ZH",""+(char)toUniInt(uni));
		put("Printable",1);
	}
	
	public CCharacter()
	{
		put("Printable",0);
	}
	
	public void put(String key, String item)
	{
		infos.put(key,item);
	}
	
	public void put(String key, int item)
	{
		infosInt.put(key,item);
	}
	
	public String get(String key)
	{
		String i=infos.get(key);
		return i;
	}

	public boolean hasInt(String key)
	{
		return infosInt.get(key)!=null;
	}

	public int getInt(String key)
	{
		Integer i=infosInt.get(key);
		if(i==null)
			throw new Error(key);
		return (int)i;
	}
	
	public int hashCode()
	{
		if(infosInt.containsKey("UNI"))
			return getInt("UNI");
		return super.hashCode();
	}
	
	public int matches(CCharacterRequest req)
	{
		return 0;
	}

	public String toString()
	{
		String res="Character "+infos.get("UNI")+"[";
		for(String s:infos.keySet())
		{
			res+=s+"="+get(s)+" ";
		}
		for(String s:infosInt.keySet())
		{
			res+=s+"="+getInt(s)+" ";
		}
		return res+"]";
	}
	
	public String translation()
	{
		return get("EN");
	}

	public static String toUniString(int value)
	{
		return Integer.toHexString((char)value);
	}

	public static int toUniInt(String unicode)
	{
		try
		{
		return (int)Integer.decode("0x"+unicode.toUpperCase().replace("UNI",""));
		}catch(Exception e){}
		return -1;
	}

	public Iterable<String> keys()
	{
		return infos.keySet();
	}

	public Iterable<String> keysInt()
	{
		return infosInt.keySet();
	}

//	For more information, see http://www.unicode.org/charts
//	The standard CJK set
	final private static int UNICJK_BEGIN=toUniInt("4E00");
	final private static int UNICJK_END=toUniInt("9FBF");
//	The standard set of radicals
	final private static int UNICJKRAD_BEGIN=toUniInt("2E80");
	final private static int UNICJKRAD_END=toUniInt("2EFF");
//	The standard set of strokes
	final private static int UNICJKSTROKE_BEGIN=toUniInt("31C0");
	final private static int UNICJKSTROKE_END=toUniInt("31EF");
//	The first CJK extension set
	final private static int UNICJKSUPPA_BEGIN=toUniInt("3400");
	final private static int UNICJKSUPPA_END=toUniInt("4DBF");
//	The second CJK extension set
	final private static int UNICJKSUPPB_BEGIN=toUniInt("20000");
	final private static int UNICJKSUPPB_END=toUniInt("2A6DF");
//	The CJK compatibility set
	final private static int UNICJKCOMP_BEGIN=toUniInt("F900");
	final private static int UNICJKCOMP_END=toUniInt("FAFF");
//	The CJK compatibility set complement
	final private static int UNICJKCOMPSUPP_BEGIN=toUniInt("2F800");
	final private static int UNICJKCOMPSUPP_END=toUniInt("2FA1F");
	
	public static boolean isChineseChar(char c)
	{
		final int val=(int)c;
		if(val>=UNICJKSTROKE_BEGIN&&val<=UNICJKSTROKE_END)
			return true;
		if(val>=UNICJKCOMPSUPP_BEGIN&&val<=UNICJKCOMPSUPP_END)
			return true;
		if(val>=UNICJKCOMP_BEGIN&&val<=UNICJKCOMP_END)
			return true;
		if(val>=UNICJKSUPPA_BEGIN&&val<=UNICJKSUPPA_END)
			return true;
		if(val>=UNICJKSUPPB_BEGIN&&val<=UNICJKSUPPB_END)
			return true;
		if(val>=UNICJKRAD_BEGIN&&val<=UNICJKRAD_END)
			return true;
		if(val>=UNICJK_BEGIN&&val<=UNICJK_END)
			return true;


		return false;
	}
}
