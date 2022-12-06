import java.util.Scanner;
import java.sql.*;
import java.util.Properties;
import java.io.*;
public class NewTrail {
	public static Connection conn;
 // static keyword is used to access varaibles inside a class and to create a object of that class
	  static Statement stm = null;
	  static String name;
	  static float english;
	  static float tamil;
	  static float maths;
	  static float science;
	  static float social;
	  static float total;
	  static float average;
	


	public static void main(String[] args) {
     
	
		setup();  
        UserInput(); 
        ReadData();{
        	NewTrail rt = new NewTrail();
    		
    		try {
    			rt.ReadData();
    		}
    		catch(Exception e) {
    			System.out.println(e);
    		}
        }
        RetrieveData();
        //closeconnection();
        
	}

   ////////////////////////////////////////////////////////

	public static void setup()
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
            

            Class.forName(driverName);  //invoking jdbc driver
            conn = DriverManager.getConnection(url,user,password);  //Establishing the connection to the database.

        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
    }
    
   
/////////////////////////////////////////////////////////////////////////////////////
 //UserInput Method
    public static void UserInput()
    {
        Scanner s=new Scanner(System.in);
        while(true)
        {
            
            System.out.println("Enter 'I' to Insert data or 'R' to Retrieve data or 'F' to read data from file or 'E' to exit: " );
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
          /* else if ("E".equals(c)) 
           {
        	   closeconnection();
        	   System.out.println("connection closed");
           }*/
           else
            {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
                 break;
            }
        }
        
    }
    
	//////////////////////////////////////////////////////////////////////////////////
    //InsertingIntoDatabase Method
    public static void InsertingIntoDatabase()
    {
        try 
        {
            //create Statement
            PreparedStatement stm=conn.prepareStatement("insert into studentmarklist values(?,?,?,?,?,?,?,?,?)");
            
            Scanner sc=new Scanner(System.in);
            Scanner s=new Scanner(System.in);
            
           int roll;
            //float english,tamil,maths,science,social,total,average;
            //String name;
            
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
            int ResultSet =stm.executeUpdate();
            
            System.out.println(ResultSet+" records inserted \n");  

            stm.close();
  
            
        } 
        catch (SQLException e)
        {
           System.out.println(e);
        } 
        
            
    }
    
    ///////////////////////////////////////////////////////////////////////////////////
    
    //read data from a file
    public static void ReadData() {
		try(Scanner input = new Scanner(new File("src/mark_list.txt"))){
			
			while(input.hasNextLine()) {
				name = "";
				String line;
				
				line = input.nextLine();
				
				//process the line of text for each data item
				try (Scanner data =  new Scanner(line))
				{
					while(!data.hasNextInt()) {
						name += data.next()+" ";
					}
					name = name.trim();
					
					//get marks
					
					if(data.hasNextInt()) {
						english = data.nextInt();
					}
					
					if(data.hasNextInt()) {
						tamil = data.nextInt();
					}
					
					if(data.hasNextInt()) {
						maths = data.nextInt();
					}
					if(data.hasNextInt()) {
						science = data.nextInt();
					}
					if(data.hasNextInt()) {
						social = data.nextInt();
					}
					
					if(data.hasNextInt()) {
						total = data.nextInt();
					}
					
					if(data.hasNextFloat()) {
						average = data.nextFloat();
					}
				}
				//System.out.println(name+"\t"+english+"\t"+tamil+"\t"+maths+"\t"+science+"\t"+social+"\t"+total+"\t"+average);
				saveData();
			}
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
    
    ///////////////////////////////////////////////////////////////////////////////////////////////
	private static void saveData() {
		try
		//(Connection conn = connect();
				//PreparedStatement stm=conn.prepareStatement("insert into trail(name,english,tamil,maths,science,social,total,average) values(?,?,?,?,?,?,?,?)"))
			//stm.setInt(1, roll_no);
		{
		PreparedStatement stm=conn.prepareStatement("insert into studentmarklist(name,english,tamil,maths,science,social,total,average) values(?,?,?,?,?,?,?,?)");{
			stm.setString(1, name);
			
			stm.setFloat(2, english);
			stm.setFloat(3, tamil);
			stm.setFloat(4, maths);
			stm.setFloat(5, science);
			stm.setFloat(6, social);
			stm.setFloat(7, total);
			stm.setFloat(8, average);
			
			int count = stm.executeUpdate();
			//int ResultSet =stm.executeUpdate();
           //System.out.println(count+" records inserted \n"); 
			
            if(count!=0) {
				System.out.println("Record  inserted");
			}
			else {
				System.out.println("Record not inserted");
			}
		}} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
     
    
    //RetrieveData Method
    public static void RetrieveData()
    {    
        try
        {
            //Create a Statement
            Statement stm = conn.createStatement();
            
            //Execute Query and Get Result set// interface can be used to fetch records from the database. It accepts a ResultSet and returns the list.
            ResultSet rs =stm.executeQuery("select * from studentmarklist");
            
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
            System.out.println("data Retreived successfully");
            stm.close();
            
        }
        catch (SQLException e) 
        {
            System.out.println(e);
        }
    }
}
  

















    

	
	
   
    



