package com.jakedelivery.db.userorder;

import com.jakedelivery.db.BaseEntity;
import com.jakedelivery.db._common.constant.UserOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_order")
@Entity
public class UserOrderEntity extends BaseEntity {
    @Column(nullable = false)
    private Long userId; // user table 1:n

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private UserOrderStatus status;

    @Column(precision = 11, scale = 4, nullable = false)
    private BigDecimal amount;

    private LocalDateTime orderedAt;
    private LocalDateTime acceptedAt;
    private LocalDateTime cookingStartedAt;
    private LocalDateTime deliveryStartedAt;
    private LocalDateTime receivedAt;
}
