package facturacion;

import com.itextpdf.text.DocumentException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class facturacion extends JFrame {
    private JTextField txtFacNumero;
    private JTextField txtCliCodigo;
    private JTextField txtFacFecha;
    private JTextField txtFacSubtotal;
    private JTextField txtFacDescuento;
    private JTextField txtFacIVA;
    private JTextField txtFacICE;
    private JTextField txtFacFormaPago;
    private JTextField txtFacStatus;
    private JTable tableDetalle;
    private DefaultTableModel tableModel;
    private factura currentFactura;
    private List<PxF> currentDetalles;

    public facturacion() {
        setTitle("Factura");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelNorth = new JPanel(new GridBagLayout());
        panelNorth.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelNorth.add(new JLabel("Número de Factura:"), gbc);
        txtFacNumero = new JTextField(15);
        gbc.gridx = 1;
        panelNorth.add(txtFacNumero, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelNorth.add(new JLabel("Código Cliente:"), gbc);
        txtCliCodigo = new JTextField(15);
        gbc.gridx = 1;
        panelNorth.add(txtCliCodigo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelNorth.add(new JLabel("Fecha:"), gbc);
        txtFacFecha = new JTextField(15);
        gbc.gridx = 1;
        panelNorth.add(txtFacFecha, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelNorth.add(new JLabel("Subtotal:"), gbc);
        txtFacSubtotal = new JTextField(15);
        gbc.gridx = 1;
        panelNorth.add(txtFacSubtotal, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panelNorth.add(new JLabel("Descuento:"), gbc);
        txtFacDescuento = new JTextField(15);
        gbc.gridx = 1;
        panelNorth.add(txtFacDescuento, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panelNorth.add(new JLabel("IVA:"), gbc);
        txtFacIVA = new JTextField(15);
        gbc.gridx = 1;
        panelNorth.add(txtFacIVA, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panelNorth.add(new JLabel("ICE:"), gbc);
        txtFacICE = new JTextField(15);
        gbc.gridx = 1;
        panelNorth.add(txtFacICE, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        panelNorth.add(new JLabel("Forma de Pago:"), gbc);
        txtFacFormaPago = new JTextField(15);
        gbc.gridx = 1;
        panelNorth.add(txtFacFormaPago, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        panelNorth.add(new JLabel("Estado:"), gbc);
        txtFacStatus = new JTextField(15);
        gbc.gridx = 1;
        panelNorth.add(txtFacStatus, gbc);

        JButton btnBuscar = new JButton("Buscar");
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panelNorth.add(btnBuscar, gbc);

        JButton btnLimpiar = new JButton("Limpiar");
        gbc.gridx = 1;
        gbc.gridy = 9;
        panelNorth.add(btnLimpiar, gbc);

        add(panelNorth, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"Producto", "Cantidad", "Valor", "Subtotal", "Estado"}, 0);
        tableDetalle = new JTable(tableModel);
        add(new JScrollPane(tableDetalle), BorderLayout.CENTER);

        JPanel panelSouth = new JPanel();
        JButton btnGenerarPdf = new JButton("Generar PDF");
        panelSouth.add(btnGenerarPdf);
        add(panelSouth, BorderLayout.SOUTH);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarFactura();
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        btnGenerarPdf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    generarPdf();
                } catch (DocumentException ex) {
                    Logger.getLogger(facturacion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void buscarFactura() {
        String facNumero = txtFacNumero.getText().trim();
        if (facNumero.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese el número de factura.");
            return;
        }

        FacturaDao facturaDAO = new FacturaDao();
        try {
            factura fac = facturaDAO.getFactura(facNumero);
            if (fac != null) {
                currentFactura = fac;
                txtCliCodigo.setText(fac.getCliCodigo());
                txtFacFecha.setText(fac.getFacFecha());
                txtFacSubtotal.setText(String.valueOf(fac.getFacSubtotal()));
                txtFacDescuento.setText(String.valueOf(fac.getFacDescuento()));
                txtFacIVA.setText(String.valueOf(fac.getFacIVA()));
                txtFacICE.setText(String.valueOf(fac.getFacICE()));
                txtFacFormaPago.setText(fac.getFacFormaPago());
                txtFacStatus.setText(fac.getFacStatus());

                List<PxF> detalle = facturaDAO.getDetalleFactura(facNumero);
                currentDetalles = detalle;
                tableModel.setRowCount(0); // Clear previous data

                for (PxF pxf : detalle) {
                    tableModel.addRow(new Object[]{
                        pxf.getProCodigo(), pxf.getPxfCantidad(), pxf.getPxfValor(), pxf.getPxfSubtotal(), pxf.getPxfStatus()
                    });
                }
            } else {
                JOptionPane.showMessageDialog(this, "Factura no encontrada.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al buscar la factura: " + ex.getMessage());
        }
    }

    private void limpiarCampos() {
        txtFacNumero.setText("");
        txtCliCodigo.setText("");
        txtFacFecha.setText("");
        txtFacSubtotal.setText("");
        txtFacDescuento.setText("");
        txtFacIVA.setText("");
        txtFacICE.setText("");
        txtFacFormaPago.setText("");
        txtFacStatus.setText("");

        tableModel.setRowCount(0); // Clear table data
    }

    private void generarPdf() throws DocumentException {
        if (currentFactura == null || currentDetalles == null || currentDetalles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay datos de factura para generar el PDF.");
            return;
        }

        String dest = "factura_" + currentFactura.getFacNumero() + ".pdf"; // Esto guardará el PDF en el directorio del proyecto
        FacturaPdfGenerator pdfGenerator = new FacturaPdfGenerator();
        try {
            pdfGenerator.generateFacturaPdf(currentFactura, currentDetalles, dest);
            JOptionPane.showMessageDialog(this, "PDF generado exitosamente: " + dest);
        } catch (FileNotFoundException | DocumentException e) {
            JOptionPane.showMessageDialog(this, "Error al generar el PDF: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new facturacion().setVisible(true);
            }
        });
    }
}
