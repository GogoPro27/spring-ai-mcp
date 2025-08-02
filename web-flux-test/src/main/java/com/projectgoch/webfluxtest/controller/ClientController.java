package com.projectgoch.webfluxtest.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/gpt")
public class ClientController {
    private final ChatClient chatClient;

    public ClientController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @PostMapping("/ask")
    public Flux<String> gpt(@RequestParam String input){
        return chatClient.prompt()
                .user(input)
                .stream()
                .content();
    }
}
