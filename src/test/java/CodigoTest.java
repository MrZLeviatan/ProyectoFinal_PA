import co.edu.uniquindio.dto.EmailDto;
import co.edu.uniquindio.services.EmailServicio;
import co.edu.uniquindio.services.impl.EmailServicioImp;
import org.junit.jupiter.api.Test;

public class CodigoTest {
    @Test
    void enviarCorreo() throws Exception {
        EmailServicio enviar= new EmailServicioImp();
        enviar.enviarCorreo(new EmailDto("prueba", "prueba", "andrey3681.ay@gmail.com"));
    }
}
