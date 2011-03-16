/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.tires.controller.command;

import com.bidover.common.controller.command.AddLotCommand;
import com.bidover.tires.model.bean.Tires;
import com.bidover.tires.model.bean.TiresContour;
import com.bidover.tires.model.bean.TiresDiameter;
import com.bidover.tires.model.bean.TiresMake;
import com.bidover.tires.model.bean.TiresSeason;
import com.bidover.tires.model.bean.TiresWidth;
import com.bidover.tires.properties.PathProperties;
import com.bidover.tires.model.logic.AddingTiresLogic;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jedai
 */
public class AddingTiresCommand implements ICommand {

    private final static String WIDTH_ATTRIBUTE = "width-select";
    private final static String CONTOUR_ATTRIBUTE = "contour-select";
    private final static String DIAMETER_ATTRIBUTE = "diameter-select";
    private final static String SEASON_ATTRIBUTE = "season-select";
    private final static String MAKE_ATTRIBUTE = "make-select";
    private AddingTiresLogic addingTiresLogic = new AddingTiresLogic();

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
        width = Integer.parseInt(widthStr);
        contour = Integer.parseInt(contourStr);
        diameter = Integer.parseInt(diameterStr);
        season = Integer.parseInt(seasonStr);
        make = Integer.parseInt(makeStr);
        Tires tires = new Tires();
        tires.setTiresWidth(new TiresWidth(width));
        tires.setTiresDiameter(new TiresDiameter(diameter));
        tires.setTiresContour(new TiresContour(contour));
        tires.setTiresSeason(new TiresSeason(season));
        tires.setTiresMake(new TiresMake(make));
        AddLotCommand addLotCommand = new AddLotCommand();
        Integer lotId = addLotCommand.addLot(request, response);
        tires.setId(lotId);
        Integer id = addingTiresLogic.add(tires);
        return PathProperties.createPathProperties().getProperty(PathProperties.INDEX_PAGE);
    }
}
