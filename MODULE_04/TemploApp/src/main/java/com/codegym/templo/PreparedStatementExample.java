package com.codegym.templo;

import java.sql.*;

public class PreparedStatementExample {
    public static void main(String[] args) {
        Connection connection = null;
        ResultSet resultset = null;
        String consulta = "select firstName, lastName from USERS where firstName like ?";
        try{
            System.out.println("Ejemplo del uso de un PreparedStatement: ");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:33060/codegymModulo04", "root", "secret");
            PreparedStatement preparedStatement = connection.prepareStatement(consulta);
            preparedStatement.setString(1, "%a%");
            resultset = preparedStatement.executeQuery();
            while(resultset.next()){
                //String nombreEmpleado = resultset.getString(1);
                String nombreEmpleado = resultset.getString("firstName");
                String apellidoPaternoEmpleado = resultset.getString("lastName");
                //String apellidoPaternoEmpleado = resultset.getString(2);
                System.out.println("Nombre : " + nombreEmpleado + ", Apellido: " + apellidoPaternoEmpleado);
                System.out.println("-----------------------");
            }
        }catch(SQLException sqlException){
            System.err.println("Error 01");
            sqlException.printStackTrace();
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
