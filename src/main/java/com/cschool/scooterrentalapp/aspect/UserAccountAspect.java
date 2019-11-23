package com.cschool.scooterrentalapp.aspect;

import com.cschool.scooterrentalapp.api.request.CreateUserAccountRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserAccountAspect {
    private Logger logger = LoggerFactory.getLogger(UserAccountAspect.class);

    @AfterReturning(value = "execution(* com.cschool.scooterrentalapp.service.UserAccountServiceImpl.createUserAccount(..))")
    public void afterUserAccountCreated(JoinPoint returning){
        CreateUserAccountRequest userAccountRequest = (CreateUserAccountRequest) returning.getArgs()[0];

        logger.info("user created with username = {}", userAccountRequest.getOwnerUserName());
    }

    @Around("execution(* com.cschool.scooterrentalapp.service.UserAccountServiceImpl.rechargeUserAccount(..))")
    public void saveUserMethod(ProceedingJoinPoint proceedingJoinPoint){
        try{
            logger.info("start recharging user account balance");

            Object[] args = proceedingJoinPoint.getArgs();
            long userId = (long) args[0];
            String amount = (String) args[1];
            proceedingJoinPoint.proceed(args);

            logger.info("user with id ={} recharged his account with amount = {}  ", userId, amount);
        } catch (Throwable e){

        }

    }
}
