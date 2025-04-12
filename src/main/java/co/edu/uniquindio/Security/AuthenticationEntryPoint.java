package co.edu.uniquindio.Security;

import co.edu.uniquindio.dto.MensajeDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.security.core.AuthenticationException;
import java.io.IOException;

/**
 * Componente que maneja la autenticación y la autorización en el sistema.
 * Esta clase implementa AuthenticationEntryPoint para gestionar el acceso no autorizado
 * y devolver una respuesta personalizada en formato JSON con un mensaje de error
 * cuando un usuario no autenticado intenta acceder a recursos protegidos.
 */
@Component
public class AuthenticationEntryPoint implements org.springframework.security.web.AuthenticationEntryPoint {

    // Este método se ejecuta automáticamente cuando un usuario no autenticado intenta acceder a un recurso protegido
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        // Se crea un objeto de respuesta personalizada con un mensaje de error
        MensajeDTO<String> dto = new MensajeDTO<>(true, "No tienes los permisos necesarios para" +
                " acceder a este recurso :(");

        // Se configura la respuesta HTTP para que sea de tipo JSON y con estado 403 (Prohibido)
        response.setContentType("application/json");
        response.setStatus(403); // Código HTTP 403 - Forbidden

        // Se escribe el objeto JSON en la respuesta utilizando Jackson
        response.getWriter().write(new ObjectMapper().writeValueAsString(dto));
        response.getWriter().flush();
        response.getWriter().close(); // Se cierra el flujo de escritura
    }
}

