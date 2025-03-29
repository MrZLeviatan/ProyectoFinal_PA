package co.edu.uniquindio.model;

import co.edu.uniquindio.model.enums.EstadoReporte;
import co.edu.uniquindio.model.enums.EstadoResulto;
import co.edu.uniquindio.model.enums.EstadoSeveridad;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "reportes")
public class Reporte {

    @Id
    private ObjectId id;
    private String titulo;
    private String idUsuario;
    private Ubicacion ubicacion;
    private EstadoResulto estadoReporte;
    private Categoria categoria;
    @DBRef  private List<Comentario> comentarios;
    private EstadoReporte verificado; // admin
    private List<HistorialEstado> historial;
    private String foto;
    private int numeroImportancia;
    private EstadoSeveridad severidad;
}
