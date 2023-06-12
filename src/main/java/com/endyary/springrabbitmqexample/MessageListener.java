package com.endyary.springrabbitmqexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    Logger logger = LoggerFactory.getLogger(MessageListener.class);

    @RabbitListener(queues = "DirectQueue")
    public void receiveDirect(MessageModel message) {
        logger.info("Message from DirectQueue: {}", message);
    }

    @RabbitListener(queues = "FanoutQueueOne")
    public void receiveFanoutOne(String message) {
        logger.info("Message from FanoutQueueOne: {}", message);
    }

    @RabbitListener(queues = "FanoutQueueTwo")
    public void receiveFanoutTwo(String message) {
        logger.info("Message from FanoutQueueTwo: {}", message);
    }

    @RabbitListener(queues = "TopicQueueOne")
    public void receiveTopicOne(String message) {
        logger.info("Message from TopicQueueOne: {}", message);
    }

    @RabbitListener(queues = "TopicQueueTwo")
    public void receiveTopicTwo(String message) {
        logger.info("Message from TopicQueueTwo: {}", message);
    }

    @RabbitListener(queues = "TopicQueue")
    public void receiveTopic(String message) {
        logger.info("Message from TopicQueue: {}", message);
    }

    @RabbitListener(queues = "HeadersQueue")
    public void receiveHeaders(String message) {
        logger.info("Message from HeaderQueue: {}", message);
    }
}
