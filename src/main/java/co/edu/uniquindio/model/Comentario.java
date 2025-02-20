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
    private String contenido;
    private String fechaComentario;
    private String idUsuario;
    private List<Comentario> comentarios= new ArrayList<>();
}
