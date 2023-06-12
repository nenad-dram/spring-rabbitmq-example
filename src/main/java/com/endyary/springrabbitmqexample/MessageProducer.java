package com.endyary.springrabbitmqexample;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;

    public MessageProducer(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    private void sendDirectMessage() {
        MessageModel messageModel = new MessageModel("msgName", "msgDescription");
        rabbitTemplate.convertAndSend("DirectExchange",
                "directRoutingKey",
                messageModel);
    }

    private void sendFanoutMessage() {
        rabbitTemplate.convertAndSend("FanoutExchange",
                "",
                "A Fanout message example");
    }

    private void sendTopicMessages() {
        rabbitTemplate.convertAndSend("TopicExchange",
                "topic.one",
                "A Topic message example one");

        rabbitTemplate.convertAndSend("TopicExchange",
                "topic.two",
                "A Topic message example two");
    }

    private void sendHeadersMessage() {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("headerKey", "headerValue");
        MessageConverter messageConverter = new SimpleMessageConverter();
        Message message =
                messageConverter.toMessage("A Headers message example", messageProperties);

        rabbitTemplate.send("HeadersExchange", "", message);
    }


    @Override
    public void run(final String... args) throws Exception {
        sendDirectMessage();
        sendFanoutMessage();
        sendTopicMessages();
        sendHeadersMessage();
    }
}
