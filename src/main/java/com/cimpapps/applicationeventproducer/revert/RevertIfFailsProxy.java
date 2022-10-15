package com.cimpapps.applicationeventproducer.revert;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@RequiredArgsConstructor
@Aspect
public class RevertIfFailsProxy {

    private final RevertContext revertContext;

    @Around("@annotation(RevertIfFails)")
    public Object revertIfFails(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("Input :\n" + joinPoint.getArgs()[0]);
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Exception exception) {
            System.out.println("STARTING revert");
            revertContext.revert();
            System.out.println("FINISHED revert");
            throw exception;
        }
        return result;
    }
}
