package co.edu.uniquindio.Security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

/**
 * Clase utilitaria para generar y validar tokens JWT.
 * Esta clase es responsable de crear tokens JWT con una duración determinada y de validar la autenticidad de los mismos.
 */
@Component
public class JWTUtils {

    public String generateToken(String id, Map<String, String> claims) {

        Instant now = Instant.now(); // Se obtiene el instante actual

        // Se construye el token JWT con los datos proporcionados
        return Jwts.builder()
                .claims(claims) // Se agregan los claims (información adicional)
                .subject(id) // Se establece el subject del token, que normalmente es el ID del usuario
                .issuedAt(Date.from(now)) // Fecha de emisión del token
                .expiration(Date.from(now.plus(1L, ChronoUnit.HOURS))) // Fecha de expiración del token (1 hora después)
                .signWith( getKey() ) // Se firma el token con la clave secreta
                .compact(); // Se genera el token como una cadena compacta
    }

    // Método para analizar y validar un token JWT
    public Jws<Claims> parseJwt(String jwtString) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, IllegalArgumentException {
        JwtParser jwtParser = Jwts.parser().verifyWith( getKey() ).build(); // Se crea el parser con la clave secreta
        return jwtParser.parseSignedClaims(jwtString); // Se analiza y devuelve los claims del token
    }

    // Método privado para obtener la clave secreta
    private SecretKey getKey(){
        String claveSecreta = "secretsecretsecretsecretsecretsecretsecretsecret"; // Cadena de texto usada como clave secreta
        byte[] secretKeyBytes = claveSecreta.getBytes(); // Se convierte la cadena a bytes
        return Keys.hmacShaKeyFor(secretKeyBytes); // Se genera la clave HMAC usando los bytes
    }
}
