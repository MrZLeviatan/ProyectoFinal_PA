package co.edu.uniquindio.services;

import co.edu.uniquindio.dto.EliminarCuentaDto;
import co.edu.uniquindio.dto.LoginDto;
import co.edu.uniquindio.dto.RestablecerPasswordDto;
import co.edu.uniquindio.dto.TokenDTO;
import co.edu.uniquindio.dto.usuario.EditarUsuarioDto;
import co.edu.uniquindio.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.dto.usuario.*;
import co.edu.uniquindio.services.impl.LoginDTO;
import jakarta.validation.Valid;

// Interfaz que define el contrato para las operaciones relacionadas con usuarios.
public interface UsuarioService {


    TokenDTO login(LoginDTO loginDTO) throws Exception;


    /**
     *  Elimina un usuario del sistema en función de su ID y contraseña.
     * @param cuentaDto: Objeto de transferencia de datos que contiene el ID y la contraseña del usuario.
     * @throws Exception: Si ocurre un error durante la eliminación.
     */
    void eliminarUsuario(EliminarCuentaDto cuentaDto) throws Exception;

    /**
     * Actualiza la información del usuario en el sistema.
     * @param usuario: Objeto de transferencia de datos que contiene los detalles actualizados del usuario.
     * @throws Exception: Si ocurre un error durante el proceso de actualización.
     */
    void actualizarUsuario(EditarUsuarioDto usuario) throws Exception;

    /**
     * Obtiene un usuario basado en su identificador único.
     * @param id: Identificador único del usuario.
     * @return: Objeto que contiene los detalles del usuario.
     * @throws Exception: Si el usuario no se encuentra o ocurre un error.
     */
    UsuarioDTO obtenerUsuarioId(String id) throws Exception;

    /**
     * Obtiene un usuario basado en su dirección de correo electrónico.
     * @param email: Dirección de correo electrónico del usuario.
     * @return: Objeto que contiene los detalles del usuario.
     * @throws Exception: Si el usuario no se encuentra o ocurre un error.
     */
    UsuarioDTO obtenerUsuarioEmail(String email) throws Exception;


    /**
     * Restablece la contraseña del usuario según la información proporcionada.
     * @param restablecerPasswordDto: Objeto de transferencia de datos que contiene la información necesaria para restablecer la contraseña.
     * @throws Exception: Si ocurre un error durante el proceso de restablecimiento de contraseña.
     */
    void restablecerPassword(RestablecerPasswordDto restablecerPasswordDto) throws Exception;


    void crearUsuario(@Valid RegistrarUsuarioDto usuarioDTO);

    void solicitarRestablecer(@Valid String email);

    void activarCuenta(@Valid ActivarCuentaDto activarCuentaDto);
}
