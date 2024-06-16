package facturacion;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class FacturaPdfGenerator {

    public void generateFacturaPdf(factura factura, List<PxF> detalles, String dest) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();

        document.add(new Paragraph("Factura COMERCIAL"));

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        table.addCell(new PdfPCell(new Phrase("Factura No.:")));
        table.addCell(new PdfPCell(new Phrase(factura.getFacNumero())));

        table.addCell(new PdfPCell(new Phrase("Cliente:")));
        table.addCell(new PdfPCell(new Phrase(factura.getCliCodigo())));

        table.addCell(new PdfPCell(new Phrase("Fecha:")));
        table.addCell(new PdfPCell(new Phrase(factura.getFacFecha())));

        table.addCell(new PdfPCell(new Phrase("Forma de Pago:")));
        table.addCell(new PdfPCell(new Phrase(factura.getFacFormaPago())));

        document.add(table);

        document.add(new Paragraph("\nDetalle de la Factura"));

        PdfPTable detalleTable = new PdfPTable(6);
        detalleTable.setWidthPercentage(100);

        detalleTable.addCell("Producto");
        detalleTable.addCell("Descripción");
        detalleTable.addCell("Unidad");
        detalleTable.addCell("Cantidad");
        detalleTable.addCell("Valor Unitario");
        detalleTable.addCell("Subtotal");

        for (PxF pxf : detalles) {
            detalleTable.addCell(pxf.getProCodigo());
            // Aquí deberías agregar el detalle de la descripción del producto basado en el código del producto
            detalleTable.addCell("Descripción del producto");
            detalleTable.addCell("Unidad de medida");
            detalleTable.addCell(String.valueOf(pxf.getPxfCantidad()));
            detalleTable.addCell(String.valueOf(pxf.getPxfValor()));
            detalleTable.addCell(String.valueOf(pxf.getPxfSubtotal()));
        }

        document.add(detalleTable);

        document.add(new Paragraph("\n\nTotales"));

        PdfPTable totalesTable = new PdfPTable(2);
        totalesTable.setWidthPercentage(50);
        totalesTable.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);

        totalesTable.addCell(new PdfPCell(new Phrase("Subtotal:")));
        totalesTable.addCell(new PdfPCell(new Phrase(String.valueOf(factura.getFacSubtotal()))));

        totalesTable.addCell(new PdfPCell(new Phrase("Descuento:")));
        totalesTable.addCell(new PdfPCell(new Phrase(String.valueOf(factura.getFacDescuento()))));

        totalesTable.addCell(new PdfPCell(new Phrase("IVA:")));
        totalesTable.addCell(new PdfPCell(new Phrase(String.valueOf(factura.getFacIVA()))));

        totalesTable.addCell(new PdfPCell(new Phrase("Total:")));
        totalesTable.addCell(new PdfPCell(new Phrase(String.valueOf(factura.getFacSubtotal() + factura.getFacIVA() - factura.getFacDescuento()))));

        document.add(totalesTable);

        document.close();
    }
}
