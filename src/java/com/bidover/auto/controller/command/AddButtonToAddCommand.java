/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.controller.command;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jedai
 */
public class AddButtonToAddCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public AddButtonToAddCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void execute() throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String button = "<input type=\"submit\" value=\"Add\" name=\"add_auto\" />";
        try {
            out.print(button);
        } finally {
            out.close();
        }
    }
}
