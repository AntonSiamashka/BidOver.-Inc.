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
import com.bidover.tires.model.logic.AddingWheelsLogic;

/**
 *
 * @author Jedai
 */
public class AddWheelsCommand implements ICommand {

    private final static String WIDTH_ATTRIBUTE = "widthList";
    private final static String DIAMETER_ATTRIBUTE = "diameterList";
    private final static String BCD_ATTRIBUTE = "bcdList";
    private final static String ED_ATTRIBUTE = "edList";
    private AddingWheelsLogic addingWheelsLogic = new AddingWheelsLogic();

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(WIDTH_ATTRIBUTE, addingWheelsLogic.findAllWidth());
        request.setAttribute(DIAMETER_ATTRIBUTE, addingWheelsLogic.findAllDiameter());
        request.setAttribute(BCD_ATTRIBUTE, addingWheelsLogic.findAllBCD());
        request.setAttribute(ED_ATTRIBUTE, addingWheelsLogic.findAllED());
        return PathProperties.createPathProperties().getProperty(PathProperties.ADD_WHEELS_PAGE);
    }
}

