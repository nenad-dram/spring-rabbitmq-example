package com.endyary.springrabbitmqexample.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectExchangeConfig {

    @Bean
    Exchange directExchange() {
        return ExchangeBuilder.directExchange("DirectExchange")
                .autoDelete()
                .build();
    }

    @Bean
    Queue directQueue() {
        return QueueBuilder.durable("DirectQueue").build();
    }

    @Bean
    Binding directBinding() {
        return BindingBuilder
                .bind(directQueue())
                .to(directExchange())
                .with("directRoutingKey")
                .noargs();
    }
}
