package com.coherent.solutions.hotel.reservations.codegym.module04.jdbc.lesson07;

public class JdbcFirstClassConnectionExample {
    public static void main(String[] args) {
        System.out.println("Hola");
        //new CodegymMysqlConnection().queryUsersTable();
        CodegymMysqlnsert claseParaInsertarRecords = new CodegymMysqlnsert();
        User[] dosUsuarios = claseParaInsertarRecords.createTwoUsers();
        new CodegymMysqlnsert().insertUsers(dosUsuarios);

        try{
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Paso directament el objeto numero 2:
        new CodegymMySQLUpdateRecord().updateRecord(dosUsuarios[1]);

        //claseParaInsertarRecords.insertMySQLUser(antonio);

        System.out.println("Adiós...");
    }

}
