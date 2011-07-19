/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.tires.controller.command;

import com.bidover.common.controller.command.AddLotCommand;
import com.bidover.tires.model.bean.Wheels;
import com.bidover.tires.model.bean.WheelsBCD;
import com.bidover.tires.model.bean.WheelsDiameter;
import com.bidover.tires.model.bean.WheelsED;
import com.bidover.tires.model.bean.WheelsWidth;
import com.bidover.tires.properties.PathProperties;
import com.bidover.tires.model.logic.AddingWheelsLogic;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jedai
 */
public class AddingWheelsCommand implements ICommand {

    private final static String WIDTH_ATTRIBUTE = "width-select";
    private final static String DIAMETER_ATTRIBUTE = "diameter-select";
    private final static String BCD_ATTRIBUTE = "bcd-select";
    private final static String ED_ATTRIBUTE = "ed-select";
    private AddingWheelsLogic addingWheelsLogic = new AddingWheelsLogic();

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer width = null;
        Integer diameter = null;
        Integer bcd = null;
        Integer ed = null;
        String widthStr = request.getParameter(WIDTH_ATTRIBUTE);
        String diameterStr = request.getParameter(DIAMETER_ATTRIBUTE);
        String bcdStr = request.getParameter(BCD_ATTRIBUTE);
        String edStr = request.getParameter(ED_ATTRIBUTE);
        width = Integer.parseInt(widthStr);
        diameter = Integer.parseInt(diameterStr);
        bcd = Integer.parseInt(bcdStr);
        ed = Integer.parseInt(edStr);
        Wheels wheels = new Wheels();
        wheels.setWheelsDiameter(new WheelsDiameter(diameter));
        wheels.setWheelsWidth(new WheelsWidth(width));
        wheels.setWheelsBCD(new WheelsBCD(bcd));
        wheels.setWheelsED(new WheelsED(ed));
        AddLotCommand addLotCommand = new AddLotCommand();
//        Integer lotId = addLotCommand.addLot(request, response);
//        wheels.setId(lotId);
        Integer id = addingWheelsLogic.add(wheels);
        return PathProperties.createPathProperties().getProperty(PathProperties.INDEX_PAGE);
    }
}
