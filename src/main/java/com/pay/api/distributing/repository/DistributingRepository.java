package com.pay.api.distributing.repository;

import com.pay.api.distributing.model.Distributing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistributingRepository extends JpaRepository<Distributing, Long>, DistributingRepositoryCustom {
}
