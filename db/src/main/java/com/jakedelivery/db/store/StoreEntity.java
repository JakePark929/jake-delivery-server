package com.jakedelivery.db.store;

import com.jakedelivery.db.BaseEntity;
import com.jakedelivery.db._common.constant.StoreCategory;
import com.jakedelivery.db._common.constant.StoreStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "store")
@Entity
public class StoreEntity extends BaseEntity {
    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 150, nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private StoreStatus status;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private StoreCategory category;

    private double star;

    @Column(length = 200, nullable = false)
    private String thumbnailUrl;

    @Column(length = 11, scale = 4, nullable = false)
    private BigDecimal minimumAmount;

    @Column(length = 11, scale = 4, nullable = false)
    private BigDecimal minimumDeliveryAmount;

    @Column(length = 20)
    private String phoneNumber;
}
