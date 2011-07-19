/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.util;

/**
 *
 * @author Jedai
 */
public class IntegerUtil {

    public static Integer parseString(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        } else {
            return Integer.valueOf(str);
        }
    }
}
