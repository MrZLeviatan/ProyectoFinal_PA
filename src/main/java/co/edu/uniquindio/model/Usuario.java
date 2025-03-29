package co.edu.uniquindio.model;

import co.edu.uniquindio.model.enums.Ciudad;
import co.edu.uniquindio.model.enums.EstadoUsuario;
import co.edu.uniquindio.model.enums.Rol;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "usuarios") // Se guarda en la colección "usuarios"
public class Usuario extends Persona{

    @Id
    private ObjectId id;
    private String email;
    private String password;
    private Rol rol;
    private EstadoUsuario estadoUsuario;
    @DBRef private List<Notificacion> notificaciones; // Referencia a Notificaciones en otra colección
    @DBRef private List<Reporte> reportes;
    @DBRef private List<Reporte> listaReportesFavorito;
    private CodigoValidacion codigoValidacion;

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
}
