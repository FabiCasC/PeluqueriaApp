/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.peluqueria.model;
import java.time.LocalDateTime;

/**
 *
 * @author LAB-USR-LCENTRO
 */


public class Cita {
    private Cliente cliente;
    private Empleado empleado;
    private LocalDateTime fechaHora;
    private String servicio;

    public Cita(Cliente cliente, Empleado empleado, LocalDateTime fechaHora, String servicio) {
        this.cliente = cliente;
        this.empleado = empleado;
        this.fechaHora = fechaHora;
        this.servicio = servicio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getServicio() {
        return servicio;
    }
}
