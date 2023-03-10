package com.airbnl.airbnlauthentication.service.implementation;

import com.airbnl.airbnlauthentication.model.User;
import com.airbnl.airbnlauthentication.service.interfaces.IUserService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserService implements IUserService {
    private final WebClient webClient;
    public UserService(WebClient databaseWebClient) {
        this.webClient = databaseWebClient;
    }

    @Override
    public User save(User user) {
        User savedUser = webClient.post()
                .uri("/user/save")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), User.class)
                .retrieve()
                .bodyToMono(User.class)
                .block();
        return savedUser;
    }

    @Override
    public User getByUsername(String username) {
        User user = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/user/getByUsername")
                        .queryParam("username", username)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(User.class)
                .block();
        return user;
    }
}
