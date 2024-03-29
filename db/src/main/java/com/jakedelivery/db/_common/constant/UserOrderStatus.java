package com.jakedelivery.db._common.constant;

public enum UserOrderStatus {
    REGISTERED("등록"),
    UNREGISTERED("해지"),

    ORDER("주문"),
    ACCEPT("확인"),
    COOKING("요리 중"),
    DELIVERY("배달 중"),
    RECEIVE("배달 완료")
    ;

    UserOrderStatus(String description) {
        this.description = description;
    }

    private String description;
}
