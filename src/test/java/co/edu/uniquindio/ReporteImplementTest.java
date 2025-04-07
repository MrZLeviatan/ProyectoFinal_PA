package co.edu.uniquindio;

import co.edu.uniquindio.dto.moderador.CategoriaDTO;
import co.edu.uniquindio.dto.reporte.RegistrarReporteDto;
import co.edu.uniquindio.dto.reporte.UbicacionDTO;
import co.edu.uniquindio.model.documentos.Categoria;
import co.edu.uniquindio.model.documentos.Usuario;
import co.edu.uniquindio.repositorios.CategoriaRepo;
import co.edu.uniquindio.repositorios.ReporteRepo;
import co.edu.uniquindio.repositorios.UsuarioRepo;
import co.edu.uniquindio.services.impl.ReporteImplement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
public class ReporteImplementTest {
    @Autowired
    ReporteImplement reporteImplement;
    @Autowired
    CategoriaRepo categoriaRepo;
    @Autowired
    UsuarioRepo usuarioRepo;
    @Autowired
    private ReporteRepo reporteRepo;

    @AfterEach
    public void tearDown() {
        usuarioRepo.deleteAll();
        categoriaRepo.deleteAll();
        reporteRepo.deleteAll();
    }
    @Test
    void agregarReporte() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNombre("Usuario 1");
        usuario.setEmail("Usuario1@gmail.com");
        usuario.setReportes(new ArrayList<>());

        Usuario usuario2 =usuarioRepo.save(usuario);

        Categoria categoria = new Categoria();
        categoria.setNombre("Categoria 1");
        categoria.setDescripcion("soy una categoria");
        Categoria categoria1= categoriaRepo.save(categoria);


        RegistrarReporteDto reporte = new RegistrarReporteDto(
                "mi_primerReporte",
                usuario2.getId().toString(),
                new UbicacionDTO(12,12,12),
                new CategoriaDTO(categoria1.getId().toString(),categoria1.getNombre(),categoria1.getDescripcion()),
                new ArrayList<>(Arrays.asList("imagen","imagen2","imagen3","imagen4","imagen5"))
        );
        reporteImplement.agregarReporte(reporte);
    }



}
