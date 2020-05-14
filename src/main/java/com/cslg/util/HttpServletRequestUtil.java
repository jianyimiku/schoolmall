package com.cslg.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-11 16:22
 **/
public class HttpServletRequestUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpServletRequestUtil.class);

    public static Integer getInt(HttpServletRequest request, String key) {
        try {
            return Integer.valueOf(request.getParameter(key));
        } catch (Exception e) {
            logger.error(String.format("getInt error %s", e.getMessage()));
            return -1;
        }
    }

    public static Long getLong(HttpServletRequest request, String key) {
        try {
            return Long.parseLong(request.getParameter(key));
        } catch (Exception e) {
            logger.error(String.format("getLong error %s", e.getMessage()));
            return -1L;
        }
    }

    public static Double getDouble(HttpServletRequest request, String key) {
        try {
            return Double.parseDouble(request.getParameter(key));
        } catch (Exception e) {
            logger.error(String.format("getDouble error %s", e.getMessage()));
            return -1.0;
        }
    }

    public static Boolean getBoolean(HttpServletRequest request, String key) {
        try {
            return Boolean.valueOf(request.getParameter(key));
        } catch (Exception e) {
            logger.error(String.format("getBoolean error %s", e.getMessage()));
            return false;
        }
    }


    public static String getString(HttpServletRequest request, String key) {
        try {
            String result = request.getParameter(key);
            if (request != null) {
                return result.trim();
            }
            if (StringUtils.isBlank(result)) {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
