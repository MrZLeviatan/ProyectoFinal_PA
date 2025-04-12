package co.edu.uniquindio.exceptions;
import co.edu.uniquindio.dto.MensajeDTO;
import co.edu.uniquindio.dto.ValidacionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice

/**
 * Clase encargada de manejar las excepciones lanzadas durante el ciclo de vida de la aplicación.
 * Utiliza anotaciones `@ExceptionHandler` para capturar diferentes tipos de excepciones y retornar una respuesta adecuada.
 */
public class RestExceptionHandler {

    /**
     * Maneja la excepción NoResourceFoundException.
     * Se devuelve un mensaje con el código de estado 404 (No encontrado) cuando el recurso solicitado no existe.
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<MensajeDTO<String>> noResourceFoundExceptionHandler(NoResourceFoundException ex) {
        return ResponseEntity.status(404).body(new MensajeDTO<>(true, "El recurso no fue encontrado"));
    }

    /**
     * Maneja excepciones generales que no fueron capturadas específicamente por otros handlers.
     * Se devuelve un mensaje de error interno con el código de estado 500.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MensajeDTO<String>> generalExceptionHandler(Exception e) {
        return ResponseEntity.internalServerError().body(new MensajeDTO<>(true, e.getMessage()));
    }

    /**
     * Maneja excepciones de validación de los argumentos recibidos en las solicitudes HTTP.
     * Devuelve los errores de validación con el código de estado 400 (Bad Request).
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MensajeDTO<List<ValidacionDTO>>> validationExceptionHandler(MethodArgumentNotValidException ex) {
        List<ValidacionDTO> errores = new ArrayList<>();
        BindingResult results = ex.getBindingResult();

        for (FieldError e : results.getFieldErrors()) {
            errores.add(new ValidacionDTO(e.getField(), e.getDefaultMessage()));
        }

        return ResponseEntity.badRequest().body(new MensajeDTO<>(true, errores));
    }

    /**
     * Maneja la excepción CiudadNoExisteException, que indica que la ciudad no fue encontrada.
     * Se devuelve un mensaje con el código de estado 404 (No encontrado).
     */
    @ExceptionHandler(CiudadNoExisteException.class)
    public ResponseEntity<MensajeDTO<String>> ciudadNoExisteExceptionHandler(CiudadNoExisteException ex) {
        return ResponseEntity.status(404).body(new MensajeDTO<>(true, ex.getMessage()));
    }

    /**
     * Maneja la excepción CredencialesInvalidasException, que se lanza cuando las credenciales de un usuario no son válidas.
     * Se devuelve un mensaje con el código de estado 401 (Unauthorized).
     */
    @ExceptionHandler(CredencialesInvalidasException.class)
    public ResponseEntity<MensajeDTO<String>> credencialesInvalidasExceptionHandler(CredencialesInvalidasException ex) {
        return ResponseEntity.status(401).body(new MensajeDTO<>(true, ex.getMessage()));
    }

    /**
     * Maneja la excepción ElementoNoEncontradoException, que se lanza cuando un elemento no es encontrado.
     * Se devuelve un mensaje con el código de estado 404 (No encontrado).
     */
    @ExceptionHandler(ElementoNoEncontradoException.class)
    public ResponseEntity<MensajeDTO<String>> elementoNoEncontradoExceptionHandler(ElementoNoEncontradoException ex) {
        return ResponseEntity.status(404).body(new MensajeDTO<>(true, ex.getMessage()));
    }

    /**
     * Maneja la excepción ElementoRepetidoException, que se lanza cuando un elemento ya existe.
     * Se devuelve un mensaje con el código de estado 409 (Conflict).
     */
    @ExceptionHandler(ElementoRepetidoException.class)
    public ResponseEntity<MensajeDTO<String>> elementoRepetidoExceptionHandler(ElementoRepetidoException ex) {
        return ResponseEntity.status(409).body(new MensajeDTO<>(true, ex.getMessage()));
    }

    /**
     * Maneja la excepción PermisoDenegadoException, que se lanza cuando un usuario no tiene permisos para realizar una acción.
     * Se devuelve un mensaje con el código de estado 403 (Forbidden).
     */
    @ExceptionHandler(PermisoDenegadoException.class)
    public ResponseEntity<MensajeDTO<String>> permisoDenegadoExceptionHandler(PermisoDenegadoException ex) {
        return ResponseEntity.status(403).body(new MensajeDTO<>(true, ex.getMessage()));
    }

    /**
     * Maneja la excepción RangoPaginaNoPermitidoException, que se lanza cuando el rango de la página no es válido o permitido.
     * Se devuelve un mensaje con el código de estado 416 (Range Not Satisfiable).
     */
    @ExceptionHandler(RangoPaginaNoPermitidoException.class)
    public ResponseEntity<MensajeDTO<String>> rangoPaginaNoPermitidoExceptionHandler(RangoPaginaNoPermitidoException ex) {
        return ResponseEntity.status(416).body(new MensajeDTO<>(true, ex.getMessage()));
    }

    /**
     * Maneja la excepción CodigoIncorrectoException, que se lanza cuando el código ingresado es incorrecto.
     * Se devuelve un mensaje con el código de estado 400 (Bad Request).
     */
    @ExceptionHandler(CodigoIncorrectoException.class)
    public ResponseEntity<MensajeDTO<String>> codigoIncorrectoHandler(CodigoIncorrectoException ex) {
        return ResponseEntity.status(400).body(new MensajeDTO<>(true, ex.getMessage()));
    }

    /**
     * Maneja la excepción CodigoExpiradoException, que se lanza cuando el código ingresado ha expirado.
     * Se devuelve un mensaje con el código de estado 410 (Gone).
     */
    @ExceptionHandler(CodigoExpiradoException.class)
    public ResponseEntity<MensajeDTO<String>> codigoExpiradoHandler(CodigoExpiradoException ex) {
        return ResponseEntity.status(410).body(new MensajeDTO<>(true, ex.getMessage()));
    }
}
