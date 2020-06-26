package com.pay;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldGetApiInfo() {
        ResponseEntity<HashMap> response = restTemplate.getForEntity("/", HashMap.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}

