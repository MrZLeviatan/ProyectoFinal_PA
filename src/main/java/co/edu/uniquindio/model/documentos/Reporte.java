package co.edu.uniquindio.model.documentos;

import co.edu.uniquindio.model.vo.HistorialEstado;
import co.edu.uniquindio.model.vo.Ubicacion;
import co.edu.uniquindio.model.enums.EstadoReporte;
import co.edu.uniquindio.model.enums.EstadoSeveridad;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Representa un reporte dentro del sistema.
 * Esta clase está mapeada a la colección "reportes" en MongoDB.
 * Un reporte contiene información sobre su título, usuario asociado, ubicación, estado, categoría, comentarios, historial, fotos,
 * importancia y severidad.
 * La clase permite la gestión y seguimiento de reportes con historial de cambios y comentarios adicionales por parte de los usuarios.
 */

@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "reportes")
public class Reporte {

    @Id
    private ObjectId id;
    private String titulo;
    private ObjectId idUsuario; // hago referencia al usuario atravez de su id
    private Ubicacion ubicacion;
    private EstadoReporte estadoReporte, solucionado;
    @DBRef private Categoria categoria;
    @DBRef  private List<Comentario> comentarios;
    private List<HistorialEstado> historial;
    private List<String> fotos; //lista de rutas
    private int numeroImportancia;
    private EstadoSeveridad severidad;

    public EstadoReporte getSolucionado() {
        return solucionado;
    }

    public void setSolucionado(EstadoReporte solucionado) {
        this.solucionado = solucionado;
    }

    public EstadoReporte getEstadoReporte() {
        return estadoReporte;
    }

    public void setEstadoReporte(EstadoReporte estadoReporte) {
        this.estadoReporte = estadoReporte;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ObjectId getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(ObjectId idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }



    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }



    public List<HistorialEstado> getHistorial() {
        return historial;
    }

    public void setHistorial(List<HistorialEstado> historial) {
        this.historial = historial;
    }

    public List<String> getFotos() {
        return fotos;
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }

    public int getNumeroImportancia() {
        return numeroImportancia;
    }

    public void setNumeroImportancia(int numeroImportancia) {
        this.numeroImportancia = numeroImportancia;
    }

    public EstadoSeveridad getSeveridad() {
        return severidad;
    }

    public void setSeveridad(EstadoSeveridad severidad) {
        this.severidad = severidad;
    }
}
