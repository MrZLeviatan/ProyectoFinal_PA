package co.edu.uniquindio.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public abstract class Persona {
    private String direccion,nombre,ciudad;
    private Ubicacion ubicacion;

}
