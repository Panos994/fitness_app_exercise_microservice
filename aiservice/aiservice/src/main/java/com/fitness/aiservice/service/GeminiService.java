package com.fitness.aiservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Map;


@Service
public class GeminiService {

    private final WebClient webClient;
    // Εισαγωγή ρυθμίσεων μέσω @Value (Field Injection)
    @Value("${gemini.api.url}")
    private String geminiApiUrl;
    @Value("${gemini.api.key}")
    private String geminiApiKey;

    // Προσθήκη ρύθμισης για το όνομα του μοντέλου, με default τιμή
    @Value("${gemini.api.model:gemini-2.5-flash}")
    private String geminiApiModel;

    // Constructor με WebClient.Builder
    public GeminiService(WebClient.Builder webClientBuilder){
        // Απλή δόμηση του WebClient.
        this.webClient = webClientBuilder.build();
    }

    // Η μέθοδος για την κλήση του Gemini API
    public String getAnswer(String question){
        // Δημιουργία του Request Body
        Map<String, Object> requestBody = Map.of(
                "contents",new Object[]{
                        Map.of("parts",new Object[]{
                                Map.of("text",question)
                        })
                }
        );

        // ΔΙΟΡΘΩΣΗ: Κατασκευή του σωστού URI για το Gemini API.
        // Το σφάλμα 404 οφειλόταν στο ότι το URL δεν περιείχε το σωστό endpoint (models/{model}:generateContent)
        // και το κλειδί API δεν περνούσε ως query parameter.
        String finalUrl = UriComponentsBuilder.fromUriString(this.geminiApiUrl)
                .path("/models/{model}:generateContent")
                .queryParam("key", this.geminiApiKey)
                .buildAndExpand(this.geminiApiModel)
                .toUriString();

        // Εκτέλεση της κλήσης
        String response = webClient.post()
                // Χρήση της σωστά δομημένης URL
                .uri(finalUrl)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return response;
    }
}
