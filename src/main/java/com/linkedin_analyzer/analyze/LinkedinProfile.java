package com.linkedin_analyzer.analyze;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LinkedinProfile {

    @CsvBindByName(column = "First Name")
    private String firstName;

    @CsvBindByName(column = "Last Name")
    private String lastName;

    @CsvBindByName(column = "Headline")
    private String headLine;

    @CsvBindByName(column = "Summary")
    private String summary;

    @CsvBindByName(column = "Industry")
    private String industry;

    @CsvBindByName(column = "Websites")
    private String websites;
    
}
