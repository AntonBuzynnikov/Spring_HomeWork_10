package ru.buzynnikov.bidservice.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.util.Arrays;


//Логирование вызывваемых методов из контроллеров
@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {

    @Around(value = "@annotation(ToLog)")
    public Object fileLog(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();

        Object[] args = joinPoint.getArgs();

        writeToFile(methodName, args);

        return joinPoint.proceed();
    }

    private void writeToFile(String methodName, Object[] args){
        try(FileWriter writer = new FileWriter("log.txt", true)){
            writer.write(String.valueOf(LocalDateTime.now())
            + " " + methodName
            + ": " + Arrays.asList(args) + "\n");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
