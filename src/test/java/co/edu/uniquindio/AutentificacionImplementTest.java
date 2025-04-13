package co.edu.uniquindio;

import co.edu.uniquindio.dto.LoginDto;
import co.edu.uniquindio.dto.TokenDTO;
import co.edu.uniquindio.exceptions.CredencialesInvalidasException;
import co.edu.uniquindio.exceptions.ElementoNoEncontradoException;
import co.edu.uniquindio.exceptions.UsuarioNoActivadoException;
import co.edu.uniquindio.model.documentos.Usuario;
import co.edu.uniquindio.model.enums.Ciudad;
import co.edu.uniquindio.model.enums.EstadoUsuario;
import co.edu.uniquindio.model.enums.Rol;
import co.edu.uniquindio.repositorios.UsuarioRepo;
import co.edu.uniquindio.services.AutentificacionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AutentificacionImplementTest {

    @Autowired
    private AutentificacionService autentificacionService;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    private final String emailValido = "test.login@example.com";
    private final String passwordValido = "12345";

    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        usuario = new Usuario();
        usuario.setNombre("Prueba Login");
        usuario.setEmail(emailValido);
        usuario.setPassword(passwordEncoder.encode(passwordValido));
        usuario.setEstadoUsuario(EstadoUsuario.ACTIVO);
        usuario.setDireccion("Calle Falsa 123");
        usuario.setCiudad(Ciudad.ARMENIA);
        usuario.setRol(Rol.USUARIO);
        this.usuario=usuarioRepo.save(usuario);
    }

    @AfterEach
    public void tearDown() {
        usuarioRepo.deleteAll();
    }



    @Test
    public void testInicioSesionExitoso() throws Exception {
        LoginDto loginDto = new LoginDto(emailValido, passwordValido);
        TokenDTO tokenDTO = autentificacionService.iniciarSesion(loginDto);
        assertNotNull(tokenDTO);
    }

    @Test
    public void testEmailNoExiste() {
        LoginDto loginDto = new LoginDto("correo.noexiste@example.com", passwordValido);
         assertThrows(ElementoNoEncontradoException.class, () ->
                autentificacionService.iniciarSesion(loginDto)
        );

    }

    @Test
    public void testContrasenaIncorrecta() {
        LoginDto loginDto = new LoginDto(emailValido, "claveIncorrecta");
        assertThrows(CredencialesInvalidasException.class, () ->
                autentificacionService.iniciarSesion(loginDto)
        );
    }

    @Test
    public void testUsuarioInactivo() {
        usuario.setEstadoUsuario(EstadoUsuario.INACTIVO);
        usuarioRepo.save(usuario);
        LoginDto loginDto = new LoginDto(emailValido, passwordValido);
         assertThrows(UsuarioNoActivadoException.class, () ->
                autentificacionService.iniciarSesion(loginDto)
        );
    }
}

