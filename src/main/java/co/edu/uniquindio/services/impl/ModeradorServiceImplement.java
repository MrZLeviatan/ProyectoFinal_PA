package co.edu.uniquindio.services.impl;

import co.edu.uniquindio.dto.EliminarCuentaDto;
import co.edu.uniquindio.dto.moderador.EditarModeradorDto;
import co.edu.uniquindio.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.exeptions.ElementoNoEncontradoException;
import co.edu.uniquindio.mapper.UsuarioMapper;
import co.edu.uniquindio.model.documentos.Usuario;
import co.edu.uniquindio.model.enums.Ciudad;
import co.edu.uniquindio.model.enums.EstadoUsuario;
import co.edu.uniquindio.repositorios.UsuarioRepo;
import co.edu.uniquindio.services.ModeradorService;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;

import org.springframework.stereotype.Service;
import  org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ModeradorServiceImplement implements ModeradorService {

    UsuarioRepo usuarioRepo;
    UsuarioMapper usuarioMapper;
    @Override
    public void eliminarModerador(EliminarCuentaDto cuentaDto) throws Exception {
        Usuario usuario= buscarUsuarioId(cuentaDto.id());
        usuario.setEstadoUsuario(EstadoUsuario.ELIMINADO);
        usuarioRepo.save(usuario);
    }

    private Usuario buscarUsuarioId(@NotBlank String id) {
        Optional<Usuario> usuario= usuarioRepo.findById(new ObjectId(id));
        if(usuario.isPresent()){
            return usuario.get();
        }else {
            throw new ElementoNoEncontradoException("El administrador no existe");
        }
    }

    @Override
    public void actualizarModerador(EditarModeradorDto moderadorAct) throws Exception {
        Usuario usuario= buscarUsuarioId(moderadorAct.id());
        usuario.setNombre(moderadorAct.nombre());
        usuario.setCiudad(moderadorAct.ciudad());
        usuario.setDireccion(moderadorAct.direccion());
        usuarioRepo.save(usuario);
    }

    @Override
    public UsuarioDTO obtenerModeradorId(String id) throws Exception {
        Usuario usuario= buscarUsuarioId(id);
        return usuarioMapper.toUsuarioDTO(usuario);
    }

    @Override
    public UsuarioDTO obtenerModeradorEmail(String email) throws Exception {
        Usuario usuario= buscarUsuarioEmail(email);
        return usuarioMapper.toUsuarioDTO(usuario);
    }



    @Override
    public List<UsuarioDTO> listarUsuarios(String nombre, String ciudad, int pagina, int size) throws Exception {
        Pageable pageable = PageRequest.of(pagina, size);
        Ciudad ciudadEnum=Ciudad.valueOf(ciudad);
        List<Usuario> usuarios= usuarioRepo.findByNombreYCiudad(nombre,ciudadEnum,pageable);
        return usuarioMapper.toUsuarioDTO(usuarios);
    }



    private Usuario buscarUsuarioEmail(String email) {
        Optional<Usuario> usuario = usuarioRepo.findByEmail(email);
        if(usuario.isPresent()){
            return usuario.get();
        }else {
            throw new ElementoNoEncontradoException("El administrador no existe");
        }
    }
}
