package main.java.com.peluqueria.controller;

import java.util.List;
import java.sql.SQLException;
import main.java.com.peluqueria.dao.CitaDAOImpl;
import main.java.com.peluqueria.dao.EmpleadoDAOImpl;
import main.java.com.peluqueria.model.Cita;

import java.sql.Connection;
import java.util.List;
import main.java.com.peluqueria.dao.CitaDAO;

public class CitaService {
    private CitaDAOImpl citaDAO;

    public CitaService(CitaDAO citaDAO) { 
        this.citaDAO = (CitaDAOImpl) citaDAO;
    }


    public void registrarCita(Cita cita) {
        if (cita.getFecha() == null || cita.getHora() == null || cita.getServicio().isEmpty()) {
            throw new IllegalArgumentException("Los datos de la cita son inválidos");
        }
        citaDAO.crear(cita);
    }

    public Cita obtenerCita(int idCita) {
        Cita cita = citaDAO.ver(String.valueOf(idCita));
        if (cita == null) {
            throw new RuntimeException("No se encontró la cita con ID: " + idCita);
        }
        return cita;
    }

    public void modificarCita(Cita cita) {
        if (cita.getId_cita() <= 0) {
            throw new IllegalArgumentException("ID de cita inválido");
        }
        citaDAO.actualizar(cita);
    }

    public void cancelarCita(int idCita) {
        citaDAO.eliminar(String.valueOf(idCita));
    }

    public List<Cita> listarCitas() {
        return citaDAO.listar();
    }
}
