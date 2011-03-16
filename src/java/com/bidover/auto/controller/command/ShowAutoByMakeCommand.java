/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.controller.command;

import com.bidover.auto.model.bean.Auto;
import com.bidover.auto.model.bean.Make;
import com.bidover.auto.database.dao.AutoDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jedai
 */
public class ShowAutoByMakeCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public ShowAutoByMakeCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void execute() throws ServletException, IOException {
        String makeIdTxt = request.getParameter("make_id");
        Auto auto = new Auto();
        Make make = new Make();
        make.setId(Integer.valueOf(makeIdTxt));
        //auto.setIdMake(make);
        AutoDAO autoDAO = new AutoDAO();
        List<Auto> autos = autoDAO.findPreview(auto);
        request.setAttribute("autos", autos);
        request.getRequestDispatcher("Controller?command=").forward(request, response);
    }
}
