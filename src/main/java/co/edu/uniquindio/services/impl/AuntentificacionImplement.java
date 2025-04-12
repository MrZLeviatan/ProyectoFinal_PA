package co.edu.uniquindio.services.impl;

import co.edu.uniquindio.Security.JWTUtils;
import co.edu.uniquindio.dto.LoginDto;
import co.edu.uniquindio.exeptions.CredencialesInvalidasException;
import co.edu.uniquindio.exeptions.ElementoNoEncontradoException;
import co.edu.uniquindio.exeptions.UsuarioNoActivadoException;
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

@Service
@RequiredArgsConstructor
public class AuntentificacionImplement implements AutentificacionService {

    private final UsuarioRepo usuarioRepo;

    private final JWTUtils jwtUtils;

    @Override
    public TokenDTO iniciarSesion(LoginDto loginDTO) throws ElementoNoEncontradoException,UsuarioNoActivadoException,CredencialesInvalidasException {
        Optional<Usuario> usuarioOptional= usuarioRepo.findByEmail(loginDTO.email());
        if(usuarioOptional.isEmpty()){
            throw new ElementoNoEncontradoException("El email no existe");
        }
        Usuario usuario = usuarioOptional.get();
        if(usuario.getPassword().equals(loginDTO.password())){
            if(usuario.getEstadoUsuario() == EstadoUsuario.ACTIVO){
              String toquen=  jwtUtils.generateToken(usuarioOptional.get().getId().toHexString(),crearClaims(usuarioOptional.get()));
              return new TokenDTO(toquen);
            }else {
                throw new UsuarioNoActivadoException("El usuario debe activarse primero ");
            }
        }else {
            throw new CredencialesInvalidasException("el usuario no puedo ingresar");
        }
    }



    private Map<String, String> crearClaims(Usuario usuario){
        return Map.of(
                "email", usuario.getEmail(),
                "nombre", usuario.getNombre(),
                "rol", "ROLE_"+usuario.getRol().name()
        );
    }


}
