import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class JDBCTask {
	static Connection conn=null;
	//static Statement stm = null;
	
	
	
	static void setup() {
		try {
			File f= new File("src/connection.properties");
			FileInputStream fis = new FileInputStream(f);
			
			Properties p= new Properties();
			p.load(fis);
			
			String driverName=p.getProperty("driverName");
			String url=p.getProperty("url");
			String user=p.getProperty("user");
			String password=p.getProperty("password");
			
			// INVOKING JDBC DRIVER
			Class.forName(driverName); 
			
     //Establishing the connection to the database.
			conn = DriverManager.getConnection(url,user,password);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
System.out.println("connection established");
    } 
   
	static void UserInputData() {
		
		Scanner userinput = new Scanner(System.in);
		while(true) {
		try {
			
			System.out.println("Enter the Roll_no");
			int RollNo = userinput.nextInt();
			
			System.out.println("Enter the name");
			String name = userinput.next();
			
			System.out.println("Enter English mark:");
			float english = userinput.nextFloat();
			
			System.out.println("Enter Tamil mark:");
			float tamil = userinput.nextFloat();
			
			System.out.println("Enter Maths mark:");
			float maths = userinput.nextFloat();
			
			System.out.println("Enter science mark:");
			float science = userinput.nextFloat();
			
			System.out.println("Enter social mark:");
			float social = userinput.nextFloat();
			

            float total = english+tamil+maths+science+social;
            float average = total/5f;
            
            PreparedStatement stm =conn.prepareStatement("insert into trail values(?,?,?,?,?,?,?,?,?)");
			
            stm.setInt(1, RollNo);
            stm.setString(2, name);
            stm.setFloat(3, english);
            stm.setFloat(4, tamil);
            stm.setFloat(5, maths);
            stm.setFloat(6, science);
            stm.setFloat(7, social);
            stm.setFloat(8, total);
            stm.setFloat(9, average);
            
            
           int ResultSet =stm.executeUpdate();
            
            System.out.println(ResultSet+" data inserted \n");  
            
            stm.close();
            
            /*int i = stm.executeUpdate();
            if(i!=0){
              System.out.println("added");
            }
            else{
              System.out.println("failed to add");
            }
          }*/
		}catch (Exception e) {
			System.out.println("user input method error");
		}
		
	}
		}
	
	/*static void ChooseOption() {
		switch (choice) {
		case 1: {
			
			UserInputData();
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + choice);
		}
	}*/
	public static void main(String[] args) {
		setup();
		UserInputData();
	}

}
