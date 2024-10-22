package pe.edu.cibertec.EF_APP_OAuth_MazuelosJuan.mdoel;

public class LoginResponse {
    private String token;

    public LoginResponse(String token) {
        this.token = token;
    }

    // Getter
    public String getToken() {
        return token;
    }
}
