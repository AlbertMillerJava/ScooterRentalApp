package com.cschool.scooterrentalapp.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConstErrorMsg {

    private String errorCode;
    private String errorMsg;

}
