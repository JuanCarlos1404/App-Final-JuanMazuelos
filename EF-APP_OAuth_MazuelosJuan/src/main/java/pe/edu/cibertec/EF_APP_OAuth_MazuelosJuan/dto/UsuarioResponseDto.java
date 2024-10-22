package pe.edu.cibertec.EF_APP_OAuth_MazuelosJuan.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UsuarioResponseDto {
    private Integer idusuario;
    private String nomusuario;
    private String token;
}