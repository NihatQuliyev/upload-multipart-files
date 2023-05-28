package com.company.internationalizationtaticfilesandresources.service;

import com.company.internationalizationtaticfilesandresources.dto.ResponseMessage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileStorageService {
    void init(); /*** (creat upload package) ***/
    ResponseMessage upload(MultipartFile file);
    Resource download(String filName);
    ResponseMessage delete(String fileName);
    Stream<Path> getFilesStream();



}
