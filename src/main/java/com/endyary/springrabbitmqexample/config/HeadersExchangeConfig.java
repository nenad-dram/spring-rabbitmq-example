package com.endyary.springrabbitmqexample.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeadersExchangeConfig {

    @Bean
    HeadersExchange headersExchange() {
        return ExchangeBuilder.headersExchange("HeadersExchange")
                .autoDelete()
                .build();
    }

    @Bean
    Queue headersQueue() {
        return QueueBuilder.durable("HeadersQueue").build();
    }

    @Bean
    Binding headersBinding() {
        return BindingBuilder
                .bind(headersQueue())
                .to(headersExchange())
                .where("headerKey")
                .matches("headerValue");
    }
}
