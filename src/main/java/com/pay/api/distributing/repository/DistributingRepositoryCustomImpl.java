package com.pay.api.distributing.repository;

import com.pay.api.base.BaseRepositoryImpl;
import com.pay.api.distributing.model.Distributing;
import com.pay.api.distributing.model.QDistributing;
import com.pay.api.roomUser.model.QRoomUser;
import com.pay.api.roomUser.model.RoomUser;

public class DistributingRepositoryCustomImpl extends BaseRepositoryImpl implements DistributingRepositoryCustom {

    private final QDistributing table = QDistributing.distributing;

    public DistributingRepositoryCustomImpl() {
        super(Distributing.class);
    }

    @Override
    public Distributing findByUserIdAndToken(long userId, String token) {
        return from(table).where(table.userId.eq(userId).and(table.token.eq(token))).fetchOne();
    }
}
