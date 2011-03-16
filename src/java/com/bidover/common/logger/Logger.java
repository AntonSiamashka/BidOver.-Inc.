/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.common.logger;

/**
 *
 * @author Jedai
 */
public class Logger {

    public synchronized static void log(Exception ex) {
        ex.printStackTrace();
    }
}
