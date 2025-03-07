package co.edu.uniquindio.model;

import co.edu.uniquindio.model.enums.EstadoUsuario;
import co.edu.uniquindio.model.enums.Rol;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Usuario extends Persona{
    private String email,password,id;
    private Rol rol;
    private EstadoUsuario estadoUsuario;
    private List<Notificacion> notificaciones;
    private List<Reporte>reportes;
    private List<Reporte> listaReportesFavorito;

}
