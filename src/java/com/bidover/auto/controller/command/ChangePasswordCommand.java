/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.controller.command;

import com.bidover.common.database.dao.UserDAO;
import com.bidover.common.model.bean.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jedai
 */
public class ChangePasswordCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public ChangePasswordCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void execute() throws ServletException, IOException {
        boolean flag = false;
        User profile = (User) request.getSession().getAttribute("profile");
        String password = (String) request.getParameter("password");
        String repeat = (String) request.getParameter("repeat");
        UserDAO userDAO = new UserDAO();
        if (password != null && repeat != null && !"".equals(password) && !"".equals(repeat)) {
            if (password.equals(repeat)) {
                String newPassword = userDAO.changePassword(password, profile.getId());
                if (newPassword != null) {
                    profile.setPassword(newPassword);
                    response.sendRedirect("profile.jsp");
                    flag = true;
                }
            }
        }
        if (!flag) {
            response.sendRedirect("passwordChanger.jsp");
        }
    }
}
