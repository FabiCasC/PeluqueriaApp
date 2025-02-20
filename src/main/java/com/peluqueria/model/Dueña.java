/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.peluqueria.model;

/**
 *
 * @author LAB-USR-LCENTRO
 */

public class Dueña extends Usuario {

    public Dueña(String usuario, String contrasena) {
        super(usuario, contrasena);
    }

    @Override
    public void acceder() {
        System.out.println("Accediendo como Dueña.");
        // Mostrar Menú de Dueña
    }
}

