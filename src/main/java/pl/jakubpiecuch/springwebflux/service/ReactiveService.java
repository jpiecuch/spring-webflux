package pl.jakubpiecuch.springwebflux.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.jakubpiecuch.springwebflux.model.Response;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ReactiveService {

    private final WebClient webClient;

    public ReactiveService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<List<Response>> list(String username) {
        return ReactiveSecurityContextHolder.getContext()
                .flatMap(sc -> Mono.just(Arrays.asList(
                        Response.builder().name(sc.getAuthentication().getName()).build(),
                        Response.builder().name(username).build())))
                .flatMap(responses -> this.webClient.get().exchange()
                        .flatMap(r -> r.bodyToMono(Map.class).flatMap(b -> Mono.just(responses))));
    }
}
