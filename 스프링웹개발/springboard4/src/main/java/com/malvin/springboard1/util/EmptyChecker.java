package com.malvin.springboard1.util;

public class EmptyChecker {

    public static boolean isEmpty(Object obj) {
        //null먼저 제거
        if(obj == null) {
            return true;
        }

        //숫자일 경우(null과 0을 체크)
        if(obj instanceof Number) {
            Number n = (Number)obj;
            return n.doubleValue() == 0d;
        }
        //문자열일 경우(null과 empty String을 체크)
        else if(obj instanceof String) {
            String s = (String)obj;
            return s.isEmpty();
        }

        return false;
    }

    public static boolean isEmpty(Object ... args) {
        if(args == null || args.length == 0) return true;

        for(Object obj : args) {
            if(!isEmpty(obj)) {
                return false;
            }
        }
        return true;
    }
}