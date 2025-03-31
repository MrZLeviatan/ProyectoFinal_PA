package co.edu.uniquindio.controller;

import co.edu.uniquindio.dto.LoginDto;
import co.edu.uniquindio.dto.MensajeDTO;
import co.edu.uniquindio.dto.RestablecerPasswordDto;
import co.edu.uniquindio.dto.usuario.ActivarCuentaDto;
import co.edu.uniquindio.dto.usuario.RegistrarUsuarioDto;
import co.edu.uniquindio.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.services.AutentificacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/Autentifcar")
@RequiredArgsConstructor
public class AutentificacionController {

    private final AutentificacionService autentificacionService;

    // Iniciar sesión
    @PostMapping("/iniciarSesion")
    public ResponseEntity<MensajeDTO<String>> iniciarSesion(@Valid @RequestBody LoginDto loginDTO ) throws Exception{
        autentificacionService.iniciarSesion(loginDTO);
        return ResponseEntity.status(200).body(new MensajeDTO<>(false, "Inicio de sesión exitoso"));
    }

    // Crear usuario
    @PostMapping("/crearUsuario")
    public ResponseEntity<MensajeDTO<String>> crearUsuario(@Valid @RequestBody RegistrarUsuarioDto usuarioDTO ) throws Exception{
        autentificacionService.crearUsuario(usuarioDTO);
        return ResponseEntity.status(200).body(new MensajeDTO<>(true, "Usuario creado"));
    }

    // Solicitar restablecimiento de contraseña
    @PutMapping("/solicitarRestablecer")
    public ResponseEntity<MensajeDTO<String>> solicitarRestablecer(@Valid @RequestBody String email ) throws Exception{
        autentificacionService.solicitarRestablecer(email);
        return ResponseEntity.status(200).body(new MensajeDTO<>(true, "Restablecer"));
    }

    // Restablecer contraseña
    @PostMapping("/restablecerPassword")
    public ResponseEntity<MensajeDTO<String>> restablecerPassword(@Valid @RequestBody RestablecerPasswordDto restablecerPasswordDto ) throws Exception{
        autentificacionService.restablecerPassword(restablecerPasswordDto);
        return ResponseEntity.status(200).body(new MensajeDTO<>(true, "Password restablecida"));
    }

    // Activar cuenta
    @PostMapping("/activarCuenta")
    public ResponseEntity<MensajeDTO<String>> autentificarCuenta(@Valid @RequestBody ActivarCuentaDto activarCuentaDto ) throws Exception{
        autentificacionService.activarCuenta(activarCuentaDto);
        return ResponseEntity.status(200).body(new MensajeDTO<>(true, "Cuenta activada"));
    }
}