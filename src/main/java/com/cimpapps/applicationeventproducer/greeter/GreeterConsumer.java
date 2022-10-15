package com.cimpapps.applicationeventproducer.greeter;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class GreeterConsumer {

    @EventListener
    @Async
    public void consumeGreet(String hello) {
        System.out.println("from consumer: " +  hello);
    }
}
