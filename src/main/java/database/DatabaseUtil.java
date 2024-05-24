package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
   public static Connection databaseLink;
   public static Connection getconnection(){
      String databaseName = "knk2024";
      String databaseUser ="root";
      String databasePassword = "";
      String url = "jdbc:mysql://localhost/" + databaseName;

      try{
         Class.forName("com.mysql.cj.jdbc.Driver");
         databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);
      }catch(Exception e) {
         e.printStackTrace();
      }
      return databaseLink;

   }

}