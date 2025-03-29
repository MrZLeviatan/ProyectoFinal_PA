package co.edu.uniquindio.controller;

import co.edu.uniquindio.dto.EliminarCuentaDto;
import co.edu.uniquindio.dto.MensajeDTO;
import co.edu.uniquindio.dto.usuario.EditarUsuarioDto;
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


    @SecurityRequirement(name = "cookieAuth")  // 🔐 Método protegido (requiere autenticación con cookies)
    @GetMapping("/{id}")
    public ResponseEntity<MensajeDTO<UsuarioDTO>> obtenerId(@PathVariable String id) throws Exception {
        UsuarioDTO info = usuarioService.obtenerUsuarioId(id);
        return ResponseEntity.ok(new MensajeDTO<>(false,info));
    }


    @SecurityRequirement(name = "cookieAuth")  // 🔐 Método protegido (requiere autenticación con cookies)
    @GetMapping("/{email}")
    public ResponseEntity<MensajeDTO<UsuarioDTO>> obtenerEmail(@PathVariable String id) throws Exception {
        UsuarioDTO info = usuarioService.obtenerUsuarioEmail(id);
        return ResponseEntity.ok(new MensajeDTO<>(false,info));
    }


    @SecurityRequirement(name = "cookieAuth")  // 🔐 Método protegido (requiere autenticación con cookies)
    @DeleteMapping
    public ResponseEntity<MensajeDTO<String>> eliminarUsuario(@RequestBody EliminarCuentaDto cuentaDto) throws Exception {
        usuarioService.eliminarUsuario(cuentaDto);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta eliminada exitosamente"));
    }

    @SecurityRequirement(name = "cookieAuth")  // 🔐 Método protegido (requiere autenticación con cookies)
    @PutMapping
    public ResponseEntity<MensajeDTO<String>> editarUsuario(@Valid @RequestBody EditarUsuarioDto cuenta) throws Exception{
        usuarioService.actualizarUsuario(cuenta);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta editada exitosamente"));
    }




}
