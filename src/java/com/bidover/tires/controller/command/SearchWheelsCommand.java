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
import com.bidover.tires.model.logic.SearchWheelsLogic;

/**
 *
 * @author Jedai
 */
public class SearchWheelsCommand implements ICommand {

    private final static String WIDTH_ATTRIBUTE = "widthList";
    private final static String DIAMETER_ATTRIBUTE = "diameterList";
    private final static String BCD_ATTRIBUTE = "bcdList";
    private final static String ED_ATTRIBUTE = "edList";
    private SearchWheelsLogic searchWheelsLogic = new SearchWheelsLogic();

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(WIDTH_ATTRIBUTE, searchWheelsLogic.findAllWidth());
        request.setAttribute(DIAMETER_ATTRIBUTE, searchWheelsLogic.findAllDiameter());
        request.setAttribute(BCD_ATTRIBUTE, searchWheelsLogic.findAllBCD());
        request.setAttribute(ED_ATTRIBUTE, searchWheelsLogic.findAllED());
        return PathProperties.createPathProperties().getProperty(PathProperties.SEARCH_WHEELS_PAGE);
    }
}
