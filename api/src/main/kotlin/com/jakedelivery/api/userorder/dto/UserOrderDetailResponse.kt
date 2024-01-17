package com.jakedelivery.api.userorder.dto

import com.jakedelivery.api.store.dto.StoreResponse
import com.jakedelivery.api.storemenu.dto.StoreMenuResponse

data class UserOrderDetailResponse (
    val userOrderResponse: UserOrderResponse? = null,
    val storeResponse: StoreResponse? = null,
    val storeMenuResponses: List<StoreMenuResponse>? = null
)
