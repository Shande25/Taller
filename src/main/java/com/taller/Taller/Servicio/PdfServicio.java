package com.taller.Taller.Servicio;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.taller.Taller.Entidad.Suscripcion;
import com.taller.Taller.Repositorio.SuscripcionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PdfServicio {

    @Autowired
    SuscripcionRepositorio suscripcionRepositorio;

    public byte[] generarPdf() throws DocumentException, IOException {
        List<Suscripcion> suscripciones = suscripcionRepositorio.findAll();
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        document.open();
        document.add(new Paragraph("Listado de Suscripciones", FontFactory.getFont("Arial", 14, Font.BOLD)));

        PdfPTable table = new PdfPTable(4); // Ajustado a 4 columnas
        table.setWidthPercentage(100);
        table.addCell(new PdfPCell(new Phrase("ID", FontFactory.getFont("Arial", 12))));
        table.addCell(new PdfPCell(new Phrase("Tipo", FontFactory.getFont("Arial", 12))));
        table.addCell(new PdfPCell(new Phrase("Precio", FontFactory.getFont("Arial", 12))));
        table.addCell(new PdfPCell(new Phrase("Duración", FontFactory.getFont("Arial", 12))));

        for (Suscripcion suscripcion : suscripciones) {
            table.addCell(new PdfPCell(new Phrase(String.valueOf(suscripcion.getId()), FontFactory.getFont("Arial", 12))));
            table.addCell(new PdfPCell(new Phrase(suscripcion.getTipo(), FontFactory.getFont("Arial", 12))));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(suscripcion.getPrecio()), FontFactory.getFont("Arial", 12))));
            table.addCell(new PdfPCell(new Phrase(suscripcion.getDuracion(), FontFactory.getFont("Arial", 12))));
        }

        document.add(table);
        document.close();
        return baos.toByteArray();
    }
}