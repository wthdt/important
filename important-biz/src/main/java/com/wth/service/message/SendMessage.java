package com.wth.service.message;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by 王天华 on 2017/6/29.
 */
public class SendMessage {

    private final static String QUEUE_NAME = "event_queue";
    private final static Logger LOGGER = LoggerFactory.getLogger(SendMessage.class);
    private static final String DEAFULT_EXCHANGE = "";
    private Channel channel;
    private Connection connection;

    public void initialize(){

        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            connection = factory.newConnection();
            channel = connection.createChannel();
        } catch (IOException e) {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");        } catch (TimeoutException e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    public void send(String message){
        try {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish(DEAFULT_EXCHANGE, QUEUE_NAME, null, message.getBytes());
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void sendByExchange(String exchange, String type, String message){
        try {
            channel.exchangeDeclare(exchange, type);
            channel.basicPublish(exchange, "", null, message.getBytes());
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    public void destroy(){
        if(connection != null){
            try {
                connection.close();
            } catch (IOException e) {
                LOGGER.warn(e.getMessage(), e);
            }
        }
    }
}

