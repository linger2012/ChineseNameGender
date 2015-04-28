package model;

import java.io.IOException;

import utils.Serializer;
import nlp.MaximumMatching;

public class Predictor {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		MaximumMatching mm = new MaximumMatching("res/dict.txt");
		Model model = (Model) Serializer.reSerializeObject("res/model.obj");
		//System.out.println(model.predict(mm.parseName("张敬轩")));
		//System.out.println(model.predict(mm.parseName("刘德华")));
		
		//System.out.println(model.predict(mm.parseName("杨幂")));
		System.out.println(model.predict(mm.parseName("范玮琪")));
		System.out.println(model.predict(mm.parseName("萧亚轩")));
		//System.out.println(model.predict(mm.parseName("范冰冰")));
		System.out.println(model.predict(mm.parseName("汪东城")));
		System.out.println(model.predict(mm.parseName("陈小宾")));
		System.out.println(model.predict(mm.parseName("唐焉")));
		System.out.println(model.predict(mm.parseName("王语嫣")));
		System.out.println(model.predict(mm.parseName("谢霆锋")));
		
		System.out.println(model.predict(mm.parseName("完颜康")));
		System.out.println(model.predict(mm.parseName("杨康")));
		System.out.println(model.predict(mm.parseName("殷素素")));
		System.out.println(model.predict(mm.parseName("郭靖")));
		System.out.println(model.predict(mm.parseName("杨过")));
		System.out.println(model.predict(mm.parseName("黄蓉")));
		System.out.println(model.predict(mm.parseName("黄药师")));
		System.out.println(model.predict(mm.parseName("令狐冲")));//单字容易辨别为女的
		System.out.println(model.predict(mm.parseName("欧阳克")));
		System.out.println(model.predict(mm.parseName("欧阳锋")));
		System.out.println(model.predict(mm.parseName("王重阳")));
		System.out.println(model.predict(mm.parseName("风清扬")));
		System.out.println(model.predict(mm.parseName("东方不败")));
		
		System.out.println(model.predict(mm.parseName("杨不悔")));
		System.out.println(model.predict(mm.parseName("张无忌")));
		System.out.println(model.predict(mm.parseName("梅若华")));
		System.out.println(model.predict(mm.parseName("梅超风")));
		System.out.println(model.predict(mm.parseName("曲灵风")));
		
		System.out.println(model.predict(mm.parseName("冯新平")));
		System.out.println(model.predict(mm.parseName("张显")));
		System.out.println(model.predict(mm.parseName("李曦")));
		System.out.println(model.predict(mm.parseName("林令民")));
		System.out.println(model.predict(mm.parseName("薛莲")));
		System.out.println(model.predict(mm.parseName("刘恋")));
		System.out.println(model.predict(mm.parseName("康潮明")));
		System.out.println(model.predict(mm.parseName("赵展")));
		System.out.println(model.predict(mm.parseName("闫博")));
		System.out.println(model.predict(mm.parseName("陈抒")));
		System.out.println(model.predict(mm.parseName("陈之进")));	
		System.out.println(model.predict(mm.parseName("卢文汐")));
		System.out.println(model.predict(mm.parseName("林轩明")));
		System.out.println(model.predict(mm.parseName("王春亮")));
		System.out.println(model.predict(mm.parseName("邬向春")));
		System.out.println(model.predict(mm.parseName("杨永辉")));
		System.out.println(model.predict(mm.parseName("卜庆辉")));
		System.out.println(model.predict(mm.parseName("戴静晖")));
		System.out.println(model.predict(mm.parseName("冯志恒")));
		System.out.println(model.predict(mm.parseName("高元胜")));
		System.out.println(model.predict(mm.parseName("郭斌")));
		System.out.println(model.predict(mm.parseName("洪宁")));
		System.out.println(model.predict(mm.parseName("高忠胜")));
		System.out.println(model.predict(mm.parseName("侯志贞")));
		
		/*
		System.out.println(model.predict(mm.parseName("欧阳启民")));
		System.out.println(model.predict(mm.parseName("林坚雄")));
		System.out.println(model.predict(mm.parseName("张云雷")));
		*/
	}

}
