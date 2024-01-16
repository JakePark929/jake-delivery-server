package com.jakedelivery.api.userordermenu.service;

import com.jakedelivery.common.error.ErrorCode;
import com.jakedelivery.common.exception.ApiException;
import com.jakedelivery.db._common.constant.UserOrderMenuStatus;
import com.jakedelivery.db.userordermenu.UserOrderMenuEntity;
import com.jakedelivery.db.userordermenu.UserOrderMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserOrderMenuService {
    private final UserOrderMenuRepository userOrderMenuRepository;

    public List<UserOrderMenuEntity> getUserOrderMenu(Long userOrderId) {
        return userOrderMenuRepository.findAllByUserOrderIdAndStatus(userOrderId, UserOrderMenuStatus.REGISTERED);
    }

    public void order(UserOrderMenuEntity userOrderMenuEntity) {
        Optional.ofNullable(userOrderMenuEntity)
                .map(it -> {
                    it.setStatus(UserOrderMenuStatus.REGISTERED);

                    return userOrderMenuRepository.save(it);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }
}
