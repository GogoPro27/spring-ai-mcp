package com.projectgoch.clienttest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectgoch.clienttest.pojo.ActorsFilms;
import com.projectgoch.clienttest.tools.DateTimeTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@Controller
class TestController {

    private final ChatClient openAiChatClient;
    private final ChatClient anthropicChatClient;
    private final DateTimeTools  dateTimeTools;
    private final ChatClient localOpenAiChatModel;
    private final PromptTemplate userInputWrapper;


    public TestController(@Qualifier("openAiChatClient") ChatClient openAiChatClient,
                          @Qualifier("anthropicChatClient") ChatClient anthropicChatClient, DateTimeTools dateTimeTools, ChatClient localOpenAiChatModel, PromptTemplate userInputWrapper) {
        this.openAiChatClient = openAiChatClient;
        this.anthropicChatClient = anthropicChatClient;
        this.dateTimeTools = dateTimeTools;
        this.localOpenAiChatModel = localOpenAiChatModel;
        this.userInputWrapper = userInputWrapper;
    }

    @GetMapping("/")
    public String chatPage() {
        return "chat";
    }

    @PostMapping("/ai")
    public String generation(@RequestParam String userInput, Model model) {
        String userInputLocal = userInputWrapper.render(Map.of("userInput", userInput));
        String localResponse = "local: " + Objects.requireNonNull(this.localOpenAiChatModel.prompt()
                        .user(userInputLocal)
                        .tools(dateTimeTools)
                        .call()
                        .content())
                .split(">")[2];
        String openaiResponse = "gpt: " + this.openAiChatClient.prompt()
                .user(userInput)
                .tools(dateTimeTools)
                .call()
                .content();

        String anthropicResponse ="claude: " +  this.anthropicChatClient.prompt()
                .user(userInput)
                .user(userInput)
                .call()
                .content();

        String response = String.join("\n", localResponse, openaiResponse, anthropicResponse);

        model.addAttribute("userInput", userInput);
        model.addAttribute("aiResponse", response);
        return "chat";
    }
}