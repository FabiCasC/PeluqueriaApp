/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.peluqueria.dao;
import main.java.com.peluqueria.model.Usuario;
import main.java.com.peluqueria.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author fabia
 */


public class UsuarioDAOImpl implements UsuarioDAO {
    private Connection conexion;

    public UsuarioDAOImpl() throws SQLException {
        conexion = DBConnection.getConnection(); 
    }

    @Override
    public Usuario login(String username, String password) {
        String sql = "SELECT username, contraseña, rol FROM Usuario WHERE username = ? AND contraseña = ?";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Usuario(rs.getString("username"), rs.getString("contraseña"), rs.getString("rol")) {};
            }
        } catch (SQLException e) {
            System.err.println("Error al autenticar usuario: " + e.getMessage());
        }
        return null;
    }
}

