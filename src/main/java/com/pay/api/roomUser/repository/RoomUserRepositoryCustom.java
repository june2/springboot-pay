package com.pay.api.roomUser.repository;

import com.pay.api.roomUser.model.RoomUser;

import java.util.List;

public interface RoomUserRepositoryCustom {
    boolean isUserInRoom(long userId, long roomId);
    List<RoomUser> findUsersExceptUserId(long userId);
}
