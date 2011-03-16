/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bidover.tires.controller.command;

/**
 *
 * @author Jedai
 */
import com.bidover.tires.model.bean.Wheels;
import com.bidover.tires.properties.PathProperties;
import com.bidover.tires.model.logic.SearchWheelsLogic;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jedai
 */
public class ShowWheelsViewCommand implements ICommand{


    private final static String TIRES_ATTRIBUTE = "wheels";
    private final static String TIRES_ID_ATTRIBUTE = "wheelsID";
    private SearchWheelsLogic searchWheelsLogic = new SearchWheelsLogic();

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter(TIRES_ID_ATTRIBUTE);
        if (idStr != null) {
            Integer id = Integer.parseInt(idStr);
            Wheels wheels = searchWheelsLogic.findById(id);
            request.setAttribute(TIRES_ATTRIBUTE, wheels);
        }
        return PathProperties.createPathProperties().getProperty(PathProperties.WHEELS_VIEW_PAGE);
    }

}