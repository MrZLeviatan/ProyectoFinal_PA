package co.edu.uniquindio.services.impl;

import co.edu.uniquindio.dto.EmailDto;
import co.edu.uniquindio.dto.LoginDto;
import co.edu.uniquindio.dto.RestablecerPasswordDto;
import co.edu.uniquindio.dto.usuario.ActivarCuentaDto;
import co.edu.uniquindio.dto.usuario.RegistrarUsuarioDto;
import co.edu.uniquindio.exeptions.UsuarioException;
import co.edu.uniquindio.mapper.UsuarioMapper;
import co.edu.uniquindio.model.Usuario;
import co.edu.uniquindio.repositorios.UsuarioRepo;
import co.edu.uniquindio.services.AutentificacionService;
import co.edu.uniquindio.services.EmailServicio;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuntentificacionImplement implements AutentificacionService {

    UsuarioMapper usuarioMapper;
    UsuarioRepo usuarioRepo;

    @Override
    public void iniciarSesion(LoginDto loginDTO) throws Exception {

    }

    @Override
    public void crearUsuario(RegistrarUsuarioDto usuarioDTO) throws UsuarioException {
        if(existeEmail(usuarioDTO.email())) //si el email es registrado tira una exception
            throw new UsuarioException("el correo se encuentra registrado");
        //el usuarioDto se transforma en un usuario nuevo
       // Usuario usuario = usuarioMapper.toDocument(usuarioDTO);
        //guardamos el usuario en el repositorio
        Usuario usuario= new Usuario();
        usuarioRepo.save(usuario);
    }



    @Override
    public void restablecerPassword(RestablecerPasswordDto restablecerPasswordDto) {

    }

    @Override
    public void solicitarRestablecer(String email) throws Exception {
        if(!existeEmail(email)){
            throw new UsuarioException("el correo ingresado no existe");
        }
        String codigo= generarCodigoActivacion();
        EmailServicio enviarEmail = new EmailServicioImp();
        String asunto = "Solicitud cambio de contraseña";
        String cuerpo = "su codigo de confirmacion es: "+ codigo;
        enviarEmail.enviarCorreo(new EmailDto(asunto,cuerpo,email));
    }




    @Override
    public void activarCuenta(ActivarCuentaDto activarCuentaDto) throws Exception {
        if(!verificarIdExiste(activarCuentaDto.id())){
            throw new UsuarioException("el id  no existe en el sistema");
        }
        String codigo = obtenerCodigo(activarCuentaDto.id());
        // (codigo.equals(activarCuentaDto.codigo()));
    }

    private String obtenerCodigo(@NotBlank String id) {
        return "hola";
    }

    private boolean verificarIdExiste(@NotBlank String id) {
        return true;
    }


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
}
