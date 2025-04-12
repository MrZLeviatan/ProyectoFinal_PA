package co.edu.uniquindio.controller;

import co.edu.uniquindio.dto.MensajeDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.uniquindio.dto.EliminarCuentaDto;
import co.edu.uniquindio.dto.moderador.CategoriaDTO;
import co.edu.uniquindio.dto.moderador.CrearCategoriaDto;
import co.edu.uniquindio.dto.moderador.EditarCategoriaDto;
import co.edu.uniquindio.dto.moderador.EditarModeradorDto;
import co.edu.uniquindio.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.services.ModeradorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/moderador")
public class ModeradorController {

    private final ModeradorService moderadorService;

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping
    public ResponseEntity<MensajeDTO> eliminarModerador(@RequestBody EliminarCuentaDto cuentaDto) throws Exception {
        moderadorService.eliminarModerador(cuentaDto);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta eliminada exitosamente"));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping
    public ResponseEntity<MensajeDTO> actualizarModerador(@RequestBody EditarModeradorDto moderadorAct) throws Exception {
        moderadorService.actualizarModerador(moderadorAct);
        return ResponseEntity.ok(new MensajeDTO<>(false,"Cuenta modificada exitosamente"));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerModeradorId(@PathVariable String id) throws Exception {
        return ResponseEntity.ok(moderadorService.obtenerModeradorId(id));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/email/{email}")
    public ResponseEntity<UsuarioDTO> obtenerModeradorEmail(@PathVariable String email) throws Exception {
        return ResponseEntity.ok(moderadorService.obtenerModeradorEmail(email));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/listar-usuarios")
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios(@RequestParam(required = false) String nombre,
                                                           @RequestParam(required = false) String ciudad,
                                                           @RequestParam int pagina,
                                                           @RequestParam int size) throws Exception {
        return ResponseEntity.ok(moderadorService.listarUsuarios(nombre, ciudad, pagina, size));
    }
}
