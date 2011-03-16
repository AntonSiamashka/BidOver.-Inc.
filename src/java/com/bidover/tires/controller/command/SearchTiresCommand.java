/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.tires.controller.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bidover.tires.properties.PathProperties;
import com.bidover.tires.model.logic.SearchTiresLogic;

/**
 *
 * @author Jedai
 */
public class SearchTiresCommand implements ICommand {

    private final static String WIDTH_ATTRIBUTE = "widthList";
    private final static String CONTOUR_ATTRIBUTE = "contourList";
    private final static String DIAMETER_ATTRIBUTE = "diameterList";
    private final static String SEASON_ATTRIBUTE = "seasonList";
    private final static String MAKE_ATTRIBUTE = "makeList";
    private SearchTiresLogic searchTiresLogic = new SearchTiresLogic();

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(WIDTH_ATTRIBUTE, searchTiresLogic.findAllTiresWidth());
        request.setAttribute(CONTOUR_ATTRIBUTE, searchTiresLogic.findAllTiresContour());
        request.setAttribute(DIAMETER_ATTRIBUTE, searchTiresLogic.findAllTiresDiameter());
        request.setAttribute(SEASON_ATTRIBUTE, searchTiresLogic.findAllTiresSeason());
        request.setAttribute(MAKE_ATTRIBUTE, searchTiresLogic.findAllTiresMake());
        return PathProperties.createPathProperties().getProperty(PathProperties.SEARCH_TIRES_PAGE);
    }
}
