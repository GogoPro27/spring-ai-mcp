package com.projectgoch.clienttest.config;

import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PromptTemplatesConfig {

    @Bean
    PromptTemplate userInputWrapper () {
        return new PromptTemplate("""
                This is the user message : < {userInput} >""");
    }
}
