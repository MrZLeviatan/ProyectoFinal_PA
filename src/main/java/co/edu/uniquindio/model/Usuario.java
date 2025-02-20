package co.edu.uniquindio.model;

import co.edu.uniquindio.model.enums.Rol;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Usuario extends Persona{
    private String correo,contrasena,id;
    private Rol rol;
}
