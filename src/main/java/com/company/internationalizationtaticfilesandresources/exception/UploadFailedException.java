package com.company.internationalizationtaticfilesandresources.exception;

import com.company.internationalizationtaticfilesandresources.enums.ExceptionEnum;

public class UploadFailedException extends RuntimeException{

    public UploadFailedException() {
        super(ExceptionEnum.UPLOAD_FAILED.name());
    }
}
