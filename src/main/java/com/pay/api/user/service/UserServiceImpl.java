package com.pay.api.user.service;

import com.pay.api.distributing.model.Distributing;
import com.pay.api.distributing.repository.DistributingRepository;
import com.pay.api.roomUser.model.RoomUser;
import com.pay.api.roomUser.repository.RoomUserRepository;
import com.pay.api.user.dto.ReqToken;
import com.pay.api.user.model.User;
import com.pay.api.user.repository.UserRepository;
import com.pay.exception.ResourceUnauthorizedException;
import com.pay.util.Date;
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
            throw new ResourceUnauthorizedException("Invalid user in room");
        }
        // 토큰 생성
        String token = Security.generateToken(3);
        // TODO: 방 유저 수 조회?
        // 뿌리기 저장
        List<Distributing> list = new ArrayList<>();
        for (int i = 0; i < reqToken.getNumber(); i++) {
            list.add(Distributing.builder()
                    .token(token)
                    .amount(reqToken.getDistributingAmount())
                    .build());
        }
        distributingRepository.saveAll(list);
        return token;
    }

    @Transactional
    @Override
    public Distributing getDistributing(long userId, long roomId, String token) {
        // 유저가 room에 속해있는지 체크
        RoomUser roomUser = roomUserRepository.findUserInRoom(userId, roomId);
        if (!Optional.ofNullable(roomUser).isPresent()) {
            throw new ResourceUnauthorizedException("Invalid user in room");
        }
        // 유저가 뿌리기 받았는지 체크
        if (distributingRepository.hasDistributing(roomUser.getId(), userId, token)) {
            throw new ResourceUnauthorizedException("Already money taken");
        }
        // 유효한 토큰 확인
        Distributing distributing = distributingRepository.findValidOne(roomUser.getId(), token);
        if (!Optional.ofNullable(distributing).isPresent()) {
            throw new ResourceUnauthorizedException("Invalid toekn");
        }
        // 시간체크 10분 유효
        if (!Date.isValidTime(distributing.getCreatedAt(), 10)) {
            throw new ResourceUnauthorizedException("Token time out");
        }
        // 돈받기 업데이트
        distributing.setTakerId(userId);
        distributing.setTakenAt(Date.getNow());
        distributingRepository.save(distributing);
        return distributing;
    }
}
