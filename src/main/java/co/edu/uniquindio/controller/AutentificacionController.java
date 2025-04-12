package co.edu.uniquindio.controller;

import co.edu.uniquindio.dto.LoginDto;
import co.edu.uniquindio.dto.MensajeDTO;
import co.edu.uniquindio.dto.TokenDTO;
import co.edu.uniquindio.services.AutentificacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Autentificar")
@RequiredArgsConstructor
public class AutentificacionController {

    private final AutentificacionService autentificacionService;

    // Iniciar sesión
    @PostMapping("/iniciarSesion")
    public ResponseEntity<MensajeDTO<TokenDTO>> iniciarSesion(@Valid @RequestBody LoginDto loginDTO ) throws Exception{
        autentificacionService.iniciarSesion(loginDTO);

        TokenDTO token = autentificacionService.iniciarSesion(loginDTO);

        return ResponseEntity.status(200).body(new MensajeDTO<>(false,  token));
    }

}