package model;

import java.io.IOException;

import utils.Serializer;
import nlp.MaximumMatching;

public class Tester {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		MaximumMatching mm = new MaximumMatching("res/dict.txt");
		Model model = (Model) Serializer.reSerializeObject("res/model.obj");
		
		Reader reader = new MysqlReader(4000000,1000000);//第2000000到2000000+10000条数据库记录
		int count=0;
		int errorCount=0;
		
		int male2male=0;
		int male2female=0;
		int female2male=0;
		int female2female=0;
		
		
		while(reader.hasNext())
		{
			NameGender ng = reader.getNext();
			String name = ng.getName().trim();
			String gender = ng.getGender().trim();
			String pre =model.predict(mm.parseName(name));
			
			if(gender.equals("male"))
			{
				if(pre.equals("male"))
				{
					male2male++;
				}
				else if(pre.equals("female"))
				{
					male2female++;
				}
			}
			else if(gender.equals("female"))
			{
				if(pre.equals("male"))
				{
					female2male++;
				}
				else if(pre.equals("female"))
				{
					female2female++;
				}
				
			}
			
			
			
			if(!gender.equals(pre))
			{
				errorCount++;
			}
			count++;			
		}
		System.out.println("count:"+count);
		System.out.println("errorCount:"+errorCount);
		
		System.out.println("male2male:"+male2male);
		System.out.println("male2female:"+male2female);
		System.out.println("female2male:"+female2male);
		System.out.println("female2female:"+female2female);
	}

}
