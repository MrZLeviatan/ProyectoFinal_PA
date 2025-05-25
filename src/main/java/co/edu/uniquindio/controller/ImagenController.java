package co.edu.uniquindio.controller;

import co.edu.uniquindio.dto.MensajeDTO;
import co.edu.uniquindio.services.ImagenServicio;
import co.edu.uniquindio.services.impl.ImagenServicioImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.aggregation.VariableOperators;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("api/imagenes")
@RequiredArgsConstructor
public class ImagenController {
   private final ImagenServicio imagenServicio;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping(consumes = "multipart/form-data")
   public ResponseEntity<MensajeDTO<String>> subirImagen(@RequestParam MultipartFile imagen) throws Exception {
       Map map = imagenServicio.subirImagen(imagen);
       return ResponseEntity.status(200).body(new MensajeDTO<>(false,map.get("url").toString()));
   }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestParam("id") String id) throws Exception {
       imagenServicio.eliminarImagen(id);
       return ResponseEntity.noContent().build();
   }
}
