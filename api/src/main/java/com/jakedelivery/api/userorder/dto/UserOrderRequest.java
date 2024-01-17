package com.jakedelivery.api.userorder.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderRequest {
    @NotNull
    private Long storeId;
    // 주문
    // 특정 사용자가, 특정메뉴 주문
    // 특정 사용자 = 로그인된 세션에 들어있는 사용자
    // 특정 메뉴 id
    @NotNull
    private List<Long> storeMenuIds;

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public List<Long> getStoreMenuIds() {
        return storeMenuIds;
    }

    public void setStoreMenuIds(List<Long> storeMenuIds) {
        this.storeMenuIds = storeMenuIds;
    }
}
