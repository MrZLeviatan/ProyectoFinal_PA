package co.edu.uniquindio.controller;

import co.edu.uniquindio.dto.LoginDto;
import co.edu.uniquindio.dto.MensajeDTO;
import co.edu.uniquindio.dto.RestablecerPasswordDto;
import co.edu.uniquindio.dto.usuario.ActivarCuentaDto;
import co.edu.uniquindio.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.services.AutentificacionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Autentifcar")
public class AutentificacionController {

    AutentificacionService autentificacionService;
// como hacer que me diga si va ser admin o no

    @PostMapping("/login")
    public ResponseEntity<MensajeDTO<String>> iniciarSesion(@Valid @RequestBody LoginDto loginDTO ) throws Exception{

        //TODO Llamar al servicio de autenticacion para validar el usuario y contraseña

        autentificacionService.iniciarSesion(loginDTO);

        return ResponseEntity.status(200).body( new MensajeDTO<>(false, "Inicio de sesion exitoso"));
    }

    @PostMapping
    public ResponseEntity<MensajeDTO<String>> crearUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO ) throws Exception{
        autentificacionService.crearUsuario(usuarioDTO);
        return ResponseEntity.status(200).body( new MensajeDTO<>(true, "Usuario creado"));

    }

    @PutMapping
    public ResponseEntity<MensajeDTO<String>> solicitarRestablecer(@Valid @RequestBody String email ) throws Exception{
        autentificacionService.solicitarRestablecer(email);
        return ResponseEntity.status(200).body( new MensajeDTO<>(true, "Restablecer"));
    }

    @PostMapping
    public ResponseEntity<MensajeDTO<String>> restablecerPassword(@Valid @RequestBody RestablecerPasswordDto restablecerPasswordDto ) throws Exception{
        autentificacionService.restablecerPassword(restablecerPasswordDto);
        return ResponseEntity.status(200).body( new MensajeDTO<>(true, "Password restablecida"));
    }

    @PostMapping
    public ResponseEntity<MensajeDTO<String>> autentificarCuenta(@Valid @RequestBody ActivarCuentaDto activarCuentaDto ) throws Exception{
        autentificacionService.activarCuenta(activarCuentaDto);
        return ResponseEntity.status(200).body( new MensajeDTO<>(true, "Cuenta activada"));
    }
}
