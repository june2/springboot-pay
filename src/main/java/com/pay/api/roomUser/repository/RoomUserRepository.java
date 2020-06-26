package com.pay.api.roomUser.repository;

import com.pay.api.roomUser.model.RoomUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomUserRepository extends JpaRepository<RoomUser, Long>, RoomUserRepositoryCustom {
}
