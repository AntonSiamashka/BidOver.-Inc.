/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.controller.command;

import com.bidover.auto.model.bean.Make;
import com.bidover.auto.database.dao.MakeDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jedai
 */
public class HelloCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public HelloCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void execute() throws ServletException, IOException {
        MakeDAO makeDAO = new MakeDAO();
        List<Make> makes = makeDAO.findAll();
        request.setAttribute("makes", makes);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
