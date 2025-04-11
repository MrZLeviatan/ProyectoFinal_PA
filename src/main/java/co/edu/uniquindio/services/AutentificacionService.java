package co.edu.uniquindio.services;

import co.edu.uniquindio.dto.LoginDto;
import co.edu.uniquindio.dto.RestablecerPasswordDto;
import co.edu.uniquindio.dto.usuario.ActivarCuentaDto;
import co.edu.uniquindio.dto.usuario.RegistrarUsuarioDto;
import co.edu.uniquindio.exeptions.UsuarioException;
import jakarta.validation.Valid;

public interface AutentificacionService {
    void iniciarSesion(LoginDto loginDTO) throws Exception;

    void crearUsuario(RegistrarUsuarioDto usuarioDTO) throws UsuarioException;

    void restablecerPassword(RestablecerPasswordDto restablecerPasswordDto) throws UsuarioException;

    void solicitarRestablecer(@Valid String email) throws Exception;

    void activarCuenta(ActivarCuentaDto activarCuentaDto) throws Exception;
}

