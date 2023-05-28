package com.company.internationalizationtaticfilesandresources.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileInfo {
    String fileName;
    String url;
}
