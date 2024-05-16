package database;

import java.sql.*;

//public class DatabaseUtil {
//    private static String URL = "jdbc:mysql://localhost:3306/knk2024";
//    private static String USER = "root";
//    private static String PASSWORD = "";
//    private static Connection connection = null;
//
//    public static Connection getConnection(){
//        if(connection == null){
//            try {
//                connection = DriverManager.getConnection(
//                        URL, USER, PASSWORD
//                );
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        return connection;
//    }
//}
public class DatabaseUtil {
   public static void main(String[] args){
       String url = "jdbc:mysql://localhost:3306/knk2024";
       String username = "root";
       String password = "";
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           Connection connection = DriverManager.getConnection(url,username,password);

           Statement statement = connection.createStatement();

           ResultSet resultSet=statement.executeQuery("select * from users");

           while(resultSet.next()) {
               System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + resultSet.getInt(3));
           }
           connection.close();
       }
       catch(Exception e) {System.out.println(e);}

   }
}