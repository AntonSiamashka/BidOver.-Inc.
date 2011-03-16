/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bidover.tires.controller.command;

import com.bidover.tires.model.bean.Tires;
import com.bidover.tires.properties.PathProperties;
import com.bidover.tires.model.logic.SearchTiresLogic;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jedai
 */
public class ShowTiresViewCommand implements ICommand{


    private final static String TIRES_ATTRIBUTE = "tires";
    private final static String TIRES_ID_ATTRIBUTE = "tiresID";
    private SearchTiresLogic searchTiresLogic = new SearchTiresLogic();

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter(TIRES_ID_ATTRIBUTE);
        if (idStr != null) {
            Integer id = Integer.parseInt(idStr);
            Tires tires = searchTiresLogic.findById(id);
            request.setAttribute(TIRES_ATTRIBUTE, tires);
        }
        return PathProperties.createPathProperties().getProperty(PathProperties.TIRES_VIEW_PAGE);
    }

}
