package com.wth.service.message;

/**
 * Created by 王天华 on 2017/6/29.
 */
public class MessageMain {
    public static void sendToDefaultExchange(){
        SendMessage sendMessage = new SendMessage();
        sendMessage.initialize();
        sendMessage.send("Test Message");
        sendMessage.destroy();
    }

    public static void main(String[] args){
        sendToDefaultExchange();
    }
}
