package co.edu.uniquindio.services;

import co.edu.uniquindio.dto.LoginDto;
import co.edu.uniquindio.dto.TokenDTO;


public interface AutentificacionService {
    TokenDTO iniciarSesion(LoginDto loginDTO) throws Exception;

}
