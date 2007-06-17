//Word.java
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
public class Word
{
// 	Word in simplified characters
	public final String simpl;
// 	Word in traditional characters
	public final String tradi;
// 	Word in pinyin
	public final String pinyin;
// 	Translation in a certain language
	public final String trans;
// 	Translation in low case
	public final String transLow;

	public Word()
	{
		simpl="";
		tradi="";
		pinyin="";
		trans="";
		transLow="";
	}
	
	public Word(final String si,final String tr, final String py, final String t)
	{
		simpl=si;
		tradi=tr;
		pinyin=py.toLowerCase();
		trans=t;
		transLow=trans.toLowerCase();
	}
	
// 	public boolean matches(final WordRequest req)
// 	{
// 		return false;
// 	}

}