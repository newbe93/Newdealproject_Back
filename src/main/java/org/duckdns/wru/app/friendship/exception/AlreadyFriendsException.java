package org.duckdns.wru.app.friendship.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyFriendsException extends RuntimeException {
    public AlreadyFriendsException(String message) {
        super(message);
    }
}
