package co.edu.uniquindio;

import co.edu.uniquindio.model.documentos.Categoria;
import co.edu.uniquindio.model.documentos.Comentario;
import co.edu.uniquindio.model.enums.EstadoReporte;
import co.edu.uniquindio.model.enums.EstadoSeveridad;
import co.edu.uniquindio.model.vo.HistorialEstado;
import co.edu.uniquindio.model.vo.Ubicacion;
import co.edu.uniquindio.repositorios.CategoriaRepo;
import co.edu.uniquindio.services.CategoriaService;
import co.edu.uniquindio.services.impl.CategoriaImplement;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@SpringBootApplication
public class ProyectoApplication {

    public static void main(String[] args) {

        SpringApplication.run(ProyectoApplication.class, args);


    }

}