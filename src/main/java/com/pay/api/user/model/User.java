package com.pay.api.user.model;

import com.pay.api.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Builder
@Entity
@AllArgsConstructor()
@NoArgsConstructor()
@Table(name = "USER")
public class User extends BaseEntity {
	@Column(unique=true)
	private String email;
	private String password;
	private String name;
}