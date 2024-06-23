
package aprobar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoFacturaDAO {
    public void createProductoFactura(ProductoFactura productoFactura) throws SQLException {
        String sql = "INSERT INTO PXF (facnumero, procodigo, pxfcantidad, pxfvalor, pxfsubtotal, pxfstatus) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBaseDatos.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, productoFactura.getFacNumero());
            pstmt.setString(2, productoFactura.getProcodigo());
            pstmt.setInt(3, productoFactura.getCantidad());
            pstmt.setDouble(4, productoFactura.getValor());
            pstmt.setDouble(5, productoFactura.getSubtotal());
            pstmt.setString(6, productoFactura.getStatus());
            pstmt.executeUpdate();
        }
    }

    public List<ProductoFactura> getProductosByFactura(String facNumero) throws SQLException {
        List<ProductoFactura> productos = new ArrayList<>();
        String sql = "SELECT * FROM PXF WHERE facnumero = ?";
        try (Connection conn = ConexionBaseDatos.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, facNumero);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    ProductoFactura producto = new ProductoFactura();
                    producto.setFacNumero(rs.getString("facnumero"));
                    producto.setProcodigo(rs.getString("procodigo"));
                    producto.setCantidad(rs.getInt("pxfcantidad"));
                    producto.setValor(rs.getDouble("pxfvalor"));
                    producto.setSubtotal(rs.getDouble("pxfsubtotal"));
                    producto.setStatus(rs.getString("pxfstatus"));
                    productos.add(producto);
                }
            }
        }
        return productos;
    }
}
