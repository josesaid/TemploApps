package com.codegym.templo;

import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ){
        System.out.println( "Hello World!" );
        CodegymMysqlnsert instancia = new CodegymMysqlnsert();
        User[] dosUsuarios = instancia.createTwoUsers();
        try {
            instancia.insertUsers(dosUsuarios);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
