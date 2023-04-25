package com.example.wishlist.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//databese connection singleton
public class ConnectionManager {

        private static Connection connection = null;

        //giv connection to database
        public static Connection getConnection(String db_url, String uid, String pwd){
            if (connection == null){
                //initialize connection
                try {
                    connection = DriverManager.getConnection(db_url, uid, pwd);
                }catch (SQLException e){
                    System.out.println("Could not connect to database");
                    e.printStackTrace();
                }
            }
            return connection;
        }
}
