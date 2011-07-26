package com.bidover.auto.controller.command;

import com.bidover.common.database.dao.UserDAO;
import com.bidover.common.model.bean.User;
import com.bidover.common.service.auth.DaoAuthenticationProvider;
import com.bidover.util.SpringContextUtil;
import java.io.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;
import java.lang.Object.*;

public class LoginCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public LoginCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void execute() throws ServletException, IOException {
        try {
//            DaoAuthenticationProvider authenticationProvider = (DaoAuthenticationProvider) SpringContextUtil.getService(DaoAuthenticationProvider.class, request.getServletContext());
            HttpSession session = request.getSession();
            
            User user = (User) session.getAttribute("profile");
            request.setCharacterEncoding("UTF8");
            response.setCharacterEncoding("UTF8");
            String result = "{'status':0, 'message':'ERROR'}";
            if (user == null) {
                result = "{'status':-1, 'message':'Wrong login+password combination!'}";
            } else {
                session.setAttribute("status", user.getStatus());
                result = "{'status':" + user.getStatus() + "}";
            }
            System.out.println(result);
            response.getWriter().write(result);
        } finally {
            response.getWriter().close();
        }

    }
}
