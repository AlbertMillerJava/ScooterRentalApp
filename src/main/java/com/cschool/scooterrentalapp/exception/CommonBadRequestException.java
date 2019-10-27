package com.cschool.scooterrentalapp.exception;

import com.cschool.scooterrentalapp.common.ConstErrorMsg;

public class CommonBadRequestException extends CommonException {

    public CommonBadRequestException(ConstErrorMsg constErrorMsg) {
        super(constErrorMsg);
    }
}
