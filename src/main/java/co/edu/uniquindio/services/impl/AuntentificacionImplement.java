package co.edu.uniquindio.services.impl;

import co.edu.uniquindio.dto.LoginDto;
import co.edu.uniquindio.exeptions.CredencialesInvalidasException;
import co.edu.uniquindio.exeptions.ElementoNoEncontradoException;
import co.edu.uniquindio.exeptions.UsuarioNoActivadoException;
import co.edu.uniquindio.model.documentos.Usuario;
import co.edu.uniquindio.model.enums.EstadoUsuario;
import co.edu.uniquindio.repositorios.UsuarioRepo;
import co.edu.uniquindio.services.AutentificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuntentificacionImplement implements AutentificacionService {

    @Autowired
    UsuarioRepo usuarioRepo;

    @Override
    public void iniciarSesion(LoginDto loginDTO) throws ElementoNoEncontradoException,UsuarioNoActivadoException,CredencialesInvalidasException {
        Optional<Usuario> usuarioOptional= usuarioRepo.findByEmail(loginDTO.email());
        if(usuarioOptional.isEmpty()){
            throw new ElementoNoEncontradoException("El email no existe");
        }
        Usuario usuario = usuarioOptional.get();
        if(usuario.getPassword().equals(loginDTO.password())){
            if(usuario.getEstadoUsuario() == EstadoUsuario.ACTIVO){
              // se logea
            }else {
                throw new UsuarioNoActivadoException("El usuario debe activarse primero ");
            }
        }else {
            //tenia contraseña incorrecta pero en clase se dijo que no se debia dar tanta informacion
            throw new CredencialesInvalidasException("el usuario no puedo ingresar");
        }
    }
}
