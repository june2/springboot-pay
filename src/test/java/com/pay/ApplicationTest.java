package com.pay;


import com.pay.api.distributing.model.Distributing;
import com.pay.api.distributing.repository.DistributingRepository;
import com.pay.api.room.model.Room;
import com.pay.api.room.repository.RoomRepository;
import com.pay.api.roomUser.model.RoomUser;
import com.pay.api.roomUser.repository.RoomUserRepository;
import com.pay.api.user.model.User;
import com.pay.api.user.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

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

    @Before
    public void init() {
        List<User> users = new ArrayList<>();
        users.add(User.builder().name("user1").build());
        users.add(User.builder().name("user2").build());
        users.add(User.builder().name("user3").build());

        List<Room> rooms = new ArrayList<>();
        rooms.add(Room.builder().name("room1").build());
        rooms.add(Room.builder().name("room2").build());

        List<RoomUser> roomUsers = new ArrayList<>();
        roomUsers.add(RoomUser.builder().roomId(1L).userId(1L).build());
        roomUsers.add(RoomUser.builder().roomId(1L).userId(2L).build());
        roomUsers.add(RoomUser.builder().roomId(1L).userId(3L).build());

        List<Distributing> distributings = new ArrayList<>();
        distributings.add(Distributing.builder().roomUserId(1L).giverId(1L).amount(1000).token("qwe").build());

        userRepository.saveAll(users);
        roomRepository.saveAll(rooms);
        roomUserRepository.saveAll(roomUsers);
        distributingRepository.saveAll(distributings);
    }

//    @After
    public void clear() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.createNativeQuery("TRUNCATE TABLE `hibernate_sequence`").executeUpdate();
        em.createNativeQuery("TRUNCATE TABLE `room`").executeUpdate();
        em.createNativeQuery("TRUNCATE TABLE `user`").executeUpdate();
        em.createNativeQuery("TRUNCATE TABLE `room_user`").executeUpdate();
        em.createNativeQuery("TRUNCATE TABLE `distributing`").executeUpdate();
        tx.commit();
        em.close();
    }

}

