package com.pay.api.roomUser.model;

import com.pay.api.base.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Builder
@Entity
@AllArgsConstructor()
@Table(name = "ROOM_USER")
public class RoomUser extends BaseEntity {
    @Column(name = "user_id")
    long userId;
    @Column(name = "room_id")
    long roomId;
}