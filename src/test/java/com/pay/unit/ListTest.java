package com.pay.unit;


import com.pay.api.distributingUser.model.DistributingUser;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ListTest {

    @Test
    public void sum() {
        List<DistributingUser> users = new ArrayList<>();
        users.add(DistributingUser.builder().amount(0).build());
        users.add(DistributingUser.builder().amount(10).build());
        users.add(DistributingUser.builder().amount(20).build());
        users.add(DistributingUser.builder().amount(30).build());

        assertThat(users.stream()
                .filter(o -> o.getAmount() != 0)
                .mapToLong(o -> o.getAmount()).sum()
        ).isEqualTo(60);
    }
}
