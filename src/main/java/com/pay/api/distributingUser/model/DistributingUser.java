package com.pay.api.distributingUser.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pay.api.base.BaseEntity;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@Entity
@AllArgsConstructor()
@NoArgsConstructor()
@JsonIgnoreProperties(value = {"takenAt"}, allowGetters = true)
@EntityListeners({AuditingEntityListener.class})
@Table(name = "DISTRIBUTING_USER")
public class DistributingUser {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "distributing_id")
    long distributingId;

    @Column(name = "user_id")
    long userId;

    long amount;

    @CreationTimestamp
    @Column(name = "taken_at")
    private LocalDateTime takenAt;
}