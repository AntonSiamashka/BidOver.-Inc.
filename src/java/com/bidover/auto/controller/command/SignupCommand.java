
package com.bidover.auto.controller.command;

import com.bidover.common.database.dao.UserDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bidover.common.mail.EmailValidator;
import com.bidover.common.mail.MailSender;


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
            UserDAO userDao = new UserDAO();
            EmailValidator validator = new EmailValidator();
            String email =  request.getParameter("email") == null ? "" : request.getParameter("email");
            boolean emailExist = userDao.isMailExist(email);
            
            if (email.trim().equals("")) result = "{'status':0, 'message':'Email field is empty!'}";
            else if (!validator.isValid(email)) result = "{'status':0, 'message':'Wrong format of email address!'}";
            else if (emailExist) result = "{'status':0, 'message':'Email address already registered in system!'}";
            else {
                String password = userDao.addUser(email);
                String mailMessage = "Your password: " + password;
                mailMessage += "/n";
                mailMessage += "<a href=\"http://localhost:8084/BidiverLast/login.jsp\">Login</a>";
                System.out.println(mailMessage);
                MailSender mailSender = new MailSender();
                mailSender.sendMail(email, "123", "Bidover.com", "Bidover registration", mailMessage);
//                rd = request.getRequestDispatcher("/login.jsp");
                result = "{'status':1}";
            }
            response.getWriter().write(result);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SignupCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SignupCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
             response.getWriter().close();
        }
    }
}
