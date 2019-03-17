package pl.jakubpiecuch.springwebflux.configuration;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;

public class CustomJwtAuthenticationToken extends JwtAuthenticationToken {

    private static final String USER_DI = "user_id";

    public CustomJwtAuthenticationToken(Jwt jwt, Collection<? extends GrantedAuthority> authorities) {
        super(jwt, authorities);
        setDetails(OAuth2AuthenticationDecodedDetails.builder()
                .companyShortName((String) jwt.getClaims().get("company_id"))
                .userId(Math.toIntExact((Long) jwt.getClaims().get(USER_DI)))
                .build());
    }
}
