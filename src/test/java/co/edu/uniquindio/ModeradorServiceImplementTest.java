package co.edu.uniquindio;

import co.edu.uniquindio.dto.EliminarCuentaDto;
import co.edu.uniquindio.dto.moderador.EditarModeradorDto;
import co.edu.uniquindio.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.exeptions.CiudadNoExisteException;
import co.edu.uniquindio.exeptions.ElementoNoEncontradoException;
import co.edu.uniquindio.exeptions.RangoPaginaNoPermitidoException;
import co.edu.uniquindio.model.documentos.Usuario;
import co.edu.uniquindio.model.enums.Ciudad;
import co.edu.uniquindio.model.enums.EstadoUsuario;
import co.edu.uniquindio.model.enums.Rol;
import co.edu.uniquindio.model.vo.CodigoValidacion;
import co.edu.uniquindio.repositorios.UsuarioRepo;
import co.edu.uniquindio.services.ModeradorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class ModeradorServiceImplementTest {

    @Autowired
    private ModeradorService moderadorService;

    @Autowired
    private UsuarioRepo usuarioRepo;

    private Usuario usuario;


    @BeforeEach
    public void setUp() {
        Optional<Usuario> usuario=usuarioRepo.findByEmail("juan@mail.com");
        if (usuario.isPresent()) {
            this.usuario=usuario.get();
        }else {
            Usuario usuario1 = new Usuario(
                    "Juan",
                    "Calle 123",
                    Ciudad.BOGOTA,
                    "juan@mail.com",
                    "1234",
                    Rol.MODERADOR,
                    EstadoUsuario.ACTIVO,
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new CodigoValidacion("123456", LocalDateTime.now())
            );
           this.usuario= usuarioRepo.save(usuario1);
        }
    }

    //elimina toda la lista de usuarios
    @AfterEach
    public void tearDown() {
       usuarioRepo.deleteAll();
    }


    @Test
    void testActualizarModerador() throws Exception {
        EditarModeradorDto editarDto = new EditarModeradorDto(
                usuario.getId().toString(), "Juan Actualizado",  Ciudad.MEDELLIN,"Calle Nueva"
        );

        moderadorService.actualizarModerador(editarDto);

        Usuario actualizado = usuarioRepo.findById(usuario.getId()).orElseThrow();
        Assertions.assertEquals("Juan Actualizado", actualizado.getNombre());
        Assertions.assertEquals(Ciudad.MEDELLIN, actualizado.getCiudad());
        Assertions.assertEquals("Calle Nueva", actualizado.getDireccion());
    }
    
    @Test
    void testActualizarModeradorNoExistente() {
        EditarModeradorDto editarDto = new EditarModeradorDto(
          "123","JUAN",Ciudad.MEDELLIN,"Calle Nueva"        
        );
        assertThrows(ElementoNoEncontradoException.class, () -> {
            moderadorService.actualizarModerador(editarDto);
        });
    }

    @Test
    void testEliminarModerador() throws Exception {
        EliminarCuentaDto eliminarDto = new EliminarCuentaDto(usuario.getId().toString(),usuario.getPassword());
        moderadorService.eliminarModerador(eliminarDto);
        Usuario eliminado = usuarioRepo.findById(usuario.getId()).orElseThrow();
        Assertions.assertEquals(EstadoUsuario.ELIMINADO, eliminado.getEstadoUsuario());
    }

    @Test
    void testObtenerModeradorPorEmail()  {
        UsuarioDTO dto = moderadorService.obtenerModeradorEmail(usuario.getEmail());
        Assertions.assertEquals(usuario.getEmail(), dto.email());
    }

    @Test
    void testObtenerModeradorPorId() throws Exception {
        UsuarioDTO dto = moderadorService.obtenerModeradorId(usuario.getId().toString());
        Assertions.assertEquals(usuario.getEmail(), dto.email());
    }

    @Test
    void testListarUsuariosPorNombreYCiudad() throws Exception {
        List<UsuarioDTO> usuarios = moderadorService.listarUsuarios("Juan", "BOGOTA", 0, 10);
        Assertions.assertFalse(usuarios.isEmpty());
        Assertions.assertEquals("juan@mail.com", usuarios.get(0).email());
    }

    @Test
    void testListarUsuariosCiudadNoExiste(){
        assertThrows(CiudadNoExisteException.class,()->
        {
           moderadorService.listarUsuarios("Juan", "mi casa", 0, 10);
        });
    }

    @Test
    void testListarUsuariosPaginaNoExiste(){
        assertThrows(RangoPaginaNoPermitidoException.class,()->{
            moderadorService.listarUsuarios("Juan", "mi casa", -1, -1);
        });

    }


}
