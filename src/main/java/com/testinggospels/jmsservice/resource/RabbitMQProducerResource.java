package com.testinggospels.jmsservice.resource;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQProducerResource {

    @PostMapping("/rabbitmq/producer")
    public String producerRabbitMQ(@RequestParam("host") String HOST, @RequestParam("queue_name") String QUEUE_NAME,
            @RequestParam Boolean DURABLE, @RequestParam Boolean EXCLUSIVE, @RequestParam Boolean AUTO_DELETE,
            @RequestBody String message) throws IOException, TimeoutException {
        ConnectionFactory conn = new ConnectionFactory();
        conn.setHost(HOST);
        Connection connect = conn.newConnection();
        Channel channel = connect.createChannel();
        channel.queueDeclare(QUEUE_NAME, DURABLE, EXCLUSIVE, AUTO_DELETE, null);
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        return "Sent '" + message + "'";
    }
}
