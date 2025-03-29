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
@AllArgsConstructor
@Document(collection = "usuarios") // Se guarda en la colección "usuarios"
public class Usuario extends Persona{

    @Id
    private ObjectId id;
    private String email;
    private String password;
    private Rol rol;
    private EstadoUsuario estadoUsuario;
    @DBRef private List<Notificacion> notificaciones; // Referencia a Notificaciones en otra colección
    @DBRef private List<Reporte>reportes;
    @DBRef private List<Reporte> listaReportesFavorito;
    private CodigoValidacion codigoValidacion;

}
