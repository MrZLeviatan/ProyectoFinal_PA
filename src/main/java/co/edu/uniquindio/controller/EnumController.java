package co.edu.uniquindio.controller;

import co.edu.uniquindio.dto.MensajeDTO;
import co.edu.uniquindio.model.enums.Ciudad;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/enums")
public class EnumController {

    @GetMapping("/ciudades")
    public ResponseEntity<MensajeDTO<List<String>>> obtenerCiudades() {
        List<String> ciudades = Arrays.stream(Ciudad.values())
                .map(Ciudad::name)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new MensajeDTO<>(false,ciudades));
    }
}
