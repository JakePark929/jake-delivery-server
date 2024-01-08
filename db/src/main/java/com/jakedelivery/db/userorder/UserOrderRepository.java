package com.jakedelivery.db.userorder;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOrderRepository extends JpaRepository<UserOrderEntity, Long> {
}
