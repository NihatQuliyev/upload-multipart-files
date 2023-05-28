package com.company.internationalizationtaticfilesandresources.exception;

import com.company.internationalizationtaticfilesandresources.enums.ExceptionEnum;

public class NotFoundException extends Exception {

    public NotFoundException() {
        super(ExceptionEnum.NOT_FOUND_FILE.name());
    }
}
