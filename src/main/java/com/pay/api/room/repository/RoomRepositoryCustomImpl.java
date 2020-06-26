package com.pay.api.room.repository;

import com.pay.api.base.BaseRepositoryImpl;
import com.pay.api.room.model.QRoom;
import com.pay.api.room.model.Room;

public class RoomRepositoryCustomImpl extends BaseRepositoryImpl implements RoomRepositoryCustom {

    private final QRoom table = QRoom.room;

    public RoomRepositoryCustomImpl() {
        super(Room.class);
    }
}
