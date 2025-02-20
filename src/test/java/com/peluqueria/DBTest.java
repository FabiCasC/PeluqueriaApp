/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test.java.com.peluqueria;

import main.java.com.peluqueria.util.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author fabia
 */


public class DBTest {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                System.out.println("¡Conexión exitosa a la base de datos!");
            } else {
                System.out.println("La conexión es nula.");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


