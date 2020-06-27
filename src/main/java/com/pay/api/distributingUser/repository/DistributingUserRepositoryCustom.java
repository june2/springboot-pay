package com.pay.api.distributingUser.repository;


import com.pay.api.distributingUser.model.DistributingUser;

import java.util.List;

public interface DistributingUserRepositoryCustom {
    boolean hasOne(long distributingId, long userId);

    List<DistributingUser> findByDistributingId(long distributingId);

    long cntBy(long distributingId);
}
