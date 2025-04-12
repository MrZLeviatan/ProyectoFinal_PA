package co.edu.uniquindio.exceptions;

/**
 * Excepción personalizada lanzada cuando el rango de página solicitado no está permitido.
 * Se utiliza cuando un usuario intenta acceder a una página fuera de los límites o de forma no válida.
 */
public class RangoPaginaNoPermitidoException extends RuntimeException {
    public RangoPaginaNoPermitidoException(String message) {
        super(message);
    }
}
