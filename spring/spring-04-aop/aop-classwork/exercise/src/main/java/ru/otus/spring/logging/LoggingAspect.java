package ru.otus.spring.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    @Around(value = "execution(* ru.otus.spring.dao.PersonDaoSimple.*(..))")
    public Object logBefore(ProceedingJoinPoint joinPoint) {
        System.out.println("Прокси : " + joinPoint.getThis().getClass().getName());
        System.out.println("Класс : " + joinPoint.getTarget().getClass().getName());
        System.out.println("Аргументы : " + Arrays.toString(joinPoint.getArgs()));
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            result = null;
            e.printStackTrace();
        }
        System.out.println("Результат : " + result);
        System.out.println("Вызов метода : " + joinPoint.getSignature().getName());
        return result;
    }
}
