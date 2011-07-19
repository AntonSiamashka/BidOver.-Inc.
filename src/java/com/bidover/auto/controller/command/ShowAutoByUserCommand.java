/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.controller.command;

import com.bidover.auto.model.bean.Auto;
import com.bidover.common.model.bean.Lot;
import com.bidover.auto.database.dao.AutoDAO;
import com.bidover.common.model.bean.User;
import com.bidover.common.database.dao.LotDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jedai
 */
public class ShowAutoByUserCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public ShowAutoByUserCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void execute() throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("profile");
//        Lot lot = new Lot();
//        lot.setSellerId(user);
//        LotDAO lotDAO = new LotDAO();
//        AutoDAO autoDAO = new AutoDAO();
//        List<Lot> lots = lotDAO.find(lot);
//        List<Auto> autos = new ArrayList<Auto>();
//        if (lots != null) {
//            for (Lot l : lots) {
//                Auto auto = new Auto();
//                auto.setLot(l);
//                List<Auto> tempAutos = autoDAO.findPreview(auto);
//                if (tempAutos != null) {
//                    autos.add(tempAutos.get(0));
//                }
//            }
//        }
//        request.setAttribute("autos", autos);
        request.getRequestDispatcher("./userAuto.jsp").forward(request, response);
    }
}
