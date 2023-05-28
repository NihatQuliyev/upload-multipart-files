package com.company.internationalizationtaticfilesandresources;

import com.company.internationalizationtaticfilesandresources.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class InternationalizationTaticFilesAndResourcesApplication  {


    public static void main(String[] args) {
        SpringApplication.run(InternationalizationTaticFilesAndResourcesApplication.class, args);
    }


}
