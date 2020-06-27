package com.pay.unit;


import com.pay.util.Security;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

public class TokenTest {

    @Test
    public void encode() {
        String password = "123456";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        System.out.println(hashedPassword);
        assertThat(passwordEncoder.matches(password, hashedPassword)).isTrue();
    }

    @Test
    public void shouldTokenSize3() {
        assertThat(Security.generateToken(3).length()).isEqualTo(3);
    }
}
