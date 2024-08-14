package com.taller.Taller.Servicio;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.taller.Taller.Entidad.Factura;
import com.taller.Taller.Entidad.Suscripcion;
import com.taller.Taller.Repositorio.FacturaRepositorio;
import com.taller.Taller.Repositorio.SuscripcionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
@Service
public class PdfServicioFactura {
    @Autowired
    private FacturaRepositorio facturaRepositorio;

    public byte[] generarPdf(Long usuarioId) throws DocumentException, IOException {
        List<Factura> facturas = facturaRepositorio.findByUsuarioId(usuarioId);
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        document.open();
        document.add(new Paragraph("Factura de Suscripción", FontFactory.getFont("Arial", 14, Font.BOLD)));

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.addCell(new PdfPCell(new Phrase("Tipo", FontFactory.getFont("Arial", 12))));
        table.addCell(new PdfPCell(new Phrase("Precio", FontFactory.getFont("Arial", 12))));
        table.addCell(new PdfPCell(new Phrase("Duración", FontFactory.getFont("Arial", 12))));

        for (Factura factura : facturas) {
            table.addCell(new PdfPCell(new Phrase(factura.getTipo(), FontFactory.getFont("Arial", 12))));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(factura.getPrecio()), FontFactory.getFont("Arial", 12))));
            table.addCell(new PdfPCell(new Phrase(factura.getDuracion(), FontFactory.getFont("Arial", 12))));
        }

        document.add(table);
        document.close();
        return baos.toByteArray();
    }
}
