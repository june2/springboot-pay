package com.pay.api.user.service;

import com.pay.api.distributing.model.Distributing;
import com.pay.api.distributing.repository.DistributingRepository;
import com.pay.api.roomUser.model.RoomUser;
import com.pay.api.roomUser.repository.RoomUserRepository;
import com.pay.api.user.dto.ReqToken;
import com.pay.api.user.model.User;
import com.pay.api.user.repository.UserRepository;
import com.pay.exception.ResourceUnauthorizedException;
import com.pay.util.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoomUserRepository roomUserRepository;
    @Autowired
    private DistributingRepository distributingRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public String addDistributing(long userId, long domainId, ReqToken reqToken) {
        // 유저가 room에 속해있는지 체크
        if (!roomUserRepository.isUserInRoom(userId, domainId)) {
            throw new ResourceUnauthorizedException("token", "id", userId);
        }
        // 토큰 생성
        String token = Security.generateToken(3);
        List<Distributing> list = new ArrayList<>();
        // 방 유저들 조회 && 뿌리기 저장
        roomUserRepository.findUsersExceptUserId(userId).forEach(user -> {
            list.add(Distributing.builder()
                    .userId(user.getUserId())
                    .token(token)
                    .amount(reqToken.getDistributingAmount())
                    .build()
            );
        });
        distributingRepository.saveAll(list);
        return token;
    }

    @Override
    public Distributing getDistributing(long userId, String token) {
        // 유효한 토큰 확인
        Distributing distributing = distributingRepository.findByUserIdAndToken(userId, token);
        if (!Optional.ofNullable(distributing).isPresent()) {
            throw new ResourceUnauthorizedException("token", "id", userId);
        }
        // 돈받기 업데이트
        distributingRepository.save(distributing);
        return distributing;
    }
}
