package co.edu.uniquindio.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public abstract class Persona {
    private String direccion,nombre,residencia;
    private Ubicacion ubicacion;

}
