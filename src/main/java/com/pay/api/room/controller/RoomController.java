package com.pay.api.room.controller;

import com.pay.api.room.constant.RoomConstant;
import com.pay.api.room.service.RoomService;
import com.pay.api.user.constant.UserConstant;
import com.pay.api.user.service.UserService;
import com.pay.handler.ResponseHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Room"})
@Slf4j
@RestController
@RequestMapping(value = RoomConstant.API_URI_PREFIX)
public class RoomController {

    @Autowired
    private RoomService roomService;

    @ApiOperation(value = "Find All rooms", notes = "Find All rooms", httpMethod = "GET")
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(
                ResponseHandler.success(roomService.findAll()), HttpStatus.OK);
    }
}
