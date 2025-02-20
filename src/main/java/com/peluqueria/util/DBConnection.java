/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.peluqueria.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author fabia
 */


public class DBConnection {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Peluqueria;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASS = "789456123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}

