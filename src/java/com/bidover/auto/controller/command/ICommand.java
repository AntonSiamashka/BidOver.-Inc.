/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.controller.command;

import java.io.IOException;
import javax.servlet.ServletException;

/**
 *
 * @author Jedai
 */
public interface ICommand {

    public void execute() throws ServletException, IOException;
}
