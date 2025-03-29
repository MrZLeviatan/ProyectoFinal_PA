package co.edu.uniquindio.services;

import co.edu.uniquindio.dto.EliminarCuentaDto;
import co.edu.uniquindio.dto.LoginDto;
import co.edu.uniquindio.dto.RestablecerPasswordDto;
import co.edu.uniquindio.dto.usuario.EditarUsuarioDto;
import co.edu.uniquindio.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.dto.usuario.*;

// Interfaz que define el contrato para las operaciones relacionadas con usuarios.
public interface UsuarioInterface {

    /**
     * Registra un nuevo usuario en el sistema.
     * @param usuario: Objeto de transferencia de datos que contiene los detalles del registro de usuario.
     * @throws Exception: Si ocurre un error durante el registro.
     */
    void registrarUsuario(RegistrarUsuarioDto usuario) throws Exception;

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
     * Envía un correo con instrucciones para restablecer la contraseña.
     * @param email: correo del usuario.
     * @throws Exception: Si el email no está registrado o ocurre un error.
     */
    void solicitudRecuperarPassword (String email) throws Exception;


    /**
     * Auténtica a un usuario en el sistema basado en sus credenciales.
     * @param loginDto: Objeto de transferencia de datos que contiene las credenciales de inicio de sesión del usuario.
     * @throws Exception: Si ocurre un error de autenticación.
     */
    void loginUsuario(LoginDto loginDto) throws Exception;


    /**
     * Restablece la contraseña del usuario según la información proporcionada.
     * @param restablecerPasswordDto: Objeto de transferencia de datos que contiene la información necesaria para restablecer la contraseña.
     * @throws Exception: Si ocurre un error durante el proceso de restablecimiento de contraseña.
     */
    void restablecerPassword(RestablecerPasswordDto restablecerPasswordDto) throws Exception;

    /**
     * Activa la cuenta de un usuario en el sistema.
     * @param activarCuentaDto Datos necesarios para la activación de la cuenta.
     * @throws Exception Sí ocurre un error durante la activación.
     */
    void activarCuenta(ActivarCuentaDto activarCuentaDto) throws Exception;
}
