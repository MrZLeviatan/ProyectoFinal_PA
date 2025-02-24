package co.edu.uniquindio.dto.administrador;

import co.edu.uniquindio.model.enums.EstadoUsuario;

public record ActualizarCuentaModeradorDto(
        String id,
        String nombre,
        String direccion,
        EstadoUsuario estadoUsuario,
        String contrasena
) {
}
