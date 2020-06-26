package com.pay.api.user.dto;

import com.pay.api.distributing.model.Distributing;
import com.pay.validator.RoomToken;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@ApiModel(value = "ResToken - 뿌리기 상태 응답")
public class ResDistributingUsers {
    LocalDateTime date;
    Long amount;
    Long takenAmount;
    List<Distributing> users;
}
