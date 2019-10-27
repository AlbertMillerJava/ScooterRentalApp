package com.cschool.scooterrentalapp.exception;

import com.cschool.scooterrentalapp.common.ConstErrorMsg;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommonException  extends RuntimeException{

    private ConstErrorMsg constErrorMsg;

}
