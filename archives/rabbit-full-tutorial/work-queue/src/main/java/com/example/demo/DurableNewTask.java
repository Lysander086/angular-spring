package com.example.demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DurableNewTask {
    private final static String QUEUE_NAME = "task_queue";

    public static void main(String[] argv) throws Exception {

        /* config connection property */
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        // factory.setPort(15672); // would raise errors
        /* default port with 5672 */
        factory.setPort(5672);

        /* establish connection */
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            boolean durable = true;

            /* declare a queue */
            channel.queueDeclare(QUEUE_NAME, durable, false, false, null);

            String message = "new Task";

            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");

        } catch (Exception e) {
            log.error(e.toString());
        }
    }
}
