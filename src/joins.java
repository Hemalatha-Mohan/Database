import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class joins {
	private static final String AddCategories = null;
	static Connection conn;
	static Statement stm = null ;
	static int p_id;
	static  String FirstName;
	static String LastName;
	static String Email;
	static String Relationship;
	static String Birthday;
	static String ContactNo;
	static String Category;
	
	static String Door_no;
	static String StreetName;
	static String City;
	static String State;
	static String Postalcode;
	static String Country;
	
	static void setup()
    {
        try 
        {
            File f= new File("src/connection.properties");
            FileInputStream fis = new FileInputStream(f);
            
            Properties p= new Properties();
            p.load(fis);
            
            String driverName=p.getProperty("driverName");
            String url=p.getProperty("url");
            String user=p.getProperty("user");
            String password=p.getProperty("password");
            
            // load and register the driver
            
            
            Class.forName(driverName); 
            //conn establishing the connection to the database
            
         
            conn = DriverManager.getConnection(url,user,password);  
            //Statement stmt=conn.createStatement();
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
    }
	
	 private static void jo() 
	 {    
	        try
	        {
	        	Statement stm = conn.createStatement();
	        	 ResultSet rs =stm.executeQuery("select *from people,contacts");
	             System.out.println("data Viewed successfully ");
	            stm.close();
	            
	        }
	        catch (SQLException e) 
	        {
	            System.out.println(e);
	            
	        }
	    }
	
	       
	public static void main(String[] args) {
		setup();
		jo();
	}

}
