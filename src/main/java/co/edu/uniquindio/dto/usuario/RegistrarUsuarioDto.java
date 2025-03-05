package co.edu.uniquindio.dto.usuario;

import co.edu.uniquindio.model.Ubicacion;
import co.edu.uniquindio.model.enums.EstadoUsuario;
import co.edu.uniquindio.model.enums.Rol;

public record RegistrarUsuarioDto(
        /**
         * todo los atributos que necesitan para crear un usuario
         */
        String id,
        String nombre,
        String direccion,
        EstadoUsuario estadoUsuario,
        Rol rol,
        String contrasena,
        String correo,

        Ubicacion ubicacion
) {

}
