package com.stackdev.springwebclient.controllers;

import com.stackdev.springwebclient.dto.Users;
import com.stackdev.springwebclient.services.WebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@RestController
@RequestMapping("/web")
public class WebClientController {

    @Autowired
    WebClientService webClientService;

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Users> getUserById(@PathVariable String id) {
        return webClientService.findUserById(id);
    }

    @GetMapping(value = "/users", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Flux<Users> findAllUsers() {
        return webClientService.findUsers();
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<Users>> createUser(@RequestBody Users user) {
        return webClientService.createUser(user)
                .map(createdUser -> ResponseEntity.status(HttpStatus.CREATED).body(createdUser));
    }

    @PutMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Users> updateUser(@PathVariable String id, @RequestBody Users user) {
        return webClientService.updateUser(id, user);
    }

    @DeleteMapping("/user/{id}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable String id) {
        return webClientService.deleteUser(id)
                .map(r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


}
