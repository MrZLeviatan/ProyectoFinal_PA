package co.edu.uniquindio.mapper;

import co.edu.uniquindio.dto.usuario.EditarUsuarioDto;
import co.edu.uniquindio.dto.usuario.RegistrarUsuarioDto;
import co.edu.uniquindio.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "rol", constant = "USUARIO")
    @Mapping(target = "estado", constant = "INACTIVO")
    Usuario toDocument(RegistrarUsuarioDto usuarioDTO);

    UsuarioDTO toDTO(Usuario usuario);

    void toDocument(EditarUsuarioDto editarUsuarioDTO, @MappingTarget Usuario usuario);



}
