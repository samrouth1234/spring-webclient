package com.stackdev.springwebclient.services;

import com.stackdev.springwebclient.dto.Users;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class WebClientService {
    private final WebClient webClient;

    // Constructor
    public WebClientService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:9090/api/v1").build();
    }

    // Find all users
    public Flux<Users> findUsers() {
        return this.webClient
                .get()
                .uri("/users")
                .retrieve()
                .bodyToFlux(Users.class);
    }

    // Find user by id
    public Mono<Users> findUserById(String id) {
        return this.webClient
                .get()
                .uri("/users/{id}", id)
                .retrieve()
                .bodyToMono(Users.class);
    }

    // Create user
    public Mono<Users> createUser(Users user) {
        return webClient.post().uri("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(user)
                .retrieve()
                .bodyToMono(Users.class);
    }

    // Update user by id
    public Mono<Users> updateUser(String id, Users user) {
        return this.webClient.put()
                .uri("/users/{id}", id) // Corrected URI pattern
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(user)
                .retrieve()
                .bodyToMono(Users.class);
    }

    // Delete user by id
    public Mono<Void> deleteUser(String id) {
        return this.webClient.delete()
                .uri("/users/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);
    }


}
