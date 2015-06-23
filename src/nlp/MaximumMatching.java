package nlp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class MaximumMatching {

	//http://www.cnblogs.com/eaglet/archive/2009/08/19/1549566.html 盘古分词-中文人名识别
	//http://blog.csdn.net/yangyan19870319/article/details/6399871  正向最大匹配中文分词算法
	HashSet<String> dictSet;
	public  MaximumMatching(String dictFile) throws IOException
	{
		dictSet = new HashSet<String>();
		File file=new File(dictFile);  
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8")); 
		String line;
		StringTokenizer tokenizer;
		while(true)
		{
			line =br.readLine();
			if(line==null) break;
			tokenizer= new StringTokenizer(line," ");
			while(tokenizer.hasMoreTokens())
			{
				dictSet.add(tokenizer.nextToken().trim());
			}
		}	
		br.close();
	}
	
	public String parseName(String name)
	{
		String surname = parseSurName(name);
		String firstname = name.replace(surname, "").replaceAll("[?|.|·|~|\\s|a-z|A-Z]*", "");//替换异常符号
		System.out.println(name+"=>"+firstname);
		return firstname;
	}
	
	public String parseSurName(String name)
	{
		String lastPrefix="";
		String prefix="";
		HashSet<String> lastCandidates =dictSet;
		HashSet<String> candidates;	
		for(int i=1;i<name.length();i++)
		{
			prefix=name.substring(0, i);
			candidates = new HashSet<String>();
			for(Iterator<String> it=lastCandidates.iterator();it.hasNext();)
			{
				String surname = it.next();
				if(surname.contains(prefix))
				{
					candidates.add(surname);
				}
			}
			
			if(candidates.isEmpty()) break;			
			lastCandidates=candidates;
			lastPrefix = prefix;			
		}	
		return lastPrefix;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		MaximumMatching mm = new MaximumMatching("res/dict.txt");
		System.out.println(mm.parseName("爱新觉罗溥仪"));
		System.out.println(mm.parseName("完颜康"));
		System.out.println(mm.parseName("欧阳锋"));
		System.out.println(mm.parseName("草泥马之名"));
		
		String name = "刘 ADc?.·恋 ??";
		name = name.replaceAll("[?|.|·|~|\\s|a-z|A-Z]*", "");
		System.out.println(name);
		
	}

}
