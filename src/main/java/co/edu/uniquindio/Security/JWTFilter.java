package co.edu.uniquindio.Security;

// Importaciones necesarias para trabajar con JWT, filtros de seguridad y autenticación en Spring
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component // Indica que esta clase es un componente manejado por Spring
@RequiredArgsConstructor // Genera automáticamente un constructor con los atributos marcados como final
public class JWTFilter extends OncePerRequestFilter {

    // Utilidad para trabajar con tokens JWT
    private final JWTUtils jwtUtil;

    // Método principal del filtro que se ejecuta una vez por solicitud
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // Se extrae el token JWT del encabezado "Authorization" de la solicitud
        String token = getToken(request);

        // Si no hay token, se continúa con el flujo normal sin autenticación
        if (token == null) {
            chain.doFilter(request, response);
            return;
        }

        try {
            // Se valida y se interpreta el token para obtener la información del usuario
            Jws<Claims> payload = jwtUtil.parseJwt(token);
            String username = payload.getPayload().getSubject(); // Usuario
            String role = payload.getPayload().get("rol", String.class); // Rol del usuario

            // Si aún no hay autenticación en el contexto de seguridad, se crea una
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // Se crea un objeto con los datos del usuario (nombre y rol)
                UserDetails userDetails = new User(
                        username,
                        "", // No se requiere contraseña aquí
                        List.of(new SimpleGrantedAuthority(role)) // Autoridades según el rol
                );

                // Se genera un token de autenticación que Spring Security reconocerá
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                // Se guarda la autenticación en el contexto de seguridad
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (Exception e) {
            // Si hay algún error con el token (expirado, mal formado, inválido), se retorna un error 401
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return;
        }

        // Si todo está bien, se continúa con el procesamiento normal de la solicitud
        chain.doFilter(request, response);
    }

    // Método auxiliar para extraer el token JWT del encabezado "Authorization"
    private String getToken(HttpServletRequest req) {
        String header = req.getHeader("Authorization");

        // Verifica que el encabezado comience con "Bearer " y extrae el token
        return header != null && header.startsWith("Bearer ") ? header.replace("Bearer ", "") : null;
    }
}
