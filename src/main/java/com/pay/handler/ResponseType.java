package com.pay.handler;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum ResponseType {
    SUCCESS("200", "Success"),
    ERROR("500", "System error"),
    NOT_FOUND("404", "Not found"),
    DUPLICATED("400", "Duplicated data"),
    INVALID_TOKEN("401", "Invalid token"),
    UNAUTHORIZED("401", "Unauthorized");

    private String code;
    @Setter
    private String message;

    ResponseType(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
