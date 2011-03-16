
package com.bidover.auto.controller.command;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.lang.Object.*;


public class ChangeLanguageCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    public ChangeLanguageCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }

    public void execute() throws ServletException, IOException {
        String LNG = request.getParameter("lng");
        session.setAttribute("LNG", LNG);
    }
}
