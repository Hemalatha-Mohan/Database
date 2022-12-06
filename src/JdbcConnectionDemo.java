import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class JdbcConnectionDemo {
	//to access varaibles inside a class and to create a object of that class
	
	static Connection conn;
	static Statement stm = null ;
	static  String name;
	static float english;
    static float tamil;
	static float maths;
	static float science;
	static float social;
	static float total;
	static float average;
	 
	 static void setup()
	    {
	        try 
	        {
	            File f= new File("src/connection.properties");
	            //read data from file
	            FileInputStream fis = new FileInputStream(f);
	            
	            //to get data from the properties file and store data into the properties file.
	            Properties p= new Properties();
	            p.load(fis);
	            
	            String driverName=p.getProperty("driverName");
	            String url=p.getProperty("url");
	            String user=p.getProperty("user");
	            String password=p.getProperty("password");
	            
	            //load and register the driver
	            //returns the Class object associated with the class or interface 
	           // with the given string name
	             Class.forName(driverName); 
	            
	         
	            conn = DriverManager.getConnection(url,user,password);  
	           
	        } 
	        catch (Exception e) 
	        {
	            System.out.println(e);
	        }
	    }
	    
	 static void UserInput()
	    {
	        Scanner s=new Scanner(System.in);
	        
	        //boolean variable indicating a condition to be either true or false.
	        // flag  let the program know that a certain condition has met
	        boolean flag = true;
	        while(flag)
	        {
	            
	            System.out.println("Enter 'I' to Insert data \nEnter 'R' to Retrieve data \nEnter 'F' to read data from file \nEnter  'E' to exit\nEnter your choice " );
	            String c=s.nextLine();
	            System.out.println("\n");
	            
	            if("I".equals(c))
	            {
	                InsertingIntoDatabase();
	                System.out.println("\n");
	            }
	            else if("R".equals(c))
	            {
	                RetrieveData();
	                System.out.println("\n");
	            }
	           else if("F".equals(c))
	            {
	                ReadData();
	                System.out.println("\n");
	            }
	          else if ("E".equals(c)) 
	           {
	        	   closeconnection();
	        	   System.out.println("connection closed");
	        	   flag = false;
	        	   System.exit(0);
	        	   
	           }
	            
	          
	        
	    }}
	    
	 //InsertingIntoDatabase Method
	   private static void InsertingIntoDatabase()
	    {
	        try 
	        {
	            //PreparedStatement is a  interface// used to execute parameterized query
	            PreparedStatement stm=conn.prepareStatement("insert into trail values(?,?,?,?,?,?,?,?,?)");
	            
	            Scanner sc=new Scanner(System.in);
	            Scanner s=new Scanner(System.in);
	            
	           int roll;
	            
	            System.out.println("Enter the Roll: ");
	            roll=sc.nextInt();
	            
	            System.out.println("Enter the name: ");
	            name=s.nextLine();
	            
	            System.out.println("Enter the English mark: ");
	            tamil=sc.nextFloat();
	            
	            System.out.println("Enter the Tamil mark: ");
	            english=sc.nextFloat();
	            
	            System.out.println("Enter the Maths mark: ");
	            maths=sc.nextFloat();
	            
	            System.out.println("Enter the Science mark: ");
	            science=sc.nextFloat();
	            
	            System.out.println("Enter the Social mark: ");
	            social=sc.nextFloat();
	            
	            total=english+tamil+maths+science+social;
	            average=total/5f;
	            
	            stm.setInt(1, roll);
	            stm.setString(2, name);
	            stm.setFloat(3, english);
	            stm.setFloat(4, tamil);
	            stm.setFloat(5, maths);
	            stm.setFloat(6, science);
	            stm.setFloat(7, social);
	            stm.setFloat(8, total);
	            stm.setFloat(9, average);
	            
	            //Get Result set
	            //resultset is an object which is used to execute the statement
	            int ResultSet =stm.executeUpdate();
	            
	            System.out.println(ResultSet+" data inserted \n");  

	            stm.close();
	  
	            
	        } 
	        catch (SQLException e)
	        {
	           System.out.println(e);
	        } 
	        
	            
	    }
	    
	    ///////////////////////////////////////////////////////////////////////////////////
	    
	    //read data from a file
	    private static void ReadData() {
	    	
	    	//containing file with the resources statement handles the file opening and closing automatically
	    	try(Scanner input = new Scanner(new File("src/mark_list.txt"))){
				
				
	    		// hasNextLine scanner checks wheather there is a line to read next 
				while(input.hasNextLine()) {
					name = "";
					String line;
					
					line = input.nextLine();
					try (Scanner sc =  new Scanner(line))
					{
						while(!sc.hasNextInt()) {
							name += sc.next()+" ";
						}
						name = name.trim();
						
						//get marks
						
						if(sc.hasNextInt()) {
							english = sc.nextInt();
						}
						
						if(sc.hasNextInt()) {
							tamil = sc.nextInt();
						}
						
						if(sc.hasNextInt()) {
							maths = sc.nextInt();
						}
						if(sc.hasNextInt()) {
							science = sc.nextInt();
						}
						if(sc.hasNextInt()) {
							social = sc.nextInt();
						}
						
						if(sc.hasNextInt()) {
							total = sc.nextInt();
						}
						
						if(sc.hasNextFloat()) {
							average = sc.nextFloat();
						}
					}
					//System.out.println(data successfully inserted);
					saveData();
				}
				
			} catch (FileNotFoundException e) {
				
				//e.printStackTrace();
				System.out.println("data from the file inserted successfully");
			}
		}
		
	    
	    ///////////////////////////////////////////////////////////////////////////////////////////////
		private static void saveData() {
			try
			
			{
			PreparedStatement stm=conn.prepareStatement("insert into trail(name,english,tamil,maths,science,social,total,average) values(?,?,?,?,?,?,?,?)");{
				stm.setString(1, name);
				
				stm.setFloat(2, english);
				stm.setFloat(3, tamil);
				stm.setFloat(4, maths);
				stm.setFloat(5, science);
				stm.setFloat(6, social);
				stm.setFloat(7, total);
				stm.setFloat(8, average);
				
				
				
				int count = stm.executeUpdate();
				
			}} catch (SQLException e) {
				
				
			e.printStackTrace();
			}
		}
		
		
		private static void closeconnection() {
	    	
	   	 try {
	 			if(stm != null) {
	 				 conn.close();
	 			 }
	 		} catch (SQLException se) {
	 			
	 			//se.printStackTrace();
	 			System.out.println("1");
	 		}
	 		 
	 		 try {
	 			if(conn !=null) {
	 				 conn.close();
	 			 }
	 		} catch (SQLException se) {


	 			//se.printStackTrace();
	 			System.out.println("2");
	 		}
	 		 
		}
		 //RetrieveData Method
	    private static void RetrieveData()
	    {    
	        try
	        {
	            //Create a Statement
	            Statement stm = conn.createStatement();
	            


	            ResultSet rs =stm.executeQuery("select *from trail");
	            
	            System.out.println("Roll_No Name English Tamil Maths Science Social Total Average ");
	            
	            
	            //Loop to print each record
	            while(rs.next())
	            {
	                
	                System.out.print(rs.getInt("roll_no")+" ");
	                System.out.printf("%10s",rs.getString("name")+" ");
	                System.out.printf("%8s",rs.getFloat("english")+" ");
	                System.out.printf("%6s",rs.getFloat("tamil")+" ");
	                System.out.printf("%6s",rs.getFloat("maths")+" ");
	                System.out.printf("%6s",rs.getFloat("science")+" ");
	                System.out.printf("%8s",rs.getFloat("social")+" ");
	                System.out.printf("%8s",rs.getFloat("total")+" ");
	                System.out.println(rs.getFloat("average")+" ");
	                
	                
	                
	            }
	            System.out.println("data Retreived successfully from database");
	            stm.close();
	            
	        }
	        catch (SQLException e) 
	        {
	            System.out.println(e);
	            
	        }
	    }
	public static void main(String[] args) {
		setup();  
        UserInput(); 
        ReadData();
        RetrieveData();
        closeconnection();
        

	}

}
