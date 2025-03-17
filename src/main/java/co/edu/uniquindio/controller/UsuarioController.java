package co.edu.uniquindio.controller;

import co.edu.uniquindio.services.SercioUsuarioImplement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")

public class UsuarioController {
    private SercioUsuarioImplement sercioUsuarioImplement;

    public void inicio(){
        sercioUsuarioImplement.iniciarSeccion("correo","contraseña");
    }

    public void cerrarSeccio(){
        sercioUsuarioImplement.cerrarSeccion();
    }
}
