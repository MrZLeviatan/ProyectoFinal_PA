package co.edu.uniquindio.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NonNull
@Document(collection = "comentarios")
public class Comentario {
    @Id private ObjectId id;
    private String contenido; // contenido
    private String fechaComentario; //en que fecha se realizó el comentario
    private String idUsuario; //quien realizo el comentario
    private String idReporte; //para saber a qué reporte esta asociado el comentario
    @DBRef private List<Comentario> comentarios= new ArrayList<>();
}
