package com.projectgoch.clienttest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectgoch.clienttest.pojo.ActorsFilms;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TestRestController {
    private final ChatClient openAiChatClient;

    public TestRestController(ChatClient openAiChatClient) {
        this.openAiChatClient = openAiChatClient;
    }


    @GetMapping("/actorMovies/{actor}")
    public ResponseEntity<String> actorMovies(@PathVariable String actor) throws JsonProcessingException {
        ActorsFilms actorsFilms = openAiChatClient.prompt()
                .user(u -> u.text("Generate the filmography of 5 movies for {actor}.")
                        .param("actor", actor))
                .call()
                .entity(ActorsFilms.class);
        ObjectMapper objectMapper = new ObjectMapper();
        return ResponseEntity.ok(objectMapper.writeValueAsString(actorsFilms));
    }

    @GetMapping("/actorMoviesMultiple")
    public ResponseEntity<List<ActorsFilms>> getActorMovies(@RequestParam List<String> actors) {
        List<ActorsFilms> actorsFilms = openAiChatClient.prompt()
                .user(String.format("%s %s.","Generate the filmography of 5 movies for ",String.join(" and ",actors)))
                .call()
                .entity(new ParameterizedTypeReference<>() {
                });
        return ResponseEntity.ok(actorsFilms);
    }
}
