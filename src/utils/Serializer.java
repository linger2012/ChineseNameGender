package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializer 
{
	static public  void serializeObject(Object obj,String path) throws IOException
	{
	    FileOutputStream fos = new FileOutputStream(path);  	     
	    ObjectOutputStream oos = new ObjectOutputStream(fos);  	 
	    oos.writeObject(obj);  	 
	    oos.flush();   
	    oos.close(); 
	}
	
	static public Object reSerializeObject(String path) throws ClassNotFoundException, IOException
	{
	    FileInputStream fis = new FileInputStream(path);  	      
	    ObjectInputStream oin = new ObjectInputStream(fis);    
	    return oin.readObject(); //会申请新的空间
	}
}

