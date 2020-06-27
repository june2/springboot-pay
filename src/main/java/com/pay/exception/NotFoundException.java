
package com.pay.exception;

import com.pay.handler.ResponseType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public ResponseType type;

    public NotFoundException(ResponseType type, String message) {
        super(String.format(message));
        this.type = type;
    }
}