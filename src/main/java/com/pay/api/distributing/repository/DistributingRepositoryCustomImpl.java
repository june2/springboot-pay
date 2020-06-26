package com.pay.api.distributing.repository;

import com.pay.api.base.BaseRepositoryImpl;
import com.pay.api.distributing.model.Distributing;
import com.pay.api.distributing.model.QDistributing;
import com.pay.api.roomUser.model.QRoomUser;
import com.pay.api.roomUser.model.RoomUser;

import java.util.List;

public class DistributingRepositoryCustomImpl extends BaseRepositoryImpl implements DistributingRepositoryCustom {

    private final QDistributing table = QDistributing.distributing;

    public DistributingRepositoryCustomImpl() {
        super(Distributing.class);
    }

    @Override
    public Distributing findValidOne(long roomUserId, String token) {
        return from(table).where(
                table.roomUserId.eq(roomUserId)
                        .and(table.token.eq(token))
                        .and(table.takerId.isNull())).fetchOne();
    }

    @Override
    public boolean hasDistributing(long roomUserId, long userId, String token) {
        return from(table).where(
                table.roomUserId.eq(roomUserId)
                        .and(table.token.eq(token))
                        .and(table.takerId.eq(userId))).fetchCount() > 0;
    }

    @Override
    public List<Distributing> findByGiverIdAndToken(long roomUserId, long giverId, String token) {
        return from(table).where(
                table.roomUserId.eq(roomUserId)
                        .and(table.giverId.eq(giverId))
                        .and(table.token.eq(token))).fetch();
    }


}
