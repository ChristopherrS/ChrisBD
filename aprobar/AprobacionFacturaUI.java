package aprobar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

  class AprobacionFactura {
    public void aprobarFactura(String facNumero) throws SQLException {
        String sql = "UPDATE FACTURAS SET facstatus = 'APR' WHERE facnumero = ?";
        try (Connection conn = ConexionBaseDatos.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, facNumero);
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("No se encontró la factura con número: " + facNumero);
            }
        }
    }
}
