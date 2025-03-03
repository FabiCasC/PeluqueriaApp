/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.peluqueria.controller;
import java.sql.SQLException;
import java.util.List;
import main.java.com.peluqueria.dao.EmpleadoDAO;
import main.java.com.peluqueria.dao.EmpleadoDAOImpl;
import main.java.com.peluqueria.model.Empleado;

/**
 *
 * @author fabia
 */

public class EmpleadoService {
    private final EmpleadoDAO empleadoDAO;

    public EmpleadoService() throws SQLException {
        this.empleadoDAO = new EmpleadoDAOImpl();
    }

    public void agregarEmpleado(String nombre, String apellido, String dni, String telefono, String correo) {
    Empleado empleado = new Empleado(0, nombre, apellido, dni, telefono, correo); // El ID no se usa en el INSERT
    empleadoDAO.crear(empleado);
    }


    public List<Empleado> listarEmpleados() {
        return empleadoDAO.listar();
    }

    public void actualizarEmpleado(int id, String nombre, String apellido, String dni, String telefono, String correo) {
        Empleado empleado = new Empleado(0, nombre, apellido, dni, telefono, correo);
        empleado.setId(id);
        empleadoDAO.actualizar(empleado);
    }

    public void eliminarEmpleado(String id) {
        empleadoDAO.eliminar(id);
    }
}


