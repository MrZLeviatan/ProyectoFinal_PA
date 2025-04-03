package co.edu.uniquindio.model.documentos;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@NonNull
@Document(collection = "comentarios")
public class Comentario {
    @Id private ObjectId id;
    private String contenido; // contenido
    private LocalDateTime fechaComentario; //en que fecha se realizó el comentario
    private ObjectId idUsuario; //quien realizo el comentario
    private ObjectId idReporte; //para saber a qué reporte esta asociado el comentario
    @DBRef private List<Comentario> comentarios= new ArrayList<>();

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(LocalDateTime fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    public ObjectId getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(ObjectId idUsuario) {
        this.idUsuario = idUsuario;
    }

    public ObjectId getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(ObjectId idReporte) {
        this.idReporte = idReporte;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}
