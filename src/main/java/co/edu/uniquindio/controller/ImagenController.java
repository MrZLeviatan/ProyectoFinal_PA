package co.edu.uniquindio.controller;

import co.edu.uniquindio.services.ImagenServicio;
import co.edu.uniquindio.services.impl.ImagenServicioImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/imagenes")
@RequiredArgsConstructor
public class ImagenController {
   private final ImagenServicio imagenServicio;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping(consumes = "multipart/form-data")
   public ResponseEntity<String> subirImagen(@RequestParam MultipartFile imagen) throws Exception {
       imagenServicio.subirImagen(imagen);
       return ResponseEntity.noContent().build();
   }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestParam("id") String id) throws Exception {
       imagenServicio.eliminarImagen(id);
       return ResponseEntity.noContent().build();
   }
}
