package com.linkedin_analyzer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.linkedin_analyzer.model.LinkedinProfile;

public record ProfileRequestDto(
    @JsonProperty("cargo_desejado")
    String cargoDesejado, 
    LinkedinProfile perfil) {

}
