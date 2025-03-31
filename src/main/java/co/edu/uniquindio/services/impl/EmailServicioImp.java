package co.edu.uniquindio.services.impl;

import co.edu.uniquindio.dto.EmailDto;
import co.edu.uniquindio.services.EmailServicio;
import co.edu.uniquindio.utils.Config;
import lombok.RequiredArgsConstructor;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class EmailServicioImp implements EmailServicio {

    Config config=new Config();
    @Override
    @Async
    public void enviarCorreo(EmailDto emailDto) throws Exception {
        Email email = EmailBuilder.startingBlank()
                .from("prgmonavanzada@gmail.com")
                .to(emailDto.destinatario())
                .withSubject(emailDto.asunto())
                .withPlainText(emailDto.cuerpo())
                .buildEmail();


        try (Mailer mailer = MailerBuilder
                    .withSMTPServer(config.SMTP_HOST(), Integer.parseInt(config.SMTP_PORT()), config.SMTP_USER(), config.SMTP_PASSWORD())
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withDebugLogging(true)
                .buildMailer()) {
            mailer.sendMail(email);
        }


    }
}
