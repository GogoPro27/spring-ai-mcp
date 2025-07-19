package com.projectgoch.clienttest.config;

import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient openAiChatClient(OpenAiChatModel chatModel) {
        ChatClient.Builder builder = ChatClient.builder(chatModel);
        builder.defaultSystem("You are a really negative person always answering negative.");
        return builder.build();
    }

    @Bean
    public ChatClient anthropicChatClient(AnthropicChatModel chatModel) {
        ChatClient.Builder builder = ChatClient.builder(chatModel);
        builder.defaultSystem("You are a really positive person always answering positive.");
        return builder.build();
    }
}