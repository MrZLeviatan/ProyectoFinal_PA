package co.edu.uniquindio.model.vo;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Representa un código de validación utilizado en procesos de autenticación o verificación.
 * Contiene el código generado y la fecha y hora de su creación para su validación.
 */
@AllArgsConstructor
@NoArgsConstructor
public class CodigoValidacion {

    private String codigo;
    private LocalDateTime horaCreacion;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDateTime getHoraCreacion() {
        return horaCreacion;
    }

    public void setHoraCreacion(LocalDateTime horaCreacion) {
        this.horaCreacion = horaCreacion;
    }
}


