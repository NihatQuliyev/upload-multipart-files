package com.company.internationalizationtaticfilesandresources.exception;

import com.company.internationalizationtaticfilesandresources.enums.ExceptionEnum;

public class DeleteFailedException extends RuntimeException {

    public DeleteFailedException() {
        super(ExceptionEnum.DELETE_FAILED.name());
    }
}
