package com.bidover.auto.controller.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bidover.common.mail.EmailValidator;
import com.bidover.common.model.bean.User;
import com.bidover.common.service.auth.DaoAuthenticationProvider;
import com.bidover.util.SpringContextUtil;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SignupCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public SignupCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void execute() throws ServletException, IOException {
        try {
            String result = "{'status':0, 'message':'ERROR'}";
            DaoAuthenticationProvider authenticationProvider = (DaoAuthenticationProvider) SpringContextUtil.getService(DaoAuthenticationProvider.class, request.getServletContext());
            EmailValidator validator = new EmailValidator();
            String email = request.getParameter("email") == null ? "" : request.getParameter("email");
            boolean emailExist = authenticationProvider.isMailExist(email);

            if (email.trim().equals("")) {
                result = "{'status':-1, 'message':'Email field is empty!'}";
            } else if (!validator.isValid(email)) {
                result = "{'status':-1, 'message':'Wrong format of email address!'}";
            } else if (emailExist) {
                result = "{'status':-1, 'message':'Email address already registered in system!'}";
            } else {
                Integer userId = authenticationProvider.addUser(email);
                User user = authenticationProvider.getUserById(userId);
                HttpSession session = request.getSession();
                session.setAttribute("status", user.getStatus());
                session.setAttribute("profile", user);
                result = "{'status':" + user.getStatus() + "}";
            }
            response.getWriter().write(result);
        } finally {
            response.getWriter().close();
        }
    }

}
