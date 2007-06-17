package org.sinensis.data;

import java.util.*;

public class WordQuery
{
// 	All the pinyins
	private static final String[] pinyins={"bang","bing","bo","dian","guo","hao","hou","hu","ji","mei","meng","nao","ni","nian","ping","pong","qing","qiu","shao","shou","tai","ting","you","yu","zhang","zhong"};
// 	The Chinese characters tokens
	private Vector<String> chars=new Vector<String>();
// 	The pinyin. Note that they are identified by heurisitic search. A request such as "you" will be identified either as pinyin or English.
	private Vector<String> pinyin=new Vector<String>();
// 	All the tokens identified as Western language (that is, not pinyin)
	private Vector<String> western=new Vector<String>();
// 	All the tokens that could be either pinyin or Western language. For example, "you"
//	private Vector<String> unknown=new Vector<String>();;

	private String query="";

	public WordQuery(String s)
	{
System.out.println("new query ("+s+")");
		query=s;
		parseQuery();
	}

	private void parseQuery()
	{
		String[] tokens=query.split(" ");
		for(int i=0;i<tokens.length;i++)
		{
			if(!heuristicChinese(tokens[i]))
				if(!heuristicPinYin(tokens[i]))
					western.add(tokens[i].toLowerCase());
		}
	}

	private boolean heuristicChinese(String token)
	{
		return heuristicChinese(chars,token);
	}
	
	public static boolean heuristicChinese(Vector<String> res,String token)
	{
		if(!containsChineseChar(token))
			return false;
		
		if(res!=null)
			res.add(token);
		return true;
	}

	private static boolean containsChineseChar(String s)
	{
		for(int i=0;i<s.length();i++)
			if(CCharacter.isChineseChar(s.charAt(i)))
				return true;
		return false;
	}
	
	private boolean heuristicPinYin(String token)
	{
		return heuristicPinYin(pinyin,token);
	}
	
	public static boolean heuristicPinYin(Vector<String> res,String token)
	{
		Vector<String> pinyinTmp=new Vector<String>();
		int index=0;
		while(index<token.length())
			if(heuristicPinYin(pinyinTmp,token,index))
			{
				index+=pinyinTmp.lastElement().length();
			}
			else return false;
			
		if(res!=null)
			res.addAll(pinyinTmp);
		return true;
	}
	
	
	private static boolean heuristicPinYin(Vector<String> parsedString, String token, int index)
	{
// 		We try to find a matching pinyin beginning at position index 
		for(int i=0;i<pinyins.length;i++)
			if(heuristicPinYin(parsedString,token,index,i))
				return true;
// 		Nothing found: this string probably not a pinyin string
		return false;
	}

// 	Checks for pinyin pinyins[py] beginning at index index in the string token
// 	Tries to add a number at the end if possible
// 	Adds the pinyin in the pinyin vector and returns true, otherwise returns false
	private static boolean heuristicPinYin(Vector<String> parsedString, String token, int index, int py)
	{
// 		The end of the string is not long enough to host the pinyin: nothing found
		if(token.length()-index<pinyins[py].length())
			return false;
// 		Looking for a match
		for(int i=0;i<pinyins[py].length();i++)
			if(token.charAt(index+i)!=pinyins[py].charAt(i))
				return false;
// 		We are at the end, so no number to end
		if(token.length()-index==pinyins[py].length())
		{
			parsedString.add(pinyins[py]);
			return true;
		}
// 		Let's see if we can add 1, 2, 3 or 4
		char next=token.charAt(index+pinyins[py].length());
		if(next=='1'||next=='2'||next=='3'||next=='4')
		{
			parsedString.add(pinyins[py]+next);
			return true;
		}
// 		The next character belongs to another pinyin
// 		We add the one we have found and we return indicating we found a pinyin
		parsedString.add(pinyins[py]);
		return true;
	}
	
	public String query()
	{
		return query;
	}
	
	public String toString()
	{
		String res="WordQuery{ ZH=(";
		for(String s:chars)
			res+=s+" ";
		res+=") PY=(";
		for(String s:pinyin)
			res+=s+" ";
		res+=") LANG=(";
		for(String s:western)
			res+=s+" ";
		res+=")";
		return res;
	}
	
	public boolean derivesFrom(WordQuery query)
	{
		return false;
	}
	
	public boolean accept(Word w)
	{
		final int res1=score(w.simpl,chars);
		final int res2=score(w.tradi,chars);
		if(res2<0 && res1<0)
			return false;
		
		final int res3=score(w.pinyin,pinyin);
		if(res3<0)
			return false;
		
		final int res4=score(w.transLow,western);
		if(res4<0)
			return false;
		return true;
	}
	
	private int score(String wordInput, Vector<String> tokens)
	{
		int res=0;
		
		int[] match=new int[wordInput.length()];
		for(int i=0;i<wordInput.length();i++)
			match[i]=-1;
		
		for(int i=0;i<tokens.size();i++)
		{
			int index=0;
			int u;
			do
			{
				u=wordInput.indexOf(tokens.get(i),index);
				index=u+1;
			}while(u>=0&&match[u]>=0);
			if(u<0)
				return -1;
			
			for(int j=0;j<tokens.get(i).length();j++)
				match[u+j]=i;
			res++;
		}
		return res+1;
	}
	
}