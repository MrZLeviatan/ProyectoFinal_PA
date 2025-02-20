package co.edu.uniquindio.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public abstract class Persona {
    private String  apellido,direccion,nombre,residencia,segundoNombre,segundoApellido;
    private Ubicacion ubicacion;

}
