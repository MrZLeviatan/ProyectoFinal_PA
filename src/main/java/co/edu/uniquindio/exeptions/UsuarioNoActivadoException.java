package co.edu.uniquindio.exeptions;

public class UsuarioNoActivadoException extends RuntimeException {
    public UsuarioNoActivadoException(String message) {
        super(message);
    }
}
