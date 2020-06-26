package com.pay.api.distributing.model;

import com.pay.api.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Builder
@Entity
@AllArgsConstructor()
@Table(name = "DISTRIBUTING")
public class Distributing extends BaseEntity {
    @Column(name = "room_user_id")
    long roomUserId;
    @Column(name = "user_id")
    long userId;
    long amount;
    String token;
}