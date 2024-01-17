package com.jakedelivery.db.userorder

import com.fasterxml.jackson.annotation.JsonIgnore
import com.jakedelivery.db._common.constant.UserOrderStatus
import com.jakedelivery.db.store.StoreEntity
import com.jakedelivery.db.userordermenu.UserOrderMenuEntity
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "user_order")
class UserOrderEntity(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @field:Column(nullable = false)
    var userId: Long? = null,

    @field:JoinColumn(nullable = false, name = "storeId")
    @field:ManyToOne
    var store: StoreEntity? = null,

    @field:Enumerated(EnumType.STRING)
    @field:Column(length = 50, nullable = false)
    var status: UserOrderStatus? = null,

    @field:Column(precision = 11, scale = 4, nullable = false)
    var amount: BigDecimal? = null,

    var orderedAt: LocalDateTime? = null,
    var acceptedAt: LocalDateTime? = null,
    var cookingStartedAt: LocalDateTime? = null,
    var deliveryStartedAt: LocalDateTime? = null,
    var receivedAt: LocalDateTime? = null,

    @field:JsonIgnore
    @field:OneToMany(mappedBy = "userOrder")
    var userOrderMenuEntities: MutableList<UserOrderMenuEntity>?= null
) {
    override fun toString(): String {
        return "UserOrderEntity(id=$id, userId=$userId, store=$store, status=$status, amount=$amount, orderedAt=$orderedAt, acceptedAt=$acceptedAt, cookingStartedAt=$cookingStartedAt, deliveryStartedAt=$deliveryStartedAt, receivedAt=$receivedAt)"
    }
}