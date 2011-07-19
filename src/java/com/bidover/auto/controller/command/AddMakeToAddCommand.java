/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.controller.command;

import com.bidover.auto.model.bean.BodyStyle;
import com.bidover.auto.model.bean.Door;
import com.bidover.auto.model.bean.DriveTrain;
import com.bidover.auto.model.bean.Engine;
import com.bidover.auto.model.bean.ExteriorColor;
import com.bidover.auto.model.bean.Fuel;
import com.bidover.auto.model.bean.InteriorColor;
import com.bidover.auto.model.bean.InteriorType;
import com.bidover.auto.model.bean.Tires;
import com.bidover.auto.model.bean.TopType;
import com.bidover.auto.model.bean.Transmission;
import com.bidover.auto.model.bean.Trim;
import com.bidover.auto.model.bean.Wheels;
import com.bidover.auto.database.dao.BodyStyleDAO;
import com.bidover.auto.database.dao.CharacteristicsDAO;
import com.bidover.auto.database.dao.CountryAssemblyDAO;
import com.bidover.auto.database.dao.DamageConditionDAO;
import com.bidover.auto.database.dao.DoorDAO;
import com.bidover.auto.database.dao.DriveTrainDAO;
import com.bidover.auto.database.dao.EngineDAO;
import com.bidover.auto.database.dao.ExteriorColorDAO;
import com.bidover.auto.database.dao.FuelDAO;
import com.bidover.auto.database.dao.InteriorColorDAO;
import com.bidover.auto.database.dao.InteriorTypeDAO;
import com.bidover.auto.database.dao.TiresDAO;
import com.bidover.auto.database.dao.TopTypeDAO;
import com.bidover.auto.database.dao.TransmissionDAO;
import com.bidover.auto.database.dao.TrimDAO;
import com.bidover.auto.database.dao.WheelsDAO;
import com.bidover.auto.model.bean.CountryAssembly;
import com.bidover.auto.model.bean.DamageCondition;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jedai
 */
public class AddMakeToAddCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public AddMakeToAddCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void execute() throws ServletException, IOException {
        CharacteristicsDAO characteristicsDAO = new CharacteristicsDAO();
        TrimDAO trimDAO = new TrimDAO();
        BodyStyleDAO bodyStyleDAO = new BodyStyleDAO();
        DriveTrainDAO driveTrainDAO = new DriveTrainDAO();
        ExteriorColorDAO exteriorColorDAO = new ExteriorColorDAO();
        InteriorColorDAO interiorColorDAO = new InteriorColorDAO();
        FuelDAO fuelDAO = new FuelDAO();
        TiresDAO tiresDAO = new TiresDAO();
        TopTypeDAO topTypeDAO = new TopTypeDAO();
        WheelsDAO wheelsDAO = new WheelsDAO();
        DoorDAO doorDAO = new DoorDAO();
        EngineDAO engineDAO = new EngineDAO();
        InteriorTypeDAO interiorTypeDAO = new InteriorTypeDAO();
        TransmissionDAO transmissionDAO = new TransmissionDAO();
        DamageConditionDAO damageConditionDAO = new DamageConditionDAO();
        CountryAssemblyDAO countryAssembleyDAO = new CountryAssemblyDAO();
        
        List<DamageCondition> damageConditions = damageConditionDAO.findAll();
        List<String> makes = characteristicsDAO.findAllMakesNames();
        List<Transmission> transmissions = transmissionDAO.findAll();
        List<InteriorType> interiorTypes = interiorTypeDAO.findAll();
        List<Engine> engines = engineDAO.findAll();
        List<Door> doors = doorDAO.findAll();
        List<Wheels> wheelsList = wheelsDAO.findAll();
        List<TopType> topTypes = topTypeDAO.findAll();
        List<Tires> tires = tiresDAO.findAll();
        List<Fuel> fuels = fuelDAO.findAll();
        List<InteriorColor> interiorColors = interiorColorDAO.findAll();
        //List<Trim> trims = trimDAO.findAll();
        List<BodyStyle> bodyStyles = bodyStyleDAO.findAll();
        List<DriveTrain> driveTrains = driveTrainDAO.findAll();
        List<ExteriorColor> exteriorColors = exteriorColorDAO.findAll(); 
        List<CountryAssembly> countryAssembleys = countryAssembleyDAO.findAll(); 
        
        request.setAttribute("makeList", makes);
        request.setAttribute("transmissions", transmissions);
        request.setAttribute("interiorTypes", interiorTypes);
        request.setAttribute("engines", engines);
        request.setAttribute("doors", doors);
        request.setAttribute("wheelsList", wheelsList);
        request.setAttribute("topTypes", topTypes);
        request.setAttribute("tires", tires);
        request.setAttribute("fuels", fuels);
        request.setAttribute("interiorColors", interiorColors);
        //request.setAttribute("trims", trims);
        request.setAttribute("bodyStyles", bodyStyles);
        request.setAttribute("driveTrains", driveTrains);
        request.setAttribute("exteriorColors", exteriorColors);
        request.setAttribute("damageConditions", damageConditions);
        request.setAttribute("countryAssembleys", countryAssembleys);
        request.getRequestDispatcher("addAuto.jsp").forward(request, response);
    }
}
