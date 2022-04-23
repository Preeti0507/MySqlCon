import java.sql.*;
import java.util.*;
public class SQLConnection {
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");// driver loading
            Connection con=null;
            con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Me","root","darshun1310");
            if(con!=null){
                System.out.println("Connection Successfull");
                Statement smt= con.createStatement();
                String sql="create table Users(Name varchar(20),Age int);";
                smt.executeUpdate(sql);
                System.out.println("Table created");
                System.out.println("Enter number of data: ");
                int n=Integer.parseInt(sc.nextLine());
                String[] names=new String[n];
                String[] ages=new String[n];
                for(int j=0;j<n;j++){
                    System.out.println("data "+ (j+1) +": ");
                    System.out.print("Name: ");
                    names[j]=sc.nextLine();
                    System.out.print("Age: ");
                    ages[j]=sc.nextLine();

                    sql="insert into Users values('"+names[j]+"',"+ages[j]+");";
                    smt.executeUpdate(sql);
                    System.out.println("Data added");
                    System.out.println();
                }
                System.out.println("Fetching table");
                sql="select * from Users;";
                ResultSet rs=smt.executeQuery(sql);
                System.out.println("Name\tAge");
                while(rs.next()){
                    System.out.print(rs.getString(1)+"\t");
                    System.out.print(rs.getInt(2)+"\n");
                }
                System.out.println("Data fetch Successful");
                con.close();
            }
            else{
                System.out.println("Connection failed");
            }
        }catch(SQLException e)
            {
                e.printStackTrace();
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
