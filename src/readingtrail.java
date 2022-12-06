import java.io.*;
import java.sql.*;
import java .util.Scanner;


public class readingtrail {
	int roll_no;
	static String name;
	static float english;
	static float tamil;
	static float maths;
	static float science;
	static float social;
	static float total;
	static float average;
	static Connection conn = null;
	Statement stmt = null;
	private static void ReadData() {
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
	
	private static void saveData() {
		try(Connection conn = connect();
				PreparedStatement stm=conn.prepareStatement("insert into trail(name,english,tamil,maths,science,social,total,average) values(?,?,?,?,?,?,?,?)")){
			//stm.setInt(1, roll_no);
			stm.setString(1, name);
			
			stm.setFloat(2, english);
			stm.setFloat(3, tamil);
			stm.setFloat(4, maths);
			stm.setFloat(5, science);
			stm.setFloat(6, social);
			stm.setFloat(7, total);
			stm.setFloat(8, average);
			
			int count = stm.executeUpdate();
			if(count!=0) {
				System.out.println("Record inserted");
			}
			else {
				System.out.println("Record not inserted");
			}
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	//create a connection
	public static Connection connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://10.30.1.64:3306/hemalamo","hemalamo","123");
		}
		catch(SQLException | ClassNotFoundException e) {
			System.out.println(e);
			return null;
		}
		return conn;
	}

	public static void main(String[] args) {
		//readingtrail rt = new readingtrail();
		
		//try {
			ReadData();
		//}
		/*catch(Exception e) {
			System.out.println(e);
		}*/
	}
		
	}
	
