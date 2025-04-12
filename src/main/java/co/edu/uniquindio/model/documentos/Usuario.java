package co.edu.uniquindio.model.documentos;

import co.edu.uniquindio.model.vo.CodigoValidacion;
import co.edu.uniquindio.model.enums.Ciudad;
import co.edu.uniquindio.model.enums.EstadoUsuario;
import co.edu.uniquindio.model.enums.Rol;
import co.edu.uniquindio.model.vo.Persona;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Representa a un usuario dentro del sistema, extendiendo de la clase Persona.
 * Esta clase está mapeada a la colección "usuarios" en MongoDB.
 * Un usuario contiene información personal, de autenticación (email y contraseña), rol dentro del sistema, estado del usuario,
 * notificaciones asociadas, reportes creados y favoritos, y un código de validación.
 * Además, registra la fecha en que se dio de alta en el sistema.
 * El rol y estado del usuario determinan el nivel de acceso y la funcionalidad disponible dentro de la aplicación.
 */
@NoArgsConstructor
@Document(collection = "usuarios")
@ToString
public class Usuario extends Persona {

    @Id
    private ObjectId id;
    private String email;
    private String password;
    private Rol rol;
    private EstadoUsuario estadoUsuario;
    @DBRef private List<Notificacion> notificaciones;
    @DBRef private List<Reporte> reportes;
    @DBRef private List<Reporte> listaReportesFavorito;
    private CodigoValidacion codigoValidacion;
    private LocalDateTime fechaRegistro;

    public Usuario(String nombre, String direccion, Ciudad ciudad, String email, String password, Rol rol, EstadoUsuario estadoUsuario, List<Notificacion> notificaciones, List<Reporte> reportes, List<Reporte> listaReportesFavorito, CodigoValidacion codigoValidacion) {
        super(nombre, direccion, ciudad);
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.estadoUsuario = estadoUsuario;
        this.notificaciones = notificaciones;
        this.reportes = reportes;
        this.listaReportesFavorito = listaReportesFavorito;
        this.codigoValidacion = codigoValidacion;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public EstadoUsuario getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(EstadoUsuario estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public List<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(List<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }

    public List<Reporte> getReportes() {
        return reportes;
    }

    public void setReportes(List<Reporte> reportes) {
        this.reportes = reportes;
    }

    public List<Reporte> getListaReportesFavorito() {
        return listaReportesFavorito;
    }

    public void setListaReportesFavorito(List<Reporte> listaReportesFavorito) {
        this.listaReportesFavorito = listaReportesFavorito;
    }

    public CodigoValidacion getCodigoValidacion() {
        return codigoValidacion;
    }

    public void setCodigoValidacion(CodigoValidacion codigoValidacion) {
        this.codigoValidacion = codigoValidacion;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }


}
