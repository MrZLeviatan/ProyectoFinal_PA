package co.edu.uniquindio.controller;

import co.edu.uniquindio.dto.MensajeDTO;
import co.edu.uniquindio.dto.moderador.CategoriaDTO;
import co.edu.uniquindio.dto.moderador.CrearCategoriaDto;
import co.edu.uniquindio.dto.moderador.EditarCategoriaDto;
import co.edu.uniquindio.services.CategoriaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public  ResponseEntity<MensajeDTO<String>> crearCategoria(@RequestBody CrearCategoriaDto categoriaDto) throws Exception {
        categoriaService.crearCategoria(categoriaDto);
        return ResponseEntity.ok(new MensajeDTO<>(false,"Categoria creada correctamente"));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping
    public ResponseEntity<MensajeDTO> editarCategoria(@RequestBody EditarCategoriaDto editarCategoriaDto) throws Exception {
        categoriaService.editarCategoria(editarCategoriaDto);
        return ResponseEntity.ok(new MensajeDTO<>(false,"Categoria editada correctamente"));
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{id}")
    public ResponseEntity<MensajeDTO> eliminarCategoria(@PathVariable String id) throws Exception {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.ok(new MensajeDTO<>(false,"Categoria eliminada correctamente"));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> obtenerCategoriaId(@PathVariable String id) throws Exception {
        return ResponseEntity.ok(categoriaService.obtenerCategoriaId(id));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarCategorias() throws Exception {
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }
}
