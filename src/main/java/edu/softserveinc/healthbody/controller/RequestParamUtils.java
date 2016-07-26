package edu.softserveinc.healthbody.controller;

public class RequestParamUtils {

    public static boolean isEmptyParam(String param) {
        if (param == null || param.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isIntegerParam(String param) {

        return param.matches("^-?\\d+$");

    }

    public static int getIntegerParam(String param) {
        if (isIntegerParam(param)) {
            return Integer.valueOf(param);
        } else {
            throw new IllegalArgumentException("Error while parsing integer, check out 'partSize' or 'partNumber'");
        }
    }
}
