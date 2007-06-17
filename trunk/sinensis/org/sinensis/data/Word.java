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