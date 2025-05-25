package co.edu.uniquindio.services.impl;

import co.edu.uniquindio.Security.JWTUtils;
import co.edu.uniquindio.constants.MensajesError;
import co.edu.uniquindio.dto.LoginDto;
import co.edu.uniquindio.exceptions.CredencialesInvalidasException;
import co.edu.uniquindio.exceptions.ElementoNoEncontradoException;
import co.edu.uniquindio.exceptions.UsuarioNoActivadoException;
import co.edu.uniquindio.dto.TokenDTO;
import co.edu.uniquindio.model.documentos.Usuario;
import co.edu.uniquindio.model.enums.EstadoUsuario;
import co.edu.uniquindio.repositorios.UsuarioRepo;
import co.edu.uniquindio.services.AutentificacionService;
import co.edu.uniquindio.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * Implementación del servicio de autenticación.
 * Proporciona la lógica para iniciar sesión mediante el uso de un DTO de login y un repositorio de usuarios.
 * Verifica la existencia y estado del usuario, y genera un token JWT si las credenciales son válidas.
 */
@Service
@RequiredArgsConstructor
public class AuntentificacionImplement implements AutentificacionService {

    private final UsuarioRepo usuarioRepo;
    private final JWTUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    /**
     * Inicia sesión verificando las credenciales del usuario y generando un token JWT si son válidas.
     *
     * @param loginDTO Objeto que contiene el email y la contraseña del usuario.
     * @return TokenDTO Contiene el token JWT generado para el usuario.
     * @throws ElementoNoEncontradoException Si el email no corresponde a ningún usuario.
     * @throws UsuarioNoActivadoException Si el usuario no tiene estado ACTIVO.
     * @throws CredencialesInvalidasException Si las credenciales proporcionadas son incorrectas.
     */

    @Override
    public TokenDTO iniciarSesion(LoginDto loginDTO) throws ElementoNoEncontradoException, UsuarioNoActivadoException, CredencialesInvalidasException {

        Usuario usuario = usuarioRepo.findByEmail(loginDTO.email()).orElseThrow(() -> new ElementoNoEncontradoException(MensajesError.CORREO_NO_ENCONTRADO + loginDTO.email()));

        if (!passwordEncoder.matches(loginDTO.password(), usuario.getPassword())) {
            throw new CredencialesInvalidasException(MensajesError.CREDENCIALES_INVALIDAS);
        }

        if (usuario.getEstadoUsuario() != EstadoUsuario.ACTIVO) {
            throw new UsuarioNoActivadoException(MensajesError.USUARIO_NO_ACTIVADO);
        }

        String token = jwtUtils.generateToken(usuario.getId().toHexString(), crearClaims(usuario));
        return new TokenDTO(token);
    }

    /**
     * Crea los claims para el token JWT, que incluyen la información básica del usuario.
     *
     * @param usuario El usuario que va a ser autenticado.
     * @return Mapa con los claims a incluir en el token JWT.
     */
    
    private Map<String, String> crearClaims(Usuario usuario) {
        return Map.of(
                "email", usuario.getEmail(),
                "nombre", usuario.getNombre(),
                "rol", "ROLE_" + usuario.getRol().name(),
                "estado", "ESTADE_"+ usuario.getEstadoUsuario().name()
        );
    }
}
