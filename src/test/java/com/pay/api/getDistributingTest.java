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
     * 유저가 방에 존재하지 않음
     * 유효한 유저가 아님
     */
    @Test
    public void shouldGetC4011() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-USER-ID", "1");
        headers.add("X-ROOM-ID", "10");
        HttpEntity<String> request = new HttpEntity<String>(headers);

        ResponseEntity<Map> response = restTemplate.exchange("/api/users/room/distributing?token=abc", HttpMethod.GET, request, Map.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        assertThat(response.getBody().get("code").toString()).isEqualTo("C4011");

    }

    /**
     * 유효한 토큰이 아닐때
     */
    @Test
    public void shouldGetC4010() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-USER-ID", "2");
        headers.add("X-ROOM-ID", "4");
        HttpEntity<String> request = new HttpEntity<String>(headers);

        ResponseEntity<Map> response = restTemplate.exchange("/api/users/room/distributing?token=abcd", HttpMethod.GET, request, Map.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        assertThat(response.getBody().get("code").toString()).isEqualTo("C4010");
    }

    /**
     * 뿌린 본인이 요청할때, 금지
     */
    @Test
    public void shouldGetC4011_2() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-USER-ID", "1");
        headers.add("X-ROOM-ID", "4");
        HttpEntity<String> request = new HttpEntity<String>(headers);

        ResponseEntity<Map> response = restTemplate.exchange("/api/users/room/distributing?token=abc", HttpMethod.GET, request, Map.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        assertThat(response.getBody().get("code").toString()).isEqualTo("C4011");
    }

    /**
     * 토큰 타임아웃 체크
     */
    @Test
    public void shouldGetC4012() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-USER-ID", "2");
        headers.add("X-ROOM-ID", "4");
        HttpEntity<String> request = new HttpEntity<String>(headers);

        ResponseEntity<Map> response = restTemplate.exchange("/api/users/room/distributing?token=qwe", HttpMethod.GET, request, Map.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        assertThat(response.getBody().get("code").toString()).isEqualTo("C4012");
    }

    /**
     * 이미 가져간 유저, 에러
     */
    @Test
    public void shouldGetC4013() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-USER-ID", "2");
        headers.add("X-ROOM-ID", "4");
        HttpEntity<String> request = new HttpEntity<String>(headers);

        ResponseEntity<Map> response = restTemplate.exchange("/api/users/room/distributing?token=abc", HttpMethod.GET, request, Map.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
        assertThat(response.getBody().get("code").toString()).isEqualTo("C4013");
    }

    /**
     * 이미 소진체크, 에러
     */
    @Test
    public void shouldGetC4015() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-USER-ID", "3");
        headers.add("X-ROOM-ID", "4");
        HttpEntity<String> request = new HttpEntity<String>(headers);

        ResponseEntity<Map> response = restTemplate.exchange("/api/users/room/distributing?token=zxc", HttpMethod.GET, request, Map.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
        assertThat(response.getBody().get("code").toString()).isEqualTo("C4015");
    }

    /**
     * 정상 동작
     */
    @Test
    public void shouldGetC200() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-USER-ID", "3");
        headers.add("X-ROOM-ID", "4");
        HttpEntity<String> request = new HttpEntity<String>(headers);

        ResponseEntity<Map> response = restTemplate.exchange("/api/users/room/distributing?token=abc", HttpMethod.GET, request, Map.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(this.userRepository.findById(3L).get().getBalence()).isEqualTo(500);
    }
}

