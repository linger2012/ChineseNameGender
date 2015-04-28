package model;

public class NameFeature {
	String X1;
	String X2;
	String X1X2;
	public NameFeature(String X1,String X2,String X1X2)
	{
		this.X1= X1;
		this.X2= X2;
		this.X1X2= X1X2;
	}
	public String getX1()
	{
		return X1;
	}
	public String getX2()
	{
		return X2;
	}
	public String getX1X2()
	{
		return X1X2;
	}
	
	//进来的name是不带姓氏的名字
	public static NameFeature parse(String name)
	{
		name = name.trim();
		String X1;
		String X2;
		String X1X2;
		
		if(name.length()==1)//单姓单名  比如: 杨康
		{
			X1=" ";
			X2=name.substring(0, 1);
		}
		else if(name.length()>=2)//如果名字是2个字以上,只取最后两个 比如: 黄药师,完颜洪烈      但是完颜康也会,这种不知道怎么识别.难道建立姓氏的辞典? 暂时先不考虑这种情况
		{
			X1=name.substring(name.length()-2, name.length()-1);
			X2=name.substring(name.length()-1, name.length());

		}
		else
		{
			return null;
		}
		X1X2=X1+X2;
		
		return new NameFeature(X1,X2,X1X2);
	}
}
