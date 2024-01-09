package com.jakedelivery.api.userorder.dto;

import com.jakedelivery.db._common.constant.UserOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderResponse {
    private Long id;
    private UserOrderStatus status;
    private BigDecimal amount;
    private LocalDateTime orderedAt;
    private LocalDateTime acceptedAt;
    private LocalDateTime cookingStartedAt;
    private LocalDateTime deliveryStartedAt;
    private LocalDateTime receivedAt;
}
