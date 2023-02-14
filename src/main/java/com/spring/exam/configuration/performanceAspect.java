package com.spring.exam.configuration;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect//AOP
@Component// pour dire cest bean spring cest composant sping nest pas un service ou controller ou ..
@Slf4j
public class performanceAspect {
    //L’aspect de gestion de la supervision
    @Around(" execution(* com.spring.exam.Services.*.*(..)) ")
    public Object performance(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed(joinPoint.getArgs());
        } finally {
            long end = System.currentTimeMillis();
            long duree = end - start;
            log.info("\u001B[32m"+"Performance de "+  joinPoint.toShortString() +" "
                    +" egale : "+String.valueOf(duree) +"ms" +"\u001B[0m");
        }
    }
}
