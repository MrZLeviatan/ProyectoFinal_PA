import co.edu.uniquindio.utils.EnviarCodigoActivacion;
import co.edu.uniquindio.utils.service.Notificación;
import org.junit.jupiter.api.Test;

public class CodigoTest {
    @Test
    void enviar(){
        Notificación enviarCodigoActivacion = new EnviarCodigoActivacion("prueba","nikis281002@gmail.com","axmiadsladj");
        enviarCodigoActivacion.EnviarNotificacion();
    }
}
