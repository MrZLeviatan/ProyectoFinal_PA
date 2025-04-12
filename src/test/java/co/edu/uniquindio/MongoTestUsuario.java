package co.edu.uniquindio;

import co.edu.uniquindio.mapper.UsuarioMapper;
import co.edu.uniquindio.model.documentos.Usuario;
import co.edu.uniquindio.model.enums.Ciudad;
import co.edu.uniquindio.model.enums.EstadoUsuario;
import co.edu.uniquindio.model.enums.Rol;
import co.edu.uniquindio.model.vo.CodigoValidacion;
import co.edu.uniquindio.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
public class MongoTestUsuario {
    @Autowired
    private UsuarioRepo usuarioRepository;

    @Test
    public void agregarUsuario(){
        Usuario usuario = new Usuario();
        usuario.setNombre("andrey");
        usuario.setDireccion("mi casa");
        usuario.setCiudad(Ciudad.ARMENIA);
        usuario.setEmail("andrey3681@gmail.com");
        usuario.setPassword("123456");
        usuario.setRol(Rol.USUARIO);
        usuario.setEstadoUsuario(EstadoUsuario.INACTIVO);
        usuario.setNotificaciones(new ArrayList<>());
        usuario.setReportes(new ArrayList<>());
        usuario.setListaReportesFavorito(new ArrayList<>());
        usuario.setCodigoValidacion(new CodigoValidacion("123", LocalDateTime.now()));

        usuarioRepository.save(usuario);
    }

    @Test
    public void actualizarUsuario(){
        Optional<Usuario> usuario = usuarioRepository.findByEmail("andrey3681@gmail.com");
        Usuario usuarioActual = usuario.get();
        usuarioActual.setNombre("Andrey Felipe Tombe Fernandez");
        usuarioRepository.save(usuarioActual);
    }


}
