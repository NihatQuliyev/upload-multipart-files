package com.company.internationalizationtaticfilesandresources.controller;

import com.company.internationalizationtaticfilesandresources.dto.ResponseMessage;
import com.company.internationalizationtaticfilesandresources.service.FileStorageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
@Slf4j
public class FileController {

    private final FileStorageService fileStorageService;

    @GetMapping()
    public ResponseEntity<Stream<Path>> getFile() {
        return ResponseEntity.ok(fileStorageService.getFilesStream());
    }

    @GetMapping("/{fileName}")

    public ResponseEntity<Resource> download(@PathVariable String fileName) {

        Resource file = fileStorageService.download(fileName);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment: fileName =\""+file.getFilename() + "\"")
                .body(file);

    }

    @PostMapping
    public ResponseEntity<ResponseMessage> upload(@RequestParam MultipartFile file) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(fileStorageService.upload(file));
    }

    @DeleteMapping("/{fileName}")
    public ResponseEntity<ResponseMessage> deleteFile(@PathVariable String fileName) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(fileStorageService.delete(fileName));
    }




}
