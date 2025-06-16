package com.linkedin_analyzer.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProfileResponseDto(
        @JsonProperty("pontos_positivos") List<String> pontosPositivos,
        @JsonProperty("pontos_negativos") List<String> pontosNegativos,
        List<String> sugestoes) {

}
