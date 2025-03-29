package co.edu.uniquindio.model;

import co.edu.uniquindio.model.enums.Ciudad;
import lombok.*;

import java.lang.annotation.Documented;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// Clase padre SIN @Document (NO se guarda directamente en MongoDB)
public abstract class Persona {
    private String nombre;
    private String direccion;
    private Ciudad ciudad;

}
