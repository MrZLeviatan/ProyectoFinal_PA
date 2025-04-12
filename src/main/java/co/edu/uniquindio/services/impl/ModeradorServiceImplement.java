package co.edu.uniquindio.services.impl;

import co.edu.uniquindio.constants.MensajesError;
import co.edu.uniquindio.dto.EliminarCuentaDto;
import co.edu.uniquindio.dto.moderador.EditarModeradorDto;
import co.edu.uniquindio.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.exceptions.CiudadNoExisteException;
import co.edu.uniquindio.exceptions.CredencialesInvalidasException;
import co.edu.uniquindio.exceptions.ElementoNoEncontradoException;
import co.edu.uniquindio.exceptions.RangoPaginaNoPermitidoException;
import co.edu.uniquindio.mapper.UsuarioMapper;
import co.edu.uniquindio.model.documentos.Usuario;
import co.edu.uniquindio.model.enums.Ciudad;
import co.edu.uniquindio.model.enums.EstadoUsuario;
import co.edu.uniquindio.repositorios.UsuarioRepo;
import co.edu.uniquindio.services.ModeradorService;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import  org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ModeradorServiceImplement implements ModeradorService {

    private final UsuarioRepo usuarioRepo;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void eliminarModerador(EliminarCuentaDto cuentaDto) throws ElementoNoEncontradoException {
        Usuario usuario= buscarUsuarioId(cuentaDto.id());
        if(!passwordEncoder.matches(cuentaDto.password(), usuario.getPassword())){
            throw new CredencialesInvalidasException(MensajesError.CREDENCIALES_INVALIDAS);
        }
        usuario.setEstadoUsuario(EstadoUsuario.ELIMINADO);
        usuarioRepo.save(usuario);
    }

    private Usuario buscarUsuarioId(@NotBlank String id) {
        return usuarioRepo.findById(new ObjectId(id))
                .orElseThrow(() -> new ElementoNoEncontradoException(MensajesError.USARIO_NO_ENCONTRADO));
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
    public List<UsuarioDTO> listarUsuarios(String nombre, String ciudad, int pagina, int size) {
        validarRangoPagina(pagina, size);
        Ciudad ciudadEnum = obtenerCiudadEnum(ciudad);
        Pageable pageable = PageRequest.of(pagina, size);
        List<Usuario> usuarios = usuarioRepo.findByNombreYCiudad(nombre, ciudadEnum, pageable);
        return usuarioMapper.toUsuarioDTO(usuarios);
    }

    private void validarRangoPagina(int pagina, int size) {
        if (pagina < 0 || size <= 0) {
            throw new RangoPaginaNoPermitidoException(MensajesError.VALORES_ERRONES_PAGE);
        }
    }

    private Ciudad obtenerCiudadEnum(String ciudad) {
        try {
            return Ciudad.valueOf(ciudad.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new CiudadNoExisteException(MensajesError.CIUDAD_NO_ENCONTRADA + ciudad);
        }
    }

    private Usuario buscarUsuarioEmail(String email) {
        return usuarioRepo.findByEmail(email)
                .orElseThrow(() -> new ElementoNoEncontradoException(MensajesError.USARIO_NO_ENCONTRADO + "con el email"+email));
    }


}
