package com.jakedelivery.storeadmin.userorder.service;

import com.jakedelivery.db.userorder.UserOrderEntity;
import com.jakedelivery.db.userorder.UserOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserOrderService {
    private final UserOrderRepository userOrderRepository;

    public Optional<UserOrderEntity> getUserOrder(Long id) {
        return userOrderRepository.findById(id);
    }
}
