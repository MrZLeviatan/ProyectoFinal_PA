package co.edu.uniquindio;

import co.edu.uniquindio.dto.comentario.*;
import co.edu.uniquindio.exceptions.ElementoNoEncontradoException;
import co.edu.uniquindio.mapper.ComentarioMapper;
import co.edu.uniquindio.model.documentos.Comentario;
import co.edu.uniquindio.model.documentos.Reporte;
import co.edu.uniquindio.model.documentos.Usuario;
import co.edu.uniquindio.model.enums.Ciudad;
import co.edu.uniquindio.model.enums.EstadoReporte;
import co.edu.uniquindio.model.enums.EstadoUsuario;
import co.edu.uniquindio.model.enums.Rol;
import co.edu.uniquindio.repositorios.ComentarioRepo;
import co.edu.uniquindio.repositorios.NotificacionRepo;
import co.edu.uniquindio.repositorios.ReporteRepo;
import co.edu.uniquindio.repositorios.UsuarioRepo;
import co.edu.uniquindio.services.impl.ComentarioServiceImplement;
import io.jsonwebtoken.security.Password;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ComentarioServiceImplementTest {

    @Autowired
    private ComentarioServiceImplement comentarioService;

    @Autowired
    private ComentarioRepo comentarioRepo;

    @Autowired
    private ReporteRepo reporteRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private NotificacionRepo notificacionRepo;

    private ObjectId idReporte;
    private ObjectId idUsuario;

    @BeforeEach
    public void setUp() {
        comentarioRepo.deleteAll();
        reporteRepo.deleteAll();

        // Crea un usuario ficticio y un reporte
        Usuario usuario = new Usuario();
        idUsuario = new ObjectId();
        usuario.setId(idUsuario);
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

        usuarioRepo.save(usuario);
        Reporte reporte = new Reporte();
        reporte.setId(new ObjectId());
        reporte.setComentarios(new ArrayList<>());
        reporte.setIdUsuario(idUsuario);
        reporte.setEstadoReporte(EstadoReporte.VERIFICADO);
        reporte.setSolucionado(EstadoReporte.NO_RESUELTO);

        Reporte saved = reporteRepo.save(reporte);
        idReporte = saved.getId();

    }

//    @AfterEach
//    void tearDown() {
//        reporteRepo.deleteAll();
//        usuarioRepo.deleteAll();
//        comentarioRepo.deleteAll();
//        notificacionRepo.deleteAll();
//    }

    @Test
    public void debeAgregarComentarioExitosamente() throws Exception {
        RegistrarComentarioDto dto = new RegistrarComentarioDto(
                "Este es un comentario",
                idReporte.toHexString(),
                idUsuario.toHexString()
        );

        comentarioService.agregarComentario(dto);

        Reporte reporteActualizado = reporteRepo.findById(idReporte).orElseThrow();
        assertEquals(1, reporteActualizado.getComentarios().size());

        Comentario comentarioGuardado = comentarioRepo.findAll().get(0);
        assertEquals("Este es un comentario", comentarioGuardado.getContenido());
    }

    @Test
    public void debeLanzarExcepcionSiReporteNoExiste() {
        RegistrarComentarioDto dto = new RegistrarComentarioDto(
                "Comentario sin reporte",
                new ObjectId().toHexString(),
                idUsuario.toHexString()
        );

        Assertions.assertThrows(ElementoNoEncontradoException.class, () -> {
            comentarioService.agregarComentario(dto);
        });
    }

    @Test
    public void debeActualizarComentario() throws Exception {
        // Primero agregamos uno
        RegistrarComentarioDto dto = new RegistrarComentarioDto(
                "Texto original",
                idReporte.toHexString(),
                idUsuario.toHexString()
        );
        comentarioService.agregarComentario(dto);
        Comentario comentario = comentarioRepo.findAll().get(0);

        // Ahora lo editamos
        EditarComentarioDto editarDto = new EditarComentarioDto(
                comentario.getId().toHexString(),
                "Texto editado"
        );

        comentarioService.actualizarComentario(editarDto);

        Comentario actualizado = comentarioRepo.findById(comentario.getId()).orElseThrow();
        assertEquals("Texto editado", actualizado.getContenido());
    }

    @Test
    public void debeEliminarComentarioYRecursivamenteSusHijos() throws Exception {
        // Crear comentario padre
        RegistrarComentarioDto padreDto = new RegistrarComentarioDto(
                "Padre",
                idReporte.toHexString(),
                idUsuario.toHexString()
        );
        comentarioService.agregarComentario(padreDto);
        Comentario padre = comentarioRepo.findAll().get(0);

        // Crear comentario hijo
        RespuestaComentarioDto hijoDto = new RespuestaComentarioDto(
                padre.getId().toHexString(),
                "Hijo",
                idReporte.toHexString(),
                idUsuario.toHexString()
        );
        comentarioService.agregarRespuestaComentario(hijoDto);


        // Eliminar el padre
        EliminarComentarioDto eliminarDto = new EliminarComentarioDto(padre.getId().toHexString());
        comentarioService.eliminarComentario(eliminarDto);

        assertEquals(0, comentarioRepo.count());
    }

    @Test
    public void debeBuscarComentarioPorId() throws Exception {
        Comentario comentario = new Comentario();
        comentario.setContenido("Este es un comentario");
        Comentario comentarioGuardado = comentarioRepo.save(comentario);

        ComentarioDTO comentarioDTO =comentarioService.buscarComentario(comentarioGuardado.getId().toHexString());
        assertEquals(comentarioDTO.contenido(), comentarioGuardado.getContenido());
    }

    @Test
    public void debeLanzarExcepcionSiComentarioNoExiste() {
        Assertions.assertThrows(ElementoNoEncontradoException.class, () -> {
            comentarioService.buscarComentario(new ObjectId().toHexString());
        });
    }
}

