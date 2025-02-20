/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.peluqueria.controller;
import main.java.com.peluqueria.model.Cita;
import main.java.com.peluqueria.model.Cliente;
import main.java.com.peluqueria.model.Empleado;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LAB-USR-LCENTRO
 */

public class CitaService implements CRUD<Cita> {
    private List<Cita> citas = new ArrayList<>();

    @Override
    public void crear(Cita cita) {
        citas.add(cita);
    }

    @Override
    public Cita buscar(String criterio) {
        for (Cita cita : citas) {
            if (cita.getCliente().getDni().equals(criterio)) {
                return cita;
            }
        }
        return null;
    }

    @Override
    public void actualizar(Cita cita) {
        Cita c = buscar(cita.getCliente().getDni());
        if (c != null) {
            // Actualizar cita
        }
    }

    @Override
    public void eliminar(String criterio) {
        Cita cita = buscar(criterio);
        if (cita != null) {
            citas.remove(cita);
        }
    }

    @Override
    public List<Cita> listar() {
        return citas;
    }
}

