package co.edu.uniquindio.dto.usuario;

import co.edu.uniquindio.model.enums.EstadoUsuario;
import co.edu.uniquindio.model.enums.Rol;

public record ActualizarUsuarioDto(
        /**
         * todo los atributos que necesitan actualizarse
         */
        String id,
        String nombre,
        String direccion,
        EstadoUsuario estadoUsuario,
        Rol rol,
        String contrasena,
        String correo
) {
}
