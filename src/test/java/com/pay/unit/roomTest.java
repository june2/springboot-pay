package com.pay.unit;

import com.pay.api.roomUser.repository.RoomUserRepository;
import com.pay.api.roomUser.service.RoomUserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;

public class roomTest {

    @InjectMocks
    private RoomUserServiceImpl roomUserService;

    @Mock
    private RoomUserRepository roomUserRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldUserInRoom() {
        assertThat(roomUserService.isUserInRoom(1L, 1L)).isFalse();
    }
}
