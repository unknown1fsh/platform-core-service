package com.sahip.platform.core.dto.response;

import java.time.Instant;
import java.util.Map;

public class ApiResponse<T> {
    private final boolean success;
    private final T data;
    private final ErrorInfo error;
    private final Instant timestamp;

    public ApiResponse(boolean success, T data, ErrorInfo error, Instant timestamp) {
        this.success = success;
        this.data = data;
        this.error = error;
        this.timestamp = timestamp;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public ErrorInfo getError() {
        return error;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public static class ErrorInfo {
        private final String code;
        private final String message;
        private final Map<String, String> fieldErrors;

        public ErrorInfo(String code, String message, Map<String, String> fieldErrors) {
            this.code = code;
            this.message = message;
            this.fieldErrors = fieldErrors;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public Map<String, String> getFieldErrors() {
            return fieldErrors;
        }
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<T>(true, data, null, Instant.now());
    }

    public static <T> ApiResponse<T> error(String code, String message, Map<String, String> fieldErrors) {
        return new ApiResponse<T>(false, null, new ErrorInfo(code, message, fieldErrors), Instant.now());
    }
}

