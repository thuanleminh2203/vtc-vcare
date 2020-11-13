package com.venesa.ctvvcare.utils;

import org.springframework.validation.Errors;

import java.util.Observer;

public class ValidatorUtils {

    public static void checkLength(String data, Errors error, int max, int min, String nameField) {
        if (checkNullOrEmpty(data, error, nameField)) return;
        if (data.length() > max || data.length() <min) {
            error.rejectValue(nameField, null, nameField + " must smaller than " + max + " and greater " + min);
        }
    }
    public static void checkIdentifyCard(String data, Errors error, String nameField) {
        if (checkNullOrEmpty(data, error, nameField)) return;
//        if (!(data.length() == min || data.length() == max)) {
//            error.rejectValue(nameField, null, nameField + " must equals  " + min + " or greater " + max);
//        }
        if(!(data.matches(ConstUtils.REGEX_IDENTIFY_CODE_NEW)|| data.matches(ConstUtils.REGEX_IDENTIFY_CODE_OLD))){
            error.rejectValue(nameField, null, nameField + "not format");
        }
    }

    public static boolean checkNullOrEmpty(String data, Errors error, String nameField) {
        if (data == null || data.isEmpty()) {
            error.rejectValue(nameField, null, nameField + " is not null");
            return true;
        }
        return false;
    }


    public static void checkPhoneNumber(String data, Errors error, String nameField) {
        if (!data.matches(ConstUtils.REGEX_NUMBER_PHONE)) {
            error.rejectValue(nameField, null, nameField + " is not format");
        }
    }

    public static void checkGender(int data, Errors error, String nameField) {
        if (data > 3 || data <= 0)
            error.rejectValue(nameField, null, nameField + " is not format");
    }

    public static void checkIdentifyType(int data, Errors error, String nameField) {
        if (data > 5 || data < 1)
            error.rejectValue(nameField, null, nameField + " is not format");
    }

    public static void checkDate(String data, Errors error, String nameField) {
        if (!data.matches(ConstUtils.REGEX_DATE)) {
            error.rejectValue(nameField, null, nameField + " is not format");
        }
    }

    public static void checkRegex(String data, Errors error, String nameField, String regex) {
//        if (checkNullOrEmpty(data, error, nameField)) return;
        if (data !=null && !data.matches(regex)) {
            error.rejectValue(nameField, null, nameField + " is not format");
        }
    }

    public static void checkRangeNumber(Long data, Errors error, String nameField, int min, int max) {
        if (checkNullOrEmpty(data.toString(), error, nameField)) return;
        if (data > max || data < min)
            error.rejectValue(nameField, null, nameField + " must be greater than" + min + " and less than " + max);
    }

    public static void checkPageable(int page ,Errors error, String nameField ){
        if(page < 0){
            error.rejectValue(nameField, null, nameField + " must be greater than 0" );
        }
    }


}
