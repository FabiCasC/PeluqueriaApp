/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main.java.com.peluqueria.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.swing.JOptionPane;
import main.java.com.peluqueria.controller.CitaService;
import main.java.com.peluqueria.model.Cita;
import main.java.com.peluqueria.util.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import main.java.com.peluqueria.dao.CitaDAO;
import main.java.com.peluqueria.dao.CitaDAOImpl;


/**
 *
 * @author fabia
 */
public class FrmCitas extends javax.swing.JFrame {
    private CitaService citaService;
    /**
     * Creates new form FrmCitas1
     */
    public FrmCitas() {
        initComponents();
        try {
            CitaDAO citaDAO = new CitaDAOImpl(); // Solo instancia sin pasar conexión
            citaService = new CitaService(citaDAO); // Inicializa el servicio correctamente
        } catch (Exception e) { // Captura cualquier error general
            JOptionPane.showMessageDialog(this, "Error al inicializar el servicio de citas: " + e.getMessage(),
                                          "Error", JOptionPane.ERROR_MESSAGE);
            citaService = null; // Evita que el programa intente usar un objeto no inicializado
        }
        personalizarTabla();
    }
    private void personalizarTabla() {
        JTableHeader header = tabla_cit.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(Color.WHITE);
        header.setForeground(new Color(150, 150, 150)); // Color gris claro para los títulos

        tabla_cit.setRowHeight(40);
        tabla_cit.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabla_cit.setForeground(new Color(50, 50, 50)); // Texto en gris oscuro
        tabla_cit.setBackground(Color.WHITE); // Fondo blanco
        tabla_cit.setGridColor(Color.BLACK); // Líneas internas negras
        tabla_cit.setShowHorizontalLines(true);
        tabla_cit.setShowVerticalLines(true); // Líneas internas visibles
        tabla_cit.setIntercellSpacing(new Dimension(1, 1)); // Espaciado de líneas más definido

        // Quitar bordes externos
        JScrollPane scrollPane = (JScrollPane) tabla_cit.getParent().getParent();
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Renderizador personalizado para dar espacio y alineación izquierda
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.LEFT);
        renderer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tabla_cit.setDefaultRenderer(Object.class, renderer);
    }
    
    private void cargarEmpleados() {
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();

        try (Connection con = DBConnection.getConnection();
             java.sql.Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id_empleado, nom_emp FROM Empleado")) { // Incluir id_empleado

            while (rs.next()) {
                int idEmpleado = rs.getInt("id_empleado");
                String nombreEmpleado = rs.getString("nom_emp");
                String formatoEmpleado = idEmpleado + " - " + nombreEmpleado; // Formato correcto
                modelo.addElement(formatoEmpleado);
            }

        } catch (SQLException e) {
            System.err.println("Error al cargar empleados: " + e.getMessage());
        }

        cmbEmpleado.setModel(modelo); // Asignamos el modelo actualizado
    }
    private void cargarTablas() {
        DefaultTableModel modelo = (DefaultTableModel) tabla_cit.getModel();
        modelo.setRowCount(0); // Limpiar la tabla antes de cargar nuevos datos

        try {
            List<Cita> citas = citaService.listarCitas(); // Llamamos al servicio para obtener las citas

            for (Cita cita : citas) {
                Object[] fila = {
                    cita.getId_cita(),
                    cita.getId_cliente(),
                    cita.getNom_emp(),
                    cita.getServicio(),
                    cita.getFecha(),
                    cita.getHora()
                };
                modelo.addRow(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar citas: " + e.getMessage(),
                                          "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnVer = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtid_cli = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        cmbServicio = new javax.swing.JComboBox<>();
        cmbEmpleado = new javax.swing.JComboBox<>();
        hora = new javax.swing.JSpinner();
        icon1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_cit = new javax.swing.JTable();
        icon = new javax.swing.JLabel();
        NavBar2 = new javax.swing.JPanel();
        Logo2 = new javax.swing.JLabel();
        btnCerrarSesion2 = new javax.swing.JButton();
        btnCitas2 = new javax.swing.JButton();
        btnEmpleados = new javax.swing.JButton();
        btnClientes1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 244, 244));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnGuardar.setBackground(new java.awt.Color(255, 182, 193));
        btnGuardar.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel2.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, 278, 47));

        btnVer.setBackground(new java.awt.Color(255, 182, 193));
        btnVer.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        btnVer.setForeground(new java.awt.Color(255, 255, 255));
        btnVer.setText("Ver tabla");
        btnVer.setToolTipText("");
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });
        jPanel2.add(btnVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 270, 278, 47));

        btnActualizar.setBackground(new java.awt.Color(255, 182, 193));
        btnActualizar.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel2.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 270, 248, 47));

        btnBorrar.setBackground(new java.awt.Color(255, 182, 193));
        btnBorrar.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        btnBorrar.setForeground(new java.awt.Color(255, 255, 255));
        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });
        jPanel2.add(btnBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 270, 248, 47));

        jLabel3.setFont(new java.awt.Font("Ebrima", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 99, 71));
        jLabel3.setText("ID de cliente");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 181, 37));

        jLabel4.setFont(new java.awt.Font("Ebrima", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 99, 71));
        jLabel4.setText("Fecha");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 90, 181, 37));

        txtid_cli.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 182, 193)));
        txtid_cli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtid_cliActionPerformed(evt);
            }
        });
        jPanel2.add(txtid_cli, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 439, 37));

        jLabel6.setFont(new java.awt.Font("Ebrima", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 99, 71));
        jLabel6.setText("Hora");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 150, 181, 37));

        jLabel7.setFont(new java.awt.Font("Ebrima", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 99, 71));
        jLabel7.setText("Servicio");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 181, 37));

        jLabel8.setFont(new java.awt.Font("Ebrima", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 99, 71));
        jLabel8.setText("Empleado");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 181, 37));
        jPanel2.add(jDateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 90, 310, 40));

        cmbServicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Corte de cabello", "Coloración", "Peinado", "Manicura", "Otros" }));
        jPanel2.add(cmbServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 240, 40));

        cmbEmpleado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--selecciona--" }));
        cmbEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEmpleadoActionPerformed(evt);
            }
        });
        jPanel2.add(cmbEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 290, 40));

        hora.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(1740972082831L), null, null, java.util.Calendar.HOUR));
        hora.setEditor(new javax.swing.JSpinner.DateEditor(hora, "HH:mm"));
        jPanel2.add(hora, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 150, 190, 40));

        icon1.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        icon1.setForeground(new java.awt.Color(255, 99, 71));
        icon1.setText("Citas");
        jPanel2.add(icon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tabla_cit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id cita", "Id cliente", "Nombre empleado", "Servicio", "Dia", "Hora"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabla_cit);
        if (tabla_cit.getColumnModel().getColumnCount() > 0) {
            tabla_cit.getColumnModel().getColumn(0).setMinWidth(100);
            tabla_cit.getColumnModel().getColumn(0).setMaxWidth(105);
        }

        icon.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        icon.setForeground(new java.awt.Color(255, 99, 71));
        icon.setText("Resultado Citas");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(icon)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1380, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(icon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        NavBar2.setBackground(new java.awt.Color(255, 255, 255));
        NavBar2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(204, 204, 204)));
        NavBar2.setPreferredSize(new java.awt.Dimension(1700, 80));
        NavBar2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Logo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Logo.png"))); // NOI18N
        NavBar2.add(Logo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 68, -1));

        btnCerrarSesion2.setBackground(new java.awt.Color(255, 204, 204));
        btnCerrarSesion2.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        btnCerrarSesion2.setForeground(new java.awt.Color(255, 99, 71));
        btnCerrarSesion2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Cerrar sesion.png"))); // NOI18N
        btnCerrarSesion2.setText("Cerrar Sesion");
        btnCerrarSesion2.setActionCommand("Citas");
        btnCerrarSesion2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        NavBar2.add(btnCerrarSesion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 20, 150, 40));

        btnCitas2.setBackground(new java.awt.Color(255, 228, 225));
        btnCitas2.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        btnCitas2.setForeground(new java.awt.Color(255, 99, 71));
        btnCitas2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Citas.png"))); // NOI18N
        btnCitas2.setText("Citas");
        btnCitas2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCitas2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCitas2ActionPerformed(evt);
            }
        });
        NavBar2.add(btnCitas2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 120, 40));

        btnEmpleados.setBackground(new java.awt.Color(255, 228, 225));
        btnEmpleados.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        btnEmpleados.setForeground(new java.awt.Color(255, 99, 71));
        btnEmpleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/empleados.png"))); // NOI18N
        btnEmpleados.setText("Empleados");
        btnEmpleados.setActionCommand("Citas");
        btnEmpleados.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpleadosActionPerformed(evt);
            }
        });
        NavBar2.add(btnEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, 140, 40));

        btnClientes1.setBackground(new java.awt.Color(255, 228, 225));
        btnClientes1.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        btnClientes1.setForeground(new java.awt.Color(255, 99, 71));
        btnClientes1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Clientes.png"))); // NOI18N
        btnClientes1.setText("Clientes");
        btnClientes1.setActionCommand("Citas");
        btnClientes1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnClientes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientes1ActionPerformed(evt);
            }
        });
        NavBar2.add(btnClientes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 120, 40));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(NavBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(NavBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
        String idCliente = txtid_cli.getText().trim();
        if (idCliente.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el ID del cliente", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Obtener datos del combo de empleado
        String empleadoSeleccionado = (String) cmbEmpleado.getSelectedItem();
        if (empleadoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un empleado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String[] empleadoDatos = empleadoSeleccionado.split(" - "); // Suponiendo que el formato es "ID - Nombre"
        int idEmpleado = Integer.parseInt(empleadoDatos[0]);
        String nombreEmpleado = empleadoDatos[1];
        
        // Obtener servicio
        String servicio = (String) cmbServicio.getSelectedItem();
        if (servicio == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un servicio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Obtener fecha
        java.util.Date fechaSeleccionada = jDateChooser.getDate();
        if (fechaSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Seleccione una fecha", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        LocalDate fecha = fechaSeleccionada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        // Obtener hora desde JSpinner
        Date horaSeleccionada = (Date) hora.getValue();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String horaStr = sdf.format(horaSeleccionada);
        
        // Crear la cita y guardarla
        Cita nuevaCita = new Cita(idCliente, idEmpleado, nombreEmpleado, servicio, fecha, horaStr);
        citaService.registrarCita(nuevaCita);
        
        JOptionPane.showMessageDialog(this, "Cita guardada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al guardar la cita: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        cargarTablas();
    }//GEN-LAST:event_btnVerActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        int filaSeleccionada = tabla_cit.getSelectedRow(); // Obtener la fila seleccionada

    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(this, "Seleccione una cita para actualizar", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        // Obtener valores de la tabla y de los campos
        int idCita = Integer.parseInt(tabla_cit.getValueAt(filaSeleccionada, 0).toString());
        String idCliente = txtid_cli.getText();
        int idEmpleado = Integer.parseInt(cmbEmpleado.getSelectedItem().toString().split(" - ")[0]); // Extraer ID del empleado
        String nomEmpleado = cmbEmpleado.getSelectedItem().toString().split(" - ")[1]; // Extraer Nombre del empleado
        String servicio = cmbServicio.getSelectedItem().toString();
        
        // Convertir la fecha del JDateChooser a LocalDate
        LocalDate fecha = jDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        // Obtener hora desde JSpinner (según tu preferencia)
        Date horaSeleccionada = (Date) hora.getValue();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String horaStr = sdf.format(horaSeleccionada);

        // Crear el objeto Cita con los nuevos datos
        Cita citaActualizada = new Cita(idCita, idCliente, idEmpleado, nomEmpleado, servicio, fecha, horaStr);

        // Llamar al servicio para actualizar la cita
        citaService.modificarCita(citaActualizada);

        // Mostrar mensaje de éxito
        JOptionPane.showMessageDialog(this, "Cita actualizada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        // Recargar la tabla para reflejar los cambios
        cargarTablas();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar la cita: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        int filaSeleccionada = tabla_cit.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una cita para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idCita = Integer.parseInt(tabla_cit.getValueAt(filaSeleccionada, 0).toString());

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar esta cita?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            // Llamamos al servicio para eliminar el cliente
            citaService.cancelarCita(idCita);

            // Confirmación
            JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente.");

            // Recargar la tabla con los datos actualizados
            cargarTablas();
        }
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void txtid_cliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtid_cliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtid_cliActionPerformed

    private void cmbEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEmpleadoActionPerformed
        cargarEmpleados();
    }//GEN-LAST:event_cmbEmpleadoActionPerformed

    private void btnCitas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCitas2ActionPerformed
        FrmCitas nuevoFormulario = new FrmCitas();

        // Hacer visible el nuevo formulario
        nuevoFormulario.setVisible(true);

        // Cerrar el formulario actual si es necesario (opcional)
        this.setVisible(false);
    }//GEN-LAST:event_btnCitas2ActionPerformed

    private void btnEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpleadosActionPerformed
        try {
            // Crear una nueva instancia del formulario al que deseas ir
            FrmEmpleados nuevoFormulario = new FrmEmpleados();

            // Hacer visible el nuevo formulario
            nuevoFormulario.setVisible(true);

            // Cerrar el formulario actual si es necesario (opcional)
            this.setVisible(false);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(FrmClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEmpleadosActionPerformed

    private void btnClientes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientes1ActionPerformed
        // Crear una nueva instancia del formulario al que deseas ir
        FrmClientes nuevoFormulario = new FrmClientes();

        // Hacer visible el nuevo formulario
        nuevoFormulario.setVisible(true);

        // Cerrar el formulario actual si es necesario (opcional)
        this.setVisible(false);
    }//GEN-LAST:event_btnClientes1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmCitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCitas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Logo2;
    private javax.swing.JPanel NavBar2;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnCerrarSesion2;
    private javax.swing.JButton btnCitas2;
    private javax.swing.JButton btnClientes1;
    private javax.swing.JButton btnEmpleados;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnVer;
    private javax.swing.JComboBox<String> cmbEmpleado;
    private javax.swing.JComboBox<String> cmbServicio;
    private javax.swing.JSpinner hora;
    private javax.swing.JLabel icon;
    private javax.swing.JLabel icon1;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_cit;
    private javax.swing.JTextField txtid_cli;
    // End of variables declaration//GEN-END:variables
}
