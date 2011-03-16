/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bidover.common.encrypters;
/**
 *
 * @author Myth
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashEncrypter {

     public static String encrypt(String x) throws NoSuchAlgorithmException{
          MessageDigest d = null;
          d = MessageDigest.getInstance("SHA-1");
          d.reset();
          d.update(x.getBytes());
          byte[] encodedStr = d.digest();
          StringBuilder sb = new StringBuilder();
          for (int i = 0; i < encodedStr.length; i++) {
            if ((encodedStr[i] & 0xff) < 0x10) sb.append("0");
            sb.append(Long.toString(encodedStr[i] & 0xff, 16));
          }
          return sb.toString();
     }

}