/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.tires.controller.command;

import com.bidover.tires.model.bean.Tires;
import com.bidover.tires.model.bean.TiresContour;
import com.bidover.tires.model.bean.TiresDiameter;
import com.bidover.tires.model.bean.TiresMake;
import com.bidover.tires.model.bean.TiresSeason;
import com.bidover.tires.model.bean.TiresWidth;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bidover.tires.properties.PathProperties;
import com.bidover.tires.model.logic.SearchTiresLogic;
import java.util.List;

/**
 *
 * @author Jedai
 */
public class SearchingTiresCommand implements ICommand {

    private final static String WIDTH_ATTRIBUTE = "width-select";
    private final static String CONTOUR_ATTRIBUTE = "contour-select";
    private final static String DIAMETER_ATTRIBUTE = "diameter-select";
    private final static String SEASON_ATTRIBUTE = "season-select";
    private final static String MAKE_ATTRIBUTE = "make-select";
    private final static String TIRES_RESULT_ATTRIBUTE = "tiresList";
    private SearchTiresLogic searchTiresLogic = new SearchTiresLogic();

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer width = null;
        Integer contour = null;
        Integer diameter = null;
        Integer season = null;
        Integer make = null;
        String widthStr = request.getParameter(WIDTH_ATTRIBUTE);
        String contourStr = request.getParameter(CONTOUR_ATTRIBUTE);
        String diameterStr = request.getParameter(DIAMETER_ATTRIBUTE);
        String seasonStr = request.getParameter(SEASON_ATTRIBUTE);
        String makeStr = request.getParameter(MAKE_ATTRIBUTE);
        if (widthStr != null && !"none".equals(widthStr)) {
            width = Integer.parseInt(widthStr);
        }
        if (contourStr != null && !"none".equals(contourStr)) {
            contour = Integer.parseInt(contourStr);
        }
        if (diameterStr != null && !"none".equals(diameterStr)) {
            diameter = Integer.parseInt(diameterStr);
        }
        if (seasonStr != null && !"none".equals(seasonStr)) {
            season = Integer.parseInt(seasonStr);
        }
        if (makeStr != null && !"none".equals(makeStr)) {
            make = Integer.parseInt(makeStr);
        }
        Tires tires = new Tires();
        tires.setTiresWidth(new TiresWidth(width));
        tires.setTiresDiameter(new TiresDiameter(diameter));
        tires.setTiresContour(new TiresContour(contour));
        tires.setTiresSeason(new TiresSeason(season));
        tires.setTiresMake(new TiresMake(make));
        List<Tires> tiresList = searchTiresLogic.find(tires);
        request.setAttribute(TIRES_RESULT_ATTRIBUTE, tiresList);
        return PathProperties.createPathProperties().getProperty(PathProperties.SEARCH_TIRES_RESULT_PAGE);
    }
}
