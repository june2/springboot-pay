package com.pay.handler;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum ResponseType {
    SUCCESS("C200", "Success"),
    ERROR("C500", "System error"),
    BAD_REQUEST("C400", "파라미터 누락, 잘못된 요청입니다."),
    FORBIDEEN("C403", "잘못된 요청입니다."),
    NOT_FOUND("C404", "Not found"),
    DUPLICATED("C409", "Duplicated data"),
    UNAUTHORIZED("C401", "Unauthorized"),
    INVALID_TOKEN("C4010", "유효한 토큰이 아닙니다."),
    INVALID_USER("C4011", "유효한 사용자가 아닙니다."),
    INVALID_TIME("C4012", "토큰시간이 만료되었습니다."),
    ALREADY_TAKEN("C4013", "이미 획득했습니다."),
    NOT_ENOUGH("C4014", "잔액이 부족합니다."),
    EVERY_TAKEN("C4015", "이미 모두 소진되었습니다.");

    private String code;
    @Setter
    private String message;

    ResponseType(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
