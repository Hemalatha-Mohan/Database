import java.sql.*;
public class creatingTablesql {

	public static void main(String[] args) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("connecting to the database");
		conn = DriverManager.getConnection("jdbc:mysql://10.30.1.64:3306/hemalamo","hemalamo","123");
		
		System.out.println("connected successfully");
		
		System.out.println("Creating the required table");
		stmt = conn.createStatement();
		
		/*String sql = "drop table list";
		System.out.println("table dropped successfully");*/
		
		String sql = "create table phone(roll_no int(20)primary key auto_increment,name varchar(30),english int(30),tamil int(20),maths int(20),science int(20),social int(20))";
		stmt.executeUpdate(sql);
		System.out.println("created the required table in the database");
		

	}
	catch(SQLException se) {
		//Handle the errors for JDBC
		
		se.printStackTrace();
}
  catch(Exception e) {
	  
	  //Handles the error for Class ForName
	  
	  e.printStackTrace();
  }
	
	finally {
		 //finally block is usually used to close the resources
		 
		 try {
			if(stmt != null) {
				 conn.close();
			 }
		} catch (SQLException se) {
			
			se.printStackTrace();
		}
		 
		 try {
			if(conn!=null) {
				 conn.close();
			 }
		} catch (SQLException se) {


			se.printStackTrace();
		}
		 
		 }
	  
	 System.out.println("done successfully"); 
	  
  }

}
