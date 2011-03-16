/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.controller.command;

import com.bidover.auto.database.dao.CharacteristicsDAO;
import com.bidover.auto.model.bean.Make;
import com.bidover.auto.database.dao.MakeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jedai
 */
public class AddMakeToAddCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public AddMakeToAddCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void execute() throws ServletException, IOException {
        CharacteristicsDAO characteristicsDAO = new CharacteristicsDAO();
        List<String> makes = characteristicsDAO.findAllMakesNames();
       /* MakeDAO makeDAO = new MakeDAO();
        List<Make> makes = makeDAO.findAll();*/
        request.setAttribute("makeList", makes);
        request.getRequestDispatcher("addAuto.jsp").forward(request, response);
    }
}
