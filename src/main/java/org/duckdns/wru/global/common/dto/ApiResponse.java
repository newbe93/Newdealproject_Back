package org.duckdns.wru.global.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.duckdns.wru.global.error.exception.BusinessException;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ApiResponse<T> {

    private boolean success;
    private T data;
    private Error error;
    private LocalDateTime timestamp;

    @Getter
    @NoArgsConstructor
    public static class Error {
        private String message;
        private String code;
        private Object details;

        public Error(String message, String code) {
            this.message = message;
            this.code = code;
        }

        public Error(String message, String code, Object details) {
            this.message = message;
            this.code = code;
            this.details = details;
        }
    }

    private ApiResponse(boolean success, T data, Error error) {
        this.success = success;
        this.data = data;
        this.error = error;
        this.timestamp = LocalDateTime.now();
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null);
    }

    public static ApiResponse<Void> error(BusinessException ex) {
        return new ApiResponse<>(false, null, new Error(ex.getMessage(), ex.getErrorCode()));
    }

    public static ApiResponse<Void> error(BusinessException ex, Object details) {
        return new ApiResponse<>(false, null, new Error(ex.getMessage(), ex.getErrorCode(), details));
    }

}