
package com.pay.exception;

import com.pay.handler.ResponseType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    public ResponseType type;

    public BadRequestException(ResponseType type, String message) {
        super(String.format(message));
        this.type = type;
    }
}