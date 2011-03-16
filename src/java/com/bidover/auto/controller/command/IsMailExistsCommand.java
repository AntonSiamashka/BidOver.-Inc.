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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nomad
 */
public class IsMailExistsCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public IsMailExistsCommand(HttpServletRequest request, HttpServletResponse response) {

        this.request = request;
        this.response = response;
    }

    public void execute() throws ServletException, IOException {
        try {
            UserDAO userDao = new UserDAO();
            String email = request.getParameter("e-mail");
            String mailExist = Boolean.toString(userDao.isMailExist(email));
            response.getWriter().write(mailExist);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IsMailExistsCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(IsMailExistsCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
             response.getWriter().close();
        }
    }
}
