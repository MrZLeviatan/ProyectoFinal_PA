package co.edu.uniquindio.services;

import co.edu.uniquindio.dto.EmailDto;

public interface EmailServicio {


    void enviarCorreo(EmailDto email) throws Exception;
}
