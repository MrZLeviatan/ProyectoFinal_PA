package co.edu.uniquindio.exceptions;

/**
 * Excepción personalizada lanzada cuando se deniega un permiso en el sistema.
 * Se utiliza cuando un usuario no tiene los derechos necesarios para realizar una operación específica.
 */
public class PermisoDenegadoException extends RuntimeException {
  public PermisoDenegadoException(String message) {
    super(message);
  }
}
