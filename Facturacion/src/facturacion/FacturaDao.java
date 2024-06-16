
package facturacion;
// FacturaDAO.java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacturaDao {
    public factura getFactura(String facNumero) throws SQLException {
        String sql = "SELECT * FROM FACTURAS WHERE FACNUMERO = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, facNumero);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    factura factura = new factura();
                    factura.setFacNumero(rs.getString("FACNUMERO"));
                    factura.setCliCodigo(rs.getString("CLICODIGO"));
                    factura.setFacFecha(rs.getString("FACFECHA"));
                    factura.setFacSubtotal(rs.getDouble("FACSUBTOTAL"));
                    factura.setFacDescuento(rs.getDouble("FACDESCUENTO"));
                    factura.setFacIVA(rs.getDouble("FACIVA"));
                    factura.setFacICE(rs.getDouble("FACICE"));
                    factura.setFacFormaPago(rs.getString("FACFORMAPAGO"));
                    factura.setFacStatus(rs.getString("FACSTATUS"));
                    return factura;
                }
            }
        }
        return null;
    }

    public List<PxF> getDetalleFactura(String facNumero) throws SQLException {
        List<PxF> detalle = new ArrayList<>();
        String sql = "SELECT * FROM PXF WHERE FACNUMERO = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, facNumero);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PxF pxf = new PxF();
                    pxf.setFacNumero(rs.getString("FACNUMERO"));
                    pxf.setProCodigo(rs.getString("PROCODIGO"));
                    pxf.setPxfCantidad(rs.getDouble("PXFCANTIDAD"));
                    pxf.setPxfValor(rs.getDouble("PXFVALOR"));
                    pxf.setPxfSubtotal(rs.getDouble("PXFSUBTOTAL"));
                    pxf.setPxfStatus(rs.getString("PXFSTATUS"));
                    detalle.add(pxf);
                }
            }
        }
        return detalle;
    }
}

