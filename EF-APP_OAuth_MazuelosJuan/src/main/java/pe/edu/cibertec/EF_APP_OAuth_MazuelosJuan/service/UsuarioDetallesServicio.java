package pe.edu.cibertec.EF_APP_OAuth_MazuelosJuan.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.EF_APP_OAuth_MazuelosJuan.mdoel.Usuario;
import pe.edu.cibertec.EF_APP_OAuth_MazuelosJuan.repository.UsuarioRepository;

import java.util.Collections;

@Service
public class UsuarioDetallesServicio implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetallesServicio(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String codigo) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCodigo(codigo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return new org.springframework.security.core.userdetails.User(
                usuario.getCodigo(),
                usuario.getPassword(),
                usuario.getActivo(),
                true,
                true,
                true,
                Collections.singletonList(new SimpleGrantedAuthority(usuario.getRol()))
        );
    }
}
