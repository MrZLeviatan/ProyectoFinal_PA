package co.edu.uniquindio.mapper;

import co.edu.uniquindio.dto.usuario.RegistrarUsuarioDto;
import co.edu.uniquindio.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.model.documentos.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {NotificacionMapper.class, ReporteMapper.class, ObjectIdMapper.class})
public interface UsuarioMapper {

    // mapeo del usuario para registrarlo
    @Mapping(target = "rol", constant = "USUARIO")
    @Mapping(target = "estadoUsuario", constant = "INACTIVO")
    @Mapping(target = "fechaRegistro", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "notificaciones", expression = "java(new java.util.ArrayList<>())")
    @Mapping(target = "reportes", expression = "java(new java.util.ArrayList<>())")
    @Mapping(target = "listaReportesFavorito", expression = "java(new java.util.ArrayList<>())") // inicializamos las listas
     //le decimos que tranforme el dto con un metodo anteriormente creado
    @Mapping(target = "id", ignore = true) //ignoamos la id la crea mongo al guardarse
    Usuario toDocument(RegistrarUsuarioDto usuarioDTO);

    // usuario a usuario Dto
     UsuarioDTO toUsuarioDTO(Usuario usuario);

     List<UsuarioDTO> toUsuarioDTO(List<Usuario> usuarios);
}
