package co.edu.uniquindio.exceptions;

/**
 * Excepción personalizada lanzada cuando un usuario intenta realizar una acción sin haber activado su cuenta.
 * Se utiliza cuando un usuario no ha completado el proceso de activación de cuenta y no puede acceder al sistema.
 */
public class UsuarioNoActivadoException extends RuntimeException {
    public UsuarioNoActivadoException(String message) {
        super(message);
    }
}
