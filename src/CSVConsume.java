import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import static java.lang.Integer.parseInt;

public class CSVConsume {
    public static void main(String[] args) {
        String jdbcUrl="jdbc:mysql://10.30.1.64:3306/hemalamo?characterEncoding=utf8&useSSL=false&useUnicode=true";
        String username="hemalamo";
        String password="123";

        String filePath="src/emp_list.txt";

       // int batchSize=20;

        Connection connection=null;


        try{
            connection= DriverManager.getConnection(jdbcUrl,username,password);
            connection.setAutoCommit(false);

            String sql="insert into employee(name,address,salary) values(?,?,?)";

            PreparedStatement statement=connection.prepareStatement(sql);

            BufferedReader lineReader=new BufferedReader(new FileReader(filePath));

            String lineText=null;
            int count=0;

            lineReader.readLine();
            while ((lineText=lineReader.readLine())!=null){
                String[] data=lineText.split(" ");

               // String id=data[0];
                String name=data[0];
                String address=data[1];
                String salary=data[2];

                //statement.setInt(1,parseInt(id));
                statement.setString(1,name);
                statement.setString(2,address);
                statement.setInt(3,parseInt(salary));
                statement.addBatch();
                if(count!=0){
                    statement.executeBatch();
                }
            }
            lineReader.close();
            statement.executeBatch();
            connection.commit();
            connection.close();
            System.out.println("Data has been inserted successfully.");

        }
        catch (Exception exception){
            exception.printStackTrace();
        }

    }
}
