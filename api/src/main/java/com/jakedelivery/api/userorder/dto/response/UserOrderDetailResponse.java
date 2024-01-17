package com.jakedelivery.api.userorder.dto.response;

import com.jakedelivery.api.store.dto.StoreResponse;
import com.jakedelivery.api.storemenu.dto.StoreMenuResponse;
import com.jakedelivery.api.userorder.dto.UserOrderResponse;
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
    private StoreResponse storeResponse;
    private List<StoreMenuResponse> storeMenuResponses;
}
