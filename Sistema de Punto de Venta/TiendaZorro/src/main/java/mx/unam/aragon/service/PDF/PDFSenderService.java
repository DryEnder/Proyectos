package mx.unam.aragon.service.PDF;

import java.io.*;
import java.util.List;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import mx.unam.aragon.model.entity.ClienteEntity;
import mx.unam.aragon.model.entity.DetalleVentaEntity;
import mx.unam.aragon.model.entity.VentaEntity;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class PDFSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public static ByteArrayInputStream exportar(VentaEntity venta, List<DetalleVentaEntity> detalleVentas) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        final String ruta = "/home/mikasa/Imágenes/Spring/";

        try {

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(80);
            table.setWidths(new int[] { 4, 4, 4 ,4});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            Paragraph paraTotal = new Paragraph("Total: $"+venta.getTotal(),headFont);

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("imagen", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Articulo", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Cantidad", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Precio", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            for (DetalleVentaEntity detalleVenta : detalleVentas) {

                PdfPCell cell;

                Image image = Image.getInstance(ruta + detalleVenta.getArticulo().getUrlFoto());
                image.scaleToFit(70f, 70f);
                cell = new PdfPCell(image, true);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(detalleVenta.getArticulo().getNombre()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(detalleVenta.getCantidad())));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(detalleVenta.getPrecio())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPaddingRight(5);
                table.addCell(cell);
            }

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(table);
            document.add(paraTotal);

            document.close();

        } catch (DocumentException ex) {

        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    public void sendMessageWithAttachment(ByteArrayInputStream iss, VentaEntity venta) throws MessagingException, IOException {

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("porkysanchez801@gmail.com");
        helper.setTo(venta.getCliente().getEmail());
        helper.setSubject("Muchas Gracias Por Su Compra "+ venta.getCliente().getNombre());
        helper.setText("A continuacion le enviamos su recibo por correo en formato PDF.");

        helper.addAttachment("venta_"+venta.getCliente().getNombre()+"_"+venta.getNumComprobante()+".pdf",
                new ByteArrayResource(IOUtils.toByteArray(iss)));

        //ByteArrayResource resource = new ByteArrayResource(excelFileAsBytes);
        //helper.addAttachment("reporte.pdf", resource);

        mailSender.send(message);
        // ...
    }
}