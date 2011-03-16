
package com.bidover.auto.controller.command;

import com.bidover.common.database.dao.UserDAO;
import com.bidover.common.model.bean.User;
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
            UserDAO ud = null;
            User user = null;
            request.setCharacterEncoding("UTF8");
            response.setCharacterEncoding("UTF8");
            String email = request.getParameter("email");
            String password = request.getParameter("pass");
            String result = "{'status':0, 'message':'ERROR'}";
            ud = new UserDAO();
            user = ud.getUserByEmailAndPassword(email, password);
            if (user == null) result = "{'status':0, 'message':'Wrong login+password combination!'}";
            else {
                HttpSession session = request.getSession();
                session.setAttribute("status", user.getStatus());
                session.setAttribute("profile", user);
                result = "{'status':1}";
            }
            response.getWriter().write(result);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
             response.getWriter().close();
        }

    }
}
