package com.company.internationalizationtaticfilesandresources.enums;

public enum ExceptionEnum {
    DELETE_FAILED(500),
    UPLOAD_FAILED(500),
    DOWNLOAD_FAILED(500),
    NOT_FOUND_FILE(404),
    DIRECTORY_CREATED_FAILED(500),
    FILE_UPLOAD_IS_FAILED(500);


    public int status ;

    ExceptionEnum(int status) {
        this.status = status;
    }
}
