package pe.edu.cibertec.EF_APP_OAuth_MazuelosJuan.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import pe.edu.cibertec.EF_APP_OAuth_MazuelosJuan.jwt.FiltroJWTAuth;
import pe.edu.cibertec.EF_APP_OAuth_MazuelosJuan.service.UsuarioDetallesServicio;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UsuarioDetallesServicio usuarioDetallesServicio;

    @Autowired
    private FiltroJWTAuth jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(usuarioDetallesServicio)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Deshabilitar CSRF para aplicaciones con JWT
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Stateless (sin sesiones)
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/api/auth/**").permitAll() // Permitir acceso a los endpoints de autenticación sin token
                .anyRequest().authenticated() // Todas las demás rutas requieren autenticación
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // Añadir filtro JWT

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(usuarioDetallesServicio);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}