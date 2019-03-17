package pl.jakubpiecuch.springwebflux.configuration;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Collections;

@Component
public class CustomJwtAuthenticationConverter implements Converter<Jwt, CustomJwtAuthenticationToken> {

    static final String COMPANY_USER_ROLE = "ROLE_company_user";
    private static final String COMPANY_ID = "company_id";

    @Override
    public CustomJwtAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = extractAuthorities(jwt);
        return new CustomJwtAuthenticationToken(jwt, authorities);
    }

    private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {

        String companyId = (String) jwt.getClaims().get(COMPANY_ID);
        return StringUtils.isEmpty(companyId) ? Collections.EMPTY_LIST : AuthorityUtils.createAuthorityList(COMPANY_USER_ROLE);
    }
}
