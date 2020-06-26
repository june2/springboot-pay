package com.pay.api.user.controller;

import com.pay.api.roomUser.service.RoomUserService;
import com.pay.api.user.constant.UserConstant;
import com.pay.api.user.dto.ReqToken;
import com.pay.api.user.service.UserService;
import com.pay.handler.ResponseHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"User"})
@Slf4j
@RestController
@RequestMapping(value = UserConstant.API_URI_PREFIX)
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoomUserService roomUserService;

    @ApiOperation(value = "모든 사용자 조히", notes = "모든 사용자 조히", httpMethod = "GET")
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(
                ResponseHandler.success(userService.findAll()), HttpStatus.OK);
    }

    @ApiOperation(value = "뿌리기", notes = "뿌리기", httpMethod = "POST")
    @PostMapping("/room/distributing")
    public ResponseEntity<?> addDistributing(@RequestBody @Valid ReqToken reqToken,
                                             @RequestHeader(value = "X-USER-ID", required = true) long userId,
                                             @RequestHeader(value = "X-ROOM-ID", required = true) long roomId) {
        // 뿌리기 & token 생성
        return new ResponseEntity<>(ResponseHandler.success(userService.addDistributing(userId, roomId, reqToken)), HttpStatus.OK);
    }

    @ApiOperation(value = "받기", notes = "받기", httpMethod = "POST")
    @GetMapping("/room/distributing")
    public ResponseEntity<?> getDistributing(@RequestParam String token,
                                             @RequestHeader(value = "X-USER-ID", required = true) long userId,
                                             @RequestHeader(value = "X-ROOM-ID", required = true) long roomId) {
        return new ResponseEntity<>(ResponseHandler.success(userService.getDistributing(userId, token)), HttpStatus.OK);
    }
}
