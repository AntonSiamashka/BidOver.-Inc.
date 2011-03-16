/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.tires.controller.command;

import com.bidover.tires.model.bean.Wheels;
import com.bidover.tires.model.bean.WheelsBCD;
import com.bidover.tires.model.bean.WheelsDiameter;
import com.bidover.tires.model.bean.WheelsED;
import com.bidover.tires.model.bean.WheelsWidth;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bidover.tires.properties.PathProperties;
import com.bidover.tires.model.logic.SearchWheelsLogic;
import java.util.List;

/**
 *
 * @author Jedai
 */
public class SearchingWheelsCommand implements ICommand {

    private final static String WIDTH_ATTRIBUTE = "width-select";
    private final static String DIAMETER_ATTRIBUTE = "diameter-select";
    private final static String BCD_ATTRIBUTE = "bcd-select";
    private final static String ED_ATTRIBUTE = "ed-select";
    private final static String WHEELS_RESULT_ATTRIBUTE = "wheelsList";
    private SearchWheelsLogic searchWheelsLogic = new SearchWheelsLogic();

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer width = null;
        Integer diameter = null;
        Integer bcd = null;
        Integer ed = null;
        String widthStr = request.getParameter(WIDTH_ATTRIBUTE);
        String diameterStr = request.getParameter(DIAMETER_ATTRIBUTE);
        String bcdStr = request.getParameter(BCD_ATTRIBUTE);
        String edStr = request.getParameter(ED_ATTRIBUTE);
        if (widthStr != null && !"none".equals(widthStr)) {
            width = Integer.parseInt(widthStr);
        }
        if (diameterStr != null && !"none".equals(diameterStr)) {
            bcd = Integer.parseInt(diameterStr);
        }
        if (edStr != null && !"none".equals(edStr)) {
            ed = Integer.parseInt(edStr);
        }
        if (bcdStr != null && !"none".equals(bcdStr)) {
            bcd = Integer.parseInt(bcdStr);
        }
        Wheels wheels = new Wheels();
        wheels.setWheelsDiameter(new WheelsDiameter(diameter));
        wheels.setWheelsWidth(new WheelsWidth(width));
        wheels.setWheelsBCD(new WheelsBCD(bcd));
        wheels.setWheelsED(new WheelsED(ed));
        List<Wheels> wheelsList = searchWheelsLogic.find(wheels);
        request.setAttribute(WHEELS_RESULT_ATTRIBUTE, wheelsList);
        return PathProperties.createPathProperties().getProperty(PathProperties.SEARCH_WHEELS_RESULT_PAGE);
    }
}
