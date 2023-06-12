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
public class FanoutExchangeConfig {

    @Bean
    Exchange fanoutExchange() {
        return ExchangeBuilder.fanoutExchange("FanoutExchange")
                .autoDelete()
                .build();
    }

    @Bean
    Queue fanoutQueueOne() {
        return QueueBuilder.durable("FanoutQueueOne").build();
    }

    @Bean
    Queue fanoutQueueTwo() {
        return QueueBuilder.durable("FanoutQueueTwo").build();
    }

    @Bean
    Binding fanoutBindingOne() {
        return BindingBuilder
                .bind(fanoutQueueOne())
                .to(fanoutExchange())
                .with("")
                .noargs();
    }

    @Bean
    Binding fanoutBindingTwo() {
        return BindingBuilder
                .bind(fanoutQueueTwo())
                .to(fanoutExchange())
                .with("")
                .noargs();
    }
}
