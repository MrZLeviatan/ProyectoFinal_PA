package co.edu.uniquindio.controller;

import co.edu.uniquindio.dto.LoginDto;
import co.edu.uniquindio.dto.MensajeDTO;
import co.edu.uniquindio.services.AutentificacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/Autentifcar")
@RequiredArgsConstructor
public class AutentificacionController {

    // Servicio encargado de la lógica de autenticación.
    private final AutentificacionService autentificacionService;

    /**
     * Endpoint para iniciar sesión en el sistema.
     * Recibe las credenciales del usuario mediante un objeto LoginDto,
     * las valida, y si son correctas, permite el inicio de sesión.
     *
     * @param loginDTO: Datos de acceso del usuario (correo y contraseña).
     * @return ResponseEntity con un mensaje de éxito si la autenticación es correcta.
     * @throws Exception: Si ocurre un error durante el proceso de autenticación.
     */
    @PostMapping("/iniciarSesion")
    public ResponseEntity<MensajeDTO<String>> iniciarSesion(@Valid @RequestBody LoginDto loginDTO ) throws Exception {
        autentificacionService.iniciarSesion(loginDTO);
        return ResponseEntity.status(200).body(new MensajeDTO<>(false, "Inicio de sesión exitoso"));
    }
}
