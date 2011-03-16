/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.controller.command;

import com.bidover.auto.database.dao.CharacteristicsDAO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author Jedai
 */
public class AddModelToSearchCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public AddModelToSearchCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void execute() throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String makeStr = request.getParameter("make");
        if (makeStr != null && !"".equals(makeStr)) {
            CharacteristicsDAO characteristicsDAO = new CharacteristicsDAO();
            List<String> models = characteristicsDAO.findModelsNamesByMakeTitle(makeStr);
            String xml = transformToXML(models);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/xml");
            out.write(xml);
        }
        try {
            
        } finally {
            out.close();
        }
    }

    private String transformToXML(List<String> modelList) {
        StringBuilder xml = new StringBuilder(100);
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xml.append("<model-list>");
        for (String model : modelList) {
            xml.append("<model>");
            xml.append("<id>");
            xml.append(model);
            xml.append("</id>");
            xml.append("<title>");
            xml.append(model);
            xml.append("</title>");
            xml.append("</model>");
        }
        xml.append("</model-list>");
        return xml.toString();
    }
}
