
import java.sql.*;  
import java.io.*;  
class RS{  
public static void main(String args[])throws Exception{  
	Connection conn=null;
	Class.forName("com.mysql.cj.jdbc.Driver");
	conn = DriverManager.getConnection("jdbc:mysql://10.30.1.64:3306/hemalamo","hemalamo","123");  
    PreparedStatement ps=conn.prepareStatement("insert into emp values(?,?,?)");  
  
    
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
 //parseInt converts string to integer 
do{  
System.out.println("enter id:");  
int id=Integer.parseInt(br.readLine());  
System.out.println("enter name:");  
String name=br.readLine();  
System.out.println("enter salary:");  
float salary=Float.parseFloat(br.readLine());  
  
ps.setInt(1,id);  
ps.setString(2,name);  
ps.setFloat(3,salary);  
int i=ps.executeUpdate();  
System.out.println(i+" records affected");  
  
System.out.println("Do you want to continue: y/n");  
String s=br.readLine();  
if(s.startsWith("n")){  
break;  
}  
}while(true);  
  
conn.close();  
}} 


/*private static void RetrieveData()
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
        System.out.println("data Retreived successfully from database");
        stm.close();
        
    }
    catch (SQLException e) 
    {
        System.out.println(e);
    }
}*/