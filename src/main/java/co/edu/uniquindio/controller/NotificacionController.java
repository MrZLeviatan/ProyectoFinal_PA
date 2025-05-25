package co.edu.uniquindio.controller;

import co.edu.uniquindio.dto.MensajeDTO;
import co.edu.uniquindio.dto.modeloDTO.MostrarNotificacionDTO;
import co.edu.uniquindio.dto.modeloDTO.NotificacionDTOM;
import co.edu.uniquindio.services.NotificacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {

    private final NotificacionService notificacionService;

    @GetMapping("/{id}")
    public ResponseEntity<MensajeDTO<List<MostrarNotificacionDTO>>> obtenerNotificaciones(@PathVariable String id){
        List<MostrarNotificacionDTO> notifiaciones= notificacionService.leerNotificaciones(id);
        return ResponseEntity.status(200).body(new MensajeDTO<>(false,notifiaciones));
    }

    @DeleteMapping("/{idNotificacion}")
    public ResponseEntity<Void> eliminarNotificacion(@PathVariable String idNotificacion) {
        notificacionService.EliminarNotificacion(idNotificacion);
        return ResponseEntity.noContent().build();
    }


}
