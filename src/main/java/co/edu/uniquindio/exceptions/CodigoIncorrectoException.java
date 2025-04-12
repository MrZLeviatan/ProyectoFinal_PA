package co.edu.uniquindio.exceptions;

/**
 * Excepción personalizada lanzada cuando un código es incorrecto.
 * Esta excepción se utiliza cuando el código ingresado no es válido o no coincide con los registros
 * en el sistema, por ejemplo, un código de verificación erróneo.
 */
public class CodigoIncorrectoException extends RuntimeException {
    public CodigoIncorrectoException(String message) {
        super(message);
    }
}
