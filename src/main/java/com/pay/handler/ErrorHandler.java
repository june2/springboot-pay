package com.pay.handler;

import com.pay.exception.BadRequestException;
import com.pay.exception.ForbiddenException;
import com.pay.exception.NotFoundException;
import com.pay.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> unauthorizedException(UnauthorizedException e) {
        log.error("[!] {}", e.getMessage());
        return new ResponseEntity<>(ResponseHandler.error(e.type), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> unauthorizedException(BadRequestException e) {
        log.error("[!] {}", e.getMessage());
        return new ResponseEntity<>(ResponseHandler.error(e.type), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<?> forbiddenException(ForbiddenException e) {
        log.error("[!] {}", e.getMessage());
        return new ResponseEntity<>(ResponseHandler.error(e.type), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundException(NotFoundException e) {
        log.error("[!] {}", e.getMessage());
        return new ResponseEntity<>(ResponseHandler.error(e.type), HttpStatus.NOT_FOUND);
    }
}
