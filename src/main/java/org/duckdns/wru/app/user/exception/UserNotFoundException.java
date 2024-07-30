package org.duckdns.wru.app.user.exception;

import org.duckdns.wru.global.error.exception.BusinessException;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(Long id) {
        super("User not found: " + id, "USER_NOT_FOUND");
    }

    public UserNotFoundException(String username) {
        super("User not found: " + username, "USER_NOT_FOUND");
    }
}
