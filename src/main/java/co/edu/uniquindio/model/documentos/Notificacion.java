package co.edu.uniquindio.model.documentos;

import co.edu.uniquindio.model.enums.EstadoNotificacion;
import co.edu.uniquindio.model.vo.Ubicacion;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Representa una Notificacion dentro del sistema.
 * Esta clase está mapeada a la colección "notificaciones" en MongoDB.
 */
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "notificaciones")
public class Notificacion {

    @Id private ObjectId id;
    @DBRef private ObjectId idDestinatario,idRemitente,IDReporteAsociado;
    private String mensaje,titulo;
    private Ubicacion ubicacionReporte;
    private EstadoNotificacion estadoNotificacion;
    private LocalDateTime fecha;


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getIdDestinatario() {
        return idDestinatario;
    }

    public void setIdDestinatario(ObjectId idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    public ObjectId getIdRemitente() {
        return idRemitente;
    }

    public void setIdRemitente(ObjectId idRemitente) {
        this.idRemitente = idRemitente;
    }

    public ObjectId getIDReporteAsociado() {
        return IDReporteAsociado;
    }

    public void setIDReporteAsociado(ObjectId IDReporteAsociado) {
        this.IDReporteAsociado = IDReporteAsociado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Ubicacion getUbicacionReporte() {
        return ubicacionReporte;
    }

    public void setUbicacionReporte(Ubicacion ubicacionReporte) {
        this.ubicacionReporte = ubicacionReporte;
    }

    public EstadoNotificacion getEstadoNotificacion() {
        return estadoNotificacion;
    }

    public void setEstadoNotificacion(EstadoNotificacion estadoNotificacion) {
        this.estadoNotificacion = estadoNotificacion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
