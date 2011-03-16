/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.detail.controller.command;

import com.bidover.detail.model.bean.Model;
import com.bidover.detail.model.bean.SubCategory;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bidover.detail.model.logic.AddingDetailLogic;

/**
 *
 * @author Jedai
 */
public class ShowYearCommand implements ICommand {

    private AddingDetailLogic addingDetailLogic = new AddingDetailLogic();
    private final static String YEAR_ATTRIBUTE = "year";
    private final static String MODEL_ID_ATTRIBUTE = "modelID";

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter(MODEL_ID_ATTRIBUTE);
        if (idStr != null) {
            Integer id = Integer.parseInt(idStr);
            Model model = addingDetailLogic.findModelById(id);
            String xml = transformToXML(model);
            response.setContentType("application/xml");
            response.getWriter().write(xml);
            request.setAttribute(YEAR_ATTRIBUTE, model);
        }
        return null;
    }

    private String transformToXML(Model model) {
        StringBuilder xml = new StringBuilder(100);
        xml.append("<year-list>");
        xml.append("<year-begin>");
        xml.append(model.getBegYear());
        xml.append("</year-begin>");
        xml.append("<year-end>");
        xml.append(model.getEndYear());
        xml.append("</year-end>");
        xml.append("</year-list>");
        return xml.toString();
    }
}
