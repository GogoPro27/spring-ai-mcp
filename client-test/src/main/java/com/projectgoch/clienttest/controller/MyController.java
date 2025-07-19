package com.projectgoch.clienttest.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
class MyController {

    private final ChatClient openAiChatClient;
    private final ChatClient anthropicChatClient;

    public MyController( @Qualifier("openAiChatClient") ChatClient openAiChatClient,
                         @Qualifier("anthropicChatClient") ChatClient anthropicChatClient) {
        this.openAiChatClient = openAiChatClient;
        this.anthropicChatClient = anthropicChatClient;
    }

    @GetMapping("/")
    public String chatPage() {
        return "chat";
    }

    @PostMapping("/ai")
    public String generation(@RequestParam String userInput, Model model) {
        String response1 = this.openAiChatClient.prompt()
                .user(userInput)
                .call()
                .content();

        String response2 = this.anthropicChatClient.prompt()
                .user(userInput)
                .call()
                .content();

        String response = "openai:\n" + response1 + "\nanthropic: \n" + response2;

        model.addAttribute("userInput", userInput);
        model.addAttribute("aiResponse", response);
        return "chat";
    }
}