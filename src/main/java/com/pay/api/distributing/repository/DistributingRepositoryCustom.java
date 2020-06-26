package com.pay.api.distributing.repository;

import com.pay.api.distributing.model.Distributing;

import java.util.List;

public interface DistributingRepositoryCustom {
    Distributing findValidOne(long userId, String token);

    boolean hasDistributing(long roomUserId, long userId, String token);

    List<Distributing> findByGiverIdAndToken(long roomUserId, long giverId, String token);
}
