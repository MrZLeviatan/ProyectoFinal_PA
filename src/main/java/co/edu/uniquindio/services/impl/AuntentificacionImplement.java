package co.edu.uniquindio.services.impl;

import co.edu.uniquindio.Security.JWTUtils;
import co.edu.uniquindio.dto.LoginDto;
import co.edu.uniquindio.exceptions.CredencialesInvalidasException;
import co.edu.uniquindio.exceptions.ElementoNoEncontradoException;
import co.edu.uniquindio.exceptions.UsuarioNoActivadoException;
import co.edu.uniquindio.dto.TokenDTO;
import co.edu.uniquindio.model.documentos.Usuario;
import co.edu.uniquindio.model.enums.EstadoUsuario;
import co.edu.uniquindio.repositorios.UsuarioRepo;
import co.edu.uniquindio.services.AutentificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del servicio de autenticación.
 * Proporciona la lógica para iniciar sesión mediante el uso de un DTO de login y un repositorio de usuarios.
 * Verifica la existencia y estado del usuario, y genera un token JWT si las credenciales son válidas.
 */
@Service
@RequiredArgsConstructor
public class AuntentificacionImplement implements AutentificacionService {

    @Autowired
    private UsuarioRepo usuarioRepo;
    private final JWTUtils jwtUtils;

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
        Optional<Usuario> usuarioOptional = usuarioRepo.findByEmail(loginDTO.email());
        if (usuarioOptional.isEmpty()) { // Si el usuario no existe
            throw new ElementoNoEncontradoException("El email no existe");
        }

        Usuario usuario = usuarioOptional.get();


        if (usuario.getPassword().equals(loginDTO.password())) {
            if (usuario.getEstadoUsuario() == EstadoUsuario.ACTIVO) {
                String token = jwtUtils.generateToken(usuario.getId().toHexString(), crearClaims(usuario));
                return new TokenDTO(token);
            } else {
                throw new UsuarioNoActivadoException("El usuario debe activarse primero");
            }
        } else {
            throw new CredencialesInvalidasException("El usuario no pudo ingresar");
        }
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
                "rol", "ROLE_" + usuario.getRol().name()
        );
    }
}
