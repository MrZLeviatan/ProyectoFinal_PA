package co.edu.uniquindio.controller;

import co.edu.uniquindio.dto.LoginDto;
import co.edu.uniquindio.dto.MensajeDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Autentifcar")
public class AutentificacionController {


    @PostMapping("/login")
    public ResponseEntity<MensajeDTO<String>> iniciarSesion(@Valid @RequestBody LoginDto loginDTO ) throws Exception{
        //TODO Llamar al servicio de autenticacion para validar el usuario y contraseña
        return ResponseEntity.status(200).body( new MensajeDTO<>(false, "Inicio de sesion exitoso"));
    }
}
