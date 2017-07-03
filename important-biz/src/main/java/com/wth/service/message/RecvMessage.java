package com.wth.service.message;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by 王天华 on 2017/6/29.
 */
public class RecvMessage {

    private final static String QUEUE_NAME = "event_queue";
    private final static Logger LOGGER = LoggerFactory.getLogger(RecvMessage.class);
    private Connection connection = null;
    private Channel channel = null;
    public void initialize(){
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            connection = factory.newConnection();
            channel = connection.createChannel();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (TimeoutException e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    public String receive(){
        if(channel == null){
            initialize();
        }

        String message = null;

        try {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
            channel.basicConsume(QUEUE_NAME, true, queueingConsumer);

            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            message = new String(delivery.getBody());
            LOGGER.info("Message Received: " + message);
            return message;
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (InterruptedException e) {
            LOGGER.warn(e.getMessage(), e);
        }
        return message;
    }

    public void destory(){
        if(connection != null){
            try {
                connection.close();
            } catch (IOException e) {
                LOGGER.warn(e.getMessage(), e);
            }
        }
    }
}
