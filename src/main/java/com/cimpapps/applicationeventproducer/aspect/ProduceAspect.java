package com.cimpapps.applicationeventproducer.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
@RequiredArgsConstructor
public class ProduceAspect {

    private final ApplicationEventPublisher publisher;

    @Around("@annotation(Produce)")
    public Object produce(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("Input :\n" + joinPoint.getArgs()[0]);


        Object result = joinPoint.proceed();

        System.out.println(result);
        publisher.publishEvent(result);
        return result;
    }
}
