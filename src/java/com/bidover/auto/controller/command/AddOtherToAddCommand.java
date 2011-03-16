package com.bidover.auto.controller.command;

import com.bidover.auto.model.bean.BodyStyle;
import com.bidover.auto.model.bean.Door;
import com.bidover.auto.model.bean.DriveTrain;
import com.bidover.auto.model.bean.Engine;
import com.bidover.auto.model.bean.ExteriorColor;
import com.bidover.auto.model.bean.Fuel;
import com.bidover.auto.model.bean.InteriorColor;
import com.bidover.auto.model.bean.InteriorType;
import com.bidover.auto.model.bean.Modification;
import com.bidover.auto.model.bean.Tires;
import com.bidover.auto.model.bean.TopType;
import com.bidover.auto.model.bean.Transmission;
import com.bidover.auto.model.bean.Trim;
import com.bidover.auto.model.bean.Wheels;
import com.bidover.auto.database.dao.BodyStyleDAO;
import com.bidover.auto.database.dao.CharacteristicsDAO;
import com.bidover.auto.database.dao.DoorDAO;
import com.bidover.auto.database.dao.DriveTrainDAO;
import com.bidover.auto.database.dao.EngineDAO;
import com.bidover.auto.database.dao.ExteriorColorDAO;
import com.bidover.auto.database.dao.FuelDAO;
import com.bidover.auto.database.dao.InteriorColorDAO;
import com.bidover.auto.database.dao.InteriorTypeDAO;
import com.bidover.auto.database.dao.ModificationDAO;
import com.bidover.auto.database.dao.TiresDAO;
import com.bidover.auto.database.dao.TopTypeDAO;
import com.bidover.auto.database.dao.TransmissionDAO;
import com.bidover.auto.database.dao.TrimDAO;
import com.bidover.auto.database.dao.WheelsDAO;
import com.bidover.auto.model.bean.Characteristics;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddOtherToAddCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public AddOtherToAddCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void execute() throws ServletException, IOException {
        StringBuilder specification = new StringBuilder(500);
        String modificationStr = request.getParameter("modification");
        Modification modifictaion = new Modification();
        modifictaion.setTitle(modificationStr);
        ModificationDAO modifictaionDAO = new ModificationDAO();
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

        List<Transmission> transmissions = transmissionDAO.findAll();
        List<InteriorType> interiorTypes = interiorTypeDAO.findAll();
        List<Engine> engines = engineDAO.findAll();
        List<Door> doors = doorDAO.findAll();
        List<Wheels> wheelsList = wheelsDAO.findAll();
        List<TopType> topTypes = topTypeDAO.findAll();
        List<Tires> tiresList = tiresDAO.findAll();
        List<Fuel> fuels = fuelDAO.findAll();
        List<InteriorColor> interiorColors = interiorColorDAO.findAll();
        List<Modification> modifications = modifictaionDAO.find(modifictaion);
        List<Trim> trims = trimDAO.findAll();
        List<BodyStyle> bodyStyle = bodyStyleDAO.findAll();
        List<DriveTrain> driveTrains = driveTrainDAO.findAll();
        List<ExteriorColor> exteriorColors = exteriorColorDAO.findAll();

        specification.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        if (modificationStr != null && !"".equals(modificationStr)) {
            specification.append("<other-list>");
            specification.append(transformYearToXML(modificationStr));
            specification.append(transformTrimToXML(trims));
            specification.append(transformBodyStyleToXML(bodyStyle));
            specification.append(transformDriveTrainToXML(driveTrains));
            specification.append(transformExteriorColorToXML(exteriorColors));
            specification.append(transformInteriorColorToXML(interiorColors));
            specification.append(transformFuelToXML(fuels));
            specification.append(transformTiresToXML(tiresList));
            specification.append(transformTopTypeToXML(topTypes));
            specification.append(transformWheelsToXML(wheelsList));
            specification.append(transformDoorToXML(doors));
            specification.append(transformEngineToXML(engines));
            specification.append(transformInteriorTypeToXML(interiorTypes));
            specification.append(transformTransmissionToXML(transmissions));
            specification.append("</other-list>");
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/xml");
        response.getWriter().write(specification.toString());
    }

    private String transformYearToXML(String modification) {
        StringBuilder xml = new StringBuilder(100);
        xml.append("<year-list>");
        CharacteristicsDAO characteristicsDAO = new CharacteristicsDAO();
        List<Characteristics> characteristics = characteristicsDAO.findAllByModificationTitle(modification);
        int begYear = characteristics.get(0).getBegYear();
        int endYear = characteristics.get(0).getEndYear();
            int time = endYear - begYear;
            for (int i = 0; i <= time; i++) {
                int currentYear = begYear + i;
                xml.append("<year>");
                xml.append("<id>");
                xml.append(currentYear);
                xml.append("</id>");
                xml.append("<title>");
                xml.append(currentYear);
                xml.append("</title>");
                xml.append("</year>");
            }
        
        xml.append("</year-list>");
        return xml.toString();
    }

    private String transformTrimToXML(List<Trim> list) {
        StringBuilder xml = new StringBuilder(100);
        xml.append("<trim-list>");
        for (Trim element : list) {
            xml.append(element.toXMLString());
        }
        xml.append("</trim-list>");
        return xml.toString();
    }

    private String transformBodyStyleToXML(List<BodyStyle> list) {
        StringBuilder xml = new StringBuilder(100);
        xml.append("<body-style-list>");
        for (BodyStyle element : list) {
            xml.append(element.toXMLString());
        }
        xml.append("</body-style-list>");
        return xml.toString();
    }

    private String transformDriveTrainToXML(List<DriveTrain> list) {
        StringBuilder xml = new StringBuilder(100);
        xml.append("<drive-train-list>");
        for (DriveTrain element : list) {
            xml.append(element.toXMLString());
        }
        xml.append("</drive-train-list>");
        return xml.toString();
    }

    private String transformExteriorColorToXML(List<ExteriorColor> list) {
        StringBuilder xml = new StringBuilder(100);
        xml.append("<exterior-color-list>");
        for (ExteriorColor element : list) {
            xml.append(element.toXMLString());
        }
        xml.append("</exterior-color-list>");
        return xml.toString();
    }

    private String transformInteriorColorToXML(List<InteriorColor> list) {
        StringBuilder xml = new StringBuilder(100);
        xml.append("<interior-color-list>");
        for (InteriorColor element : list) {
            xml.append(element.toXMLString());
        }
        xml.append("</interior-color-list>");
        return xml.toString();
    }

    private String transformFuelToXML(List<Fuel> list) {
        StringBuilder xml = new StringBuilder(100);
        xml.append("<fuel-list>");
        for (Fuel element : list) {
            xml.append(element.toXMLString());
        }
        xml.append("</fuel-list>");
        return xml.toString();
    }

    private String transformTiresToXML(List<Tires> list) {
        StringBuilder xml = new StringBuilder(100);
        xml.append("<tires-list>");
        for (Tires element : list) {
            xml.append(element.toXMLString());
        }
        xml.append("</tires-list>");
        return xml.toString();
    }

    private String transformTopTypeToXML(List<TopType> list) {
        StringBuilder xml = new StringBuilder(100);
        xml.append("<top-type-list>");
        for (TopType element : list) {
            xml.append(element.toXMLString());
        }
        xml.append("</top-type-list>");
        return xml.toString();
    }

    private String transformWheelsToXML(List<Wheels> list) {
        StringBuilder xml = new StringBuilder(100);
        xml.append("<wheels-list>");
        for (Wheels element : list) {
            xml.append(element.toXMLString());
        }
        xml.append("</wheels-list>");
        return xml.toString();
    }

    private String transformDoorToXML(List<Door> list) {
        StringBuilder xml = new StringBuilder(100);
        xml.append("<door-list>");
        for (Door element : list) {
            xml.append(element.toXMLString());
        }
        xml.append("</door-list>");
        return xml.toString();
    }

    private String transformEngineToXML(List<Engine> list) {
        StringBuilder xml = new StringBuilder(100);
        xml.append("<engine-list>");
        for (Engine element : list) {
            xml.append(element.toXMLString());
        }
        xml.append("</engine-list>");
        return xml.toString();
    }

    private String transformInteriorTypeToXML(List<InteriorType> list) {
        StringBuilder xml = new StringBuilder(100);
        xml.append("<interior-type-list>");
        for (InteriorType element : list) {
            xml.append(element.toXMLString());
        }
        xml.append("</interior-type-list>");
        return xml.toString();
    }

    private String transformTransmissionToXML(List<Transmission> list) {
        StringBuilder xml = new StringBuilder(100);
        xml.append("<transmission-list>");
        for (Transmission element : list) {
            xml.append(element.toXMLString());
        }
        xml.append("</transmission-list>");
        return xml.toString();
    }

    private String createTrimSelect() {
        String select = "";
        TrimDAO trimDAO = new TrimDAO();
        List<Trim> trims = trimDAO.findAll();
        select += "<tr>";
        select += "<td align=\"right\">Trim:</td>";
        select += "<td><select name=\"trim\" id=\"trim\" onchange=\"\">";
        for (Trim trim : trims) {
            select += "<option width=\"100%\" value=\"" + trim.getId() + "\">" + trim.getTitle() + "</option>";
        }
        select += "</select></td>";
        select += "</tr>";
        return select;
    }

    private String createBodyStyleSelect() {
        String select = "";
        BodyStyleDAO bodyStyleDAO = new BodyStyleDAO();
        List<BodyStyle> bodys = bodyStyleDAO.findAll();
        select += "<tr>";
        select += "<td align=\"right\">Body Style:</td>";
        select += "<td><select name=\"body-style\" id=\"body-tyle\" onchange=\"\">";
        for (BodyStyle body : bodys) {
            select += "<option width=\"100%\" value=\"" + body.getId() + "\">" + body.getTitle() + "</option>";
        }
        select += "</select></td>";
        select += "</tr>";
        return select;
    }

    private String createDriveTrainSelect() {
        String select = "";
        DriveTrainDAO driveTrainDAO = new DriveTrainDAO();
        List<DriveTrain> driveTrains = driveTrainDAO.findAll();
        select += "<tr>";
        select += "<td align=\"right\">Drive Train:</td>";
        select += "<td><select name=\"drive_train\" id=\"drive_train\" onchange=\"\">";
        for (DriveTrain driveTrain : driveTrains) {
            select += "<option width=\"100%\" value=\"" + driveTrain.getId() + "\">" + driveTrain.getTitle() + "</option>";
        }
        select += "</select></td>";
        select += "</tr>";
        return select;
    }

    private String createExteriorColorSelect() {
        String select = "";
        ExteriorColorDAO exteriorColorDAO = new ExteriorColorDAO();
        List<ExteriorColor> exteriorColors = exteriorColorDAO.findAll();
        select += "<tr>";
        select += "<td align=\"right\">Exterior Color:</td>";
        select += "<td><select name=\"exterior_color\" id=\"exterior_color\" onchange=\"\">";
        for (ExteriorColor exteriorColor : exteriorColors) {
            select += "<option width=\"100%\" value=\"" + exteriorColor.getId() + "\">" + exteriorColor.getTitle() + "</option>";
        }
        select += "</select></td>";
        select += "</tr>";
        return select;
    }

    private String createInteriorColorSelect() {
        String select = "";
        InteriorColorDAO interiorColorDAO = new InteriorColorDAO();
        List<InteriorColor> interiorColors = interiorColorDAO.findAll();
        select += "<tr>";
        select += "<td align=\"right\">Interior Color:</td>";
        select += "<td><select name=\"interior_color\" id=\"interior_color\" onchange=\"\">";
        for (InteriorColor interiorColor : interiorColors) {
            select += "<option width=\"100%\" value=\"" + interiorColor.getId() + "\">" + interiorColor.getTitle() + "</option>";
        }
        select += "</select></td>";
        select += "</tr>";
        return select;
    }

    private String createFuelSelect() {
        String select = "";
        FuelDAO fuelDAO = new FuelDAO();
        List<Fuel> fuels = fuelDAO.findAll();
        select += "<tr>";
        select += "<td align=\"right\">Fuel:</td>";
        select += "<td><select name=\"fuel\" id=\"fuel\" onchange=\"\">";
        for (Fuel fuel : fuels) {
            select += "<option width=\"100%\" value=\"" + fuel.getId() + "\">" + fuel.getTitle() + "</option>";
        }
        select += "</select></td>";
        select += "</tr>";
        return select;
    }

    private String createTiresSelect() {
        String select = "";
        TiresDAO tiresDAO = new TiresDAO();
        List<Tires> tiresList = tiresDAO.findAll();
        select += "<tr>";
        select += "<td align=\"right\">Tires:</td>";
        select += "<td><select name=\"tires\" id=\"tires\" onchange=\"\">";
        for (Tires tires : tiresList) {
            select += "<option width=\"100%\" value=\"" + tires.getId() + "\">" + tires.getTitle() + "</option>";
        }
        select += "</select></td>";
        select += "</tr>";
        return select;
    }

    private String createTopTypeSelect() {
        String select = "";
        TopTypeDAO topTypeDAO = new TopTypeDAO();
        List<TopType> topTypes = topTypeDAO.findAll();
        select += "<tr>";
        select += "<td align=\"right\">Top Type:</td>";
        select += "<td><select name=\"top_type\" id=\"top_type\" onchange=\"\">";
        for (TopType topType : topTypes) {
            select += "<option width=\"100%\" value=\"" + topType.getId() + "\">" + topType.getTitle() + "</option>";
        }
        select += "</select></td>";
        select += "</tr>";
        return select;
    }

    private String createWheelsSelect() {
        String select = "";
        WheelsDAO bodyStyleDAO = new WheelsDAO();
        List<Wheels> wheelsList = bodyStyleDAO.findAll();
        select += "<tr>";
        select += "<td align=\"right\">Wheels:</td>";
        select += "<td><select name=\"wheels\" id=\"wheels\" onchange=\"\">";
        for (Wheels wheels : wheelsList) {
            select += "<option width=\"100%\" value=\"" + wheels.getId() + "\">" + wheels.getTitle() + "</option>";
        }
        select += "</select></td>";
        select += "</tr>";
        return select;
    }

    private String createDoorSelect() {
        String select = "";
        DoorDAO doorDAO = new DoorDAO();
        List<Door> doors = doorDAO.findAll();
        select += "<tr>";
        select += "<td align=\"right\">Doors:</td>";
        select += "<td><select name=\"door\" id=\"door\" onchange=\"\">";
        for (Door door : doors) {
            select += "<option width=\"100%\" value=\"" + door.getId() + "\">" + door.getTitle() + "</option>";
        }
        select += "</select></td>";
        select += "</tr>";
        return select;
    }

    private String createEngineSelect() {
        String select = "";
        EngineDAO engineDAO = new EngineDAO();
        List<Engine> engines = engineDAO.findAll();
        select += "<tr>";
        select += "<td align=\"right\">Engine:</td>";
        select += "<td><select name=\"engine\" id=\"engine\" onchange=\"\">";
        for (Engine engine : engines) {
            select += "<option width=\"100%\" value=\"" + engine.getId() + "\">" + engine.getTitle() + "</option>";
        }
        select += "</select></td>";
        select += "</tr>";
        return select;
    }

    private String createInteriorTypeSelect() {
        String select = "";
        InteriorTypeDAO interiorTypeDAO = new InteriorTypeDAO();
        List<InteriorType> interiorTypes = interiorTypeDAO.findAll();
        select += "</tr>";
        select += "<td align=\"right\">Interior Type:</td>";
        select += "<td><select name=\"interior_type\" id=\"interior_type\" onchange=\"\">";
        for (InteriorType interiorType : interiorTypes) {
            select += "<option width=\"100%\" value=\"" + interiorType.getId() + "\">" + interiorType.getTitle() + "</option>";
        }
        select += "</select></td>";
        select += "</tr>";
        return select;
    }

    private String createTransmissionSelect() {
        String select = "";
        TransmissionDAO transmissionDAO = new TransmissionDAO();
        List<Transmission> transmissions = transmissionDAO.findAll();
        select += "<tr>";
        select += "<td align=\"right\">Transmission:</td>";
        select += "<td><select name=\"transmission\" id=\"transmission\" onchange=\"\">";
        for (Transmission transmission : transmissions) {
            select += "<option width=\"100%\" value=\"" + transmission.getId() + "\">" + transmission.getTitle() + "</option>";
        }
        select += "</select></td>";
        select += "</tr>";
        return select;
    }

    private String createVinEdit() {
        String edit = "";
        edit += "<tr>";
        edit += "<td align=\"right\">VIN:</td>";
        edit += "<td><input type=\"text\" id=\"vin\" name=\"vin\" value=\"12345678912345678\" /></td>";
        edit += "</tr>";
        return edit;
    }

    private String createOdometerEdit() {
        String edit = "";
        edit += "<tr>";
        edit += "<td align=\"right\">Odometer:</td>";
        edit += "<td><input type=\"text\" id=\"odometer\" name=\"odometer\" value=\"1000\" /></td>";
        edit += "</tr>";
        return edit;
    }
}
