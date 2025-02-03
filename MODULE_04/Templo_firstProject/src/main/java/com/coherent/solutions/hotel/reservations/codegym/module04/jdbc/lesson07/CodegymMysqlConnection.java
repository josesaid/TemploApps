package com.coherent.solutions.hotel.reservations.codegym.module04.jdbc.lesson07;

import java.sql.*;

public class CodegymMysqlConnection {
    public void queryUsersTable(){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;
        try{
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:33060/codegymModulo04",
                    "root", "secret");
        }catch(SQLException sqlException){
            System.err.println("Error 01");
            sqlException.printStackTrace();
        }
        try{
            statement = connection.createStatement();
        }catch(SQLException sqlException){
            System.err.println("Error 02");
            sqlException.printStackTrace();
        }
        try{
            resultset = statement.executeQuery("SELECT * FROM USERS");
            while(resultset.next()){
                Integer id = resultset.getInt(1);
                String firstName = resultset.getString(2);
                String lastName = resultset.getString(3);
                String address = resultset.getString(4);
                String city = resultset.getString(5);
                System.out.println("id: "+ id);
                System.out.println("firstName: " + firstName);
                System.out.println("lastName: " + lastName);
                System.out.println("address: " + address);
                System.out.println("city: " + city);
            }
            System.out.println("***************");
        }catch(SQLException sqlException){
            System.err.println("Error 03");
            sqlException.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException sqlException) {
            System.err.println("Error 04");
            sqlException.printStackTrace();
        }
    }

}
