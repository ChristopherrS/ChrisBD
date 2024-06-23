package aprobar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class VentasUI extends JFrame {
    private JTextField txtFacNumero, txtCliCodigo, txtFacFecha, txtFacSubtotal, txtFacDescuento, txtFacIva, txtFacIce, txtFacFormaPago, txtFacStatus;
    private JTextField txtProCodigo, txtProCantidad, txtProValor, txtProSubtotal;
    private JButton btnCrear, btnMostrar, btnAprobar, btnBuscar, btnAgregarProducto, btnMostrarProductos;
    private JTextArea textArea;

    public VentasUI() {
        setTitle("Sistema de Ventas");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblFacNumero = new JLabel("Número Factura:");
        lblFacNumero.setBounds(20, 20, 150, 20);
        add(lblFacNumero);

        txtFacNumero = new JTextField();
        txtFacNumero.setBounds(150, 20, 200, 20);
        add(txtFacNumero);

        JLabel lblCliCodigo = new JLabel("Código Cliente:");
        lblCliCodigo.setBounds(20, 60, 150, 20);
        add(lblCliCodigo);

        txtCliCodigo = new JTextField();
        txtCliCodigo.setBounds(150, 60, 200, 20);
        add(txtCliCodigo);

        JLabel lblFacFecha = new JLabel("Fecha Factura:");
        lblFacFecha.setBounds(20, 100, 150, 20);
        add(lblFacFecha);

        txtFacFecha = new JTextField();
        txtFacFecha.setBounds(150, 100, 200, 20);
        add(txtFacFecha);

        JLabel lblFacSubtotal = new JLabel("Subtotal:");
        lblFacSubtotal.setBounds(20, 140, 150, 20);
        add(lblFacSubtotal);

        txtFacSubtotal = new JTextField();
        txtFacSubtotal.setBounds(150, 140, 200, 20);
        add(txtFacSubtotal);

        JLabel lblFacDescuento = new JLabel("Descuento:");
        lblFacDescuento.setBounds(20, 180, 150, 20);
        add(lblFacDescuento);

        txtFacDescuento = new JTextField();
        txtFacDescuento.setBounds(150, 180, 200, 20);
        add(txtFacDescuento);

        JLabel lblFacIva = new JLabel("IVA:");
        lblFacIva.setBounds(20, 220, 150, 20);
        add(lblFacIva);

        txtFacIva = new JTextField();
        txtFacIva.setBounds(150, 220, 200, 20);
        add(txtFacIva);

        JLabel lblFacIce = new JLabel("ICE:");
        lblFacIce.setBounds(20, 260, 150, 20);
        add(lblFacIce);

        txtFacIce = new JTextField();
        txtFacIce.setBounds(150, 260, 200, 20);
        add(txtFacIce);

        JLabel lblFacFormaPago = new JLabel("Forma de Pago:");
        lblFacFormaPago.setBounds(20, 300, 150, 20);
        add(lblFacFormaPago);

        txtFacFormaPago = new JTextField();
        txtFacFormaPago.setBounds(150, 300, 200, 20);
        add(txtFacFormaPago);

        JLabel lblFacStatus = new JLabel("Estado:");
        lblFacStatus.setBounds(20, 340, 150, 20);
        add(lblFacStatus);

        txtFacStatus = new JTextField();
        txtFacStatus.setBounds(150, 340, 200, 20);
        add(txtFacStatus);

        btnCrear = new JButton("Crear Factura");
        btnCrear.setBounds(20, 380, 150, 30);
        add(btnCrear);

        btnMostrar = new JButton("Mostrar Facturas");
        btnMostrar.setBounds(200, 380, 150, 30);
        add(btnMostrar);

        btnAprobar = new JButton("Aprobar Factura");
        btnAprobar.setBounds(380, 380, 150, 30);
        add(btnAprobar);

        btnBuscar = new JButton("Buscar Factura");
        btnBuscar.setBounds(20, 420, 150, 30);
        add(btnBuscar);

        JLabel lblProCodigo = new JLabel("Código Producto:");
        lblProCodigo.setBounds(20, 460, 150, 20);
        add(lblProCodigo);

        txtProCodigo = new JTextField();
        txtProCodigo.setBounds(150, 460, 200, 20);
        add(txtProCodigo);

        JLabel lblProCantidad = new JLabel("Cantidad:");
        lblProCantidad.setBounds(20, 500, 150, 20);
        add(lblProCantidad);

        txtProCantidad = new JTextField();
        txtProCantidad.setBounds(150, 500, 200, 20);
        add(txtProCantidad);

        JLabel lblProValor = new JLabel("Valor:");
        lblProValor.setBounds(20, 540, 150, 20);
        add(lblProValor);

        txtProValor = new JTextField();
        txtProValor.setBounds(150, 540, 200, 20);
        add(txtProValor);

        JLabel lblProSubtotal = new JLabel("Subtotal:");
        lblProSubtotal.setBounds(20, 580, 150, 20);
        add(lblProSubtotal);

        txtProSubtotal = new JTextField();
        txtProSubtotal.setBounds(150, 580, 200, 20);
        add(txtProSubtotal);

        btnAgregarProducto = new JButton("Agregar Producto");
        btnAgregarProducto.setBounds(20, 620, 150, 30);
        add(btnAgregarProducto);

        btnMostrarProductos = new JButton("Mostrar Productos");
        btnMostrarProductos.setBounds(200, 620, 150, 30);
        add(btnMostrarProductos);

        textArea = new JTextArea();
        textArea.setBounds(400, 20, 350, 630);
        add(textArea);

        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Factura factura = new Factura();
                factura.setFacNumero(txtFacNumero.getText());
                factura.setCliCodigo(txtCliCodigo.getText());
                factura.setFacFecha(txtFacFecha.getText());
                factura.setFacSubtotal(Double.parseDouble(txtFacSubtotal.getText()));
                factura.setFacDescuento(Double.parseDouble(txtFacDescuento.getText()));
                factura.setFacIva(Double.parseDouble(txtFacIva.getText()));
                factura.setFacIce(Double.parseDouble(txtFacIce.getText()));
                factura.setFacFormaPago(txtFacFormaPago.getText());
                factura.setFacStatus(txtFacStatus.getText());

                FacturaDAO dao = new FacturaDAO();
                try {
                    dao.createFactura(factura);
                    JOptionPane.showMessageDialog(null, "Factura creada con éxito");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al crear la factura");
                }
            }
        });

        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FacturaDAO dao = new FacturaDAO();
                try {
                    List<Factura> facturas = dao.getAllFacturas();
                    textArea.setText("");
                    for (Factura factura : facturas) {
                        textArea.append(factura.getFacNumero() + " - " + factura.getCliCodigo() + " - " + factura.getFacFecha() + "\n");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnAprobar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeroFactura = txtFacNumero.getText();
                AprobacionFactura aprobacionFactura = new AprobacionFactura();
                try {
                    aprobacionFactura.aprobarFactura(numeroFactura);
                    JOptionPane.showMessageDialog(null, "Factura aprobada con éxito!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al aprobar la factura: " + ex.getMessage());
                }
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeroFactura = txtFacNumero.getText();
                FacturaDAO dao = new FacturaDAO();
                try {
                    Factura factura = dao.getFacturaByNumero(numeroFactura);
                    if (factura != null) {
                        txtCliCodigo.setText(factura.getCliCodigo());
                        txtFacFecha.setText(factura.getFacFecha());
                        txtFacSubtotal.setText(String.valueOf(factura.getFacSubtotal()));
                        txtFacDescuento.setText(String.valueOf(factura.getFacDescuento()));
                        txtFacIva.setText(String.valueOf(factura.getFacIva()));
                        txtFacIce.setText(String.valueOf(factura.getFacIce()));
                        txtFacFormaPago.setText(factura.getFacFormaPago());
                        txtFacStatus.setText(factura.getFacStatus());
                    } else {
                        JOptionPane.showMessageDialog(null, "Factura no encontrada");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al buscar la factura: " + ex.getMessage());
                }
            }
        });

        btnAgregarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductoFactura producto = new ProductoFactura();
                producto.setFacNumero(txtFacNumero.getText());
                producto.setProcodigo(txtProCodigo.getText());
                producto.setCantidad(Integer.parseInt(txtProCantidad.getText()));
                producto.setValor(Double.parseDouble(txtProValor.getText()));
                producto.setSubtotal(Double.parseDouble(txtProSubtotal.getText()));
                producto.setStatus("PEN");

                ProductoFacturaDAO dao = new ProductoFacturaDAO();
                try {
                    dao.createProductoFactura(producto);
                    JOptionPane.showMessageDialog(null, "Producto agregado a la factura con éxito");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al agregar el producto a la factura");
                }
            }
        });

        btnMostrarProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeroFactura = txtFacNumero.getText();
                ProductoFacturaDAO dao = new ProductoFacturaDAO();
                try {
                    List<ProductoFactura> productos = dao.getProductosByFactura(numeroFactura);
                    textArea.setText("");
                    for (ProductoFactura producto : productos) {
                        textArea.append(producto.getProcodigo() + " - " + producto.getCantidad() + " - " + producto.getValor() + " - " + producto.getSubtotal() + "\n");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentasUI().setVisible(true);
            }
        });
    }
}
