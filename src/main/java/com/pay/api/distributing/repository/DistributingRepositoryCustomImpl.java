package com.pay.api.distributing.repository;

import com.pay.api.base.BaseRepositoryImpl;
import com.pay.api.distributing.model.Distributing;
import com.pay.api.distributing.model.QDistributing;

public class DistributingRepositoryCustomImpl extends BaseRepositoryImpl implements DistributingRepositoryCustom {

    private final QDistributing table = QDistributing.distributing;

    public DistributingRepositoryCustomImpl() {
        super(Distributing.class);
    }

    @Override
    public Distributing findValidOne(long roomId, String token) {
        return from(table).where(
                table.roomId.eq(roomId)
                        .and(table.token.eq(token))).fetchOne();
    }
}
