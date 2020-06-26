package com.pay.api;


import com.pay.ApplicationTest;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 뿌리기 API 테스트
 */
public class AddDistributingTest extends ApplicationTest {

    /**
     * 유효한 방이 아닐때,
     * 유저가 방에 존재하지 않음
     */
    @Test
    public void shouldGet401() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-USER-ID", "100");
        headers.add("X-ROOM-ID", "100");
        headers.add("Content-Type", "application/json");
        HttpEntity<String> request = new HttpEntity<String>(
                "{\"number\": 10, \"amount\":10 }",
                headers);
        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/room/distributing", request, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    /**
     * 잘못된 body 요청
     * number(뿌리기 인원), amount(뿌리기 금액)가 0일때
     * number(뿌리기 인원), amount(뿌리기 금액) 나누어 떨어지지 않을때
     */
    @Test
    public void shouldGet400() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-USER-ID", "100");
        headers.add("X-ROOM-ID", "100");
        headers.add("Content-Type", "application/json");
        HttpEntity<String> request = new HttpEntity<String>(
                "{\"number\": 10, \"amount\":0 }",
                headers);
        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/room/distributing", request, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    /**
     * 정상 동작, 토큰값 리턴
     */
    @Test
    public void shouldGet200() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-USER-ID", "1");
        headers.add("X-ROOM-ID", "1");
        headers.add("Content-Type", "application/json");
        HttpEntity<String> request = new HttpEntity<String>(
                "{\"number\": 10, \"amount\":100 }",
                headers);
        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/room/distributing", request, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}

