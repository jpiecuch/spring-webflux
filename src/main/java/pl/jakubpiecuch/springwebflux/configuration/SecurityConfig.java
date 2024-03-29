package pl.jakubpiecuch.springwebflux.configuration;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http,
                                                         OAuth2ResourceServerProperties oAuth2ResourceServerProperties,
                                                         CustomJwtAuthenticationConverter converter) {
        http.authorizeExchange()
                .anyExchange().authenticated()
                .and()
                .oauth2ResourceServer().jwt()
                .jwkSetUri(oAuth2ResourceServerProperties.getJwt().getJwkSetUri())
                .jwtAuthenticationConverter(jwt -> Mono.just(converter.convert(jwt)));
        return http.build();
    }

    @Bean
    public WebClient getWebClient(OAuth2ResourceServerProperties oAuth2ResourceServerProperties) {
        return WebClient.builder()
                .baseUrl(oAuth2ResourceServerProperties.getJwt().getJwkSetUri())
                .build();
    }

}
