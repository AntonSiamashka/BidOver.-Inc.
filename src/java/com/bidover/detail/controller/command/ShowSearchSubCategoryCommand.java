/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.detail.controller.command;

import com.bidover.detail.model.bean.SubCategory;
import com.bidover.detail.model.logic.SearchDetailsLogic;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Jedai
 */
public class ShowSearchSubCategoryCommand implements ICommand {

    private SearchDetailsLogic searchDetailsLogic = new SearchDetailsLogic();
    private final static String SUBCATEGORY_ATTRIBUTE = "subCategoryList";
    private final static String CATEGORY_ID_ATTRIBUTE = "categoryID";

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter(CATEGORY_ID_ATTRIBUTE);
        if (idStr != null) {
            Integer id = Integer.parseInt(idStr);
            List<SubCategory> subCategoryList = searchDetailsLogic.findSubCategoryByCategoryId(id);
            String xml = transformToXML(subCategoryList);
            response.setContentType("application/xml");
            response.getWriter().write(xml);
            request.setAttribute(SUBCATEGORY_ATTRIBUTE, subCategoryList);
        }
        return null;
    }

    private String transformToXML(List<SubCategory> subCategoryList){
            StringBuilder xml = new StringBuilder(100);
            xml.append("<sub-category-list>");
            for (SubCategory subCategory : subCategoryList) {
                xml.append( "<sub-category>");
                xml.append("<id>");
                xml.append(subCategory.getId());
                xml.append("</id>");
                xml.append("<name>");
                xml.append(subCategory.getName());
                xml.append("</name>");
                xml.append("</sub-category>");
            }
            xml.append("</sub-category-list>");
            return xml.toString();
    }
}
