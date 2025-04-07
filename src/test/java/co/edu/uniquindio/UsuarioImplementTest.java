package co.edu.uniquindio;

import co.edu.uniquindio.dto.EliminarCuentaDto;
import co.edu.uniquindio.dto.RestablecerPasswordDto;
import co.edu.uniquindio.dto.usuario.*;
import co.edu.uniquindio.exeptions.DatoErroneoException;
import co.edu.uniquindio.exeptions.ElementoNoEncontradoException;
import co.edu.uniquindio.exeptions.ElementoRepetidoException;
import co.edu.uniquindio.exeptions.UsuarioException;
import co.edu.uniquindio.model.documentos.Usuario;
import co.edu.uniquindio.model.enums.Ciudad;
import co.edu.uniquindio.model.enums.EstadoUsuario;
import co.edu.uniquindio.model.vo.CodigoValidacion;
import co.edu.uniquindio.repositorios.UsuarioRepo;
import co.edu.uniquindio.services.UsuarioService;
import jdk.jfr.DataAmount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UsuarioImplementTest {
    @Autowired
    UsuarioService usuarioService;

    @Autowired
    UsuarioRepo usuarioRepo;

    private String email = "juan.perez@example.com";
    private String password = "contrasena123";

    private Usuario usuario;


    //metodo para garantizar que el usuario para los test siempre este creado
    @BeforeEach
    public void setUp() throws Exception {
        Optional<Usuario> existente = usuarioRepo.findByEmail(email);
        if (existente.isEmpty()) {
            RegistrarUsuarioDto nuevo = new RegistrarUsuarioDto(
                    "Juan Pérez",
                    "Calle 123",
                    Ciudad.ARMENIA,
                    email,
                    password,
                    new CodigoValidacionDTO("ABC123", LocalDateTime.now())
            );
            usuarioService.crearUsuario(nuevo);
        }

        usuario = usuarioRepo.findByEmail(email).get();
    }
    // agregar un usuario con correo repetido
    @Test
    public void crearUsuario() throws ElementoRepetidoException {
        RegistrarUsuarioDto usuario = new RegistrarUsuarioDto(
                "Juan Pérez", // nombre
                "Calle 123 #45-67", // dirección
                Ciudad.ARMENIA, // ciudad (enum, asegúrate que exista este valor en tu enum Ciudad)
                "juan.perez@example.com", // email
                "contrasena123", // password
                new CodigoValidacionDTO("ABC123", LocalDateTime.now()) // código de validación (suponiendo que esa clase tiene un constructor así)
        );
        assertThrows(ElementoRepetidoException.class, () -> {
            usuarioService.crearUsuario(usuario);
        });

    }

    //prueba de tratar de elimnar la cuenta de un usuario no registrado
    @Test
    public void eliminarUsuarioNoExistente() throws ElementoRepetidoException {
        EliminarCuentaDto e = new EliminarCuentaDto("123","123");
        assertThrows(ElementoNoEncontradoException.class, () -> {
            usuarioService.eliminarUsuario(e);
        });
    }

    // prueba de tratar de eliminar un cuanta de un usuario con id correcta pero contraseña erronea
    // para ejecutar este metodo deben haber realizado primero el test numero uno que agrega ese usuario ala base de datos
    @Test
    public void eliminarUsuarioPasswordIncorrecta() throws Exception {
        EliminarCuentaDto e = new EliminarCuentaDto(String.valueOf(usuario.getId()),"123");
        assertThrows(DatoErroneoException.class, () -> {
            usuarioService.eliminarUsuario(e);
        });
    }

    //aca se eliminara  al usuario juan.perez@example.com
    @Test
    public void eliminarUsuario() throws Exception {
        EliminarCuentaDto e = new EliminarCuentaDto(String.valueOf(usuario.getId()),usuario.getPassword());
        usuarioService.eliminarUsuario(e);
        // comprobamos que el estado cambio a ELIMINADO
        Usuario eliminado = usuarioRepo.findById(usuario.getId()).get();
        assertEquals(EstadoUsuario.ELIMINADO, eliminado.getEstadoUsuario());
    }

    //volvermos a ingresar un usario ya registro pero que se encuentra eliminado
//    @Test
//    public void crearUsuario2() throws ElementoRepetidoException {
//        RegistrarUsuarioDto usuario = new RegistrarUsuarioDto(
//                "Juan Pérez", // nombre
//                "Calle 123 #45-67", // dirección
//                Ciudad.ARMENIA, // ciudad (enum, asegúrate que exista este valor en tu enum Ciudad)
//                "juan.perez@example.com", // email
//                "contrasena123", // password
//                new CodigoValidacionDTO("ABC123", LocalDateTime.now()) // código de validación (suponiendo que esa clase tiene un constructor así)
//        );
//        usuarioService.crearUsuario(usuario);
//    }

    @Test
    public void actualizarUsuarioNoExiste() {
        EditarUsuarioDto editar = new EditarUsuarioDto(
                "123",
                "Nombre Modificado",
                Ciudad.BOGOTA,
                "Nueva dirección"

        );

        assertThrows(ElementoNoEncontradoException.class, () -> {
            usuarioService.actualizarUsuario(editar);
        });

    }
    @Test
    public void testActualizarUsuario() throws Exception {
        EditarUsuarioDto editar = new EditarUsuarioDto(
                usuario.getId().toString(),
                "Nombre Modificado",
                Ciudad.BOGOTA,
                "Nueva dirección"

        );

        usuarioService.actualizarUsuario(editar);

        Usuario actualizado = usuarioRepo.findById(usuario.getId()).get();
        assertEquals("Nombre Modificado", actualizado.getNombre());
        assertEquals("Nueva dirección", actualizado.getDireccion());
        assertEquals(Ciudad.BOGOTA, actualizado.getCiudad());
    }

    @Test
    public void testObtenerUsuarioNoExistente() throws Exception {
        assertThrows(ElementoNoEncontradoException.class, () -> {
            usuarioService.obtenerUsuarioId("notexist");
        });
    }
    @Test
    public void testObtenerUsuarioId() throws Exception {
        UsuarioDTO dto = usuarioService.obtenerUsuarioId(usuario.getId().toString());
        assertEquals(usuario.getEmail(), dto.email());
    }


    @Test
    public void testObtenerUsuarioNoExisteEmail(){
        assertThrows(ElementoNoEncontradoException.class, () -> {
           usuarioService.obtenerUsuarioEmail("notexist");
        });
    }
    @Test
    public void testObtenerUsuarioEmail() throws Exception {
        UsuarioDTO dto = usuarioService.obtenerUsuarioEmail(usuario.getEmail());
        assertEquals(usuario.getNombre(), dto.nombre());
    }


    @Test
    public void testRestablecerPassword() throws Exception {
        String nuevaClave = "claveNueva123";
        RestablecerPasswordDto dto = new RestablecerPasswordDto(email, nuevaClave);
        usuarioService.restablecerPassword(dto);

        Usuario actualizado = usuarioRepo.findByEmail(email).get();
        assertEquals(nuevaClave, actualizado.getPassword());
    }

    @Test
    public void testSolicitarRestablecerCorrecto() throws Exception {
        Usuario usuario= usuarioRepo.findByEmail("andrey3681@gmail.com").get();
        usuario.setEstadoUsuario(EstadoUsuario.ACTIVO);
        usuarioRepo.save(usuario);

        usuarioService.solicitarRestablecer("andrey3681@gmail.com");

        Usuario actualizado = usuarioRepo.findByEmail("andrey3681@gmail.com").get();
        assertNotNull(actualizado.getCodigoValidacion());
    }

    @Test
    public void testSolicitarRestablecerEliminado() {
        usuario.setEstadoUsuario(EstadoUsuario.ELIMINADO);
        usuarioRepo.save(usuario);

        assertThrows(ElementoNoEncontradoException.class, () -> {
            usuarioService.solicitarRestablecer(email);
        });
    }

    @Test
    public void testActivarCuentaCorrecta() throws Exception {
        // Simula que el usuario está INACTIVO con un código válido
        String codigo = "ABCDEF";
        LocalDateTime ahora = LocalDateTime.now();

        usuario.setEstadoUsuario(EstadoUsuario.INACTIVO);
        usuario.setCodigoValidacion(new CodigoValidacion(codigo, ahora));
        usuarioRepo.save(usuario);

        ActivarCuentaDto activar = new ActivarCuentaDto(
                usuario.getId().toString(),
                new CodigoValidacionDTO(codigo, ahora)
        );

        usuarioService.activarCuenta(activar);

        Usuario activado = usuarioRepo.findByEmail(email).get();
        assertEquals(EstadoUsuario.ACTIVO, activado.getEstadoUsuario());
        assertNull(activado.getCodigoValidacion());
    }

    @Test
    public void testActivarCuentaCodigoIncorrecto() {
        usuario.setEstadoUsuario(EstadoUsuario.INACTIVO);
        usuario.setCodigoValidacion(new CodigoValidacion("CORRECTO", LocalDateTime.now()));
        usuarioRepo.save(usuario);

        ActivarCuentaDto dto = new ActivarCuentaDto(
                usuario.getId().toString(),
                new CodigoValidacionDTO("INCORRECTO", LocalDateTime.now())
        );

        assertThrows(UsuarioException.class, () -> {
            usuarioService.activarCuenta(dto);
        });
    }





}
