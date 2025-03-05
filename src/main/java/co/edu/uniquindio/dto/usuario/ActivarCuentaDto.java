package co.edu.uniquindio.dto.usuario;

public record ActivarCuentaDto(

        String email,//  correo perteneciente al usuario a activar

        String codigo //codigo ingresado por el cliente
) {

}
