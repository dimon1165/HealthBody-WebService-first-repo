package edu.softserveinc.healthbody.controller;

import javax.servlet.http.HttpServletRequest;

public class ParamUtils {
    private static ParamUtils paramUtils = null;

    public static ParamUtils getInstanse() {
        if (paramUtils == null) {
            synchronized (ParamUtils.class) {
                if (paramUtils == null) {
                    paramUtils = new ParamUtils();
                }
            }
        }
        return paramUtils;
    }

    public static int getPartNumber(HttpServletRequest request) {
        String partNumberString = request.getParameter("partNumber");
        if (RequestParamUtils.isEmptyParam(partNumberString)) {
            return 1;
        }
        return RequestParamUtils.getIntegerParam(partNumberString);
    }

    public static int getPartSize(HttpServletRequest request) {
        String partSizeString = request.getParameter("partSize");
        if (RequestParamUtils.isEmptyParam(partSizeString)) {
            return 5;
        }
        return RequestParamUtils.getIntegerParam(partSizeString);
    }

    public static String getLogin(HttpServletRequest request) {
        String login = request.getParameter("login");
        if (login == null || login.isEmpty()) {
            return "Login 1";
        }
        return login;
    }
}
