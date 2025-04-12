package co.edu.uniquindio;

import co.edu.uniquindio.dto.moderador.CategoriaDTO;
import co.edu.uniquindio.dto.moderador.CrearCategoriaDto;
import co.edu.uniquindio.dto.moderador.EditarCategoriaDto;
import co.edu.uniquindio.exeptions.ElementoNoEncontradoException;
import co.edu.uniquindio.mapper.CategoriaMapper;
import co.edu.uniquindio.model.documentos.Categoria;
import co.edu.uniquindio.repositorios.CategoriaRepo;
import co.edu.uniquindio.services.CategoriaService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoriaImplementTest {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private CategoriaRepo categoriaRepo;

    private Categoria categoriaExistente;

    @BeforeEach
    public void setUp() {
        Optional<Categoria> categoriaExistente= categoriaRepo.findByNombre("Tecnologia");
        if(categoriaExistente.isEmpty()){
           this.categoriaExistente= categoriaRepo.save(new Categoria("Tecnología","Todo sobre tecnología"));
        }else {
            this.categoriaExistente =categoriaRepo.findByNombre("Tecnologia").get();
        }
    }


    //elimina la categoria creada con el @BeforeEach para no tener datos repetidos
    @AfterEach
    public void tearDown() {

        categoriaRepo.deleteAll();
    }


    @Test
    public void testCrearCategoria() throws Exception {
        CrearCategoriaDto dto = new CrearCategoriaDto("Deportes", "Categoría de deportes");
        categoriaService.crearCategoria(dto);
        List<Categoria> categorias = categoriaRepo.findAll();
        assertTrue(categorias.stream().anyMatch(c -> c.getNombre().equals("Deportes")));
    }

    @Test
    public void testEditarCategoriaExistente() throws Exception {
        EditarCategoriaDto editar = new EditarCategoriaDto(
                categoriaExistente.getId().toString(),
                "Tecnología Avanzada",
                "Actualización"
        );
        categoriaService.editarCategoria(editar);
        Categoria actualizada = categoriaRepo.findById(categoriaExistente.getId()).get();
        assertEquals("Tecnología Avanzada", actualizada.getNombre());
    }
    private static final Logger logger = (Logger) LoggerFactory.getLogger(CategoriaImplementTest.class);
    @Test
    public void testEditarCategoriaInexistente() throws Exception {
        EditarCategoriaDto editar = new EditarCategoriaDto(
                new ObjectId().toString(),
                "Nueva",
                "Descripción"
        );

       Exception ex= assertThrows(ElementoNoEncontradoException.class, () ->
                categoriaService.editarCategoria(editar));

       logger.info(ex.getMessage());
    }

    @Test
    public void testEliminarCategoriaExistente() throws Exception {
        categoriaService.eliminarCategoria(categoriaExistente.getId().toString());
        assertFalse(categoriaRepo.findById(categoriaExistente.getId()).isPresent());
    }

    @Test
    public void testEliminarCategoriaInexistente() {
        assertThrows(ElementoNoEncontradoException.class, () ->
                categoriaService.eliminarCategoria(new ObjectId().toString()));
    }

    @Test
    public void testObtenerCategoriaPorId() throws Exception {
        CategoriaDTO dto = categoriaService.obtenerCategoriaId(categoriaExistente.getId().toString());
        assertEquals(categoriaExistente.getNombre(), dto.nombre());
    }

    @Test
    public void testObtenerCategoriaPorIdInexistente() {
        assertThrows(ElementoNoEncontradoException.class, () ->
                categoriaService.obtenerCategoriaId(new ObjectId().toString()));
    }

    @Test
    public void testListarCategorias() throws Exception {
        List<CategoriaDTO> categorias = categoriaService.listarCategorias();
        assertFalse(categorias.isEmpty());
    }
}

