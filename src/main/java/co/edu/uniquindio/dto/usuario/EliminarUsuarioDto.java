package co.edu.uniquindio.dto.usuario;

public record EliminarUsuarioDto(


        String id,//atributo para identiicar el usuario
        String contrasena //contraseña para determinar si es el usuario el que realiza la accion
) {
}
