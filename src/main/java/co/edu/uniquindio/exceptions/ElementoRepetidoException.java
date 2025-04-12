package co.edu.uniquindio.exceptions;

/**
 * Excepción personalizada lanzada cuando un elemento ya existe en el sistema.
 * Se utiliza cuando se intenta agregar un elemento que ya está presente, como cuando se intenta crear un registro duplicado.
 */
public class ElementoRepetidoException extends RuntimeException {
    public ElementoRepetidoException(String message) {
        super(message);
    }
}
