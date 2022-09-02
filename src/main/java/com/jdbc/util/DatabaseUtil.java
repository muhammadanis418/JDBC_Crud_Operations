package com.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    public DatabaseUtil() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    } //---End of Constructor
  public Connection getConnection() throws SQLException {
     return DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_crud_operations","root","root");
  }  //---End of getConnection
}
