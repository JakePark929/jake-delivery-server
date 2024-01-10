package com.jakedelivery.api._core.common.health;

import com.jakedelivery.api._core.rabbitmq.Producer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/open-api")
@RestController
public class HealthOpenApiController {
    private final Producer producer;

    @GetMapping("/health")
    public void health() {
        log.info("health call");
        producer.producer("delivery.exchange", "delivery.key", "hello");
    }
}
