package com.codegym.templo;

import java.sql.*;

public class CallableStatementExample {
    public static void main(String[] args) {
        Connection connection = null;
        ResultSet resultset = null;
        String consulta = "select firstName, lastName from USERS where firstName like ?";
        try{
            System.out.println("Ejemplo del uso de un CallableStatement: ");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:33060/codegymModulo04", "root", "secret");
            CallableStatement stmt=connection.prepareCall("{ CALL codegymModulo04.GetAllUsers() }");
            boolean resultado = stmt.execute();
            if(resultado){
                System.out.println("success");
                ResultSet rs = stmt.getResultSet();
                while(rs.next()){
                    String id = rs.getString(1);
                    String nombre = rs.getString(2);
                    String apellidoPaterno =  rs.getString(3);
                    String direccion  = rs.getString(4);
                    String ciudad  = rs.getString(5);
                    System.out.println("ID: " + id+", nombre: " + nombre+", apellidoPaterno: " + apellidoPaterno+", dirección: " +
                            direccion+", finalmente la ciudad: " + ciudad);
                    System.out.println("--------------------------------------------------");
                }
            }else{
                System.out.println("Error...");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally{
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Fallo al cerrar la conexión...");
            }
        }

    }

}
