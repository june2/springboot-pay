package com.pay.api.distributing.repository;

import com.pay.api.distributing.model.Distributing;

public interface DistributingRepositoryCustom {
    Distributing findValidOne(long roomId, String token);
}
