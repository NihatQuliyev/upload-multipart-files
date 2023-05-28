package com.company.internationalizationtaticfilesandresources.handler;

import com.company.internationalizationtaticfilesandresources.dto.ResponseMessage;
import com.company.internationalizationtaticfilesandresources.enums.ExceptionEnum;
import com.company.internationalizationtaticfilesandresources.exception.DeleteFailedException;
import com.company.internationalizationtaticfilesandresources.exception.DownloadFailedException;
import com.company.internationalizationtaticfilesandresources.exception.NotFoundException;
import com.company.internationalizationtaticfilesandresources.exception.UploadFailedException;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FileSizeLimitExceededException.class)
    public ResponseEntity<ResponseMessage> handle() {
        return ResponseEntity
                .status(500)
                .body(ResponseMessage.builder()
                        .message(new StringBuilder("File size to large!"))
                        .build());

    }

    @ExceptionHandler(DeleteFailedException.class)
    public ResponseEntity<ResponseMessage> deleteHandler() {
        return ResponseEntity
                .status(500)
                .body(ResponseMessage.builder()
                        .message(new StringBuilder(ExceptionEnum.DELETE_FAILED.name()))
                        .build());

    }

    @ExceptionHandler(DownloadFailedException.class)
    public ResponseEntity<ResponseMessage> downloadHandler() {
        return ResponseEntity
                .status(500)
                .body(ResponseMessage.builder()
                        .message(new StringBuilder(ExceptionEnum.DOWNLOAD_FAILED.name()))
                        .build());

    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseMessage> NotFoundHandler() {
        return ResponseEntity
                .status(404)
                .body(ResponseMessage.builder()
                        .message(new StringBuilder(ExceptionEnum.NOT_FOUND_FILE.name()))
                        .build());

    }

    @ExceptionHandler(UploadFailedException.class)
    public ResponseEntity<ResponseMessage> uploadHandler() {
        return ResponseEntity
                .status(404)
                .body(ResponseMessage.builder()
                        .message(new StringBuilder(ExceptionEnum.UPLOAD_FAILED.name()))
                        .build());

    }

}
