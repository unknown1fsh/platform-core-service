package com.sahip.platform.core.exception;

public class NotFoundException extends BaseException {
    public NotFoundException(String message) {
        super("NOT_FOUND", message);
    }
}
