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
public class RestExceptionHandler {

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<MensajeDTO<String>> noResourceFoundExceptionHandler
            (NoResourceFoundException ex) {
        return ResponseEntity.status(404).body(new MensajeDTO<>(true, "El recurso no fue encontrado"));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MensajeDTO<String>> generalExceptionHandler (Exception e){
        return ResponseEntity.internalServerError().body( new MensajeDTO<>(true, e.getMessage())
        );
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MensajeDTO<List<ValidacionDTO>>> validationExceptionHandler (
            MethodArgumentNotValidException ex ) {
        List<ValidacionDTO> errores = new ArrayList<>();
        BindingResult results = ex.getBindingResult();

        for (FieldError e: results.getFieldErrors()) {
            errores.add( new ValidacionDTO(e.getField(), e.getDefaultMessage()) );
        }

        return ResponseEntity.badRequest().body( new MensajeDTO<>(true, errores) );
    }
    //404 me indica que un elemento no existe
    @ExceptionHandler(CiudadNoExisteException.class)
    public ResponseEntity<MensajeDTO<String>> ciudadNoExisteExceptionHandler (CiudadNoExisteException ex) {
        return ResponseEntity.status(404).body(new MensajeDTO<>(true,ex.getMessage()));
    }

    //401 me indica que ubo un fallo en comparar las credenciales un login como en este caso
    @ExceptionHandler(CredencialesInvalidasException.class)
    public ResponseEntity<MensajeDTO<String>> credencialesInvalidasExceptionHandler (CredencialesInvalidasException ex) {
        return ResponseEntity.status(401).body(new MensajeDTO<>(true, ex.getMessage()));
    }

    //404 me indica que un elemento no existe
    @ExceptionHandler(ElementoNoEncontradoException.class)
    public ResponseEntity<MensajeDTO<String>> elementoNoEncontradoExceptionHandler (ElementoNoEncontradoException ex) {
        return ResponseEntity.status(404).body(new MensajeDTO<>(true, ex.getMessage()));
    }

    //409 Conflict me indica que ya existe un elemento, no se puede repetir
    @ExceptionHandler(ElementoRepetidoException.class)
    public ResponseEntity<MensajeDTO<String>> elementoRepetidoExceptionHandler (ElementoRepetidoException ex) {
        return ResponseEntity.status(409).body(new MensajeDTO<>(true, ex.getMessage()));
    }
    //403 me indica que el usuario no tiene permiso de realizar eso
    @ExceptionHandler(PermisoDenegadoException.class)
    public ResponseEntity<MensajeDTO<String>> permisoDenegadoExceptionHandler (PermisoDenegadoException ex) {
        return ResponseEntity.status(403).body(new MensajeDTO<>(true, ex.getMessage()));
    }
    // 416 Range Not Satisfiable me indica que el rango que esta trantado de usar no satisface la peticion
    @ExceptionHandler(RangoPaginaNoPermitidoException.class)
    public ResponseEntity<MensajeDTO<String>> rangoPaginaNoPermitidoExceptionHandler (RangoPaginaNoPermitidoException ex) {
        return ResponseEntity.status(416).body(new MensajeDTO<>(true, ex.getMessage()));
    }
    //indica que el codigo que ingreso esta mal
    @ExceptionHandler(CodigoIncorrectoException.class)
    public ResponseEntity<MensajeDTO<String>> codigoIncorrectoHandler(CodigoIncorrectoException ex) {
        return ResponseEntity.status(400).body(new MensajeDTO<>(true, ex.getMessage()));
    }

    //410 me indica que el dato ingresado ya esta vencido
    @ExceptionHandler(CodigoExpiradoException.class)
    public ResponseEntity<MensajeDTO<String>> codigoExpiradoHandler(CodigoExpiradoException ex) {
        return ResponseEntity.status(410).body(new MensajeDTO<>(true, ex.getMessage()));
    }
}
