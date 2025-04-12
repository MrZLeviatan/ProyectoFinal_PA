package co.edu.uniquindio.exceptions;

/**
 * Excepción personalizada lanzada cuando un elemento no se encuentra en el sistema.
 * Se utiliza cuando se intenta acceder o realizar una operación con un elemento que no existe en la base de datos.
 */
public class ElementoNoEncontradoException extends RuntimeException {
    public ElementoNoEncontradoException(String message) {
        super(message);
    }
}
