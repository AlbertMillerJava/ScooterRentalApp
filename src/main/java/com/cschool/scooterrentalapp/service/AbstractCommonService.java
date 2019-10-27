package com.cschool.scooterrentalapp.service;

import com.cschool.scooterrentalapp.common.MsgSource;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class AbstractCommonService {
    protected MsgSource msgSource;
}
