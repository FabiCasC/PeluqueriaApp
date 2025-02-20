/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.peluqueria.dao;
import main.java.com.peluqueria.model.Usuario;

/**
 *
 * @author fabia
 */

public interface UsuarioDAO {
    public Usuario login(String username, String password);
}