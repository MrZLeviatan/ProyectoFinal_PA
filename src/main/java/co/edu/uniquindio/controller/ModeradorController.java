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

    // Servicio que contiene la lógica de negocio asociada a los moderadores.
    private final ModeradorService moderadorService;

    /**
     * Endpoint para eliminar un moderador del sistema.
     * Recibe un DTO con la información necesaria (ID y contraseña) para eliminar al moderador.
     *
     * @param cuentaDto: Objeto con los datos del moderador a eliminar.
     * @return ResponseEntity sin contenido si la eliminación es exitosa.
     * @throws Exception: Si ocurre un error durante el proceso.
     */
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping
    public ResponseEntity<MensajeDTO> eliminarModerador(@RequestBody EliminarCuentaDto cuentaDto) throws Exception {
        moderadorService.eliminarModerador(cuentaDto);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta eliminada exitosamente"));
    }

    /**
     * Endpoint para actualizar la información de un moderador.
     * Recibe un DTO con los datos modificados del moderador.
     *
     * @param moderadorAct: Objeto con la información actualizada del moderador.
     * @return ResponseEntity sin contenido si la actualización es exitosa.
     * @throws Exception: Si ocurre un error durante el proceso.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping
    public ResponseEntity<MensajeDTO> actualizarModerador(@RequestBody EditarModeradorDto moderadorAct) throws Exception {
        moderadorService.actualizarModerador(moderadorAct);
        return ResponseEntity.ok(new MensajeDTO<>(false,"Cuenta modificada exitosamente"));
    }

    /**
     * Endpoint para obtener un moderador según su identificador único.
     *
     * @param id: Identificador del moderador.
     * @return ResponseEntity con los datos del moderador si se encuentra.
     * @throws Exception: Si no se encuentra el moderador o hay un error.
     */

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerModeradorId(@PathVariable String id) throws Exception {
        return ResponseEntity.ok(moderadorService.obtenerModeradorId(id));
    }

    /**
     * Endpoint para obtener un moderador según su dirección de correo electrónico.
     *
     * @param email: Correo electrónico del moderador.
     * @return ResponseEntity con los datos del moderador si se encuentra.
     * @throws Exception: Si no se encuentra el moderador o hay un error.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/email/{email}")
    public ResponseEntity<UsuarioDTO> obtenerModeradorEmail(@PathVariable String email) throws Exception {
        return ResponseEntity.ok(moderadorService.obtenerModeradorEmail(email));
    }

    /**
     * Endpoint para listar los usuarios registrados, con la opción de aplicar filtros por nombre y ciudad.
     * La lista es paginada según los parámetros de página y tamaño.
     *
     * @param nombre: (Opcional) Filtro por nombre del usuario.
     * @param ciudad: (Opcional) Filtro por ciudad del usuario.
     * @param pagina: Número de página que se desea consultar.
     * @param size: Cantidad de elementos por página.
     * @return ResponseEntity con una lista de usuarios filtrados y paginados.
     * @throws Exception: Si ocurre un error durante el proceso.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/listar-usuarios")
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios(@RequestParam(required = false) String nombre,
                                                           @RequestParam(required = false) String ciudad,
                                                           @RequestParam int pagina,
                                                           @RequestParam int size) throws Exception {
        return ResponseEntity.ok(moderadorService.listarUsuarios(nombre, ciudad, pagina, size));
    }
}
