package co.edu.uniquindio.services.impl;

import co.edu.uniquindio.dto.EliminarCuentaDto;
import co.edu.uniquindio.dto.moderador.EditarModeradorDto;
import co.edu.uniquindio.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.exeptions.CiudadNoExisteException;
import co.edu.uniquindio.exeptions.ElementoNoEncontradoException;
import co.edu.uniquindio.exeptions.RangoPaginaNoPermitido;
import co.edu.uniquindio.mapper.UsuarioMapper;
import co.edu.uniquindio.model.documentos.Usuario;
import co.edu.uniquindio.model.enums.Ciudad;
import co.edu.uniquindio.model.enums.EstadoUsuario;
import co.edu.uniquindio.repositorios.UsuarioRepo;
import co.edu.uniquindio.services.ModeradorService;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import  org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ModeradorServiceImplement implements ModeradorService {

    @Autowired
    UsuarioRepo usuarioRepo;
    @Autowired
    UsuarioMapper usuarioMapper;


    @Override
    public void eliminarModerador(EliminarCuentaDto cuentaDto) throws ElementoNoEncontradoException {
        Usuario usuario= buscarUsuarioId(cuentaDto.id());
        usuario.setEstadoUsuario(EstadoUsuario.ELIMINADO);
        usuarioRepo.save(usuario);
    }

    private Usuario buscarUsuarioId(@NotBlank String id) {
        try {
            Optional<Usuario> usuario= usuarioRepo.findById(new ObjectId(id));
            if(usuario.isPresent()){
                return usuario.get();
            }else {
                throw new ElementoNoEncontradoException("El administrador no existe");
            }
        }catch (IllegalArgumentException e) {
            throw new ElementoNoEncontradoException("no existe el administrador con el id " + id);
        }


    }

    @Override
    public void actualizarModerador(EditarModeradorDto moderadorAct) throws ElementoNoEncontradoException {
        Usuario usuario= buscarUsuarioId(moderadorAct.id());
        usuario.setNombre(moderadorAct.nombre());
        usuario.setCiudad(moderadorAct.ciudad());
        usuario.setDireccion(moderadorAct.direccion());
        usuarioRepo.save(usuario);
    }

    @Override
    public UsuarioDTO obtenerModeradorId(String id) throws ElementoNoEncontradoException {
        Usuario usuario= buscarUsuarioId(id);
        return usuarioMapper.toUsuarioDTO(usuario);
    }

    @Override
    public UsuarioDTO obtenerModeradorEmail(String email) throws ElementoNoEncontradoException {
        Usuario usuario= buscarUsuarioEmail(email);
        return usuarioMapper.toUsuarioDTO(usuario);
    }



    @Override
    public List<UsuarioDTO> listarUsuarios(String nombre, String ciudad, int pagina, int size) throws RangoPaginaNoPermitido,CiudadNoExisteException {
        if (pagina < 0 || size <= 0) {
            throw new RangoPaginaNoPermitido("Página y tamaño deben ser mayores que 0");
        }

        Ciudad ciudadEnum;
        try {
            ciudadEnum = Ciudad.valueOf(ciudad.toUpperCase()); // en caso de que ciudad llegue en minúsculas
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new CiudadNoExisteException("Ciudad inválida: " + ciudad);
        }
        Pageable pageable = PageRequest.of(pagina, size);

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
