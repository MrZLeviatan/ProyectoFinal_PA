package co.edu.uniquindio;

import co.edu.uniquindio.dto.EmailDto;
import co.edu.uniquindio.services.EmailServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailImplementTest {

    @Autowired
    private EmailServicio emailServicio;

    @Test
    void enviarCorreo() throws Exception {
        String asunto="correoEnviado";
        String cuerpo="cuerpoEnviado";
        //cambiar a un correo real donde enviar los mensajes
        //recordar revisar el spam
        String destinatario="andrey3681.ay@gmail.com";
        emailServicio.enviarCorreo(new EmailDto(asunto,cuerpo,destinatario));
    }
}
