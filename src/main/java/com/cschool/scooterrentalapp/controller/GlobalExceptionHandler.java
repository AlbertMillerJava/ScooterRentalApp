package com.cschool.scooterrentalapp.controller;

import com.cschool.scooterrentalapp.api.response.BasicResponse;
import com.cschool.scooterrentalapp.exception.CommonBadRequestException;
import com.cschool.scooterrentalapp.exception.CommonConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    BasicResponse handleBadRequestException(CommonBadRequestException exception) {
        return BasicResponse.ofError(
                exception.getConstErrorMsg().getErrorCode(),
                exception.getConstErrorMsg().getErrorMsg());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    BasicResponse handleBadRequestException(CommonConflictException exception) {
        return BasicResponse.ofError(
                exception.getConstErrorMsg().getErrorCode(),
                exception.getConstErrorMsg().getErrorMsg());
    }

}
