package com.pay.api;


import com.pay.ApplicationTest;
import org.junit.Test;
import org.springframework.http.*;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 조회 API 테스트
 */
public class GetDistributingUserTest extends ApplicationTest {
    /**
     * 유저가 방에 존재하지 않음
     * 유효한 유저가 아님
     */
    @Test
    public void shouldGetC4011() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-USER-ID", "1");
        headers.add("X-ROOM-ID", "10");
        HttpEntity<String> request = new HttpEntity<String>(headers);

        ResponseEntity<Map> response = restTemplate.exchange("/api/users/room/distributing/users?token=abc", HttpMethod.GET, request, Map.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        assertThat(response.getBody().get("code").toString()).isEqualTo("C4011");
    }

    /**
     * 유효한 토큰이 아닐때
     */
    @Test
    public void shouldGetC4010() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-USER-ID", "1");
        headers.add("X-ROOM-ID", "4");
        HttpEntity<String> request = new HttpEntity<String>(headers);

        ResponseEntity<Map> response = restTemplate.exchange("/api/users/room/distributing/users?token=abcd", HttpMethod.GET, request, Map.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        assertThat(response.getBody().get("code").toString()).isEqualTo("C4010");
    }

    /**
     * 뿌린 본인이 요청이 아닐때, 금지
     */
    @Test
    public void shouldGetC4011_2() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-USER-ID", "2");
        headers.add("X-ROOM-ID", "4");
        HttpEntity<String> request = new HttpEntity<String>(headers);

        ResponseEntity<Map> response = restTemplate.exchange("/api/users/room/distributing/users?token=abc", HttpMethod.GET, request, Map.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        assertThat(response.getBody().get("code").toString()).isEqualTo("C4011");
    }

    /**
     * 토큰 타임아웃 체크
     */
    @Test
    public void shouldGetC4012() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-USER-ID", "1");
        headers.add("X-ROOM-ID", "4");
        HttpEntity<String> request = new HttpEntity<String>(headers);

        ResponseEntity<Map> response = restTemplate.exchange("/api/users/room/distributing/users?token=asd", HttpMethod.GET, request, Map.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        assertThat(response.getBody().get("code").toString()).isEqualTo("C4012");
    }

    /**
     * 정상 동작
     */
    @Test
    public void shouldGetC200() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-USER-ID", "1");
        headers.add("X-ROOM-ID", "4");
        HttpEntity<String> request = new HttpEntity<String>(headers);

        ResponseEntity<Map> response = restTemplate.exchange("/api/users/room/distributing/users?token=abc", HttpMethod.GET, request, Map.class);
        System.out.println(response.getBody().get("data"));
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}

