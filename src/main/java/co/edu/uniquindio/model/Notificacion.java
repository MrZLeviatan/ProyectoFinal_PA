package co.edu.uniquindio.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Notificacion {
    private String id,idDestinatario,mensaje,fecha,idRemitente,titulo,ubicacionReporte,IDReporteAsociado;
}
