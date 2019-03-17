package pl.jakubpiecuch.springwebflux.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jakubpiecuch.springwebflux.model.Response;
import pl.jakubpiecuch.springwebflux.service.ReactiveService;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class ReactiveController {

    private final ReactiveService reactiveService;

    public ReactiveController(ReactiveService reactiveService) {
        this.reactiveService = reactiveService;
    }

    @GetMapping
    public Mono<List<Response>> get(Mono<Authentication> authentication) {
        return authentication
                .flatMap(a -> this.reactiveService.list(a.getName()));
    }
}
