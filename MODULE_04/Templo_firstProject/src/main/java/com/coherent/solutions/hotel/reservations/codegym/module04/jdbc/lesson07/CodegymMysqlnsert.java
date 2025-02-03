package com.coherent.solutions.hotel.reservations.codegym.module04.jdbc.lesson07;

import java.sql.*;

public class CodegymMysqlnsert {

    public User[] createTwoUsers(){
        User antonio = new User();
        antonio.setId(2);
        antonio.setFirstName("Antonio");
        antonio.setLastName("Xilcahua");
        antonio.setAddress("Calle imaginaria bajo las coloridas nieves de la ciudad de Oaxaca");
        antonio.setCity("Oaxaca, Oaxaca");

        User jessica = new User();
        jessica.setId(3);
        jessica.setFirstName("Jessica");
        jessica.setLastName("Alvarado");
        jessica.setAddress("Calle de las flores");
        jessica.setCity("Guayaquil, Ecuador");

        return new User[]{antonio, jessica};
    }
    public void insertUsers(User ...users){
        for(User user : users){
            insertMySQLUser(user);
        }
    }
    public void insertMySQLUser(User  user){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;
        try{
            System.out.println("Insertando al usuario: " + user.getFirstName()+", "+user.getLastName()+" en la BD");
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
            String sqlStatement = "INSERT INTO USERS (id, firstName, lastName, address, city) VALUES ("
                    + user.getId()+ ", '" + user.getFirstName() + "' , " + "'"+ user.getLastName() + "', '"+user.getAddress()+"', '" + user.getCity() + "') ";
            statement.executeUpdate(sqlStatement);

            System.out.println("Registro insertado: " + user);
        }catch(SQLException sqlException){
            System.err.println("Error 03 - Sintaxis mal -- en el sqlStatement");
            sqlException.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException sqlException) {
            System.err.println("Error 04");
            sqlException.printStackTrace();
        }
        System.out.println("El usuario: " + user.getFirstName()+", "+user.getLastName() + "  ha sido correctamente capturado en la BD...");
    }

}
