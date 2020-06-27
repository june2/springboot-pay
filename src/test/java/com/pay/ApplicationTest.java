package com.pay;


import com.pay.api.distributing.model.Distributing;
import com.pay.api.distributing.repository.DistributingRepository;
import com.pay.api.distributingUser.model.DistributingUser;
import com.pay.api.distributingUser.repository.DistributingUserRepository;
import com.pay.api.room.model.Room;
import com.pay.api.room.repository.RoomRepository;
import com.pay.api.roomUser.model.RoomUser;
import com.pay.api.roomUser.repository.RoomUserRepository;
import com.pay.api.user.model.User;
import com.pay.api.user.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTest {

    @Autowired
    private EntityManagerFactory emf;

    @Autowired
    protected TestRestTemplate restTemplate;
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected RoomRepository roomRepository;
    @Autowired
    protected RoomUserRepository roomUserRepository;
    @Autowired
    protected DistributingRepository distributingRepository;
    @Autowired
    protected DistributingUserRepository distributingUserRepository;

    @Before
    public void init() {
        List<User> users = new ArrayList<>();
        User user1 = User.builder().name("user1").balence(20000).build();
        user1.setId(1L);
        User user2 = User.builder().name("user2").build();
        user2.setId(2L);
        User user3 = User.builder().name("user3").build();
        user3.setId(3L);
        users.add(user1);
        users.add(user2);
        users.add(user3);

        List<Room> rooms = new ArrayList<>();
        Room room1 = Room.builder().name("room1").build();
        room1.setId(4L);
        Room room2 = Room.builder().name("room2").build();
        room2.setId(5L);
        rooms.add(room1);
        rooms.add(room2);

        List<RoomUser> roomUsers = new ArrayList<>();
        RoomUser roomUsers1 = RoomUser.builder().roomId(4L).userId(1L).build();
        RoomUser roomUsers2 = RoomUser.builder().roomId(4L).userId(2L).build();
        RoomUser roomUsers3 = RoomUser.builder().roomId(4L).userId(3L).build();
        roomUsers.add(roomUsers1);
        roomUsers.add(roomUsers2);
        roomUsers.add(roomUsers3);

        List<Distributing> distributings = new ArrayList<>();
        distributings.add(Distributing.builder()
                .roomId(4L)
                .userId(1L)
                .amount(1000)
                .number(2)
                .token("abc")
                .build());
        distributings.add(Distributing.builder()
                .roomId(4L)
                .userId(1L)
                .amount(1000)
                .number(2)
                .token("qwe")
                .build());
        distributings.add(Distributing.builder()
                .roomId(4L)
                .userId(1L)
                .amount(1000)
                .number(1)
                .token("asd")
                .build());
        distributings.add(Distributing.builder()
                .roomId(4L)
                .userId(1L)
                .amount(1000)
                .number(1)
                .token("zxc")
                .build());

        userRepository.saveAll(users);
        roomRepository.saveAll(rooms);
        roomUserRepository.saveAll(roomUsers);
        distributingRepository.saveAll(distributings);
        distributingUserRepository.save(DistributingUser.builder()
                .userId(2L).distributingId(9).amount(500).takenAt(LocalDateTime.now()).build());
        distributingUserRepository.save(DistributingUser.builder()
                .userId(2L).distributingId(12).amount(1000).takenAt(LocalDateTime.now()).build());

        Optional<Distributing> d = distributingRepository.findById(10L);
        Distributing d1 = d.get();
        d1.setCreatedAt(LocalDateTime.now().minusDays(1L));
        distributingRepository.save(d1);

        d = distributingRepository.findById(11L);
        Distributing d2 = d.get();
        d2.setCreatedAt(LocalDateTime.now().minusMonths(1L));
        distributingRepository.save(d2);
    }

    @After
    public void clear() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.createNativeQuery("UPDATE hibernate_sequence SET next_val = 1").executeUpdate();
        em.createNativeQuery("TRUNCATE TABLE `room`").executeUpdate();
        em.createNativeQuery("TRUNCATE TABLE `room`").executeUpdate();
        em.createNativeQuery("TRUNCATE TABLE `user`").executeUpdate();
        em.createNativeQuery("TRUNCATE TABLE `room_user`").executeUpdate();
        em.createNativeQuery("TRUNCATE TABLE `distributing`").executeUpdate();
        em.createNativeQuery("TRUNCATE TABLE `distributing_user`").executeUpdate();
        tx.commit();
        em.close();
    }

    @Test
    public void run() {
//        assertThat(true).isTrue();
    }
}

