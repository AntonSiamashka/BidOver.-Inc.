/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.common.controller.command;

import com.bidover.auto.controller.command.ICommand;
import com.bidover.common.model.bean.Lot;
import com.bidover.common.model.bean.User;
import com.bidover.common.database.dao.LotDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jedai
 */
public class ShowUserLotsCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public ShowUserLotsCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void execute() throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("profile");
        Lot lot = new Lot();
        lot.setSellerId(user);
        LotDAO lotDAO = new LotDAO();
        List<Lot> lots = lotDAO.find(lot);
        request.setAttribute("lots", lots);
        request.getRequestDispatcher("./userLots.jsp").forward(request, response);
    }
}
