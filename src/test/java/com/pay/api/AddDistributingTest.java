package com.pay.api;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 뿌리기 API 테스트
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddDistributingTest {

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * 유효한 방이 아닐때,
     * 유저가 방에 존재하지 않음
     */
    @Test
    public void shouldGet401RoomToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-USER-ID", "100");
        headers.add("X-ROOM-ID", "100");
        headers.add("Content-Type", "application/json");
        HttpEntity<String> request = new HttpEntity<String>(
                "{\"number\": 10, \"amount\":10 }",
                headers);
        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/room/token", request, String.class);
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
        headers.add("X-USER-ID", "100");
        headers.add("X-ROOM-ID", "100");
        headers.add("Content-Type", "application/json");
        HttpEntity<String> request = new HttpEntity<String>(
                "{\"number\": 10, \"amount\":0 }",
                headers);
        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/room/token", request, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

}

