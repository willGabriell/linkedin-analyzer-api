package com.linkedin_analyzer.controller;

import com.linkedin_analyzer.dto.LinkedinProfileDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.linkedin_analyzer.dto.ProfileRequestDto;
import com.linkedin_analyzer.service.AnalyzeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/analyze")
@RequiredArgsConstructor
public class AnalyzeController {

    private final AnalyzeService service;

    @PostMapping
    public ResponseEntity<ProfileRequestDto> uploadLinkedinCsv(@RequestParam("file") MultipartFile file, @RequestParam("cargo") String cargoDesejado) {
        LinkedinProfileDto profileDto = service.formatarCSV(file);
        return ResponseEntity.ok().body(new ProfileRequestDto("Desenvolvedor Java Junior", profileDto));
    }

}
