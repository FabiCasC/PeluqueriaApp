/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.peluqueria.model;

/**
 *
 * @author LAB-USR-LCENTRO
 */

public class Venta {
    private Cliente cliente;
    private String productoServicio;
    private int cantidad;
    private double precio;

    public Venta(Cliente cliente, String productoServicio, int cantidad, double precio) {
        this.cliente = cliente;
        this.productoServicio = productoServicio;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getProductoServicio() {
        return productoServicio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }
}

