package co.edu.uniquindio.services;

import co.edu.uniquindio.dto.LoginDto;

public interface AutentificacionService {
    void iniciarSesion(LoginDto loginDTO) throws Exception;
}
