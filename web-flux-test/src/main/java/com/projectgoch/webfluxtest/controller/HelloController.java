package com.projectgoch.webfluxtest.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@RestController
public class HelloController {

    // Original endpoint - converted to return Mono
    @GetMapping("/hello")
    public Mono<String> handle() {
        return Mono.just("Hello WebFlux");
    }

    // Streaming endpoint - returns multiple items over time
    @GetMapping(value = "/stream", produces = MediaType.TEXT_PLAIN_VALUE)
    public Flux<String> streamData() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(i -> "Message " + i + " at " + LocalDateTime.now() + "\n")
                .take(10); // Limit to 10 messages
    }

    // Server-Sent Events (SSE) streaming
    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamEvents() {
        return Flux.interval(Duration.ofSeconds(2))
                .map(i -> "Event " + i + " - " + LocalDateTime.now())
                .take(5); // Limit to 5 events
    }

    // JSON streaming
    @GetMapping(value = "/json-stream", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<DataItem> streamJsonData() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(i -> new DataItem(i, "Item " + i, LocalDateTime.now()))
                .take(8); // Limit to 8 items
    }

    // Simple data class for JSON streaming
    public static class DataItem {
        private Long id;
        private String name;
        private LocalDateTime timestamp;

        public DataItem(Long id, String name, LocalDateTime timestamp) {
            this.id = id;
            this.name = name;
            this.timestamp = timestamp;
        }

        // Getters and setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }
    }
}
