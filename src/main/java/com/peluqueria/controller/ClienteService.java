/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.peluqueria.controller;

import java.sql.SQLException;
import java.util.List;
import main.java.com.peluqueria.dao.ClienteDAO;
import main.java.com.peluqueria.dao.ClienteDAOImpl;
import main.java.com.peluqueria.model.Cliente;

/**
 *
 * @author fabia
 */
public class ClienteService {
    private ClienteDAO clienteDAO;

    public ClienteService() {
        try {
            this.clienteDAO = new ClienteDAOImpl();
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public void agregarCliente(String nombre, String apellido, String dni, String telefono) {
        Cliente cliente = new Cliente(null, nombre, apellido, dni, telefono);
        clienteDAO.crear(cliente);
    }

    public Cliente obtenerClientePorId(String idCliente) {
        return clienteDAO.ver(idCliente);
    }

    public void actualizarCliente(String idCliente, String nombre, String apellido, String dni, String telefono) {
        Cliente cliente = new Cliente(idCliente, nombre, apellido, dni, telefono);
        clienteDAO.actualizar(cliente);
    }

    public void eliminarCliente(String idCliente) {
        clienteDAO.eliminar(idCliente);
    }

    public List<Cliente> listarClientes() {
        return clienteDAO.listar();
    }
    
    public List<Cliente> buscarClientes(String criterio) {
        return clienteDAO.buscar(criterio);  // Esto debe devolver una lista
    }


}


