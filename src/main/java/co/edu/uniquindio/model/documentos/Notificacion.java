package co.edu.uniquindio.model.documentos;

import co.edu.uniquindio.model.enums.EstadoNotificacion;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor

public class Notificacion {
    private String id,idDestinatario,mensaje,fecha,idRemitente,titulo,ubicacionReporte,IDReporteAsociado;
    private EstadoNotificacion estadoNotificacion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdDestinatario() {
        return idDestinatario;
    }

    public void setIdDestinatario(String idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getIdRemitente() {
        return idRemitente;
    }

    public void setIdRemitente(String idRemitente) {
        this.idRemitente = idRemitente;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUbicacionReporte() {
        return ubicacionReporte;
    }

    public void setUbicacionReporte(String ubicacionReporte) {
        this.ubicacionReporte = ubicacionReporte;
    }

    public String getIDReporteAsociado() {
        return IDReporteAsociado;
    }

    public void setIDReporteAsociado(String IDReporteAsociado) {
        this.IDReporteAsociado = IDReporteAsociado;
    }

    public EstadoNotificacion getEstadoNotificacion() {
        return estadoNotificacion;
    }

    public void setEstadoNotificacion(EstadoNotificacion estadoNotificacion) {
        this.estadoNotificacion = estadoNotificacion;
    }
}
