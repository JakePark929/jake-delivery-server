package com.jakedelivery.db.userordermenu;

import com.jakedelivery.db.BaseEntity;
import com.jakedelivery.db._common.constant.UserOrderMenuStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_order_menu")
@Entity
public class UserOrderMenuEntity extends BaseEntity {
    @Column(nullable = false)
    private Long userOrderId; // 1:n

    @Column(nullable = false)
    private Long storeMenuId;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private UserOrderMenuStatus status;
}
