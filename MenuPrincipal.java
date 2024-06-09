package Comercio;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class MenuPrincipal {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> mostrarMenu());
    }

    private static void mostrarMenu() {
        new ClientesGUI();
    }
}

class ClientesGUI extends JFrame {
    // Componentes de la interfaz
    private JTextField txtCodigo, txtNombre, txtIdentificacion, txtDireccion, txtTelefono, txtCelular, txtEmail, txtTipo, txtStatus;
    private JButton btnGuardar, btnBuscar;

    // Parámetros de conexión a la base de datos
    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USERNAME = "System";
    private static final String PASSWORD = "oracle123";

    public ClientesGUI() {
        setTitle("Gestión de Clientes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(11, 2, 5, 5));

        // Componentes
        add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        add(txtCodigo);

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Identificación:"));
        txtIdentificacion = new JTextField();
        add(txtIdentificacion);

        add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        add(txtDireccion);

        add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        add(txtTelefono);

        add(new JLabel("Celular:"));
        txtCelular = new JTextField();
        add(txtCelular);

        add(new JLabel("Email:"));
        txtEmail = new JTextField();
        add(txtEmail);

        add(new JLabel("Tipo:"));
        txtTipo = new JTextField();
        add(txtTipo);

        add(new JLabel("Status:"));
        txtStatus = new JTextField();
        add(txtStatus);

        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarCliente());
        add(btnGuardar);

        btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> buscarClientePorCodigo());
        add(btnBuscar);

        setVisible(true);
    }

    private void guardarCliente() {
        // Aquí va la lógica para guardar el cliente en la base de datos
        JOptionPane.showMessageDialog(this, "Función de guardado de cliente");
        limpiarCampos();
    }

    private void buscarClientePorCodigo() {
        String codigoCliente = txtCodigo.getText().trim();
        if (codigoCliente.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese el código del cliente.");
            return;
        }

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM CLIENTES WHERE CLICODIGO = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, codigoCliente);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                txtNombre.setText(resultSet.getString("CLINOMBRE"));
                txtIdentificacion.setText(resultSet.getString("CLIIDENTIFICACION"));
                txtDireccion.setText(resultSet.getString("CLIDIRECCION"));
                txtTelefono.setText(resultSet.getString("CLITELEFONO"));
                txtCelular.setText(resultSet.getString("CLICELULAR"));
                txtEmail.setText(resultSet.getString("CLIEMAIL"));
                txtTipo.setText(resultSet.getString("CLITIPO"));
                txtStatus.setText(resultSet.getString("CLISTATUS"));
            } else {
                JOptionPane.showMessageDialog(this, "Cliente no encontrado.");
                limpiarCampos();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al buscar cliente: " + ex.getMessage());
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtIdentificacion.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtCelular.setText("");
        txtEmail.setText("");
        txtTipo.setText("");
        txtStatus.setText("");
    }
}
