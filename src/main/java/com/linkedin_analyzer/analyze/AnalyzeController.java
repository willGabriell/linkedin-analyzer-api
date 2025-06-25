package com.linkedin_analyzer.analyze;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/analyze")
@RequiredArgsConstructor
@Tag(name = "Profile Analyze Controller")
public class AnalyzeController {

    private final AnalyzeService service;

    @Operation(summary = "Recebe um CSV do perfil do linkedin e o cargo que o usuário deseja alcançar, e os converte e envia para a API em python que gera a análise com AI", description = "Este endpoint recebe um arquivo CSV exportado do LinkedIn e o cargo desejado, processa os dados e retorna sugestões de melhorias de perfil baseadas em IA.", parameters = {
        @Parameter(name = "cargo", description = "Cargo desejado pelo usuário", required = true, example = "Desenvolvedor Sênior"),
        @Parameter(name = "file", description = "Arquivo CSV do perfil do usuário", required = true)
    })
    @ApiResponse(responseCode = "200", description = "Análise gerada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProfileResponseDto.class), examples = @ExampleObject(value = "{\"sugestao\":\"Adicione mais projetos relevantes ao seu perfil.\"}")))
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProfileResponseDto> gerarSugestao(@RequestParam("file") MultipartFile file, @RequestParam("cargo") String cargoDesejado) {
        return ResponseEntity.ok().body(service.gerarSugestao(cargoDesejado, file));
    }

}
