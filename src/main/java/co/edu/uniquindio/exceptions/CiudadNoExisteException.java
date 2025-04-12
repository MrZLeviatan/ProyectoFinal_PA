package co.edu.uniquindio.exceptions;
/**
 * Excepción personalizada lanzada cuando no se encuentra una ciudad en el sistema.
 * Esta excepción se utiliza cuando se intenta realizar una operación con una ciudad
 * que no existe en la base de datos o sistema.
 */
public class CiudadNoExisteException extends RuntimeException {
    public CiudadNoExisteException(String message) {
        super(message);
    }
}
