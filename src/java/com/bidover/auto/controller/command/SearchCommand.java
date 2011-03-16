/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.controller.command;

import com.bidover.auto.model.bean.Auto;
import com.bidover.auto.model.bean.BodyStyle;
import com.bidover.auto.model.bean.Door;
import com.bidover.auto.model.bean.DriveTrain;
import com.bidover.auto.model.bean.Engine;
import com.bidover.auto.model.bean.ExteriorColor;
import com.bidover.auto.model.bean.Fuel;
import com.bidover.auto.model.bean.InteriorColor;
import com.bidover.auto.model.bean.InteriorType;
import com.bidover.auto.model.bean.Make;
import com.bidover.auto.model.bean.Model;
import com.bidover.auto.model.bean.Tires;
import com.bidover.auto.model.bean.TopType;
import com.bidover.auto.model.bean.Transmission;
import com.bidover.auto.model.bean.Wheels;
import com.bidover.auto.database.dao.AutoDAO;
import com.bidover.auto.model.bean.Characteristics;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jedai
 */
public class SearchCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public SearchCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void execute() throws ServletException, IOException {
        try {
            String makeTxt = request.getParameter("make");
            String modelTxt = request.getParameter("model");
            String topTypeTxt = request.getParameter("top_type");
            String exteriorColorTxt = request.getParameter("exterior_color");
            String interiorColorTxt = request.getParameter("interior_color");
            String yearBegTxt = request.getParameter("years_beg");
            String yearEndTxt = request.getParameter("years_end");
            String odometerTxt = request.getParameter("odometer");
            String doorsChTxt = request.getParameter("doors");
            String driveTrainChTxt = request.getParameter("drive_train");
            String intTypeChTxt = request.getParameter("int_type");
            String wheelsChTxt = request.getParameter("wheels");
            String tiresChTxt = request.getParameter("tires");
            String engineTxt = request.getParameter("engine");
            String fuelTxt = request.getParameter("fuel");
            String transmissionTxt = request.getParameter("transmission");
            String bodyStyleTxt = request.getParameter("body_style");

            Auto auto = new Auto();

            BodyStyle bodyStyle = new BodyStyle();
            if (Integer.valueOf(fuelTxt) != -1) {
                bodyStyle.setId(Integer.valueOf(bodyStyleTxt));
            }
            Fuel fuel = new Fuel();
            if (Integer.valueOf(fuelTxt) != -1) {
                fuel.setId(Integer.valueOf(driveTrainChTxt));
            }
            DriveTrain driveTrain = new DriveTrain();
            if (Integer.valueOf(driveTrainChTxt) != -1) {
                driveTrain.setId(Integer.valueOf(driveTrainChTxt));
            }
            ExteriorColor extColor = new ExteriorColor();
            if (Integer.valueOf(exteriorColorTxt) != -1) {
                extColor.setId(Integer.valueOf(exteriorColorTxt));
            }
            InteriorColor intColor = new InteriorColor();
            if (Integer.valueOf(interiorColorTxt) != -1) {
                intColor.setId(Integer.valueOf(interiorColorTxt));
            }
            Tires tires = new Tires();
            if (Integer.valueOf(tiresChTxt) != -1) {
                tires.setId(Integer.valueOf(tiresChTxt));
            }
            TopType topType = new TopType();
            if (Integer.valueOf(topTypeTxt) != -1) {
                topType.setId(Integer.valueOf(topTypeTxt));
            }
            Wheels wheels = new Wheels();
            if (Integer.valueOf(wheelsChTxt) != -1) {
                wheels.setId(Integer.valueOf(wheelsChTxt));
            }
            Door door = new Door();
            if (Integer.valueOf(doorsChTxt) != -1) {
                door.setId(Integer.valueOf(doorsChTxt));
            }
            Engine engine = new Engine();
            if (Integer.valueOf(engineTxt) != -1) {
                engine.setId(Integer.valueOf(engineTxt));
            }
            InteriorType interiorType = new InteriorType();
            if (Integer.valueOf(intTypeChTxt) != -1) {
                interiorType.setId(Integer.valueOf(intTypeChTxt));
            }
            Transmission transmission = new Transmission();
            if (Integer.valueOf(transmissionTxt) != -1) {
                transmission.setId(Integer.valueOf(transmissionTxt));
            }

            if (Integer.valueOf(odometerTxt) != -1) {
                auto.setOdometer(Integer.valueOf(odometerTxt));
            }

            auto.setDoors(door);
            auto.setInteriorType(interiorType);
            auto.setIdDriveTrain(driveTrain);
            auto.setIdExteriorColor(extColor);
            auto.setIdInteriorColor(intColor);
            Characteristics characteristics = new Characteristics();
            characteristics.setMake(makeTxt);
            characteristics.setModel(modelTxt);
            auto.setCharacteristics(characteristics);
            auto.setIdTires(tires);
            auto.setIdTopType(topType);
            auto.setIdWheels(wheels);
            auto.setEngine(engine);
            auto.setTransmission(transmission);
            auto.setIdFuel(fuel);
            auto.setIdBodyStyle(bodyStyle);
            AutoDAO autoDAO = new AutoDAO();
            if (yearBegTxt == null || yearEndTxt == null) {
                yearBegTxt = String.valueOf(Integer.MIN_VALUE);
                yearEndTxt = String.valueOf(Integer.MAX_VALUE);
            }
            List<Auto> autos = autoDAO.findPreview(auto, Integer.valueOf(yearBegTxt), Integer.valueOf(yearEndTxt));
            request.setAttribute("autos", autos);
            request.getRequestDispatcher("searchResult.jsp").forward(request, response);
        } catch (NumberFormatException ex) {
            response.sendRedirect("Controller?command=SHOW_SEARCH");
            //request.getRequestDispatcher("Controller?command=SHOW_SEARCH").forward(request, response);
        }
    }
}
