package com.pay.api;


import com.pay.ApplicationTest;
import com.pay.api.room.model.Room;
import com.pay.api.room.repository.RoomRepository;
import com.pay.api.roomUser.model.RoomUser;
import com.pay.api.roomUser.repository.RoomUserRepository;
import com.pay.api.user.model.User;
import com.pay.api.user.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 받기 API 테스트
 */
public class GetDistributingTest  extends ApplicationTest {
    /**
     * 유효한 토큰이 아닐때,
     * 유저가 방에 존재하지 않음
     */
    @Test
    public void shouldGet401() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-USER-ID", "1");
        headers.add("X-ROOM-ID", "1");
        HttpEntity<String> request = new HttpEntity<String>(headers);

        ResponseEntity<Map> response = restTemplate.exchange("/api/users/room/distributing?token=abc", HttpMethod.GET, request, Map.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    /**
     * 잘못된 body 요청
     * number(뿌리기 인원), amount(뿌리기 금액)가 0일때
     * number(뿌리기 인원), amount(뿌리기 금액) 나누어 떨어지지 않을때
     */
    @Test
    public void shouldGet400RoomToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-USER-ID", "1");
        headers.add("X-ROOM-ID", "1");
        headers.add("Content-Type", "application/json");
        HttpEntity<String> request = new HttpEntity<String>(
                "{\"number\": 10, \"amount\":0 }",
                headers);
        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/room/token", request, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    /**
     * 유효한 토큰이 아닐때,
     * 유저가 방에 존재하지 않음
     */
    @Test
    public void shouldGet200() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-USER-ID", "1");
        headers.add("X-ROOM-ID", "1");
        HttpEntity<String> request = new HttpEntity<String>(headers);

        ResponseEntity<Map> response = restTemplate.exchange("/api/users/room/distributing?token=abc", HttpMethod.GET, request, Map.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

}

