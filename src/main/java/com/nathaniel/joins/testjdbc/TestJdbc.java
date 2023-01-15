package com.nathaniel.joins.testjdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/hb-04-one-to-many-uni?useSSL=false";
        String username = "hbstudent";
        String password = "hbstudent";
        
        try {
            System.out.println("Connecting to database: " + jdbcUrl);
            Connection myConnection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connectiion successful!!!\n");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
