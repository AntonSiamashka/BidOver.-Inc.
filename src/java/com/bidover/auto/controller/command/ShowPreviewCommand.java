/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.controller.command;

import com.bidover.auto.model.bean.BodyStyle;
import com.bidover.auto.model.bean.Damage;
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
import com.bidover.auto.model.bean.Location;
import com.bidover.common.model.bean.Condition;
import com.bidover.auto.database.dao.BodyStyleDAO;
import com.bidover.auto.database.dao.DoorDAO;
import com.bidover.auto.database.dao.DriveTrainDAO;
import com.bidover.auto.database.dao.EngineDAO;
import com.bidover.auto.database.dao.ExteriorColorDAO;
import com.bidover.auto.database.dao.FuelDAO;
import com.bidover.auto.database.dao.InteriorColorDAO;
import com.bidover.auto.database.dao.InteriorTypeDAO;
import com.bidover.auto.database.dao.MakeDAO;
import com.bidover.auto.database.dao.ModelDAO;
import com.bidover.auto.database.dao.ModificationDAO;
import com.bidover.auto.database.dao.TiresDAO;
import com.bidover.auto.database.dao.TopTypeDAO;
import com.bidover.auto.database.dao.TransmissionDAO;
import com.bidover.auto.database.dao.TrimDAO;
import com.bidover.auto.database.dao.LocationDAO;
import com.bidover.auto.database.dao.ConditionDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nomad
 */
public class ShowPreviewCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public ShowPreviewCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void execute() throws ServletException, IOException {

        String resp = "";
        String makeTxt = request.getParameter("make");
        MakeDAO makeDAO = new MakeDAO();
        resp += "Марка: ";
        resp += makeDAO.findById(Integer.valueOf(makeTxt)).getTitle();
        resp += "<br/>";
        String modelTxt = request.getParameter("model");
        ModelDAO modelDAO = new ModelDAO();
        resp += "Модель: ";
        resp += modelDAO.findById(Integer.valueOf(modelTxt)).getTitle();
        resp += "<br/>";
        resp += "Двигатель: ";
        String engineTxt = request.getParameter("engine");
        EngineDAO engDAO = new EngineDAO();
        Engine eng = new Engine();
        eng.setId(Integer.valueOf(engineTxt));
        resp += engDAO.find(eng).getTitle();
        resp += "<br/>";
        resp += "Пробег: ";
        String odometerTxt = request.getParameter("odometer");
        resp += Integer.valueOf(odometerTxt);
        resp += "<br/>";
        resp += "Trim: ";
        String trimTxt = request.getParameter("trim");
        TrimDAO trimDAO = new TrimDAO();
        Trim trim = new Trim();
        trim.setId(Integer.valueOf(trimTxt));
        resp += trimDAO.find(trim).get(0).getTitle();
        resp += "<br/>";
        resp += "Модификация: ";
        String modificationTxt = request.getParameter("modification");
        ModificationDAO modDAO = new ModificationDAO();
        resp += modDAO.findById(Integer.valueOf(modificationTxt)).getTitle();
        resp += "<br/>";
        resp += "Год: ";
        String yearTxt = request.getParameter("year");
        resp += Integer.valueOf(yearTxt);
        resp += "<br/>";
        resp += "Body Style: ";
        String bodyStyleTxt = request.getParameter("body_style");
        BodyStyleDAO bsDAO = new BodyStyleDAO();
        BodyStyle bStyle = new BodyStyle();
        bStyle.setId(Integer.valueOf(bodyStyleTxt));
        resp += bsDAO.find(bStyle).get(0).getTitle();
        resp += "<br/>";
        resp += "Двери: ";
        String doorTxt = request.getParameter("door");
        DoorDAO doorDAO = new DoorDAO();
        Door door = new Door();
        door.setId(Integer.valueOf(doorTxt));
        resp += doorDAO.find(door).getTitle();
        resp += "<br/>";
        resp += "Подвеска: ";
        String driveTrainTxt = request.getParameter("drive_train");
        DriveTrainDAO dtDAO = new DriveTrainDAO();
        DriveTrain dTrain = new DriveTrain();
        dTrain.setId(Integer.valueOf(driveTrainTxt));
        resp += dtDAO.find(dTrain).get(0).getTitle();
        resp += "<br/>";
        resp += "Exterior Color: ";
        String exteriorColorTxt = request.getParameter("exterior_color");
        ExteriorColor extColor = new ExteriorColor();
        extColor.setId(Integer.valueOf(exteriorColorTxt));
        ExteriorColorDAO extDAO = new ExteriorColorDAO();
        resp += extDAO.find(extColor).get(0).getTitle();
        resp += "<br/>";
        resp += "Interior Color: ";
        String interiorColorTxt = request.getParameter("interior_color");
        InteriorColor intColor = new InteriorColor();
        intColor.setId(Integer.valueOf(interiorColorTxt));
        InteriorColorDAO intDAO = new InteriorColorDAO();
        resp += intDAO.find(intColor).get(0).getTitle();
        resp += "<br/>";
        resp += "Fuel: ";
        String fuelTxt = request.getParameter("fuel");
        Fuel fuel = new Fuel();
        fuel.setId(Integer.valueOf(fuelTxt));
        FuelDAO fuelDAO = new FuelDAO();
        resp += fuelDAO.find(fuel).get(0).getTitle();
        resp += "<br/>";
        resp += "Tires: ";
        String tiresTxt = request.getParameter("tires");
        Tires tire = new Tires();
        tire.setId(Integer.valueOf(tiresTxt));
        TiresDAO tiresDAO = new TiresDAO();
        resp += tiresDAO.find(tire).getTitle();
        resp += "<br/>";
        resp += "Top Type: ";
        String topTypeTxt = request.getParameter("top_type");
        TopType topType = new TopType();
        topType.setId(Integer.valueOf(topTypeTxt));
        TopTypeDAO ttDAO = new TopTypeDAO();
        resp += ttDAO.find(topType).get(0).getTitle();
        resp += "<br/>";
        resp += "Колеса: R";
        String wheelsTxt = request.getParameter("wheels");
        resp += Integer.valueOf(wheelsTxt);
        resp += "<br/>";
        resp += "Трансмиссия: ";
        String transmissionTxt = request.getParameter("transmission");
        Transmission trans = new Transmission();
        trans.setId(Integer.valueOf(transmissionTxt));
        TransmissionDAO transDAO = new TransmissionDAO();
        resp += transDAO.find(trans).getTitle();
        resp += "<br/>";
        resp += "VIN: ";
        resp += request.getParameter("vin");
        resp += "<br/>";
        resp += "Interior Type: ";
        String interiorTypeTxt = request.getParameter("interior_type");
        InteriorType intType = new InteriorType();
        intType.setId(Integer.valueOf(interiorTypeTxt));
        InteriorTypeDAO intTypeDAO = new InteriorTypeDAO();
        resp += intTypeDAO.find(intType).getTitle();
        resp += "<br/>";
        //  resp += "<th/>";
        String hoodTxt = request.getParameter("hood");
        String roofTxt = request.getParameter("roof");
        String windshieldTxt = request.getParameter("windshield");
        String lfDoorTxt = request.getParameter("lf_door");
        String lrDoorTxt = request.getParameter("lr_door");
        String rfDoorTxt = request.getParameter("rf_door");
        String rrDoorTxt = request.getParameter("rr_door");
        String lQtrPanelTxt = request.getParameter("l_qtr_panel");
        String rQtrPanelTxt = request.getParameter("r_qtr_panel");
        String frontBumperTxt = request.getParameter("front_bumper");
        String rearBumperTxt = request.getParameter("rear_bumper");
        String lfFenderTxt = request.getParameter("lf_fender");
        String rfFenderTxt = request.getParameter("rf_fender");
        String deckLidTxt = request.getParameter("deck_lid");
        String seatsTxt = request.getParameter("seats");
        String overallVehicleTxt = request.getParameter("overall_vehicle");

        Damage damage = new Damage();
        if (deckLidTxt != null) {
            damage.setDeckLid(Integer.valueOf(deckLidTxt));
            damage.setFrontBumper(Integer.valueOf(frontBumperTxt));
            damage.setHood(Integer.valueOf(hoodTxt));
            damage.setLQtrPanel(Integer.valueOf(lQtrPanelTxt));
            damage.setLfDoor(Integer.valueOf(lfDoorTxt));
            damage.setLfFender(Integer.valueOf(lfFenderTxt));
            damage.setLrDoor(Integer.valueOf(lrDoorTxt));
            damage.setOverallVehicle(Integer.valueOf(overallVehicleTxt));
            damage.setRQtrPanel(Integer.valueOf(rQtrPanelTxt));
            damage.setRfFender(Integer.valueOf(rfFenderTxt));
            damage.setRearBumper(Integer.valueOf(rearBumperTxt));
            damage.setRfDoor(Integer.valueOf(rfDoorTxt));
            damage.setRoof(Integer.valueOf(roofTxt));
            damage.setRrDoor(Integer.valueOf(rrDoorTxt));
            damage.setSeats(Integer.valueOf(seatsTxt));
            damage.setWindshield(Integer.valueOf(windshieldTxt));
        } else {
            damage.setDeckLid(0);
            damage.setFrontBumper(0);
            damage.setHood(0);
            damage.setLQtrPanel(0);
            damage.setLfDoor(0);
            damage.setLfFender(0);
            damage.setLrDoor(0);
            damage.setOverallVehicle(0);
            damage.setRQtrPanel(0);
            damage.setRfFender(0);
            damage.setRearBumper(0);
            damage.setRfDoor(0);
            damage.setRoof(0);
            damage.setRrDoor(0);
            damage.setSeats(0);
            damage.setWindshield(0);
        }
        resp += "<br/>";
        resp += "<b>Damages: </b>";
        resp += showDamageName(damage.getDeckLidStr(), "deck_lid");
        resp += showDamageName(damage.getHoodStr(), "Hood");
        resp += showDamageName(damage.getLfDoorStr(), "lf_door");
        resp += showDamageName(damage.getLfFenderStr(), "lf_fender");
        resp += showDamageName(damage.getLrDoorStr(), "lr_door");
        resp += showDamageName(damage.getOverallVehicleStr(), "overall_vehicle");
        resp += showDamageName(damage.getRearBumperStr(), "rear_bumper");
        resp += showDamageName(damage.getRfDoorStr(), "rf_door");
        resp += showDamageName(damage.getRfFenderStr(), "rf_fender");
        resp += showDamageName(damage.getRoofStr(), "roof");
        resp += showDamageName(damage.getRrDoorStr(), "rr_door");
        resp += showDamageName(damage.getSeatsStr(), "seats");
        resp += showDamageName(damage.getWindshieldStr(), "windshield");
        resp += showDamageName(damage.getlQtrPanelStr(), "l_qtr_panel");
        resp += showDamageName(damage.getFrontBumperStr(), "front_bumper");
        resp += showDamageName(damage.getrQtrPanelStr(), "r_qtr_panel");

        resp += "<br/>";
        resp += "<br/>";
        resp += "<b>Options: </b>";
        resp += showOptionName(new Integer(request.getParameter("pwr_locks")), "pwr_locks");
        resp += showOptionName(new Integer(request.getParameter("pwr_windows")), "pwr_windows");
        resp += showOptionName(new Integer(request.getParameter("pwr_steering")), "pwr_steering");
        resp += showOptionName(new Integer(request.getParameter("pwr_mirrors")), "pwr_mirrors");
        resp += showOptionName(new Integer(request.getParameter("pwr_seats")), "pwr_seats");
        resp += showOptionName(new Integer(request.getParameter("pwr_seats_drivers_only")), "pwr_seats_drivers_only");
        resp += showOptionName(new Integer(request.getParameter("tilt_steering")), "tilt_steering");
        resp += showOptionName(new Integer(request.getParameter("air_conditioning")), "air_conditioning");
        resp += showOptionName(new Integer(request.getParameter("rear_air_conditioning")), "rear_air_conditioning");
        resp += showOptionName(new Integer(request.getParameter("cruise_ctl")), "cruise_ctl");
        resp += showOptionName(new Integer(request.getParameter("rear_defrost")), "rear_defrost");
        resp += showOptionName(new Integer(request.getParameter("side_air_bags")), "side_air_bags");
        resp += showOptionName(new Integer(request.getParameter("dual_air_bags")), "dual_air_bags");
        resp += showOptionName(new Integer(request.getParameter("trip_counter")), "trip_counter");
        resp += showOptionName(new Integer(request.getParameter("luggage_rack")), "luggage_rack");
        resp += showOptionName(new Integer(request.getParameter("xenon_headlights")), "xenon_headlights");
        resp += showOptionName(new Integer(request.getParameter("cd_player")), "cd_player");
        resp += showOptionName(new Integer(request.getParameter("cd_changer")), "cd_changer");
        resp += showOptionName(new Integer(request.getParameter("rear_wiper")), "rear_wiper");
        resp += showOptionName(new Integer(request.getParameter("floor_mats")), "floor_mats");
        resp += showOptionName(new Integer(request.getParameter("security_system")), "security_system");
        resp += showOptionName(new Integer(request.getParameter("traction_control")), "traction_control");
        resp += showOptionName(new Integer(request.getParameter("rear_tail")), "rear_tail");
        resp += showOptionName(new Integer(request.getParameter("lift_gate")), "lift_gate");
        resp += showOptionName(new Integer(request.getParameter("heated_seats")), "heated_seats");
        resp += showOptionName(new Integer(request.getParameter("tinted_windows")), "tinted_windows");
        resp += showOptionName(new Integer(request.getParameter("homelink")), "homelink");
        resp += showOptionName(new Integer(request.getParameter("tire_pressure_monitor_system")), "tire_pressure_monitor_system");
        resp += showOptionName(new Integer(request.getParameter("steering_wheel_audio_control")), "steering_wheel_audio_control");
        resp += showOptionName(new Integer(request.getParameter("spoiler")), "spoiler");
        resp += showOptionName(new Integer(request.getParameter("bug_deflector")), "bug_deflector");
        resp += showOptionName(new Integer(request.getParameter("keyless_entry")), "keyless_entry");
        resp += showOptionName(new Integer(request.getParameter("hand_tool_kit")), "hand_tool_kit");
        resp += showOptionName(new Integer(request.getParameter("dual_sliding_side_doors")), "dual_sliding_side_doors");
        resp += showOptionName(new Integer(request.getParameter("power_vent_windows")), "power_vent_windows");
        resp += showOptionName(new Integer(request.getParameter("front_center_console")), "front_center_console");
        resp += showOptionName(new Integer(request.getParameter("stow_and_go")), "stow_and_go");
        resp += showOptionName(new Integer(request.getParameter("stow_and_go_3rd_row_only")), "stow_and_go_3rd_row_only");
        resp += showOptionName(new Integer(request.getParameter("wood_trim_dash")), "wood_trim_dash");
        resp += showOptionName(new Integer(request.getParameter("woodgrain_interior_package")), "woodgrain_interior_package");

        Location location = new Location();
        LocationDAO locationDAO = new LocationDAO();
        location = locationDAO.find(request.getParameter("location_code"));
        String full_location = location.getLocation() + " (" + location.getATD() + ", " + location.getCountry() + ")";

        Condition condition = new Condition();
        ConditionDAO conditionDAO = new ConditionDAO();
        condition.setId(Integer.valueOf(request.getParameter("condition")));
        String full_condition = conditionDAO.find(condition).get(0).getTitle();

        resp += "<br/>";
        resp += "<br/>";
        resp += "<b>Sales Details: </b>";
        resp += "<br>Condition: " + full_condition;
        resp += "<br>Sales Duration: " + request.getParameter("sales_duration") + " days";
        resp += "<br>Location: " + full_location;
        resp += "<br>Forbid Bidding: " + (Byte.valueOf(request.getParameter("forbid_bidding"))==1 ? "Yes" : "No");
        resp += "<br>Fixed Price: " + (request.getParameter("fixed_price").equals("") ? 0 : request.getParameter("fixed_price"));
        resp += "<br>Fixed Price Only: " + (Byte.valueOf(request.getParameter("fixed_price_only"))==1 ? "Yes" : "No");
        resp += "<br>Qnt Items Available: " + request.getParameter("qnt_items_available");
        resp += "<br>Floor Price: " + (request.getParameter("floor_price").equals("") ? 0 : request.getParameter("floor_price"));
        resp += "<br>Starting Bid: " + (request.getParameter("starting_bid").equals("") ? 0 : request.getParameter("starting_bid"));
        resp += "<br>Currency: " + request.getParameter("currency_code");
        resp += "<br>Payment Method: " + request.getParameter("payment_method");
        resp += "<br>Payment Instructions: " + request.getParameter("payment_instructions");
        resp += "<br>Free Shipping: " + request.getParameter("free_shipping");
        resp += "<br>Paid Shipping: " + request.getParameter("paid_shipping");
        resp += "<br>Handling Time: " + request.getParameter("handling_time") + " business day(s)";

        response.getWriter().write(resp);
    }

    private String showOptionName(int id, String name) {
        String t = "<br/>" + name + ": ";
        if (id == 0) {
            return "";
        } else if (id == 1) {
            return t + "Да";

        } else {
            return t + "Нет";
        }
    }

    private String showDamageName(String level, String name) {
        String t = "<br/>" + name + ": ";
        if (level.equals("unknown")) { //not specified
            return "";
        } else {
            return t + level;
        }
    }
}
