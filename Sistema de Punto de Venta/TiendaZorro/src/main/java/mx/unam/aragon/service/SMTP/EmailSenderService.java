package mx.unam.aragon.service.SMTP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    private MailSender mailSender;

    public void correoProveedor(String to, String subject, String nomProveedor, String nomArticulo, int cantidad) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("porkysanchez801@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText("Buenas tardes " + nomProveedor + ", espero tenga buen dia, me comunico con ustedes porque necesitamos del siguiente articulo: " + nomArticulo+
                ", en nuestra sucursal ya se estan agotando y necesitamos la siguiente cantidad: " + cantidad);
        mailSender.send(message);
    }
}