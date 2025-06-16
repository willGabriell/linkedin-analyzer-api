package com.linkedin_analyzer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.linkedin_analyzer.dto.ProfileResponseDto;
import com.linkedin_analyzer.service.AnalyzeService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/analyze")
@RequiredArgsConstructor
public class AnalyzeController {

    private final AnalyzeService service;

    @PostMapping
    public ResponseEntity<ProfileResponseDto> uploadLinkedinCsv(@RequestParam("file") MultipartFile file, @RequestParam("cargo") String cargoDesejado) throws JsonProcessingException {
        return ResponseEntity.ok().body(service.gerarSugestao(cargoDesejado, file));
    }
    

}
