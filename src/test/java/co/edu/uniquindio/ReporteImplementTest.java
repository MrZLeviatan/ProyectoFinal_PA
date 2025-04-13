package co.edu.uniquindio;

import co.edu.uniquindio.dto.moderador.CategoriaDTO;
import co.edu.uniquindio.dto.reporte.*;
import co.edu.uniquindio.exceptions.ElementoNoEncontradoException;
import co.edu.uniquindio.exceptions.PermisoDenegadoException;
import co.edu.uniquindio.model.documentos.Categoria;
import co.edu.uniquindio.model.documentos.Reporte;
import co.edu.uniquindio.model.documentos.Usuario;
import co.edu.uniquindio.model.enums.Ciudad;
import co.edu.uniquindio.model.enums.EstadoReporte;
import co.edu.uniquindio.model.enums.EstadoUsuario;
import co.edu.uniquindio.model.enums.Rol;
import co.edu.uniquindio.repositorios.CategoriaRepo;
import co.edu.uniquindio.repositorios.ReporteRepo;
import co.edu.uniquindio.repositorios.UsuarioRepo;
import co.edu.uniquindio.services.impl.ReporteImplement;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class ReporteImplementTest {

    @Autowired
    private ReporteImplement reporteService;

    @Autowired
    private ReporteRepo reporteRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private CategoriaRepo categoriaRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        // Limpiar colecciones antes de cada prueba
        reporteRepo.deleteAll();
        usuarioRepo.deleteAll();
        categoriaRepo.deleteAll();
    }

    @Test
    @WithMockUser(username = "507f1f77bcf86cd799439011")
    void agregarReporte_deberiaGuardarCorrectamente() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(new ObjectId("507f1f77bcf86cd799439011"));
        usuario.setReportes(new ArrayList<>());
        usuario.setNotificaciones(new ArrayList<>());
        usuarioRepo.save(usuario);
        RegistrarReporteDto dto = new RegistrarReporteDto(
                "Reporte de prueba",
                "507f1f77bcf86cd799439011",
                new UbicacionDTO(4,6, 4),
                new CategoriaDTO("507f1f77bcf86cd799439012", "Categoría X","categoria1"),
                List.of("foto1.jpg")
        );
        Categoria categoria = new Categoria();
        categoria.setId(new ObjectId("507f1f77bcf86cd799439012"));
        categoria.setNombre("Categoría X");
        categoriaRepo.save(categoria);
        ReporteDTO resultado = reporteService.agregarReporte(dto);
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals("Reporte de prueba", resultado.titulo());
        Assertions.assertEquals(1, usuarioRepo.findById(usuario.getId()).get().getReportes().size());
    }

    @Test
    @WithMockUser(username = "507f1f77bcf86cd799439011")
    void actualizarReporte_deberiaActualizarCorrectamente() throws Exception {
        Categoria categoria = new Categoria();
        categoria.setId(new ObjectId("507f1f77bcf86cd799439012"));
        categoria.setNombre("Categoría X");
        Categoria categoriaGuardada= categoriaRepo.save(categoria);
        Usuario usuario = new Usuario();
        usuario.setId(new ObjectId("507f1f77bcf86cd799439011"));
        usuario.setReportes(new ArrayList<>());
        usuario.setNotificaciones(new ArrayList<>());
        usuarioRepo.save(usuario);
        Reporte reporte = new Reporte();
        reporte.setTitulo("Viejo título");
        reporte.setIdUsuario(usuario.getId());
        reporte.setCategoria(categoria);
        reporte.setFotos(new ArrayList<>());
        reporteRepo.save(reporte);
        EditarReporteDto dto = new EditarReporteDto(
                reporte.getId().toString(),
                "Nuevo título",
                new UbicacionDTO(1, 2, 10),
                new CategoriaDTO(categoriaGuardada.getId().toString(), categoriaGuardada.getNombre(),categoriaGuardada.getDescripcion()),
                List.of("nueva_foto.jpg")
        );
        ReporteDTO actualizado = reporteService.actualizarReporte(dto);
        Assertions.assertEquals("Nuevo título", actualizado.titulo());
        Assertions.assertEquals(1, actualizado.fotos().size());
    }

    @Test
    @WithMockUser(username = "507f1f77bcf86cd799439011")
    void eliminarReporte_deberiaEliminarCorrectamente() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(new ObjectId("507f1f77bcf86cd799439011"));
        usuario.setPassword(passwordEncoder.encode("clave123"));
        usuario.setReportes(new ArrayList<>());
        usuario.setNotificaciones(new ArrayList<>());
        usuarioRepo.save(usuario);
        Reporte reporte = new Reporte();
        reporte.setIdUsuario(usuario.getId());
        reporte.setSolucionado(EstadoReporte.PENDIENTE);
        reporteRepo.save(reporte);
        usuario.getReportes().add(reporte);
        usuarioRepo.save(usuario);
        EliminarReporteDto dto = new EliminarReporteDto(
                reporte.getId().toString(),
                "clave123"
        );

        reporteService.eliminarReporte(dto);
        Reporte eliminado = reporteRepo.findById(reporte.getId()).orElseThrow();
        Assertions.assertEquals(EstadoReporte.ELIMINADO, eliminado.getHistorial().get(eliminado.getHistorial().size() - 1).getEstadoActual());
    }

    @Test
    void marcarReporteImportante_deberiaIncrementarImportancia() throws Exception {

        Usuario usuario = new Usuario();
        usuario.setId(new ObjectId());
        usuario.setNotificaciones(new ArrayList<>());
        usuario.setReportes(new ArrayList<>());
        usuario.setNotificaciones(new ArrayList<>());
        usuarioRepo.save(usuario);
        Reporte reporte = new Reporte();
        reporte.setNumeroImportancia(0);
        reporte.setIdUsuario(usuario.getId());
        Reporte reporteGuardado= reporteRepo.save(reporte);
        MarcarReporteDto dto = new MarcarReporteDto(
                usuario.getId().toString(),
                reporteGuardado.getId().toString()
        );
        reporteService.marcarReporteImportante(dto);
        Reporte actualizado = reporteRepo.findById(reporte.getId()).orElseThrow();
        Assertions.assertEquals(1, actualizado.getNumeroImportancia());
    }

    @Test
    @WithMockUser(username = "507f1f77bcf86cd799439011")
    void agregarReporte_deberiaLanzarExcepcion_SiUsuarioNoExiste() {
        RegistrarReporteDto dto = new RegistrarReporteDto(
                "Reporte inválido",
                "507f1f77bcf86cd799439099", // Usuario inexistente
                new UbicacionDTO(1, 2, 3),
                new CategoriaDTO("507f1f77bcf86cd799439012", "Categoría X", "categoria1"),
                List.of("foto.jpg")
        );
        categoriaRepo.save(new Categoria(new ObjectId("507f1f77bcf86cd799439012"), "Categoría X", "categoria1"));
        Assertions.assertThrows(ElementoNoEncontradoException.class, () -> {
            reporteService.agregarReporte(dto);
        });
    }

    @Test
    @WithMockUser(username = "507f1f77bcf86cd799439011")
    void actualizarReporte_deberiaLanzarExcepcion_SiReporteNoExiste() {
        Usuario usuario = new Usuario();
        usuario.setId(new ObjectId("507f1f77bcf86cd799439011"));
        usuarioRepo.save(usuario);

        EditarReporteDto dto = new EditarReporteDto(
                new ObjectId().toString(),
                "Nuevo título",
                new UbicacionDTO(1, 2, 3),
                new CategoriaDTO(new ObjectId().toString(), "Categoría falsa", "desc"),
                List.of("foto.jpg")
        );
        Assertions.assertThrows(ElementoNoEncontradoException.class, () -> {
            reporteService.actualizarReporte(dto);
        });
    }

    @Test
    @WithMockUser(username = "507f1f77bcf86cd799439011")
    void eliminarReporte_deberiaLanzarExcepcion_SiContrasenaIncorrecta() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setId(new ObjectId("507f1f77bcf86cd799439011"));
        usuario.setPassword(passwordEncoder.encode("claveCorrecta"));
        usuarioRepo.save(usuario);

        Reporte reporte = new Reporte();
        reporte.setIdUsuario(usuario.getId());
        reporte.setSolucionado(EstadoReporte.PENDIENTE);
        reporteRepo.save(reporte);

        usuario.setReportes(List.of(reporte));
        usuarioRepo.save(usuario);

        EliminarReporteDto dto = new EliminarReporteDto(
                reporte.getId().toString(),
                "claveIncorrecta"
        );

        // Act + Assert
        Assertions.assertThrows(PermisoDenegadoException.class, () -> {
            reporteService.eliminarReporte(dto);
        });
    }

    @Test
    void marcarReporteImportante_deberiaLanzarExcepcion_SiReporteNoExiste() {
        // Arrange
        MarcarReporteDto dto = new MarcarReporteDto("507f1f77bcf86cd799439011", new ObjectId().toString());

        // Act + Assert
        Assertions.assertThrows(ElementoNoEncontradoException.class, () -> {
            reporteService.marcarReporteImportante(dto);
        });
    }

    @Test
    void marcarComoFavorito_deberiaAgregarReporteAFavoritos() throws Exception {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setId(new ObjectId("507f1f77bcf86cd799439011"));
        usuario.setListaReportesFavorito(new ArrayList<>());
        usuario.setNotificaciones(new ArrayList<>());
        String idUsuario= usuarioRepo.save(usuario).getId().toString();
        Reporte reporte = new Reporte();
        Reporte reporteGuardado = reporteRepo.save(reporte);

        MarcarReporteDto dto = new MarcarReporteDto(idUsuario, reporteGuardado.getId().toString());

        // Act
        reporteService.marcarReporteFavorito(dto);

        // Assert
        Usuario actualizado = usuarioRepo.findById(usuario.getId()).orElseThrow();
        Assertions.assertTrue(!actualizado.getListaReportesFavorito().isEmpty());
    }

    @Test
    @WithMockUser(username = "507f1f77bcf86cd799439011")
    void marcarComoResuelto_deberiaActualizarEstado() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(new ObjectId("507f1f77bcf86cd799439011"));
        usuario.setNombre("Usuario");
        usuario.setEmail("andrey3681.ay@gmail.com");
        usuario.setReportes(new ArrayList<>());
        usuario.setRol(Rol.USUARIO);
        usuario.setPassword(passwordEncoder.encode("1234"));
        usuario.setEstadoUsuario(EstadoUsuario.ACTIVO);
        usuario.setCiudad(Ciudad.ARMENIA);
        usuario.setDireccion("mi casa");
        usuario.setNotificaciones(new ArrayList<>());
        usuario.setListaReportesFavorito(new ArrayList<>());
        usuario.setFechaRegistro(LocalDateTime.now());

        String idUsuario= usuario.getId().toString();


        // Arrange
        Reporte reporte = new Reporte();
        reporte.setSolucionado(EstadoReporte.NO_RESUELTO);
        reporte.setIdUsuario(usuario.getId());
        Reporte reporteGuardado= reporteRepo.save(reporte);
        usuarioRepo.save(usuario);


        reporteService.marcarReporteResuelto(new MarcarReporteDto(idUsuario,reporteGuardado.getId().toString()));

        Reporte actualizado = reporteRepo.findById(reporte.getId()).orElseThrow();
        Assertions.assertEquals(EstadoReporte.RESUELTO, actualizado.getSolucionado());
    }
}
