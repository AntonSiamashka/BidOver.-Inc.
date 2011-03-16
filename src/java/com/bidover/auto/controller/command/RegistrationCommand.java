/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.controller.command;

import com.bidover.common.model.bean.Timezone;
import com.bidover.common.model.bean.User;
import com.bidover.common.database.dao.TimezoneDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jedai
 */
public class RegistrationCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public RegistrationCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void execute() throws ServletException, IOException {
        TimezoneDAO timezoneDAO = new TimezoneDAO();
        List<Timezone> timezones = timezoneDAO.findAll();
        request.setAttribute("timezones", timezones);
        request.getRequestDispatcher("userInfo.jsp").forward(request, response);
    }
}
