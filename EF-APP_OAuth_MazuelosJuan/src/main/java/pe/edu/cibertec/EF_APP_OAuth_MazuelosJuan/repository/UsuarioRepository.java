package pe.edu.cibertec.EF_APP_OAuth_MazuelosJuan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.EF_APP_OAuth_MazuelosJuan.mdoel.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCodigo(String codigo);
}
