package co.edu.uniquindio.exeptions;

public class RangoPaginaNoPermitido extends RuntimeException {
    public RangoPaginaNoPermitido(String message) {
        super(message);
    }
}
