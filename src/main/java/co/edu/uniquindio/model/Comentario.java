package co.edu.uniquindio.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NonNull
public class Comentario {
    private String contenido; // contenido
    private String fechaComentario; //en que fecha se realizo el comentario
    private String idUsuario; //quien realizo el comentario
    private String id; //id unica del comentario
    private String idReporte; //para saber a que reporte esta asociado el comentario
    private List<Comentario> comentarios= new ArrayList<>();
}
