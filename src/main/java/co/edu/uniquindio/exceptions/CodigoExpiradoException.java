package co.edu.uniquindio.exceptions;

/**
 * Excepción personalizada lanzada cuando un código ha expirado.
 * Esta excepción se utiliza para manejar casos donde un código (por ejemplo, un código de verificación)
 * ha superado su tiempo de validez y ya no es aceptable.
 */
public class CodigoExpiradoException extends RuntimeException {
    public CodigoExpiradoException(String message) {
        super(message);
    }
}
