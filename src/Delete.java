import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class Delete {
	static Connection conn;
	static Statement stm = null ;
	static String contact_id;
	

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
	private static void DeleteContact() 
    {
        try 
        {

        	PreparedStatement stm=conn.prepareStatement("delete from contacts where contact_id=?");
            
            
            Scanner s=new Scanner(System.in);
            
            System.out.println("Enter the contact_id: ");
            contact_id=s.nextLine();
            
          
            
            stm.setString(1, contact_id);
            
            int ResultSet =stm.executeUpdate();
            
            System.out.println(ResultSet+" data deleted \n");  

            stm.close();

            
        } 
        catch (SQLException e)
        {
           System.out.println(e);
        } 
      System.out.println("deleted a record people success");  
            
    }

	public static void main(String[] args) {
	setup();
	DeleteContact();

	}

}
