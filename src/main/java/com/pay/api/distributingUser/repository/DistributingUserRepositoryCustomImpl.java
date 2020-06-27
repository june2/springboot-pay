package com.pay.api.distributingUser.repository;

import com.pay.api.base.BaseRepositoryImpl;
import com.pay.api.distributingUser.model.DistributingUser;
import com.pay.api.distributingUser.model.QDistributingUser;

import java.util.List;

public class DistributingUserRepositoryCustomImpl extends BaseRepositoryImpl implements DistributingUserRepositoryCustom {

    private final QDistributingUser table = QDistributingUser.distributingUser;

    public DistributingUserRepositoryCustomImpl() {
        super(DistributingUser.class);
    }

    @Override
    public boolean hasOne(long distributingId, long userId) {
        return from(table).where(table.distributingId.eq(distributingId)
                .and(table.userId.eq(userId))).fetchCount() > 0;
    }

    @Override
    public List<DistributingUser> findByDistributingId(long distributingId) {
        return from(table).where(table.distributingId.eq(distributingId)).fetch();
    }

    @Override
    public long cntBy(long distributingId) {
        return from(table).where(table.distributingId.eq(distributingId)).fetchCount();
    }
}
