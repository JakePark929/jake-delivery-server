package com.jakedelivery.db.storeuser;

import com.jakedelivery.db.BaseEntity;
import com.jakedelivery.db._common.constant.StoreUserRole;
import com.jakedelivery.db._common.constant.StoreUserStatus;
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
@Table(name = "store_user")
@Entity
public class StoreUserEntity extends BaseEntity {
    @Column(nullable = false)
    private Long storeId;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private StoreUserStatus status;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private StoreUserRole role;

    private LocalDateTime registeredAt;
    private LocalDateTime unRegisteredAt;
    private LocalDateTime lastLoginAt;
}
