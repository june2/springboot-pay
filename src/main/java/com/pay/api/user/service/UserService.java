package com.pay.api.user.service;

import com.pay.api.distributing.model.Distributing;
import com.pay.api.distributingUser.model.DistributingUser;
import com.pay.api.user.dto.ReqToken;
import com.pay.api.user.dto.ResDistributingUsers;
import com.pay.api.user.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    String addDistributing(long userId, long roomId, ReqToken reqToken);

    DistributingUser getDistributing(long userId, long roomId, String token);

    ResDistributingUsers getDistributingUsers(long userId, long roomId, String token);
}
