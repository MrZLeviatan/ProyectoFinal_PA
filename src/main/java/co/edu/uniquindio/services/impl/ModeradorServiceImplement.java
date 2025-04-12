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


/**
 * Implementación del servicio del Moderador.
 * Proporciona la lógica para la gestión de los
 * Moderadores y consulta de usuarios
 */
@Service
@RequiredArgsConstructor
public class ModeradorServiceImplement implements ModeradorService {

    private final UsuarioRepo usuarioRepo;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    /**
     * Elimina un moderador cambiando su estado a 'ELIMINADO' en lugar de borrarlo físicamente.
     * @param cuentaDto Objeto DTO que contiene la información del moderador a eliminar
     * @throws ElementoNoEncontradoException Si no se encuentra el moderador con el ID especificado
     */
    @Override
    public void eliminarModerador(EliminarCuentaDto cuentaDto) throws ElementoNoEncontradoException {
        Usuario usuario= buscarUsuarioId(cuentaDto.id());
        if(!passwordEncoder.matches(cuentaDto.password(), usuario.getPassword())){
            throw new CredencialesInvalidasException(MensajesError.CREDENCIALES_INVALIDAS);
        }
        usuario.setEstadoUsuario(EstadoUsuario.ELIMINADO);
        usuarioRepo.save(usuario);
    }

    /**
     * Busca un moderador en la base de datos por su ID.
     * @param id ID del moderador
     * @return Usuario encontrado
     * @throws ElementoNoEncontradoException Si el moderador no existe
     */
    private Usuario buscarUsuarioId(@NotBlank String id) {
        return usuarioRepo.findById(new ObjectId(id))
                .orElseThrow(() -> new ElementoNoEncontradoException(MensajesError.USARIO_NO_ENCONTRADO));
    }

    /**
     * Actualiza la información de un moderador (nombre, ciudad, dirección).
     * @param moderadorAct DTO con los datos a actualizar
     * @throws ElementoNoEncontradoException Si el moderador no existe
     */
    @Override
    public void actualizarModerador(EditarModeradorDto moderadorAct) throws ElementoNoEncontradoException {
        Usuario usuario= buscarUsuarioId(moderadorAct.id());
        usuario.setNombre(moderadorAct.nombre());
        usuario.setCiudad(moderadorAct.ciudad());
        usuario.setDireccion(moderadorAct.direccion());
        usuarioRepo.save(usuario);
    }

    /**
     * Obtiene los detalles de un moderador a través de su ID.
     * @param id ID del moderador
     * @return DTO del moderador
     * @throws ElementoNoEncontradoException Si el moderador no existe
     */
    @Override
    public UsuarioDTO obtenerModeradorId(String id) throws ElementoNoEncontradoException {
        Usuario usuario= buscarUsuarioId(id);
        return usuarioMapper.toUsuarioDTO(usuario);
    }

    /**
     * Obtiene los detalles de un moderador a través de su email.
     * @param email Email del moderador
     * @return DTO del moderador
     * @throws ElementoNoEncontradoException Si el moderador no existe
     */
    @Override
    public UsuarioDTO obtenerModeradorEmail(String email) throws ElementoNoEncontradoException {
        Usuario usuario= buscarUsuarioEmail(email);
        return usuarioMapper.toUsuarioDTO(usuario);
    }

    /**
     * Lista los usuarios filtrados por nombre y ciudad. La búsqueda es paginada.
     * @param nombre Nombre del moderador
     * @param ciudad Ciudad del moderador
     * @param pagina Número de página para paginación
     * @param size Tamaño de la página para paginación
     * @return Lista de DTOs de usuarios
     * @throws RangoPaginaNoPermitidoException Si la página o el tamaño son inválidos
     * @throws CiudadNoExisteException Si la ciudad proporcionada no es válida
     */
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


    /**
     * Busca un moderador en la base de datos por su email.
     * @param email Email del moderador
     * @return Usuario encontrado
     * @throws ElementoNoEncontradoException Si el moderador no existe
     */
    private Usuario buscarUsuarioEmail(String email) {
        return usuarioRepo.findByEmail(email)
                .orElseThrow(() -> new ElementoNoEncontradoException(MensajesError.USARIO_NO_ENCONTRADO + "con el email"+email));
    }


}
