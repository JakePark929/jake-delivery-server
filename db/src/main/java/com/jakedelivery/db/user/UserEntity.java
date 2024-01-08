package com.jakedelivery.db.user;

import com.jakedelivery.db.BaseEntity;
import com.jakedelivery.db._common.constant.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Entity
public class UserEntity extends BaseEntity {
    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 150, nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private UserStatus status;

    private LocalDateTime registeredAt;
    private LocalDateTime unRegisteredAt;
    private LocalDateTime lastLoginAt;
}
