
package com.bidover.auto.controller.command;

import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelpCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public HelpCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void execute() throws ServletException, IOException {
        request.getRequestDispatcher("inf.hlp.jsp").forward(request, response);
    }
}