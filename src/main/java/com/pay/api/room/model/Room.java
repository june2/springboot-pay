package com.pay.api.room.model;

import com.pay.api.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Builder
@Entity
@AllArgsConstructor()
@Table(name = "ROOM")
public class Room extends BaseEntity {
    String name;
}