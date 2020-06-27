package com.pay.unit;

import com.pay.api.user.repository.UserRepository;
import com.pay.api.user.service.UserService;
import com.pay.api.user.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldUserInRoom() {
        assertThat(userService.findAll().size()).isZero();
    }
}
