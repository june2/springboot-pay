
package com.pay.exception;

import com.pay.handler.ResponseType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {
    public ResponseType type;

    public ForbiddenException(ResponseType type, String message) {
        super(String.format(message));
        this.type = type;
    }
}