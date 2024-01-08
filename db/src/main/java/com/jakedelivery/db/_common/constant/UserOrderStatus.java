package com.jakedelivery.db._common.constant;

public enum UserOrderStatus {
    REGISTERED("등록"),
    UNREGISTERED("해지")
    ;

    UserOrderStatus(String description) {
        this.description = description;
    }

    private String description;
}
