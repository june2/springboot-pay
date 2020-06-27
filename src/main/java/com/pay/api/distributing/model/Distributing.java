package com.pay.api.distributing.model;

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
@NoArgsConstructor()
@Table(name = "DISTRIBUTING")
public class Distributing extends BaseEntity {

    @Column(name = "room_id")
    long roomId;

    @Column(name = "user_id")
    long userId;

    long amount;

    long number;

    String token;
}