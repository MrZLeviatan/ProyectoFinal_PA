package co.edu.uniquindio.services;

import co.edu.uniquindio.dto.LoginDto;
import co.edu.uniquindio.dto.TokenDTO;
import co.edu.uniquindio.exceptions.CredencialesInvalidasException;
import co.edu.uniquindio.exceptions.ElementoNoEncontradoException;
import co.edu.uniquindio.exceptions.UsuarioNoActivadoException;


public interface AutentificacionService {
    TokenDTO iniciarSesion(LoginDto loginDTO) throws ElementoNoEncontradoException, UsuarioNoActivadoException,
            CredencialesInvalidasException;

}
