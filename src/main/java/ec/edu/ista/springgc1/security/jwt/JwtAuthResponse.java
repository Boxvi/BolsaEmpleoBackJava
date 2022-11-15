package ec.edu.ista.springgc1.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class JwtAuthResponse {

    private String accessToken;
    private String tokenType = "Bearer";
    private String username;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtAuthResponse(String accessToken, String username, Collection<? extends GrantedAuthority> authorities) {
        super();
        this.accessToken = accessToken;
        this.username = username;
        this.authorities = authorities;
    }
}
