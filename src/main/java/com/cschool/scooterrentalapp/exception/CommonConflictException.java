package com.cschool.scooterrentalapp.exception;

import com.cschool.scooterrentalapp.common.ConstErrorMsg;

public class CommonConflictException extends CommonException {

    public CommonConflictException(ConstErrorMsg constErrorMsg) {
        super(constErrorMsg);
    }
}
