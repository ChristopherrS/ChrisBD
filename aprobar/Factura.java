
package aprobar;

public class Factura {
    private String facNumero;
    private String cliCodigo;
    private String facFecha;
    private double facSubtotal;
    private double facDescuento;
    private double facIva;
    private double facIce;
    private String facFormaPago;
    private String facStatus;

    // Getters y setters
    public String getFacNumero() {
        return facNumero;
    }

    public void setFacNumero(String facNumero) {
        this.facNumero = facNumero;
    }

    public String getCliCodigo() {
        return cliCodigo;
    }

    public void setCliCodigo(String cliCodigo) {
        this.cliCodigo = cliCodigo;
    }

    public String getFacFecha() {
        return facFecha;
    }

    public void setFacFecha(String facFecha) {
        this.facFecha = facFecha;
    }

    public double getFacSubtotal() {
        return facSubtotal;
    }

    public void setFacSubtotal(double facSubtotal) {
        this.facSubtotal = facSubtotal;
    }

    public double getFacDescuento() {
        return facDescuento;
    }

    public void setFacDescuento(double facDescuento) {
        this.facDescuento = facDescuento;
    }

    public double getFacIva() {
        return facIva;
    }

    public void setFacIva(double facIva) {
        this.facIva = facIva;
    }

    public double getFacIce() {
        return facIce;
    }

    public void setFacIce(double facIce) {
        this.facIce = facIce;
    }

    public String getFacFormaPago() {
        return facFormaPago;
    }

    public void setFacFormaPago(String facFormaPago) {
        this.facFormaPago = facFormaPago;
    }

    public String getFacStatus() {
        return facStatus;
    }

    public void setFacStatus(String facStatus) {
        this.facStatus = facStatus;
    }
}

