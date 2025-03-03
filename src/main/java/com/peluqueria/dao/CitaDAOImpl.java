package main.java.com.peluqueria.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import main.java.com.peluqueria.dao.CitaDAO;
import main.java.com.peluqueria.model.Cita;
import main.java.com.peluqueria.util.DBConnection;

public class CitaDAOImpl implements CitaDAO {
    private Connection conexion;

    public CitaDAOImpl() throws SQLException {
        this.conexion = DBConnection.getConnection(); // Aquí se obtiene la conexión
    }

    @Override
    public void crear(Cita cita) {
        String sql = "INSERT INTO Cita (id_clientes, id_emp, nom_emp, servicio, fecha, hora) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, cita.getId_cliente());
            ps.setInt(2, cita.getId_emp());
            ps.setString(3, cita.getNom_emp());
            ps.setString(4, cita.getServicio());
            ps.setDate(5, Date.valueOf(cita.getFecha()));
            ps.setString(6, cita.getHora());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cita ver(String criterio) {
        String sql = "SELECT * FROM Cita WHERE id_cita = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(criterio));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Cita(
                    rs.getInt("id_cita"),
                    rs.getString("id_cliente"),
                    rs.getInt("id_emp"),
                    rs.getString("nom_emp"),
                    rs.getString("servicio"),
                    rs.getDate("fecha").toLocalDate(),
                    rs.getString("hora")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void actualizar(Cita cita) {
        String sql = "UPDATE Cita SET id_clientes=?, id_emp=?, nom_emp=?, servicio=?, fecha=?, hora=? WHERE id_cita=?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, cita.getId_cliente());
            ps.setInt(2, cita.getId_emp());
            ps.setString(3, cita.getNom_emp());
            ps.setString(4, cita.getServicio());
            ps.setDate(5, Date.valueOf(cita.getFecha()));
            ps.setString(6, cita.getHora());
            ps.setInt(7, cita.getId_cita());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(String criterio) {
        String sql = "DELETE FROM Cita WHERE id_cita = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(criterio));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Cita> listar() {
        List<Cita> lista = new ArrayList<>();
        String sql = "SELECT * FROM Cita";
        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Cita(
                    rs.getInt("id_cita"),
                    rs.getString("id_clientes"),
                    rs.getInt("id_emp"),
                    rs.getString("nom_emp"),
                    rs.getString("servicio"),
                    rs.getDate("fecha").toLocalDate(),
                    rs.getString("hora")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}