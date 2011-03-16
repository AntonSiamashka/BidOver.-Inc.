/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.controller.command;

import com.bidover.auto.database.dao.CharacteristicsDAO;
import com.bidover.auto.database.dao.ModelDAO;
import com.bidover.auto.model.bean.Characteristics;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jedai
 */
public class AddYearToSearchCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public AddYearToSearchCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void execute() throws ServletException, IOException {
        String select = "";
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/xml");
        PrintWriter out = response.getWriter();
        String modelTxt = request.getParameter("model");
        if (modelTxt != null && !modelTxt.equals("")) {
            CharacteristicsDAO characteristicsDAO = new CharacteristicsDAO();
            List<Characteristics> characteristics = characteristicsDAO.findAllByModelTitle(modelTxt);
            int begYear = characteristics.get(0).getBegYear();
            int endYear = characteristics.get(0).getEndYear();
            StringBuilder xml = new StringBuilder(100);
            xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            xml.append(transformYearToXML("year", begYear, endYear));
            select = xml.toString();
            request.setAttribute("year", select);
        }
        try {
            out.write(select);

        } finally {
            out.close();
        }
    }

    private String transformYearToXML(String name,int begYear, int endYear) {
        StringBuilder xml = new StringBuilder(100);
        xml.append("<");
        xml.append(name);
        xml.append("-list>");
        int time = endYear - begYear;
        for (int i = 0; i <= time; i++) {
            int currentYear = begYear + i;
            xml.append("<year>");
            xml.append("<id>");
            xml.append(currentYear);
            xml.append("</id>");
            xml.append("<title>");
            xml.append(currentYear);
            xml.append("</title>");
            xml.append("</year>");
        }
        xml.append("</");
        xml.append(name);
        xml.append("-list>");
        return xml.toString();
    }

}
