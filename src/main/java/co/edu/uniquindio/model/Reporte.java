package co.edu.uniquindio.model;

import co.edu.uniquindio.model.enums.EstadoReporte;
import co.edu.uniquindio.model.enums.IsEstadoResulto;
import co.edu.uniquindio.model.enums.Severidad;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reporte {
    private IsEstadoResulto estadoReporte;
    private Categoria categoria;
    private List<Comentario> comentarios;
    private EstadoReporte verificado; // admin

    private String idUsuario,id,titulo;
    private Ubicacion ubicacion;
    private List<HistorialEstado> historial;

    private String foto;
    private int numeroImportancia;

    private Severidad severidad;
}
