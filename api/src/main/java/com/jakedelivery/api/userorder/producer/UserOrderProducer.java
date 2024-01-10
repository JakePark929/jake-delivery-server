package com.jakedelivery.api.userorder.producer;

import com.jakedelivery.api._core.rabbitmq.Producer;
import com.jakedelivery.db.userorder.UserOrderEntity;
import com.jakedelivery.message.model.UserOrderMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserOrderProducer {
    private final Producer producer;
    private static final String EXCHANGE = "delivery.exchange";
    private static final String ROUTE_KEY = "delivery.key";

    public void sendOrder(UserOrderEntity userOrderEntity) {
        sendOrder(userOrderEntity.getId());
    }

    public void sendOrder(Long userOrderId) {
        var message = UserOrderMessage.builder()
                .userOrderId(userOrderId)
                .build();

        producer.producer(EXCHANGE, ROUTE_KEY, message);
    }
}
