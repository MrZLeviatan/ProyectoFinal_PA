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

@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "reportes")
public class Reporte {

    @Id
    private ObjectId id;
    private String titulo;
    private ObjectId idUsuario; // hago referencia al usuario atravez de su id
    private Ubicacion ubicacion;
    private EstadoResulto estadoReporte;
    private Categoria categoria;
    @DBRef  private List<Comentario> comentarios;
    private EstadoReporte verificado; // admin
    private List<HistorialEstado> historial;
    private List<String> fotos; //lista de rutas
    private int numeroImportancia;
    private EstadoSeveridad severidad;


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

    public EstadoResulto getEstadoReporte() {
        return estadoReporte;
    }

    public void setEstadoReporte(EstadoResulto estadoReporte) {
        this.estadoReporte = estadoReporte;
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

    public EstadoReporte getVerificado() {
        return verificado;
    }

    public void setVerificado(EstadoReporte verificado) {
        this.verificado = verificado;
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
