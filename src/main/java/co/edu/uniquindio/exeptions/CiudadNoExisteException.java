package co.edu.uniquindio.exeptions;

public class CiudadNoExisteException extends RuntimeException {
    public CiudadNoExisteException(String message) {
        super(message);
    }
}
