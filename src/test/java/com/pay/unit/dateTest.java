package com.pay.unit;


import com.pay.util.Date;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class dateTest {

    @Test
    public void validDate() {
        assertThat(Date.isValidTime(
                LocalDateTime.now().minusMinutes(5), 10)
        ).isTrue();
    }
}
