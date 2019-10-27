package com.cschool.scooterrentalapp.validation;

import java.util.regex.Pattern;

public class ValidationUtils {

    private static final Integer MIN_AGE = 1;
    private static final Integer MAX_AGE = 100;
    private static final Integer MIN_SPEED = 1;
    private static final Integer MAX_SPEED= 40;
    private static Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");

    public static boolean isNullOrEmpty(String value){
        return value == null || value.isEmpty();
    }

    public static boolean isNull(Object object){
        return object == null;
    }

    public  static  boolean isUncorrectedEmail(String email){
        return !emailPattern.matcher(email).matches();
    }

    public static boolean isUncorrectedAge(Integer age){
        return age<MIN_AGE || age>MAX_AGE;
    }

    public static boolean isUncorrectedSpeed(Integer speed){
        return speed<MIN_SPEED || speed>MAX_SPEED;
    }

}
