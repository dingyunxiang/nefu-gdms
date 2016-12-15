package cn.edu.nefu.gdms.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dingyunxiang on 16/12/15.
 */
public class RequestUtils {
    private static final String USER_ACCOUNT_ATTRIBUTE_NAME = "_const_cas_assertion_";

    public static String getUserNameFromHttpRequest(HttpServletRequest request) {
        String str = (String) request.getSession().getAttribute(USER_ACCOUNT_ATTRIBUTE_NAME);
        if (str == null) return null;
        return str;
    }
}