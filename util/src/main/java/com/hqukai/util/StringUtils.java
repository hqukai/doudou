package com.hqukai.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * StringUtil.java
 */
public class StringUtils {

    public static boolean isEmpty(Object o) throws IllegalArgumentException {
        if (o == null)
            return true;
        if (o instanceof String) {
            return (o == null) || (o.toString().trim().length() == 0) || "null".equalsIgnoreCase(o.toString());
        } else if (o instanceof Collection) {
            if (((Collection) o).isEmpty()) {
                return true;
            }
        } else if (o.getClass().isArray()) {
            if (Array.getLength(o) == 0) {
                return true;
            }
        } else if (o instanceof Map) {
            if (((Map) o).isEmpty()) {
                return true;
            }
        } else {
            return false;
        }
        return false;
    }

    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

    /**
     * 判断字符串是否是数字
     *
     * @param s
     * @return
     * @author hankai
     */
    public final static boolean isNumeric(String s) {
        if (s != null && !"".equals(s.trim()))
            return s.matches("^[0-9]*$");
        else
            return false;
    }


    public static boolean isPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || "".equals(phoneNumber))
            return false;
        return Pattern.matches("1[3,5,7,8][0,1,2,3,4,5,6,7,8,9]\\d{8}", phoneNumber);
    }

    public static boolean isEmail(String email) {
    	if (email == null || "".equals(email))
            return false;
        return Pattern.matches("[\\p{Alnum},_,.]+@[\\w+\\.]+\\p{Alpha}{2,3}", email);
    }


    public static String trim(String str) {
        if (isEmpty(str)) {
            return "";
        } else {
            return str.trim();
        }
    }

    /**
     * 模糊字符串  例如：银行卡号部分用*代替
     *
     * @param s
     * @param front
     * @param back
     * @return
     * @throws Exception
     */
    public static String fuzzyString(String s, int front, int back) throws Exception {

        if (s.length() < front + back) {
            throw new Exception("参数不正确");
        }

        String r = s.substring(0, front) + "****" + s.substring(s.length() - back, s.length());

        return r;

//
//        String r = s.substring(front);
//        for (int i = 0; i < front; i++) {
//            r = "*" + r;
//        }
//        r = r.substring(0, r.length() - back);
//        for (int j = 0; j < back; j++) {
//            r = r + "*";
//        }


//        return r;
    }

    public static void main(String[] args) throws Exception {
        String s = "16851681618681638161";
        String r = StringUtils.fuzzyString(s, 4, 4);
        System.out.println(r);
    }
}
