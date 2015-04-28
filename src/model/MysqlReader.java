package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class MysqlReader extends Reader{

	   // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/crawler";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "09388296";
	   
	   Connection conn = null;
	   Statement stmt = null;
	   ResultSet rs =null;
	   
	public MysqlReader(int start,int num)
	{
		   try{
			      //STEP 2: Register JDBC driver
			      Class.forName("com.mysql.jdbc.Driver");
			      //STEP 3: Open a connection
			      System.out.println("Connecting to database...");
			      conn = DriverManager.getConnection(DB_URL,USER,PASS);		      
			   }catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }finally{

			   }//end try
		   
		   
		   try{
			     
			      //STEP 4: Execute a query
			      stmt = conn.createStatement();
			      String sql;
			      sql = "select name,gender from chinese_name_gender limit "+start+","+num+";";
			      rs = stmt.executeQuery(sql);
			      

		   }
		   catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }

		   
		   
		   
	}
	
	
	   public void close()
	   {
			  // nothing we can do
		      try
		      {
		    	  rs.close();
			      if(stmt!=null)
				    stmt.close();
		          if(conn!=null)
		            conn.close();
		         
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		      System.out.println("Goodbye!");
	   }
	   
	@Override
	public Boolean hasNext() {
		// TODO Auto-generated method stub
	     try {
			return rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public NameGender getNext() {
		        String name;
				try {
					name = rs.getString("name");
					String gender = rs.getString("gender");
					return new NameGender(name,gender);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}		        		 
	}

}
