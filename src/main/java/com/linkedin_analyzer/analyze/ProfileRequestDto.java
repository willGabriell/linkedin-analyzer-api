package com.linkedin_analyzer.analyze;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProfileRequestDto(
    @JsonProperty("cargo_desejado")
    String cargoDesejado, 
    LinkedinProfile perfil) {

}
