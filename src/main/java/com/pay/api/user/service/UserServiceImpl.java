package com.pay.api.user.service;

import com.pay.api.distributing.model.Distributing;
import com.pay.api.distributing.repository.DistributingRepository;
import com.pay.api.distributingUser.model.DistributingUser;
import com.pay.api.distributingUser.repository.DistributingUserRepository;
import com.pay.api.roomUser.model.RoomUser;
import com.pay.api.roomUser.repository.RoomUserRepository;
import com.pay.api.user.dto.ReqToken;
import com.pay.api.user.dto.ResDistributingUsers;
import com.pay.api.user.model.User;
import com.pay.api.user.repository.UserRepository;
import com.pay.exception.ForbiddenException;
import com.pay.exception.NotFoundException;
import com.pay.exception.UnauthorizedException;
import com.pay.handler.ResponseType;
import com.pay.util.Date;
import com.pay.util.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private DistributingUserRepository distributingUserRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public String addDistributing(long userId, long roomId, ReqToken reqToken) {
        // 유저가 room에 속해있는지 체크
        RoomUser roomUser = roomUserRepository.findUserInRoom(userId, roomId);
        if (!Optional.ofNullable(roomUser).isPresent()) {
            throw new UnauthorizedException(ResponseType.INVALID_USER, "Invalid user in room");
        }
        // 유저 잔액 조회
        Optional<User> userO = userRepository.findById(userId);
        if (!userO.isPresent()) {
            throw new NotFoundException(ResponseType.NOT_FOUND, "not found user");
        }
        User user = userO.get();
        if (reqToken.getAmount() > user.getBalence()) {
            throw new ForbiddenException(ResponseType.NOT_ENOUGH, "not enouggh");
        }
        // 토큰 생성
        String token = Security.generateToken(3);
        // TODO: 방 유저 수 조회?
        // 뿌리기 저장
        distributingRepository.save(
                Distributing.builder()
                        .amount(reqToken.getAmount())
                        .number(reqToken.getNumber())
                        .userId(userId)
                        .token(token)
                        .roomId(roomId)
                        .build());
        // 잔액 차감
        user.setBalence(user.getBalence() - reqToken.getAmount());
        userRepository.save(user);
        return token;
    }

    @Transactional
    @Override
    public DistributingUser getDistributing(long userId, long roomId, String token) {
        // 유저가 room에 속해있는지 체크
        RoomUser roomUser = roomUserRepository.findUserInRoom(userId, roomId);
        if (!Optional.ofNullable(roomUser).isPresent()) {
            throw new UnauthorizedException(ResponseType.INVALID_USER, "Invalid user");
        }
        // 뿌리기 유효성 조회
        Distributing distributing = distributingRepository.findValidOne(roomId, token);
        if (!Optional.ofNullable(distributing).isPresent()) {
            throw new UnauthorizedException(ResponseType.INVALID_TOKEN, "Invalid token");
        }
        // 뿌리기 본인 체크
        if (distributing.getUserId() == userId) {
            throw new UnauthorizedException(ResponseType.INVALID_USER, "can`t get money by yourself");
        }
        // 시간체크 10분 유효
        if (!Date.isValidTime(distributing.getCreatedAt(), 10)) {
            throw new UnauthorizedException(ResponseType.INVALID_TIME, "Token time out");
        }
        // 토큰 받았는지 체큰
        if (distributingUserRepository.hasOne(distributing.getId(), userId)) {
            throw new ForbiddenException(ResponseType.ALREADY_TAKEN, "Already taken");
        }
        // 유저 잔액 조회
        Optional<User> userO = userRepository.findById(userId);
        if (!userO.isPresent()) {
            throw new NotFoundException(ResponseType.NOT_FOUND, "not found user");
        }
        // 돈받기 업데이트 && 잔액 증감
        User user = userO.get();
        long money = distributing.getAmount() / distributing.getNumber();
        user.setBalence(user.getBalence() + money);
        userRepository.save(user);
        return distributingUserRepository.save(
                DistributingUser.builder()
                        .userId(userId)
                        .amount(money)
                        .distributingId(distributing.getId())
                        .build());
    }

    @Override
    public ResDistributingUsers getDistributingUsers(long userId, long roomId, String token) {
        // 유저가 room에 속해있는지 체크
        RoomUser roomUser = roomUserRepository.findUserInRoom(userId, roomId);
        if (!Optional.ofNullable(roomUser).isPresent()) {
            throw new UnauthorizedException(ResponseType.INVALID_USER, "Invalid user");
        }
        // 뿌리기 유효성 토큰 조회
        Distributing distributing = distributingRepository.findValidOne(roomId, token);
        if (!Optional.ofNullable(distributing).isPresent()) {
            throw new UnauthorizedException(ResponseType.INVALID_TOKEN, "Invalid token");
        }
        // 뿌리기 본인 체크
        if (distributing.getUserId() != userId) {
            throw new UnauthorizedException(ResponseType.INVALID_USER, "User is not owner");
        }
        // 시간체크 7시간 유효
        if (!Date.isValidTime(distributing.getCreatedAt(), 60 * 24 * 7)) {
            throw new UnauthorizedException(ResponseType.INVALID_TIME, "Token time out");
        }
        // 받은 사용자 조회
        List<DistributingUser> users = distributingUserRepository.findByDistributingId(distributing.getId());
        return ResDistributingUsers.builder()
                .amount(distributing.getAmount())
                .date(distributing.getCreatedAt())
                .takenAmount(users.stream().filter(o -> o.getAmount() != 0)
                        .mapToLong(o -> o.getAmount()).sum())
                .users(users)
                .build();
    }
}
