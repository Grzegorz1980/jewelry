package com.jewelry.core.service.importFile;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FileImportException extends RuntimeException {

    private List<Integer> skippedSku = new ArrayList<>();

    public FileImportException(List<Integer> skippedSku) {
        this.skippedSku = skippedSku;
    }

    public FileImportException(String message, List<Integer> skippedSku) {
        super(message);
        this.skippedSku = skippedSku;
    }

    public FileImportException(String message, Throwable cause, List<Integer> skippedSku) {
        super(message, cause);
        this.skippedSku = skippedSku;
    }

    public FileImportException(Throwable cause, List<Integer> skippedSku) {
        super(cause);
        this.skippedSku = skippedSku;
    }

    public FileImportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, List<Integer> skippedSku) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.skippedSku = skippedSku;
    }
}
