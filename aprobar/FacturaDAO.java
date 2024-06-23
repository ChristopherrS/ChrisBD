
package aprobar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO {
    public void createFactura(Factura factura) throws SQLException {
        String sql = "INSERT INTO FACTURAS (facnumero, clicodigo, facfecha, facsubtotal, facdescuento, faciva, facice, facformapago, facstatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBaseDatos.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, factura.getFacNumero());
            pstmt.setString(2, factura.getCliCodigo());
            pstmt.setString(3, factura.getFacFecha());
            pstmt.setDouble(4, factura.getFacSubtotal());
            pstmt.setDouble(5, factura.getFacDescuento());
            pstmt.setDouble(6, factura.getFacIva());
            pstmt.setDouble(7, factura.getFacIce());
            pstmt.setString(8, factura.getFacFormaPago());
            pstmt.setString(9, factura.getFacStatus());
            pstmt.executeUpdate();
        }
    }

    public List<Factura> getAllFacturas() throws SQLException {
        List<Factura> facturas = new ArrayList<>();
        String sql = "SELECT * FROM FACTURAS";
        try (Connection conn = ConexionBaseDatos.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Factura factura = new Factura();
                factura.setFacNumero(rs.getString("facnumero"));
                factura.setCliCodigo(rs.getString("clicodigo"));
                factura.setFacFecha(rs.getString("facfecha"));
                factura.setFacSubtotal(rs.getDouble("facsubtotal"));
                factura.setFacDescuento(rs.getDouble("facdescuento"));
                factura.setFacIva(rs.getDouble("faciva"));
                factura.setFacIce(rs.getDouble("facice"));
                factura.setFacFormaPago(rs.getString("facformapago"));
                factura.setFacStatus(rs.getString("facstatus"));
                facturas.add(factura);
            }
        }
        return facturas;
    }
   
     public Factura getFacturaByNumero(String facNumero) throws SQLException {
        String sql = "SELECT * FROM FACTURAS WHERE facnumero = ?";
        Factura factura = null;
        try (Connection conn = ConexionBaseDatos.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, facNumero);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    factura = new Factura();
                    factura.setFacNumero(rs.getString("facnumero"));
                    factura.setCliCodigo(rs.getString("clicodigo"));
                    factura.setFacFecha(rs.getString("facfecha"));
                    factura.setFacSubtotal(rs.getDouble("facsubtotal"));
                    factura.setFacDescuento(rs.getDouble("facdescuento"));
                    factura.setFacIva(rs.getDouble("faciva"));
                    factura.setFacIce(rs.getDouble("facice"));
                    factura.setFacFormaPago(rs.getString("facformapago"));
                    factura.setFacStatus(rs.getString("facstatus"));
                }
            }
        }
        return factura;
     }
}

