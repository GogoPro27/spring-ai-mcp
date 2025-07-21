package com.projectgoch.clienttest.config;

import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {
    @Value("${local.llm.url}")
    private String baseUrl;

    @Bean
    public ChatClient openAiChatClient(OpenAiChatModel chatModel) {
        ChatClient.Builder builder = ChatClient.builder(chatModel);
        builder.defaultSystem("You are a pirate and you are always talking like a pirate and spamming pirate words.");
        return builder.build();
    }

    @Bean
    public ChatClient anthropicChatClient(AnthropicChatModel chatModel) {
        ChatClient.Builder builder = ChatClient.builder(chatModel);
        builder.defaultSystem("You are a really positive person always answering positive.");
        return builder.build();
    }

    @Bean
    public ChatClient localOpenAiChatModel() {
        OpenAiApi api = OpenAiApi.builder()
                .baseUrl(baseUrl)
                .apiKey("dummy-key")
                .build();
        OpenAiChatOptions options = OpenAiChatOptions.builder()
                .model("Qwen/Qwen3-14B-AWQ")
                .temperature(0.5)
                .build();

        return ChatClient.builder(new OpenAiChatModel(api, options))
                .defaultSystem("You are a really negative person always answering negative.")
                .build();
    }

}