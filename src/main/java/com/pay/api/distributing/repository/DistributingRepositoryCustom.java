package com.pay.api.distributing.repository;

import com.pay.api.distributing.model.Distributing;

public interface DistributingRepositoryCustom {
    Distributing findValidOne(long userId, String token);
    boolean hasDistributing(long roomUserId, long userId, String token);
}
