package co.edu.uniquindio.services;

import co.edu.uniquindio.dto.EliminarCuentaDto;
import co.edu.uniquindio.dto.RestablecerPasswordDto;
import co.edu.uniquindio.dto.reporte.ReporteDTO;
import co.edu.uniquindio.dto.usuario.EditarUsuarioDto;
import co.edu.uniquindio.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.dto.usuario.*;
import jakarta.validation.Valid;
import co.edu.uniquindio.exceptions.ElementoRepetidoException;

import java.util.List;

// Interfaz que define el contrato para las operaciones relacionadas con usuarios.
public interface UsuarioService {

    /**
     * Elimina un usuario del sistema en función de su ID y contraseña.
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

    /**
     * Crea un nuevo usuario en el sistema.
     * @param usuarioDTO: Objeto de transferencia que contiene la información del nuevo usuario a registrar.
     * @throws ElementoRepetidoException: Si ya existe un usuario con el mismo correo electrónico o documento.
     */
    void crearUsuario(@Valid RegistrarUsuarioDto usuarioDTO) throws ElementoRepetidoException;

    /**
     * Inicia el proceso de restablecimiento de contraseña enviando un correo al usuario.
     * @param email: Correo electrónico del usuario que solicita restablecer la contraseña.
     * @throws Exception: Si el correo no está registrado o ocurre un error durante el proceso.
     */
    void solicitarRestablecer(@Valid String email) throws Exception;

    /**
     * Activa la cuenta del usuario validando el código enviado a su correo electrónico.
     * @param activarCuentaDto: Objeto que contiene la información necesaria para activar la cuenta.
     * @throws Exception: Si el código es inválido o ocurre un error durante la activación.
     */
    void activarCuenta(@Valid ActivarCuentaDto activarCuentaDto) throws Exception;

    List<ReporteDTO> obtenerReportesUsuario(@Valid String id);
}
