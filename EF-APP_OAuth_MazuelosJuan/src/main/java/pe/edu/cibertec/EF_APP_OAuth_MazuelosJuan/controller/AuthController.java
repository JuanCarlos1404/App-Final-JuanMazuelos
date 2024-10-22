package pe.edu.cibertec.EF_APP_OAuth_MazuelosJuan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.EF_APP_OAuth_MazuelosJuan.jwt.JwtTokenProvider;
import pe.edu.cibertec.EF_APP_OAuth_MazuelosJuan.mdoel.LoginRequest;
import pe.edu.cibertec.EF_APP_OAuth_MazuelosJuan.mdoel.LoginResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public LoginResponse authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            // Realiza la autenticación
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getCodigo(),
                            loginRequest.getPassword()
                    )
            );

            // Genera el token JWT
            String jwt = tokenProvider.generateToken(authentication);
            return new LoginResponse(jwt);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Credenciales incorrectas");
        }
    }
}
