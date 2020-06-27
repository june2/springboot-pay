package com.pay.api.user.dto;

import com.pay.api.distributing.model.Distributing;
import com.pay.api.distributingUser.model.DistributingUser;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
    List<DistributingUser> users;
}
