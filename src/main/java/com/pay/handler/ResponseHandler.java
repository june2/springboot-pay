
package com.pay.handler;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "[Response] - 응답 정보")
public class ResponseHandler<T> {
    private String code;
    private String message;
    private T data;

    @Builder
    public ResponseHandler(String code, String message, T data) {
        this.code = code;
        this.data = data;
        this.message = message;
    }


    public static <T> ResponseHandler<T> create(ResponseType code, T data) {
        return ResponseHandler.<T>builder()
                .code(code.getCode())
                .message(code.getMessage())
                .data(data)
                .build();
    }


    public static <T> ResponseHandler<T> error(ResponseType code) {
        return create(code, null);
    }

    public static <T> ResponseHandler<T> success(T data) {
        return create(ResponseType.SUCCESS, data);
    }

    public static <T> ResponseHandler<T> success() {
        return create(ResponseType.SUCCESS, null);
    }
}