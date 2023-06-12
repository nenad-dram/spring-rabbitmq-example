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
public class TopicExchangeConfig {

    @Bean
    Exchange topicExchange() {
        return ExchangeBuilder.topicExchange("TopicExchange")
                .autoDelete()
                .build();
    }

    @Bean
    Queue topicQueueOne() {
        return QueueBuilder.durable("TopicQueueOne").build();
    }

    @Bean
    Queue topicQueueTwo() {
        return QueueBuilder.durable("TopicQueueTwo").build();
    }

    @Bean
    Queue topicQueue() {
        return QueueBuilder.durable("TopicQueue").build();
    }

    @Bean
    Binding topicBindingOne() {
        return BindingBuilder
                .bind(topicQueueOne())
                .to(topicExchange())
                .with("topic.one")
                .noargs();
    }

    @Bean
    Binding topicBindingTwo() {
        return BindingBuilder
                .bind(topicQueueTwo())
                .to(topicExchange())
                .with("topic.two")
                .noargs();
    }

    @Bean
    Binding topicBinding() {
        return BindingBuilder
                .bind(topicQueue())
                .to(topicExchange())
                .with("topic.*")
                .noargs();
    }
}
