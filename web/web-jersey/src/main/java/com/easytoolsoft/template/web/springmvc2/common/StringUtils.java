package com.easytoolsoft.template.web.springmvc2.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String助手类
 *
 * @author zhiwei.deng
 * @date 2017-03-28
 **/
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    private static final Pattern IP_PATTERN = Pattern.compile(
        "(((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|"
            + "(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|"
            + "([1-9]\\d)|(\\d))");

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^([a-zA-Z0-9_\\-\\.\\+]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))"
            + "([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");

    private static final Pattern MOBILE_PATTERN = Pattern.compile(
        "^((176)|(13[0-9])|(14[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");

    /**
     * ip校验
     *
     * @param s
     * @return 格式是否正确
     */
    public static boolean isIpAddress(String s) {
        Matcher m = IP_PATTERN.matcher(s);
        return m.matches();
    }

    /**
     * email校验
     *
     * @param email
     * @return 格式是否正确
     */
    public static boolean isEmail(String email) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(email)) {
            return false;
        }
        Matcher m = EMAIL_PATTERN.matcher(email);
        return m.matches();
    }

    /**
     * 手机号校验
     *
     * @param mobiles
     * @return 格式是否正确
     */
    public static boolean isMobile(String mobiles) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(mobiles)) {
            return false;
        }
        Matcher m = MOBILE_PATTERN.matcher(mobiles);
        return m.matches();
    }
}
