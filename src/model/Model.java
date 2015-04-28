package model;

import java.io.Serializable;
import java.util.HashMap;

public class Model  implements Serializable{

	int[] lenCount;
	int[] maleLenCount;
	int[] femaleLenCount;
	
	int maleCount;
	int femaleCount;
	
	HashMap<String,Integer> maleX1Count;
	HashMap<String,Integer> maleX2Count;
	HashMap<String,Integer> maleX1X2Count;
	
	HashMap<String,Integer> femaleX1Count;
	HashMap<String,Integer> femaleX2Count;
	HashMap<String,Integer> femaleX1X2Count;
	
	public void show()
	{
		System.out.println("maleCount:"+maleCount);
		System.out.println("femaleCount:"+femaleCount);
		for(int i=0;i<lenCount.length;i++)
		{
			int len=i+1;
			System.out.println("len of "+len+":"+lenCount[i]);
			System.out.println("male=>len of "+len+":"+maleLenCount[i]);
			System.out.println("female=>len of "+len+":"+femaleLenCount[i]);
		}
	}
	
	private int getCount(HashMap<String,Integer> map,String key)
	{
		if(map.containsKey(key))
		{
			//System.out.println("count:"+map.get(key));
			return map.get(key)+1;
		}
		return 1;
	}
	
	//进来的name是不带姓氏的名字
	public String predict(String name)
	{
		name = name.trim();
		NameFeature nf = NameFeature.parse(name);
		if(nf==null) return "unknown gender";
		
		String X1=nf.getX1();
		String X2=nf.getX2();
		String X1X2=nf.getX1X2();
		
		//System.out.println("X1:"+X1);
		//System.out.println("X2:"+X2);
		//System.out.println("X1X2:"+X1X2);
		
		double male_p1=(double)getCount(maleX1Count,X1)/(double)maleCount;
		double male_p2=(double)getCount(maleX2Count,X2)/(double)maleCount;
		double male_p3=(double)getCount(maleX1X2Count,X1X2)/(double)maleCount;
		double male_p = (double)maleCount/(maleCount+femaleCount);		
		
		
		double female_p1=(double)getCount(femaleX1Count,X1)/(double)femaleCount;
		double female_p2=(double)getCount(femaleX2Count,X2)/(double)femaleCount;
		double female_p3=(double)getCount(femaleX1X2Count,X1X2)/(double)femaleCount;
		double female_p = (double)femaleCount/(maleCount+femaleCount);	
		
		
		
		double male_prob = male_p1*male_p2*male_p3*male_p;
		double female_prob = female_p1*female_p2*female_p3*female_p;
		
		/*
		System.out.println("male_p1:"+male_p1);
		System.out.println("female_p1:"+female_p1);
	
		System.out.println("male_p2:"+male_p2);
		System.out.println("female_p2:"+female_p2);
		
		System.out.println("male_p3:"+male_p3);
		System.out.println("female_p3:"+female_p3);
		
		System.out.println("male_prob:"+male_prob);
		System.out.println("female_prob:"+female_prob);
		*/
		return male_prob>=female_prob?"male":"female";	
	}
	
	public Model()
	{
		lenCount = new int[5];
		maleLenCount = new int[5];
		femaleLenCount = new int[5];
		for(int i=0;i<lenCount.length;i++) 
		{
			lenCount[i]=0;
			maleLenCount[i]=0;
			femaleLenCount[i]=0;
		}
		
		
		
		maleCount =0;
		femaleCount =0;
		
		maleX1Count = new HashMap<String,Integer>();
		maleX2Count = new HashMap<String,Integer>();
		maleX1X2Count = new HashMap<String,Integer>();
		
		femaleX1Count = new HashMap<String,Integer>();
		femaleX2Count = new HashMap<String,Integer>();
		femaleX1X2Count = new HashMap<String,Integer>();		
	}
	
	
	//进来的name是不带姓氏的名字
	public void add(String name,String gender)
	{
		name = name.trim();
		System.out.println(name);
		if(name.length()>0&&name.length()<5)//1,2,3,4 => 0,1,2,3   单字名,双字名,三字名,四字名
			{
				lenCount[name.length()-1]++;
				if(gender.equals("male"))
				{
					maleLenCount[name.length()-1]++;
				}
				else
				{
					femaleLenCount[name.length()-1]++;
				}
			}
		else 
		{
			lenCount[4]++;// 5=>4 表示异常
			return;
		}
		
		//System.out.println(name.charAt(0));
		//System.out.println(name.charAt(1));
		//System.out.println(name.charAt(2));
		
		NameFeature nf = NameFeature.parse(name);
		String X1=nf.getX1();
		String X2=nf.getX2();
		String X1X2=nf.getX1X2();
		//System.out.println("X1:"+X1);
		//System.out.println("X2:"+X2);
		//System.out.println("X1X2:"+X1X2);
		
		if(gender.equals("male"))
		{
			maleCount++;
			
			if(maleX1Count.containsKey(X1))
			{
				maleX1Count.put(X1, maleX1Count.get(X1)+1);
			}
			else
			{
				maleX1Count.put(X1, 1);
			}
			
			if(maleX2Count.containsKey(X2))
			{
				maleX2Count.put(X2, maleX2Count.get(X2)+1);
			}
			else
			{
				maleX2Count.put(X2, 1);
			}
			
			if(maleX1X2Count.containsKey(X1X2))
			{
				maleX1X2Count.put(X1X2, maleX1X2Count.get(X1X2)+1);
			}
			else
			{
				maleX1X2Count.put(X1X2, 1);
			}
			
			
		}
		else if(gender.equals("female"))
		{
			femaleCount++;
			
			if(femaleX1Count.containsKey(X1))
			{
				femaleX1Count.put(X1, femaleX1Count.get(X1)+1);
			}
			else
			{
				femaleX1Count.put(X1, 1);
			}
			
			if(femaleX2Count.containsKey(X2))
			{
				femaleX2Count.put(X2, femaleX2Count.get(X2)+1);
			}
			else
			{
				femaleX2Count.put(X2, 1);
			}
			
			if(femaleX1X2Count.containsKey(X1X2))
			{
				femaleX1X2Count.put(X1X2, femaleX1X2Count.get(X1X2)+1);
			}
			else
			{
				femaleX1X2Count.put(X1X2, 1);
			}
			
			
		}
		else
		{
			System.out.println("unknown gender!");
		}
	}
	

	
	
}
