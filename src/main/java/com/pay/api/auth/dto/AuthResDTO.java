package com.pay.api.auth.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@ApiModel(value = "Auth Create Req", description = "Auth Create Req")
@Getter
@Setter
@NoArgsConstructor
public class AuthResDTO implements Serializable {
    private String email;
    private String password;
}