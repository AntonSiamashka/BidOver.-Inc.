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
public class ShowModelCommand implements ICommand {

    private AddingDetailLogic addingDetailLogic = new AddingDetailLogic();
    private final static String MODEL_ATTRIBUTE = "modelList";
    private final static String MAKE_ID_ATTRIBUTE = "makeID";

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter(MAKE_ID_ATTRIBUTE);
        if (idStr != null) {
            Integer id = Integer.parseInt(idStr);
            List<Model> subCategoryList = addingDetailLogic.findModelByMakeId(id);
            String xml = transformToXML(subCategoryList);
            response.setContentType("application/xml");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(xml);
            request.setAttribute(MODEL_ATTRIBUTE, subCategoryList);
        }
        return null;
    }

    private String transformToXML(List<Model> modelList){
            StringBuilder xml = new StringBuilder(100);
            xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            xml.append("<model-list>");
            for (Model model : modelList) {
                xml.append( "<model>");
                xml.append("<id>");
                xml.append(model.getId());
                xml.append("</id>");
                xml.append("<name>");
                xml.append(model.getTitle());
                xml.append("</name>");
                xml.append("</model>");
            }
            xml.append("</model-list>");
            return xml.toString();
    }
}
