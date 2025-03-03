/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.peluqueria.model;

/**
 *
 * @author LAB-USR-LCENTRO
 */

public class Cliente {
    private String id_clientes;  // id_cli (se genera automáticamente)
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;

    // Constructor con ID (para cuando obtenemos un cliente desde la BD)
    public Cliente(String id_clientes, String nombre, String apellido, String dni, String telefono) {
        this.id_clientes = id_clientes;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
    }

    // Constructor sin ID (para cuando creamos un nuevo cliente y el ID se genera solo)
    public Cliente(String nombre, String apellido, String dni, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
    }

    // Getters y Setters
    public String getid_clientes() {
        return id_clientes;
    }

    public void setid_clientes(String id_clientes) {
        this.id_clientes = id_clientes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}

