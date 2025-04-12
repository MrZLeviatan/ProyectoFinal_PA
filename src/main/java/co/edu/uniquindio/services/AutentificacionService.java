package co.edu.uniquindio.services;

import co.edu.uniquindio.dto.LoginDto;
import co.edu.uniquindio.dto.TokenDTO;

public interface AutentificacionService {

    /**
     * Inicia sesión en el sistema utilizando las credenciales proporcionadas.
     * @param loginDTO: Objeto que contiene el correo electrónico y la contraseña del usuario.
     * @return Objeto TokenDTO que contiene el token de autenticación generado si las credenciales son válidas.
     * @throws Exception: Si las credenciales son incorrectas o ocurre un error durante el proceso de autenticación.
     */
    TokenDTO iniciarSesion(LoginDto loginDTO) throws Exception;

}
