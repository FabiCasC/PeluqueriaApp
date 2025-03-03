/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.peluqueria.controller;
import main.java.com.peluqueria.model.Inventario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LAB-USR-LCENTRO
 */


public class InventarioService implements CRUD<Inventario> {
    private List<Inventario> inventario = new ArrayList<>();

    @Override
    public void crear(Inventario item) {
        inventario.add(item);
    }

    @Override
    public Inventario ver(String nombre) {
        for (Inventario producto : inventario) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return producto;
            }
        }
        return null;
    }

    @Override
    public void actualizar(Inventario item) {
        Inventario producto = ver(item.getNombre());
        if (producto != null) {
            producto.setCantidad(item.getCantidad());
        }
    }

    @Override
    public void eliminar(String nombre) {
        Inventario producto = ver(nombre);
        if (producto != null) {
            inventario.remove(producto);
        }
    }

    @Override
    public List<Inventario> listar() {
        return inventario;
    }
}

