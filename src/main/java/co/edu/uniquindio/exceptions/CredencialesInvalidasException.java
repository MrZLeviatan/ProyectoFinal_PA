package co.edu.uniquindio.exceptions;

/**
 * Excepción personalizada lanzada cuando las credenciales proporcionadas son inválidas.
 * Se utiliza cuando un usuario intenta acceder al sistema con un nombre de usuario o contraseña incorrectos.
 */
public class CredencialesInvalidasException extends RuntimeException {
    public CredencialesInvalidasException(String message) {
        super(message);
    }
}
