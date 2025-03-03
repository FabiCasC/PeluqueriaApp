/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.peluqueria.dao;
import main.java.com.peluqueria.util.DBConnection;
import main.java.com.peluqueria.model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabia
 */

public class ClienteDAOImpl implements ClienteDAO {
    private Connection conexion;

    public ClienteDAOImpl() throws SQLException {
        conexion = DBConnection.getConnection();
    }

    @Override
    public void crear(Cliente cliente) {
        String sql = "INSERT INTO Clientes (nom_cli, ape_cli, dni_cli, tel_cli) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getDni());
            ps.setString(4, cliente.getTelefono());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar cliente: " + e.getMessage());
        }
    }

    @Override
    public Cliente ver(String idCliente) {
        String sql = "SELECT * FROM Clientes WHERE id_clientes = ?";
        Cliente cliente = null;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, idCliente);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cliente = new Cliente(
                    rs.getString("id_clientes"),
                    rs.getString("nom_cli"),
                    rs.getString("ape_cli"),
                    rs.getString("dni_cli"),
                    rs.getString("tel_cli")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al ver cliente: " + e.getMessage());
        }
        return cliente;
    }

    @Override
    public void actualizar(Cliente cliente) {
        String sql = "UPDATE Clientes SET nom_cli = ?, ape_cli = ?, dni_cli = ?, tel_cli = ? WHERE id_clientes = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getDni());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getid_clientes());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(String idCliente) {
        String sql = "DELETE FROM Clientes WHERE id_clientes = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, idCliente);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente: " + e.getMessage());
        }
    }

    @Override
    public List<Cliente> listar() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Clientes";

        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cliente cli = new Cliente(
                    rs.getString("id_clientes"),
                    rs.getString("nom_cli"),
                    rs.getString("ape_cli"),
                    rs.getString("dni_cli"),
                    rs.getString("tel_cli")
                );
                clientes.add(cli);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar clientes: " + e.getMessage());
        }
        return clientes;
    }

    @Override
    public List<Cliente> buscar(String criterio) {
        String sql = "SELECT * FROM Clientes WHERE nom_cli LIKE ? OR ape_cli LIKE ? OR dni_cli = ?";
        List<Cliente> listaClientes = new ArrayList<>();

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, "%" + criterio + "%");
            ps.setString(2, "%" + criterio + "%");
            ps.setString(3, criterio);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getString("id_clientes"),
                    rs.getString("nom_cli"),
                    rs.getString("ape_cli"),
                    rs.getString("dni_cli"),
                    rs.getString("tel_cli")
                );
                listaClientes.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar clientes: " + e.getMessage());
        }

        return listaClientes;
    }

}


