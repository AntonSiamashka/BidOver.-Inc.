/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.controller.command;

import com.bidover.common.database.dao.UserDAO;
import com.bidover.common.model.bean.Timezone;
import com.bidover.common.model.bean.User;
import com.bidover.common.database.dao.TimezoneDAO;
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
public class ChangeUserInfoCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public ChangeUserInfoCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void execute() throws ServletException, IOException {
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String nickName = request.getParameter("nickName");
        String timezoneId = request.getParameter("timezone");
        String userData = request.getParameter("user_data");
        String userCountry = request.getParameter("user_country");
        String userPhone = request.getParameter("user_phone");
        String userAddress = request.getParameter("user_address");
        Timezone timezone = new Timezone();
        TimezoneDAO timezoneDAO = new TimezoneDAO();
        timezone = timezoneDAO.find(Integer.valueOf(timezoneId));
        User user = new User();
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setNickName(nickName);
        user.setTimezone(timezone);
        user.setUserAddress(userAddress);
        user.setUserCountry(userCountry);
        user.setUserPhone(userPhone);
        user.setUserData(userData);
        User profile = (User) request.getSession().getAttribute("profile");
        user.setId(profile.getId());
        UserDAO userDAO;
        userDAO = new UserDAO();
        if (userDAO.setUserInfo(user)) {
            profile.setStatus(UserDAO.TOTAL_REG_STATUS);
            profile.setLastName(lastName);
            profile.setFirstName(firstName);
            profile.setNickName(nickName);
            profile.setTimezone(timezone);
            profile.setUserAddress(userAddress);
            profile.setUserCountry(userCountry);
            profile.setUserPhone(userPhone);
            profile.setUserData(userData);
            request.getSession().setAttribute("status", UserDAO.TOTAL_REG_STATUS);
            request.getSession().setAttribute("profile", profile);
            request.getRequestDispatcher("cp.jsp").forward(request, response);
        }

    }
}
