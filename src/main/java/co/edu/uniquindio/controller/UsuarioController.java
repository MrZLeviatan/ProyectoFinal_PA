package co.edu.uniquindio.controller;

import co.edu.uniquindio.dto.EliminarCuentaDto;
import co.edu.uniquindio.dto.MensajeDTO;
import co.edu.uniquindio.dto.RestablecerPasswordDto;
import co.edu.uniquindio.dto.reporte.ReporteDTO;
import co.edu.uniquindio.dto.usuario.ActivarCuentaDto;
import co.edu.uniquindio.dto.usuario.EditarUsuarioDto;
import co.edu.uniquindio.dto.usuario.RegistrarUsuarioDto;
import co.edu.uniquindio.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.services.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    /**
     * 🔐 Obtiene la información de un usuario a partir de su ID.
     *
     * @param id: Identificador del usuario a buscar.
     * @return ResponseEntity con los datos del usuario encontrados.
     * @throws Exception: Si ocurre un error durante la búsqueda.
     */

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public ResponseEntity<MensajeDTO<UsuarioDTO>> obtenerId(@PathVariable String id) throws Exception {
        UsuarioDTO info = usuarioService.obtenerUsuarioId(id);
        return ResponseEntity.ok(new MensajeDTO<>(false,info));
    }
    /**
     * 🔐 Obtiene la información de un usuario a partir de su correo electrónico.
     *
     * @param email: Id del usuario (pasado como parámetro en la URL).
     * @return ResponseEntity con los datos del usuario encontrados.
     * @throws Exception: Si ocurre un error durante la búsqueda.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/email/{email}")
    public ResponseEntity<MensajeDTO<UsuarioDTO>> obtenerEmail(@PathVariable String email) throws Exception {
        UsuarioDTO info = usuarioService.obtenerUsuarioEmail(email);
        return ResponseEntity.ok(new MensajeDTO<>(false,info));
    }

    /**
     * 🔐 Elimina la cuenta de un usuario.
     *
     * @param cuentaDto: Datos necesarios para eliminar la cuenta.
     * @return Mensaje confirmando la eliminación exitosa.
     * @throws Exception: Si ocurre un error al eliminar la cuenta.
     */
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping
    public ResponseEntity<MensajeDTO<String>> eliminarUsuario(@RequestBody EliminarCuentaDto cuentaDto) throws Exception {
        usuarioService.eliminarUsuario(cuentaDto);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta eliminada exitosamente"));
    }

    /**
     * 🔐 Edita la información de un usuario.
     *
     * @param cuenta: Datos actualizados del usuario.
     * @return Mensaje confirmando la edición exitosa.
     * @throws Exception: Si ocurre un error al editar la información.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping
    public ResponseEntity<MensajeDTO<String>> editarUsuario(@Valid @RequestBody EditarUsuarioDto cuenta) throws Exception{
        usuarioService.actualizarUsuario(cuenta);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta editada exitosamente"));
    }

    /**
     * Crea un nuevo usuario en el sistema.
     *
     * @param usuarioDTO: Datos del nuevo usuario a registrar.
     * @return Mensaje confirmando la creación del usuario.
     * @throws Exception: Si ocurre un error durante el registro.
     */
    @PostMapping
    public ResponseEntity<MensajeDTO<String>> crearUsuario(@Valid @RequestBody RegistrarUsuarioDto usuarioDTO ) throws Exception{
        usuarioService.crearUsuario(usuarioDTO);
        return ResponseEntity.status(200).body(new MensajeDTO<>(false, "Usuario creado"));
    }

    /**
     * Solicita el envío de un código para restablecer la contraseña.
     *
     * @param email: Correo del usuario que desea restablecer su contraseña.
     * @return Mensaje indicando que el proceso fue iniciado.
     * @throws Exception: Si ocurre un error al enviar el código.
     */
    @PostMapping("/codigoVerificacion/{email}")
    public ResponseEntity<MensajeDTO<String>> solicitarRestablecer(@PathVariable String email) throws Exception{
        usuarioService.solicitarRestablecer(email);
        return ResponseEntity.status(200).body(new MensajeDTO<>(false, "Restablecer"));
    }

    /**
     * Restablece la contraseña de un usuario.
     *
     * @param restablecerPasswordDto: Datos necesarios para realizar el cambio de contraseña.
     * @return Mensaje confirmando el cambio exitoso.
     * @throws Exception: Si ocurre un error durante el proceso.
     */
    @PutMapping("/password")
    public ResponseEntity<MensajeDTO<String>> restablecerPassword(@Valid @RequestBody RestablecerPasswordDto restablecerPasswordDto ) throws Exception{
        usuarioService.restablecerPassword(restablecerPasswordDto);
        return ResponseEntity.status(200).body(new MensajeDTO<>(false, "Password restablecida"));
    }

    /**
     * Activa la cuenta de un usuario mediante un código de verificación.
     *
     * @param activarCuentaDto: Datos necesarios para activar la cuenta.
     * @return Mensaje confirmando que la cuenta ha sido activada.
     * @throws Exception: Si ocurre un error durante la activación.
     */
    @PutMapping("/estado")
    public ResponseEntity<MensajeDTO<String>> autentificarCuenta(@Valid @RequestBody ActivarCuentaDto activarCuentaDto ) throws Exception{
        usuarioService.activarCuenta(activarCuentaDto);
        return ResponseEntity.status(200).body(new MensajeDTO<>(false, "Cuenta activada"));
    }

    @GetMapping("/reportes/{id}")
    public ResponseEntity<MensajeDTO<List<ReporteDTO>>> obtenerReportesUsuario( @PathVariable String id) throws Exception{
        List<ReporteDTO> reporteDTOList= usuarioService.obtenerReportesUsuario(id);
        return ResponseEntity.status(200).body(new MensajeDTO<>(false,reporteDTOList));
    }





}


