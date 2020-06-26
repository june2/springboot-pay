package com.pay.api.distributing.model;

import com.pay.api.base.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@Entity
@AllArgsConstructor()
@NoArgsConstructor()
@Table(name = "DISTRIBUTING")
public class Distributing extends BaseEntity {
    @Column(name = "room_user_id")
    long roomUserId;
    @Column(name = "giver_id")
    long giverId;
    @Column(name = "taker_id")
    long takerId;
    long amount;
    String token;
    @Column(name = "taken_at")
    private LocalDateTime takenAt;
}