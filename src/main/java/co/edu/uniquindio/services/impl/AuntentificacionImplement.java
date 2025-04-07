package co.edu.uniquindio.services.impl;

import co.edu.uniquindio.dto.EmailDto;
import co.edu.uniquindio.dto.LoginDto;
import co.edu.uniquindio.dto.RestablecerPasswordDto;
import co.edu.uniquindio.dto.usuario.ActivarCuentaDto;
import co.edu.uniquindio.dto.usuario.RegistrarUsuarioDto;
import co.edu.uniquindio.exeptions.UsuarioException;
import co.edu.uniquindio.mapper.UsuarioMapper;
import co.edu.uniquindio.model.vo.CodigoValidacion;
import co.edu.uniquindio.model.documentos.Usuario;
import co.edu.uniquindio.model.enums.EstadoUsuario;
import co.edu.uniquindio.model.enums.Rol;
import co.edu.uniquindio.repositorios.UsuarioRepo;
import co.edu.uniquindio.services.AutentificacionService;
import co.edu.uniquindio.services.EmailServicio;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuntentificacionImplement implements AutentificacionService {

    @Autowired
    UsuarioRepo usuarioRepo;

    @Override
    public void iniciarSesion(LoginDto loginDTO) throws Exception {
        Optional<Usuario> usuarioOptional= usuarioRepo.findByEmail(loginDTO.email());
        if(usuarioOptional.isEmpty()){
            throw new UsuarioException("El email no existe");
        }
        Usuario usuario = usuarioOptional.get();
        if(usuario.getPassword().equals(loginDTO.password())){
            if(usuario.getEstadoUsuario() == EstadoUsuario.ACTIVO){
              // se logea
            }else {
                throw new UsuarioException("El usuario debe activarse primero ");
            }
        }else {
            throw new UsuarioException("Contraseña incorrecta");
        }
    }
}
