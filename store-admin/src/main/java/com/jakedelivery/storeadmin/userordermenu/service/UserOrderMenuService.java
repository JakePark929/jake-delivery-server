package com.jakedelivery.storeadmin.userordermenu.service;

import com.jakedelivery.db._common.constant.UserOrderMenuStatus;
import com.jakedelivery.db.userordermenu.UserOrderMenuEntity;
import com.jakedelivery.db.userordermenu.UserOrderMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserOrderMenuService {
    private final UserOrderMenuRepository userOrderMenuRepository;

    public List<UserOrderMenuEntity> getUserOrderMenuList(Long userOrderId) {
        return userOrderMenuRepository.findAllByUserOrderIdAndStatus(userOrderId, UserOrderMenuStatus.REGISTERED);
    }
}
