package ru.itis.javalab.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.javalab.dto.MethodLogDto;
import ru.itis.javalab.services.MethodCallLogService;

import java.util.Date;

@Aspect
@Component
public class MethodCallLogAspect {
    @Autowired
    private MethodCallLogService methodLogService;

    @Around(value = "execution(* ru.itis.javalab.controllers.*.*(..))")
    public Object methodCallLog(ProceedingJoinPoint joinPoint) throws Throwable {

        Date dateTime = new Date(System.currentTimeMillis());
        Object object = joinPoint.proceed();
        String methodName = joinPoint.getTarget().getClass().getName() + " " + joinPoint.getSignature().getName();

        methodLogService.logMethodCall(MethodLogDto
                .builder()
                .methodName(methodName)
                .callTime(dateTime)
                .build());

        return object;
    }
}
