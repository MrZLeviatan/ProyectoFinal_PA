package co.edu.uniquindio.services.impl;

import co.edu.uniquindio.dto.EmailDto;
import co.edu.uniquindio.services.EmailServicio;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class EmailServicioImp implements EmailServicio {

    @Value("${SMTP_HOST}")
    private String host;

    @Value("${SMTP_PORT}")
    private String port;

    @Value("${SMTP_USER}")
    private String user;

    @Value("${SMTP_PASSWORD}")
    private String password;

    @Override
    @Async
    public void enviarCorreo(EmailDto emailDto) throws Exception {
        Email email = EmailBuilder.startingBlank()
                .from(user)
                .to(emailDto.destinatario())
                .withSubject(emailDto.asunto())
                .withPlainText(emailDto.cuerpo())
                .buildEmail();


        try (Mailer mailer = MailerBuilder
                    .withSMTPServer(host, Integer.parseInt(port),user,password)
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withDebugLogging(true)
                .buildMailer()) {
            mailer.sendMail(email);
        }


    }
}
