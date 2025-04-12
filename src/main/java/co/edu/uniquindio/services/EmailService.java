package co.edu.uniquindio.services;

import co.edu.uniquindio.dto.EmailDto;

/**
 * Interfaz para el servicio de envío de correos electrónicos.
 * Define el método para enviar un correo utilizando los datos proporcionados.
 */
public interface EmailService {

    /**
     * Envía un correo electrónico utilizando la información proporcionada en el DTO.
     *
     * @param email DTO que contiene la información del correo (destinatario, asunto, cuerpo, etc.).
     * @throws Exception Si ocurre un error durante el proceso de envío del correo.
     */
    void enviarCorreo(EmailDto email) throws Exception;
}

