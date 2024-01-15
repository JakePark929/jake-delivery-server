package com.jakedelivery.api._core.config;

/*
@Configuration
public class RabbitMqConfig {
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("delivery.exchange");
    }

    @Bean
    public Queue queue() {
        return new Queue("delivery.queue");
    }

    @Bean
    public Binding binding(DirectExchange directExchange, Queue queue) {
        return BindingBuilder.bind(queue).to(directExchange).with("delivery.key");
    }

    // end queue 설정
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);

        return rabbitTemplate;
    }

    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
*/
