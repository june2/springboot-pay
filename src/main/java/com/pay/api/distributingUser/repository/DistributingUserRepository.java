package com.pay.api.distributingUser.repository;


import com.pay.api.distributingUser.model.DistributingUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistributingUserRepository extends JpaRepository<DistributingUser, Long>, DistributingUserRepositoryCustom {
}
