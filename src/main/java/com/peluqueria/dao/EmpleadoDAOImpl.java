/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.peluqueria.dao;
import main.java.com.peluqueria.model.Empleado;
import main.java.com.peluqueria.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author fabia
 */

public class EmpleadoDAOImpl implements EmpleadoDAO {
    private Connection conexion;

    public EmpleadoDAOImpl() throws SQLException {
        conexion = DBConnection.getConnection();
    }

    @Override
    public void crear(Empleado empleado) {
        String sql = "INSERT INTO Empleado (nom_emp, ape_emp, dni_emp, tel_emp, cor_emp) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellido());
            ps.setString(3, empleado.getDni());
            ps.setString(4, empleado.getTelefono());
            ps.setString(5, empleado.getCorreo());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar empleado: " + e.getMessage());
        }
    }

    @Override
    public Empleado ver(String criterio) {
        String sql = "SELECT * FROM Empleado WHERE id_empleado = ?";
        Empleado empleado = null;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(criterio));
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                empleado = new Empleado(
                    0, rs.getString("nom_emp"),
                    rs.getString("ape_emp"),
                    rs.getString("dni_emp"),
                    rs.getString("tel_emp"),
                    rs.getString("cor_emp")
                );
                empleado.setId(rs.getInt("id_empleado"));
            }
        } catch (SQLException e) {
            System.err.println("Error al ver empleado: " + e.getMessage());
        }
        return empleado;
    }

    @Override
    public void actualizar(Empleado empleado) {
        String sql = "UPDATE Empleado SET nom_emp = ?, ape_emp = ?, dni_emp = ?, tel_emp = ?, cor_emp = ? WHERE id_empleado = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellido());
            ps.setString(3, empleado.getDni());
            ps.setString(4, empleado.getTelefono());
            ps.setString(5, empleado.getCorreo());
            ps.setInt(6, empleado.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar empleado: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(String criterio) {
        String sql = "DELETE FROM Empleado WHERE id_empleado = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(criterio));
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar empleado: " + e.getMessage());
        }
    }

    @Override
    public List<Empleado> listar() {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM Empleado";

        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Empleado emp = new Empleado(
                    0, rs.getString("nom_emp"),
                    rs.getString("ape_emp"),
                    rs.getString("dni_emp"),
                    rs.getString("tel_emp"),
                    rs.getString("cor_emp")
                );
                emp.setId(rs.getInt("id_empleado"));
                empleados.add(emp);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar empleados: " + e.getMessage());
        }
        return empleados;
    }
    public int obtenerIdPorNombre(String nomEmp) {
    String sql = "SELECT id_empleado FROM Empleado WHERE nom_emp = ?";
    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, nomEmp);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);  // Devuelve el id_empleado
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1; // Retorna -1 si no encuentra el empleado
}
}




