package co.edu.uniquindio.model;

import co.edu.uniquindio.model.enums.EstadoReporte;
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
    private EstadoReporte estadoReporte;
    private Categoria categoria;
    private List<Comentario> comentarios;
    private boolean isVerificado;
    private String idUsuario,idReporte,Titulo;
    private Ubicacion ubicacion;
    private List<HistorialEstado> historial;


    //imagenes

}
