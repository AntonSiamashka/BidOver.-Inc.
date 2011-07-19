/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.controller.command;

import com.bidover.auto.database.dao.CharacteristicsDAO;
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
public class AddModificationToAddCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public AddModificationToAddCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void execute() throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String modelStr = request.getParameter("model");
        if (modelStr != null && !"".equals(modelStr)) {
            CharacteristicsDAO characteristicsDAO = new CharacteristicsDAO();
            List<String> modifications = characteristicsDAO.findByModelTitle(modelStr);
            String xml = transformToXML(modifications);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/xml");
            response.getWriter().write(xml);
        }
    }

    private String transformToXML(List<String> modificationList) {
        StringBuilder xml = new StringBuilder(100);
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xml.append("<modification-list>");
        for (String modification : modificationList) {
            xml.append("<modification>");
            xml.append("<id>");
            xml.append(0);
            xml.append("</id>");
            xml.append("<title>");
            xml.append(modification);
            xml.append("</title>");
            xml.append("</modification>");
        }
        xml.append("</modification-list>");
        return xml.toString();
    }
}
