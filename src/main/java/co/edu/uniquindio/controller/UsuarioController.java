package co.edu.uniquindio.controller;

import co.edu.uniquindio.dto.EliminarCuentaDto;
import co.edu.uniquindio.dto.MensajeDTO;
import co.edu.uniquindio.dto.RestablecerPasswordDto;
import co.edu.uniquindio.dto.usuario.ActivarCuentaDto;
import co.edu.uniquindio.dto.usuario.EditarUsuarioDto;
import co.edu.uniquindio.dto.usuario.RegistrarUsuarioDto;
import co.edu.uniquindio.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.services.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;


    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public ResponseEntity<MensajeDTO<UsuarioDTO>> obtenerId(@PathVariable String id) throws Exception {
        UsuarioDTO info = usuarioService.obtenerUsuarioId(id);
        return ResponseEntity.ok(new MensajeDTO<>(false,info));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/email/{email}")
    public ResponseEntity<MensajeDTO<UsuarioDTO>> obtenerEmail(@PathVariable String email) throws Exception {
        UsuarioDTO info = usuarioService.obtenerUsuarioEmail(email);
        return ResponseEntity.ok(new MensajeDTO<>(false,info));
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping
    public ResponseEntity<MensajeDTO<String>> eliminarUsuario(@RequestBody EliminarCuentaDto cuentaDto) throws Exception {
        usuarioService.eliminarUsuario(cuentaDto);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta eliminada exitosamente"));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping
    public ResponseEntity<MensajeDTO<String>> editarUsuario(@Valid @RequestBody EditarUsuarioDto cuenta) throws Exception{
        usuarioService.actualizarUsuario(cuenta);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta editada exitosamente"));
    }


    // Crear usuario
    @PostMapping
    public ResponseEntity<MensajeDTO<String>> crearUsuario(@Valid @RequestBody RegistrarUsuarioDto usuarioDTO ) throws Exception{
        usuarioService.crearUsuario(usuarioDTO);
        return ResponseEntity.status(200).body(new MensajeDTO<>(true, "Usuario creado"));
    }

    // Solicitar restablecimiento de contraseña
    @PostMapping("/codigoVerificacion/{email}")
    public ResponseEntity<MensajeDTO<String>> solicitarRestablecer(@PathVariable String email) throws Exception{
        usuarioService.solicitarRestablecer(email);
        return ResponseEntity.status(200).body(new MensajeDTO<>(true, "Restablecer"));
    }

    // Restablecer contraseña
    @PutMapping("/password")
    public ResponseEntity<MensajeDTO<String>> restablecerPassword(@Valid @RequestBody RestablecerPasswordDto restablecerPasswordDto ) throws Exception{
        usuarioService.restablecerPassword(restablecerPasswordDto);
        return ResponseEntity.status(200).body(new MensajeDTO<>(true, "Password restablecida"));
    }

    // Activar cuenta
    @PutMapping("/estado")
    public ResponseEntity<MensajeDTO<String>> autentificarCuenta(@Valid @RequestBody ActivarCuentaDto activarCuentaDto ) throws Exception{
        usuarioService.activarCuenta(activarCuentaDto);
        return ResponseEntity.status(200).body(new MensajeDTO<>(true, "Cuenta activada"));
    }
}
