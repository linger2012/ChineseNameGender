package model;

import java.io.IOException;

import utils.Serializer;
import nlp.MaximumMatching;

public class Trainer {

	public static void main(String[] args) throws IOException {
		MaximumMatching mm = new MaximumMatching("res/dict.txt");
		Model model = new Model();
		//model.add("水晴雪", "female");
		//model.add("刘恋", "male");
		//model.add("完颜洪烈", "female");
		
		//可优化的地方,调整男女人数比例一致
		
		Reader reader = new MysqlReader(1,3000000);//第1到第1+2000000条数据库记录
		while(reader.hasNext())
		{
			NameGender ng = reader.getNext();
			String name = mm.parseName(ng.getName()).trim();
			String gender = ng.getGender().trim();
			//System.out.println(name+gender);
			model.add(name, gender);
			
		}
		
		model.show();
		Serializer.serializeObject(model, "res/model.obj");
	}

}
