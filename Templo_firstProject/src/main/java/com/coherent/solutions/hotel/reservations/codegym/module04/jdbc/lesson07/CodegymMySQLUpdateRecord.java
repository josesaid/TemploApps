package com.coherent.solutions.hotel.reservations.codegym.module04.jdbc.lesson07;

import java.sql.*;

public class CodegymMySQLUpdateRecord {
    public void updateRecord(User user){
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Statement statement = null;
        //ResultSet resultset = null;
        try{
            System.out.println("Actualizando al usuario: " + user.getFirstName()+", "+user.getLastName()+" en la BD");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:33060/codegymModulo04",
                    "root", "secret");
        }catch(SQLException sqlException){
            System.err.println("Error 01");
            sqlException.printStackTrace();
        }
        try{
            String query = "UPDATE USERS set address=? where Id=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "CALLE DE LAS FLORES AMARILLAS PORQUE VERANO");
            preparedStatement.setInt(2, 3);
            int resultado = preparedStatement.executeUpdate();
            System.out.println("Update result: " + resultado);
        }catch(SQLException sqlException){
            System.err.println("Error 02");
            sqlException.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException sqlException) {
            System.err.println("Error 04");
            sqlException.printStackTrace();
        }
        System.out.println("El usuario: " + user.getFirstName()+", "+user.getLastName() + "  ha sido correctamente actualizado en la BD...");
    }

}
