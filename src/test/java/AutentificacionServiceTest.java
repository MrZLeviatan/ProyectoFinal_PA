import co.edu.uniquindio.services.AutentificacionService;
import co.edu.uniquindio.services.impl.AuntentificacionImplement;
import org.junit.jupiter.api.Test;

public class AutentificacionServiceTest {
    AutentificacionService service= new AuntentificacionImplement();
    @Test
    void enviarCodigoSolicitudRestablecer() throws Exception {

        service.solicitarRestablecer("andrey3681.ay@gmail.com");
    }

    @Test
    void enviarCodigoAutentificacion() throws Exception {

    }



}
