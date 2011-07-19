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
import com.bidover.auto.model.bean.Make;
import com.bidover.auto.model.bean.Tires;
import com.bidover.auto.model.bean.TopType;
import com.bidover.auto.model.bean.Transmission;
import com.bidover.auto.model.bean.Wheels;
import com.bidover.auto.database.dao.BodyStyleDAO;
import com.bidover.auto.database.dao.CharacteristicsDAO;
import com.bidover.auto.database.dao.CountryAssemblyDAO;
import com.bidover.auto.database.dao.DoorDAO;
import com.bidover.auto.database.dao.DriveTrainDAO;
import com.bidover.auto.database.dao.EngineDAO;
import com.bidover.auto.database.dao.ExteriorColorDAO;
import com.bidover.auto.database.dao.FuelDAO;
import com.bidover.auto.database.dao.InteriorColorDAO;
import com.bidover.auto.database.dao.MakeDAO;
import com.bidover.auto.database.dao.TiresDAO;
import com.bidover.auto.database.dao.TopTypeDAO;
import com.bidover.auto.database.dao.TransmissionDAO;
import com.bidover.auto.database.dao.WheelsDAO;
import com.bidover.auto.model.bean.CountryAssembly;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Jedai
 */
public class ShowSearchCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public ShowSearchCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void execute() throws ServletException, IOException {
        MakeDAO makeDAO = new MakeDAO();
        /*
        List<Make> makes = makeDAO.findAll();
        request.setAttribute("makes", makes);
         */

        List<Integer> years = new ArrayList<Integer>();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for(int i=year+1; i>=1950; i--){
            years.add(i);
        }
        request.setAttribute("years", years);
        CharacteristicsDAO characteristicsDAO = new CharacteristicsDAO();
        List<String> makes = characteristicsDAO.findAllMakesNames();
        request.setAttribute("makes", makes);

        TopTypeDAO topTypeDAO = new TopTypeDAO();
        List<TopType> topTypes = topTypeDAO.findAll();
        request.setAttribute("top_types", topTypes);

        ExteriorColorDAO extColorDAO = new ExteriorColorDAO();
        List<ExteriorColor> extColors = extColorDAO.findAll();
        request.setAttribute("exterior_color", extColors);

        InteriorColorDAO intColorDAO = new InteriorColorDAO();
        List<InteriorColor> intColors = intColorDAO.findAll();
        request.setAttribute("interior_color", intColors);

        DoorDAO doorDAO = new DoorDAO();
        List<Door> doors = doorDAO.findAll();
        request.setAttribute("doors", doors);

        WheelsDAO wheelDAO = new WheelsDAO();
        List<Wheels> wheels = wheelDAO.findAll();
        request.setAttribute("wheels", wheels);

//        TiresDAO tiresDAO = new TiresDAO();
//        List<Tires> tires = tiresDAO.findAll();
//        request.setAttribute("tires", tires);

        FuelDAO fuelDAO = new FuelDAO();
        List<Fuel> fuel = fuelDAO.findAll();
        request.setAttribute("fuel", fuel);

        TransmissionDAO transmissionDAO = new TransmissionDAO();
        List<Transmission> transmission = transmissionDAO.findAll();
        request.setAttribute("transmission", transmission);

        DriveTrainDAO driveTrainDAO = new DriveTrainDAO();
        List<DriveTrain> driveTrain = driveTrainDAO.findAll();
        request.setAttribute("drive_train", driveTrain);

        BodyStyleDAO bodyStyleDAO = new BodyStyleDAO();
        List<BodyStyle> bodyStyle = bodyStyleDAO.findAll();
        request.setAttribute("body_style", bodyStyle);

        EngineDAO engineDAO = new EngineDAO();
        List<Engine> engine = engineDAO.findAll();
        request.setAttribute("engine", engine);
        
        CountryAssemblyDAO CountryAssemblyDAO = new CountryAssemblyDAO();
        List<CountryAssembly> countryAssembly = CountryAssemblyDAO.findAll();
        request.setAttribute("countryAssemblys", countryAssembly);

        request.getRequestDispatcher("./search.jsp").forward(request, response);
    }
}
