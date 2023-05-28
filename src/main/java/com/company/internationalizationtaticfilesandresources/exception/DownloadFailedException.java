package com.company.internationalizationtaticfilesandresources.exception;

import com.company.internationalizationtaticfilesandresources.enums.ExceptionEnum;

public class DownloadFailedException extends RuntimeException{

    public DownloadFailedException() {
        super(ExceptionEnum.DOWNLOAD_FAILED.name());
    }
}
