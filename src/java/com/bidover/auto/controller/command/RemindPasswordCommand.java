/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.controller.command;

import com.bidover.common.database.dao.UserDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bidover.common.mail.MailSender;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author Jedai
 */
public class RemindPasswordCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public RemindPasswordCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void execute() throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("passwordReminder.jsp");
        try {
            UserDAO userDAO = new UserDAO();
            String email = request.getParameter("e-mail");
            Integer userId = userDAO.getUserIdByEmail(email);
            String newPassword = null;
            if (userId != null) {
                newPassword = userDAO.changePassword(userId);
                if (newPassword != null) {
                    String mailMessage = "New password: " + newPassword;
                    System.out.println(mailMessage);
                    MailSender mailSender = new MailSender();
                    mailSender.sendMail(email, "123", "Bidover.com", "Bidover registration", mailMessage);
                    requestDispatcher = request.getRequestDispatcher("/fm.login.jsp");
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RemindPasswordCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RemindPasswordCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        requestDispatcher.forward(request, response);

    }
}
