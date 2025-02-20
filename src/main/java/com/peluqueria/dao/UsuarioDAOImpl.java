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

    @Override
    public Usuario login(String username, String password) {
        Usuario usuario = null;
        String sql = "SELECT * FROM Usuario WHERE username = ? AND password = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
             ps.setString(1, username);
             ps.setString(2, password);
             
             try (ResultSet rs = ps.executeQuery()) {
                 if (rs.next()) {
                     String rol = rs.getString("rol");
                     usuario = new Usuario(username, password, rol) {};
                 }
             }
             
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return usuario;
    }
}
