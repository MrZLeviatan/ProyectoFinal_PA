package co.edu.uniquindio.services.impl;

import co.edu.uniquindio.dto.EmailDto;
import co.edu.uniquindio.dto.LoginDto;
import co.edu.uniquindio.dto.RestablecerPasswordDto;
import co.edu.uniquindio.dto.TokenDTO;
import co.edu.uniquindio.dto.usuario.ActivarCuentaDto;
import co.edu.uniquindio.dto.usuario.RegistrarUsuarioDto;
import co.edu.uniquindio.exeptions.UsuarioException;
import co.edu.uniquindio.mapper.UsuarioMapper;
import co.edu.uniquindio.model.vo.CodigoValidacion;
import co.edu.uniquindio.model.documentos.Usuario;
import co.edu.uniquindio.model.enums.EstadoUsuario;
import co.edu.uniquindio.model.enums.Rol;
import co.edu.uniquindio.repositorios.UsuarioRepo;
import co.edu.uniquindio.services.AutentificacionService;
import co.edu.uniquindio.services.EmailServicio;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuntentificacionImplement implements AutentificacionService {

    UsuarioMapper usuarioMapper;
    UsuarioRepo usuarioRepo;


    @Override
    public void iniciarSesion(LoginDto loginDTO) throws Exception {
        Optional<Usuario> usuarioOptional= usuarioRepo.findByEmail(loginDTO.email());
        if(usuarioOptional.isEmpty()){
            throw new UsuarioException("El email no existe");
        }
        Usuario usuario = usuarioOptional.get();
        if(usuario.getPassword().equals(loginDTO.password())){
            if(usuario.getEstadoUsuario() == EstadoUsuario.ACTIVO){
                if(usuario.getRol()== Rol.USUARIO){
                    // aca que se vaya a la vista de un usuario
                }else {
                    //aca diriamos que se vaya a la vista de un moderador
                }
            }else {
                throw new UsuarioException("El usuario debe activarse primero ");
                //aca le mandariamos algo???
            }
        }else {
            throw new UsuarioException("Contraseña incorrecta");
        }
    }

    @Override
    public void crearUsuario(RegistrarUsuarioDto usuarioDTO) throws UsuarioException {
        if(existeEmail(usuarioDTO.email())) //si el email es registrado tira una exception
            throw new UsuarioException("el correo se encuentra registrado");
        //el usuarioDto se transforma en un usuario nuevo
        Usuario usuario = usuarioMapper.toDocument(usuarioDTO);
        //guardamos el usuario en el repositorio
        usuarioRepo.save(usuario);
    }



    @Override
    public void restablecerPassword(RestablecerPasswordDto restablecerPasswordDto) throws UsuarioException {
        Optional<Usuario> usuario = usuarioRepo.findByEmail(restablecerPasswordDto.email());
        if (usuario.isPresent()) {
            usuario.get().setPassword(restablecerPasswordDto.password());
            usuarioRepo.save(usuario.get());
        }else {
            throw new UsuarioException("el correo ingresado no existe");
        }
    }

    @Override
    public void solicitarRestablecer(String email) throws Exception {
        if(!existeEmail(email)){
            throw new UsuarioException("el correo ingresado no existe");
        }
        String codigo= generarCodigoActivacion();
        EmailServicio enviarEmail = new EmailServicioImp();
        String asunto = "Solicitud cambio de contraseña";
        String cuerpo = "su codigoActivacion de confirmacion es: "+ codigo;
        enviarEmail.enviarCorreo(new EmailDto(asunto,cuerpo,email));


        // esto debemos pensarlo mejor porque tin como lo hariamos

    }



    @Override
    public void activarCuenta(ActivarCuentaDto activarCuentaDto) throws Exception {
        // Obtenemos el usuario y verificamos si existe en la base de datos
        Optional<Usuario> usuarioOptional = usuarioRepo.findById(new ObjectId(activarCuentaDto.id()));
        if (usuarioOptional.isEmpty()) {
            throw new UsuarioException("El ID no existe en el sistema");
        }

        Usuario usuario = usuarioOptional.get();
        CodigoValidacion codigoValidacion = usuario.getCodigoValidacion();

        if (codigoValidacion == null) {
            throw new UsuarioException("El código no existe en el sistema");
        }

        // Verificamos si el código de validación ha vencido
        if (isCodVen(codigoValidacion)) {
            throw new UsuarioException("El código ingresado ya venció");
        }

        // Verificamos si el código es correcto
        if (!codigoValidacion.getCodigo().equals(activarCuentaDto.codigoActivacion().codigo())){
            throw new UsuarioException("El código de confirmación no es correcto");
        }

        // Eliminamos el código de validación del usuario
        eliminarCodigo(usuario);
    }

    private void eliminarCodigo(Usuario usuario) {
        //eliminamos el atributo codigoActivacion ya no es necesario
        usuario.setCodigoValidacion(null);
        usuario.setEstadoUsuario(EstadoUsuario.ACTIVO);
        usuarioRepo.save(usuario);
    }

    private boolean isCodVen(CodigoValidacion codigoValidacion) {
        // Compara si el código ha vencido
        LocalDateTime limite = LocalDateTime.now().minusMinutes(15);
        return codigoValidacion.getHoraCreacion().isBefore(limite);
    }

    // metodo creado para generar El codigoActivacion de Activacion del usuario
    private String generarCodigoActivacion() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"; // Caracteres posibles
        Random rand = new Random();
        StringBuilder codigo = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int indiceAleatorio = rand.nextInt(caracteres.length());
            codigo.append(caracteres.charAt(indiceAleatorio));
        }

        return codigo.toString();
    }

    private boolean existeEmail(String email){
        return usuarioRepo.findByEmail(email).isPresent();
    }

    public TokenDTO login(LoginDto loginDTO) throws Exception {

        Optional<Usuario> optionalUsuario = usuarioRepo.findByEmail(loginDTO.email());

        if (optionalUsuario.isEmpty()) {
            throw new Exception("El usuario no existe");
        }

        Usuario usuario = optionalUsuario.get();

        // Verificar si la contraseña es correcta usando el PasswordEncoder
        if (!passwordEncoder.matches(loginDTO.password(), usuario.getPassword())) {
            throw new Exception("El usuario no existe");
        }

        String token = jwtUtils.generateToken(usuario.getId().toString(), crearClaims(usuario));
        return new TokenDTO(token);
    }

    private Map<String, String> crearClaims(Usuario usuario) {
        return Map.of(
                "email", usuario.getEmail(),
                "nombre", usuario.getNombre(),
                "rol", "ROLE_" + usuario.getRol().name()
        );
    }
}
