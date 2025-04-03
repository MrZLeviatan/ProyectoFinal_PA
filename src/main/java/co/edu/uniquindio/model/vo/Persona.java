package co.edu.uniquindio.model.vo;

import co.edu.uniquindio.model.enums.Ciudad;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
// Clase padre SIN @Document (NO se guarda directamente en MongoDB)
public abstract class Persona {
    private String nombre;
    private String direccion;
    private Ciudad ciudad;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
}
