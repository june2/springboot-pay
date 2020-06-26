package com.pay.api.user.dto;

import com.pay.validator.RoomToken;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@RoomToken({"amount", "number"})
@ApiModel(value = "[ReqToke] - 뿌리기 요청")
public class ReqToken {
    @NotNull(message = "please input amount")
    Long amount;

    @NotNull(message = "please input number")
    Long number;

    public long getDistributingAmount() {
        return this.amount / this.number;
    }
}
