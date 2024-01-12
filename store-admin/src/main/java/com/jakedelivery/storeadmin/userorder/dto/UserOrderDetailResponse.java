package com.jakedelivery.storeadmin.userorder.dto;

import com.jakedelivery.storeadmin.storemenu.dto.StoreMenuResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderDetailResponse {
    private UserOrderResponse userOrderResponse;
    private List<StoreMenuResponse> storeMenuResponses;
}
