package com.linkedin_analyzer.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.linkedin_analyzer.dto.LinkedinProfileDto;
import com.linkedin_analyzer.dto.ProfileRequestDto;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@Service
public class AnalyzeService {

    public void gerarSugestão(String cargoDesejado, MultipartFile linkedinCsv) {

        LinkedinProfileDto linkedinProfileDto = formatarCSV(linkedinCsv);

        ProfileRequestDto requestDto = new ProfileRequestDto(cargoDesejado, linkedinProfileDto);

    }

    public LinkedinProfileDto formatarCSV(MultipartFile file) {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<LinkedinProfileDto> csvToBean = new CsvToBeanBuilder<LinkedinProfileDto>(reader)
                    .withType(LinkedinProfileDto.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            LinkedinProfileDto profileDto = csvToBean.parse().get(0);

            return profileDto;
        } catch (IOException e) {
            return null;
        }
    }

}
