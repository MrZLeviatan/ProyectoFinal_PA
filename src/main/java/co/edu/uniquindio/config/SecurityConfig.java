package co.edu.uniquindio.config;

import co.edu.uniquindio.Security.AuthenticationEntryPoint;
import co.edu.uniquindio.Security.JWTFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    // Esta clase se encarga de configurar la seguridad de la aplicación usando Spring Security

    private final JWTFilter jwtFilter;
    // Filtro personalizado para procesar los tokens JWT antes de la autenticación estándar

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Este método configura la cadena de filtros de seguridad (SecurityFilterChain)

        http
                .csrf(AbstractHttpConfigurer::disable)
                // Desactiva la protección CSRF, útil para APIs REST (ya que no hay sesiones)

                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                // Habilita CORS y usa la configuración definida en corsConfigurationSource()

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Define que no se usen sesiones (stateless), ya que se usa JWT

                .authorizeHttpRequests(req -> req
                                .requestMatchers("/api/auth/**").permitAll()
                                // Permite libre acceso a las rutas que comienzan con /api/auth (por ejemplo: login, registro)

                                .anyRequest().authenticated()
                        // Cualquier otra ruta requiere autenticación
                )

                .exceptionHandling(ex -> ex.authenticationEntryPoint(new AuthenticationEntryPoint()))
                // Manejo de errores cuando no se ha autenticado el usuario (puedes personalizar este EntryPoint)

                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        // Añade el filtro JWT antes del filtro de autenticación por usuario y contraseña

        return http.build();
        // Retorna la cadena de filtros de seguridad configurada
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        // Configura CORS (Cross-Origin Resource Sharing) para permitir solicitudes desde otros orígenes

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("*"));
        // Permite solicitudes desde cualquier origen (en producción es mejor restringir esto)

        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // Métodos HTTP permitidos

        config.setAllowedHeaders(List.of("*"));
        // Permite cualquier encabezado

        config.setAllowCredentials(true);
        // Permite el envío de cookies o tokens en las solicitudes

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        // Aplica esta configuración a todas las rutas

        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Proporciona un codificador de contraseñas usando BCrypt
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        // Devuelve el AuthenticationManager que Spring Security usa internamente
        return configuration.getAuthenticationManager();
    }
}

