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
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuntentificacionImplement implements AutentificacionService {

    private final UsuarioRepo usuarioRepo;
    private final JWTUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;


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

    private Map<String, String> crearClaims(Usuario usuario) {
        return Map.of(
                "email", usuario.getEmail(),
                "nombre", usuario.getNombre(),
                "rol", "ROLE_" + usuario.getRol().name()
        );
    }

}
