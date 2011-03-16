/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.detail.controller.command;

import com.bidover.detail.model.bean.Item;
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
public class ShowItemCommand implements ICommand {

    private AddingDetailLogic addingDetailLogic = new AddingDetailLogic();
    private final static String ITEM_ATTRIBUTE = "itemList";
    private final static String SUBCATEGORY_ID_ATTRIBUTE = "subcategoryID";

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter(SUBCATEGORY_ID_ATTRIBUTE);
        if (idStr != null) {
            Integer id = Integer.parseInt(idStr);
            List<Item> itemList = addingDetailLogic.findItemBySubCategoryId(id);
            String xml = transformToXML(itemList);
            response.setContentType("application/xml");
            response.getWriter().write(xml);
            request.setAttribute(ITEM_ATTRIBUTE, itemList);
        }
        return null;
    }

    private String transformToXML(List<Item> itemList){
            StringBuilder xml = new StringBuilder(100);
            xml.append("<item-list>");
            for (Item item : itemList) {
                xml.append("<item>");
                xml.append("<id>");
                xml.append(item.getId());
                xml.append("</id>");
                xml.append("<name>");
                xml.append(item.getName());
                xml.append("</name>");
                xml.append("</item>");
            }
            xml.append("</item-list>");
            return xml.toString();
    }
}
