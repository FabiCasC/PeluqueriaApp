/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.peluqueria.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author LAB-USR-LCENTRO
 */

public class Cita {
    private int id_cita;
    private String id_cliente;
    private int id_emp; // Nuevo campo para la relación con Empleado
    private String nom_emp;
    private String servicio;
    private LocalDate fecha;
    private String hora;

    // Constructor con todos los atributos
    public Cita(int id_cita, String id_cliente, int id_emp, String nom_emp, String servicio, LocalDate fecha, String hora) {
        this.id_cita = id_cita;
        this.id_cliente = id_cliente;
        this.id_emp = id_emp;
        this.nom_emp = nom_emp;
        this.servicio = servicio;
        this.fecha = fecha;
        this.hora = hora;
    }

    // Constructor sin id_cita (para crear nuevas citas)
    public Cita(String id_cliente, int id_emp, String nom_emp, String servicio, LocalDate fecha, String hora) {
        this.id_cliente = id_cliente;
        this.id_emp = id_emp;
        this.nom_emp = nom_emp;
        this.servicio = servicio;
        this.fecha = fecha;
        this.hora = hora;
    }

    // Getters y Setters
    public int getId_cita() {
        return id_cita;
    }

    public void setId_cita(int id_cita) {
        this.id_cita = id_cita;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_emp() {
        return id_emp;
    }

    public void setId_emp(int id_emp) {
        this.id_emp = id_emp;
    }

    public String getNom_emp() {
        return nom_emp;
    }

    public void setNom_emp(String nom_emp) {
        this.nom_emp = nom_emp;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}

