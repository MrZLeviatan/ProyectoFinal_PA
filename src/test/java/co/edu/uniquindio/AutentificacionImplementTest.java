package co.edu.uniquindio;

import co.edu.uniquindio.dto.LoginDto;
import co.edu.uniquindio.exeptions.CredencialesInvalidasException;
import co.edu.uniquindio.exeptions.ElementoNoEncontradoException;
import co.edu.uniquindio.exeptions.UsuarioNoActivadoException;
import co.edu.uniquindio.model.documentos.Usuario;
import co.edu.uniquindio.model.enums.Ciudad;
import co.edu.uniquindio.model.enums.EstadoUsuario;
import co.edu.uniquindio.repositorios.UsuarioRepo;
import co.edu.uniquindio.services.AutentificacionService;
import co.edu.uniquindio.services.impl.AuntentificacionImplement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AutentificacionImplementTest {

    @Autowired
    private AutentificacionService autentificacionService;

    @Autowired
    private UsuarioRepo usuarioRepo;

    private final String emailValido = "test.login@example.com";
    private final String passwordValida = "12345";

    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        usuario = new Usuario();
        usuario.setNombre("Prueba Login");
        usuario.setEmail(emailValido);
        usuario.setPassword(passwordValida);
        usuario.setEstadoUsuario(EstadoUsuario.ACTIVO);
        usuario.setDireccion("Calle Falsa 123");
        usuario.setCiudad(Ciudad.ARMENIA);
        this.usuario=usuarioRepo.save(usuario);
    }

    @AfterEach
    public void tearDown() {
        usuarioRepo.deleteAll();
    }



    @Test
    public void testInicioSesionExitoso() throws Exception {

        LoginDto loginDto = new LoginDto(emailValido, passwordValida);
        assertDoesNotThrow(() -> autentificacionService.iniciarSesion(loginDto));
    }

    @Test
    public void testEmailNoExiste() {
        LoginDto loginDto = new LoginDto("correo.noexiste@example.com", passwordValida);
        Exception ex = assertThrows(ElementoNoEncontradoException.class, () ->
                autentificacionService.iniciarSesion(loginDto)
        );
        assertEquals("El email no existe", ex.getMessage());
    }

    @Test
    public void testContrasenaIncorrecta() {
        LoginDto loginDto = new LoginDto(emailValido, "claveIncorrecta");
        Exception ex = assertThrows(CredencialesInvalidasException.class, () ->
                autentificacionService.iniciarSesion(loginDto)
        );
        assertEquals("el usuario no puedo ingresar", ex.getMessage());
    }

    @Test
    public void testUsuarioInactivo() {
        usuario.setEstadoUsuario(EstadoUsuario.INACTIVO);
        usuarioRepo.save(usuario);

        LoginDto loginDto = new LoginDto(emailValido, passwordValida);
        Exception ex = assertThrows(UsuarioNoActivadoException.class, () ->
                autentificacionService.iniciarSesion(loginDto)
        );
        assertEquals("El usuario debe activarse primero ", ex.getMessage());
    }
}

