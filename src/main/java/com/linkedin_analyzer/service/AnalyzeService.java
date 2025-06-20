package com.linkedin_analyzer.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

import com.linkedin_analyzer.dto.ProfileRequestDto;
import com.linkedin_analyzer.dto.ProfileResponseDto;
import com.linkedin_analyzer.model.LinkedinProfile;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnalyzeService {

    private final RestClient client;

    public ProfileResponseDto gerarSugestao(String cargoDesejado, MultipartFile linkedinCsv) {

        ProfileRequestDto requestDto = montarDto(cargoDesejado, linkedinCsv);

        return client.post()
                .uri("http://localhost:8000/api/analyze")
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestDto)
                .retrieve()
                .body(ProfileResponseDto.class);

    }

    public ProfileRequestDto montarDto(String cargoDesejado, MultipartFile linkedinCsv) {
        LinkedinProfile linkedinProfileDto = formatarCSV(linkedinCsv);
        ProfileRequestDto requestDto = new ProfileRequestDto(cargoDesejado, linkedinProfileDto);
        return requestDto;
    }

    public LinkedinProfile formatarCSV(MultipartFile file) {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<LinkedinProfile> csvToBean = new CsvToBeanBuilder<LinkedinProfile>(reader)
                    .withType(LinkedinProfile.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            LinkedinProfile profileDto = csvToBean.parse().get(0);

            return profileDto;
        } catch (IOException e) {
            throw new IllegalArgumentException("Houve um erro ao tentar converter o CSV");
        }
    }

}
