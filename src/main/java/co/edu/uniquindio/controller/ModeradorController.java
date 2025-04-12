package co.edu.uniquindio.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.uniquindio.dto.EliminarCuentaDto;
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

    @DeleteMapping("/eliminar")
    public ResponseEntity<Void> eliminarModerador(@RequestBody EliminarCuentaDto cuentaDto) throws Exception {
        moderadorService.eliminarModerador(cuentaDto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Void> actualizarModerador(@RequestBody EditarModeradorDto moderadorAct) throws Exception {
        moderadorService.actualizarModerador(moderadorAct);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<UsuarioDTO> obtenerModeradorId(@PathVariable String id) throws Exception {
        return ResponseEntity.ok(moderadorService.obtenerModeradorId(id));
    }

    @GetMapping("/obtener/email/{email}")
    public ResponseEntity<UsuarioDTO> obtenerModeradorEmail(@PathVariable String email) throws Exception {
        return ResponseEntity.ok(moderadorService.obtenerModeradorEmail(email));
    }

    @GetMapping("/listar-usuarios")
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios(@RequestParam(required = false) String nombre,
                                                           @RequestParam(required = false) String ciudad,
                                                           @RequestParam int pagina,
                                                           @RequestParam int size) throws Exception {
        return ResponseEntity.ok(moderadorService.listarUsuarios(nombre, ciudad, pagina, size));
    }
}
