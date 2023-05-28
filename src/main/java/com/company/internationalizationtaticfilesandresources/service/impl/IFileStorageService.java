package com.company.internationalizationtaticfilesandresources.service.impl;

import com.company.internationalizationtaticfilesandresources.dto.ResponseMessage;
import com.company.internationalizationtaticfilesandresources.enums.ExceptionEnum;
import com.company.internationalizationtaticfilesandresources.enums.SuccessMessageEnum;
import com.company.internationalizationtaticfilesandresources.exception.DeleteFailedException;
import com.company.internationalizationtaticfilesandresources.exception.DownloadFailedException;
import com.company.internationalizationtaticfilesandresources.exception.NotFoundException;
import com.company.internationalizationtaticfilesandresources.exception.UploadFailedException;
import com.company.internationalizationtaticfilesandresources.service.FileStorageService;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

@Slf4j
@Service
public class IFileStorageService implements FileStorageService {
    private final Path ROOT = Paths.get("upload");

    @Override
    public void init() {
        try {
            Path directory = Files.createDirectory(ROOT);
            log.info("Path: {}", directory.getRoot());
        } catch (IOException e) {
            log.error(ExceptionEnum.DIRECTORY_CREATED_FAILED.name());
            throw new RuntimeException();
        }
    }

    @Override
    public ResponseMessage upload(MultipartFile file) {
        StringBuilder message = new StringBuilder();
        try {
                Files.copy(file.getInputStream(), this.ROOT.resolve(Objects.requireNonNull(file.getOriginalFilename())));
                message.append(SuccessMessageEnum.FILE_UPLOAD_IS_SUCCESS.name());
                log.info(SuccessMessageEnum.FILE_UPLOAD_IS_SUCCESS.name());
        } catch (IOException e) {
            log.error(ExceptionEnum.FILE_UPLOAD_IS_FAILED.name());
            throw new UploadFailedException();
        }
        return ResponseMessage.builder()
                .message(message)
                .build();
    }

    @SneakyThrows
    @Override
    public Resource download(String fileName) {
        Path file = ROOT.resolve(fileName);
        try {
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                log.info("bytes: {}",resource.getInputStream());
                return resource;
            }
            else {
                log.error(ExceptionEnum.DOWNLOAD_FAILED + ": {}",fileName);
                throw new DownloadFailedException();
            }
        } catch (IOException e) {
            throw new DownloadFailedException();
        }
    }

    @SneakyThrows
    @Override
    public ResponseMessage delete(String fileName) {
        StringBuilder message = new StringBuilder();
        try {
            if (Files.deleteIfExists(ROOT.resolve(fileName))) {
                log.info(SuccessMessageEnum.DELETE_SUCCESSFULLY.name());
                message.append(SuccessMessageEnum.DELETE_SUCCESSFULLY.name());
            } else {
                log.error(ExceptionEnum.DELETE_FAILED.name());
                throw new DeleteFailedException();
            }
        } catch (IOException e) {
            throw new DeleteFailedException();
        }
        return ResponseMessage.builder()
                .message(message)
                .build();
    }

    @SneakyThrows
    public Stream<Path> getFilesStream(){
        try {
            Stream<Path> pathStream = Files.walk(ROOT)
                    .filter(Files::isRegularFile);
            if (pathStream.findAny().isEmpty()) {
                log.error(ExceptionEnum.NOT_FOUND_FILE.name());
                throw new NotFoundException();
            }
            log.info("file: {}",pathStream);
            return pathStream;
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }


}
