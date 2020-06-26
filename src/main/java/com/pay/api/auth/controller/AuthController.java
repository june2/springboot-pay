package com.pay.api.auth.controller;

import com.pay.api.auth.constant.AuthConstant;
import com.pay.api.auth.service.AuthService;
import com.pay.api.user.service.UserService;
import com.pay.jwt.JwtService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Auth"})
@Slf4j
@RestController
@RequestMapping(value = AuthConstant.API_URI_PREFIX)
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

//    @ApiOperation(value = "Create User", notes = "Create User", response = AuthReqDTO.class, httpMethod = "POST")
//    @PostMapping("/signUp")
//    public ResponseHandler create(@RequestBody AuthReqDTO req) {
//        return ResponseHandler.builder().status("success").data(userService.create(
//                User.builder().
//                        email(req.getEmail()).
//                        password(Security.encode(req.getPassword())).
//                        build()).getEmail()).build();
//    }

//    @ApiOperation(value = "Login", notes = "Login", response = AuthReqDTO.class, httpMethod = "POST")
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody AuthReqDTO req) {
//        try {
//            User user = userService.findByEmail(req.getEmail());
//            if (!Optional.ofNullable(user).isPresent()) throw new Error();
//            if (!authService.authenticate(req.getPassword(), user.getPassword())) throw new Error();
//            return ResponseEntity.ok(jwtService.getToken(user));
//        } catch (Error err) {
//            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
//        }
//    }
}
