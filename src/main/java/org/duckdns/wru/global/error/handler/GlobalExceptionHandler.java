package org.duckdns.wru.global.error.handler;

import org.apache.coyote.Response;
import org.duckdns.wru.app.friendship.exception.AlreadyFriendsException;
import org.duckdns.wru.app.user.exception.UserNotFoundException;
import org.duckdns.wru.global.auth.exception.DuplicateDataException;
import org.duckdns.wru.global.common.dto.ApiResponse;
import org.duckdns.wru.global.error.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGlobalException(Exception ex) {
        ApiResponse<Void> response = ApiResponse.error(
                new BusinessException(ex.getMessage(), "INTERNAL_SERVER_ERROR")
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DuplicateDataException.class)
    public ResponseEntity<ApiResponse<Void>> handleDuplicateDataException(Exception exception) {
        ApiResponse<Void> response = ApiResponse.error(
                new BusinessException(exception.getMessage(), "DUPLICATE_DATA")
        );

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AlreadyFriendsException.class)
    public ResponseEntity<ApiResponse<Void>> handleAlreadyFriendsException(Exception exception) {
        ApiResponse<Void> response = ApiResponse.error(
                new BusinessException(exception.getMessage(), "DUPLICATE_DATA")
        );

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleUserNotFoundException(Exception exception) {
        ApiResponse<Void> response = ApiResponse.error(
                new BusinessException(exception.getMessage(), "USER_NOT_FOUND")
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
