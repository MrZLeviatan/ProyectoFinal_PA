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
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/usuario").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/usuario/reportes").hasAnyAuthority("ROLE_USUARIO")
                        .requestMatchers("/api/usuario/estado").permitAll()
                        .requestMatchers("/api/usuario/codigoVerificacion/{email}").permitAll()
                        .requestMatchers("/api/usuario/password").permitAll()
                        .requestMatchers("/api/autentificar/**").permitAll()
                        .requestMatchers("/api/moderador/**").hasAuthority("ROLE_MODERADOR")
                        .requestMatchers(HttpMethod.OPTIONS).permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/api/categoria").hasAnyAuthority("ROLE_MODERADOR")
                        .requestMatchers(HttpMethod.POST,"/api/categoria").hasAuthority("ROLE_MODERADOR")
                        .requestMatchers(HttpMethod.PUT,"/api/categoria").hasAuthority("ROLE_MODERADOR")
                        .requestMatchers("/api/enums").permitAll()
                        .requestMatchers("/api/enums/ciudades").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "api/categoria").permitAll()
                        /*
                        .requestMatchers("api/reporte/**").hasAnyAuthority("ROLE_MODERADOR","ROLE_USUARIO")

                         */
                        .requestMatchers("api/reportes/").permitAll()
                        .requestMatchers("api/reportes/ubicacion").permitAll()
                        .requestMatchers("api/usuario/reportes").permitAll()
                        .requestMatchers(HttpMethod.POST,"api/imagenes/").permitAll()
                        /*.requestMatchers("api/imagenes/**").hasAnyAuthority("ROLE_MODERADOR","ROLE_USUARIO")*/
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex.authenticationEntryPoint(new AuthenticationEntryPoint()))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
        // Retorna la cadena de filtros de seguridad configurada
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // Especifica el origen explícitamente
        config.setAllowedOrigins(List.of("http://localhost:4200"));

        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));

        // Esto solo funciona si NO usas "*"
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

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

