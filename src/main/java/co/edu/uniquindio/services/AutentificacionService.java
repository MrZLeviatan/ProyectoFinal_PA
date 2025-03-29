package co.edu.uniquindio.services;

import co.edu.uniquindio.dto.LoginDto;
import co.edu.uniquindio.dto.RestablecerPasswordDto;
import co.edu.uniquindio.dto.usuario.ActivarCuentaDto;
import co.edu.uniquindio.dto.usuario.UsuarioDTO;
import jakarta.validation.Valid;

public interface AutentificacionService {
    void iniciarSesion(LoginDto loginDTO) throws Exception;

    void crearUsuario(UsuarioDTO usuarioDTO);

    void restablecerPassword(RestablecerPasswordDto restablecerPasswordDto);

    void solicitarRestablecer(@Valid String email);

    void activarCuenta(ActivarCuentaDto activarCuentaDto) throws Exception;
}
