package com.wth.service.message;

/**
 * Created by 王天华 on 2017/6/30.
 */
public class ReceiverDemo {

    public static void main(String[] args) throws InterruptedException {
        final RecvMessage message = new RecvMessage();
        final RecvMessage message1 = new RecvMessage();
        message.initialize();
        message1.initialize();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                message.receive();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                message1.receive();
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        message.destory();
        message1.destory();
    }
}
