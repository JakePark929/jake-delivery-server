package com.jakedelivery.db.userordermenu;

import com.jakedelivery.db.BaseEntity;
import com.jakedelivery.db._common.constant.UserOrderMenuStatus;
import com.jakedelivery.db.storemenu.StoreMenuEntity;
import com.jakedelivery.db.userorder.UserOrderEntity;
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
    @JoinColumn(nullable = false, name="userOrderId")
    @ManyToOne
    private UserOrderEntity userOrder; // n : 1

    @JoinColumn(nullable = false, name="storeMenuId")
    @ManyToOne
    private StoreMenuEntity storeMenu; // n : 1

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private UserOrderMenuStatus status;
}
