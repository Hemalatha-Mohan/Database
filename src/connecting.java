import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class connecting {
	static Connection conn;
	static Statement stm = null ;
	
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
        
        System.out.println("Connection created successfully");
    }
	 static void ViewSingle()
	    {    
	        try
	        {
	        	Scanner s=new Scanner(System.in);
	        	 System.out.println("Enter the p_id:");
	        	 int number = Integer.parseInt(s.nextLine());
	            //Create a Statement
	            Statement stm = conn.createStatement();
	            String query = ("select  people.p_id,people.FirstName,people.LastName,people.Email,contacts.contactNo,contacts.Category,Address.AddCategories,\r\n"
	    		 		+ "Address.Door_no,Address.StreetName,Address.city,Address.State,Address.Postalcode,Address.Country\r\n"
	    		 		+ "from people\r\n"
	    		 		+ "join contacts on people.p_id=\r\n"
	    		 		+ "contacts.p_id\r\n"
	    		 		+ "join Address on contacts.contact_id = \r\n"
	    		 		+ "Address.contact_id \r\n"
	    		 		+ "where people.p_id = "+ number);
	    		
	    		 Statement stmt = conn.createStatement();
	    		 ResultSet rs = stmt.executeQuery(query);
	    		   while(rs.next()) {
	    		 System.out.print(+rs.getInt(1)+", ");
	    	      System.out.print("First name: "+rs.getString(2)+", ");
	    	      System.out.print("Last name: "+rs.getString(3)+", ");
	    	      System.out.print("Email: "+rs.getString(4)+", ");
	    	      System.out.print("contactNo: "+rs.getString(5)+", ");
	    	      System.out.print("Category: "+rs.getString(6)+" ,");
	    	      System.out.print("AddCategory: "+rs.getString(7)+" ,");
	    	      System.out.print("Door_no: "+rs.getString(8)+" ,");
	    	      System.out.print("StreetName: "+rs.getString(9)+" ,");
	    	      System.out.print("City: "+rs.getString(10)+" ,");
	    	      System.out.print("State: "+rs.getString(11)+" ,");
	    	      System.out.print("Postalcode: "+rs.getString(12)+" ,");
	    	      System.out.print("Country: "+rs.getString(13)+"\n ");
	    	}
	            System.out.println("data viewed successfully from all tables");
	            stm.close();
	            
	        }
	        catch (SQLException e) 
	        {
	            System.out.println(e);
	            
	        }
	    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		setup();
		ViewSingle();

	}

}
