package com.jakedelivery.db.userorder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jakedelivery.db.BaseEntity;
import com.jakedelivery.db._common.constant.UserOrderStatus;
import com.jakedelivery.db.store.StoreEntity;
import com.jakedelivery.db.userordermenu.UserOrderMenuEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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

    @JoinColumn(nullable = false, name="storeId")
    @ManyToOne
    private StoreEntity store;

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

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "userOrder")
    private List<UserOrderMenuEntity> userOrderMenuEntities;
}
