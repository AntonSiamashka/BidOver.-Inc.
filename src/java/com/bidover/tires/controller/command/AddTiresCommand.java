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
import com.bidover.tires.model.logic.AddingTiresLogic;

/**
 *
 * @author Jedai
 */
public class AddTiresCommand implements ICommand {

    private final static String WIDTH_ATTRIBUTE = "widthList";
    private final static String CONTOUR_ATTRIBUTE = "contourList";
    private final static String DIAMETER_ATTRIBUTE = "diameterList";
    private final static String SEASON_ATTRIBUTE = "seasonList";
    private final static String MAKE_ATTRIBUTE = "makeList";
    private AddingTiresLogic addingTiresLogic = new AddingTiresLogic();


    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(WIDTH_ATTRIBUTE, addingTiresLogic.findAllTiresWidth());
        request.setAttribute(CONTOUR_ATTRIBUTE, addingTiresLogic.findAllTiresContour());
        request.setAttribute(DIAMETER_ATTRIBUTE, addingTiresLogic.findAllTiresDiameter());
        request.setAttribute(SEASON_ATTRIBUTE, addingTiresLogic.findAllTiresSeason());
        request.setAttribute(MAKE_ATTRIBUTE, addingTiresLogic.findAllTiresMake());
        return PathProperties.createPathProperties().getProperty(PathProperties.ADD_TIRES_PAGE);
    }
}

