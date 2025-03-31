import co.edu.uniquindio.services.AutentificacionService;
import co.edu.uniquindio.services.impl.AuntentificacionImplement;
import org.junit.jupiter.api.Test;

public class AutentificacionServiceTest {
    @Test
    void enviarCodigoSolicitudRestablecer() throws Exception {
        AutentificacionService service= new AuntentificacionImplement();
        service.solicitarRestablecer("andrey3681.ay@gmail.com");
    }



}
