package com.example.demo.aspects;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Arrays;
import java.util.List;

@ControllerAdvice
@Aspect
@RequiredArgsConstructor
public class LoggingAspect {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Pointcut("execution(* com.example.demo.controller.CityController.addCity(..))")
    private void addCityController(){}

    @After("addCityController()")
    public void afterAddCity(JoinPoint joinPoint){
       List<Object> args = Arrays.stream(joinPoint.getArgs()).toList();
       kafkaTemplate.send(args.get(1).toString(), args.get(0).toString());
    }
}
