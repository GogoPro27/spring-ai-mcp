package com.projectgoch.clienttest.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
class MyController {

    private final ChatClient chatClient;

    public MyController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/")
    public String chatPage() {
        return "chat";
    }

    @PostMapping("/ai")
    public String generation(@RequestParam String userInput, Model model) {
        String response = this.chatClient.prompt()
                .user(userInput)
                .call()
                .content();

        model.addAttribute("userInput", userInput);
        model.addAttribute("aiResponse", response);
        return "chat";
    }
}