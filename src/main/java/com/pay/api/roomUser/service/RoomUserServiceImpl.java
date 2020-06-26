package com.pay.api.roomUser.service;

import com.pay.api.roomUser.repository.RoomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomUserServiceImpl implements RoomUserService {

    @Autowired
    private RoomUserRepository roomUserRepository;

    @Override
    public boolean isUserInRoom(long userId, long roomId) {
        return roomUserRepository.isUserInRoom(userId, roomId);
    }
}
