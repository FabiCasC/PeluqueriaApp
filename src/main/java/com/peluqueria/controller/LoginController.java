/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.peluqueria.controller;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.com.peluqueria.dao.UsuarioDAO;
import main.java.com.peluqueria.dao.UsuarioDAOImpl;
import main.java.com.peluqueria.model.Usuario;

/**
 *
 * @author fabia
 */


public class LoginController {
    private UsuarioDAO usuarioDAO;
    
    public LoginController() {
        try {
            usuarioDAO = new UsuarioDAOImpl();
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Usuario authenticate(String username, String password) {
        return usuarioDAO.login(username, password);
    }
}

