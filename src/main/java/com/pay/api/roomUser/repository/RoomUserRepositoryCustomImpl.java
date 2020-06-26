package com.pay.api.roomUser.repository;

import com.pay.api.base.BaseRepositoryImpl;
import com.pay.api.roomUser.model.QRoomUser;
import com.pay.api.roomUser.model.RoomUser;
import com.pay.api.user.model.QUser;

import java.util.List;

public class RoomUserRepositoryCustomImpl extends BaseRepositoryImpl implements RoomUserRepositoryCustom {

    private final QRoomUser table = QRoomUser.roomUser;

    public RoomUserRepositoryCustomImpl() {
        super(RoomUser.class);
    }

    @Override
    public boolean isUserInRoom(long userId, long roomId) {
        return from(table).where(table.userId.eq(userId).and(table.roomId.eq(roomId))).fetchCount() > 0;
    }

    @Override
    public RoomUser findUserInRoom(long userId, long roomId) {
        return from(table).where(table.userId.eq(userId).and(table.roomId.eq(roomId))).fetchOne();
    }

    @Override
    public List<RoomUser> findUsersExceptUserId(long userId) {
        return from(table).where(table.userId.ne(userId)).fetch();
    }
}
