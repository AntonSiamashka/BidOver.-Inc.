package com.bidover.auto.controller.command;

import com.bidover.auto.model.bean.Auto;
import com.bidover.auto.model.bean.BodyStyle;
import com.bidover.auto.model.bean.Damage;
import com.bidover.auto.model.bean.Door;
import com.bidover.auto.model.bean.DriveTrain;
import com.bidover.auto.model.bean.Engine;
import com.bidover.auto.model.bean.ExteriorColor;
import com.bidover.auto.model.bean.Fuel;
import com.bidover.auto.model.bean.InteriorColor;
import com.bidover.auto.model.bean.InteriorType;
import com.bidover.auto.model.bean.Options;
import com.bidover.auto.model.bean.Tires;
import com.bidover.auto.model.bean.TopType;
import com.bidover.auto.model.bean.Transmission;
import com.bidover.auto.model.bean.Trim;
import com.bidover.auto.model.bean.Wheels;
import com.bidover.auto.database.dao.AutoDAO;
import com.bidover.auto.database.dao.CharacteristicsDAO;
import com.bidover.auto.database.dao.MakeDAO;
import com.bidover.auto.database.dao.ModelDAO;
import com.bidover.auto.database.dao.ModificationDAO;
import com.bidover.auto.model.bean.Characteristics;
import com.bidover.common.controller.command.AddLotCommand;
import com.bidover.common.upload.FileIOSet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddAutoCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public AddAutoCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void execute() throws ServletException, IOException {
        try {
            String makeTxt = request.getParameter("make");
            String modelTxt = request.getParameter("model");
            String modificationTxt = request.getParameter("modification");
            String engineTxt = request.getParameter("engine");
            String odometerTxt = request.getParameter("odometer");
            String trimTxt = request.getParameter("trim");
            String yearTxt = request.getParameter("year");
            String bodyStyleTxt = request.getParameter("body-style");
            String doorTxt = request.getParameter("door");
            String driveTrainTxt = request.getParameter("drive-train");
            String exteriorColorTxt = request.getParameter("exterior-color");
            String interiorColorTxt = request.getParameter("interior-color");
            String fuelTxt = request.getParameter("fuel");
            String tiresTxt = request.getParameter("tires");
            String topTypeTxt = request.getParameter("top-type");
            String wheelsTxt = request.getParameter("wheels");
            String transmissionTxt = request.getParameter("transmission");
            String vinTxt = request.getParameter("vin");
            String interiorTypeTxt = request.getParameter("interior-type");

            String airConditioningTxt = request.getParameter("air_conditioning");
            String pwrLocksTxt = request.getParameter("pwr_locks");
            String pwrWindowsTxt = request.getParameter("pwr_windows");
            String pwrSteeringTxt = request.getParameter("pwr_steering");
            String pwrMirrorsTxt = request.getParameter("pwr_mirrors");
            String pwrSeatsTxt = request.getParameter("pwr_seats");
            String pwrSeatsDriverOnlyTxt = request.getParameter("pwr_seats_drivers_only");
            String tiltSteeringTxt = request.getParameter("tilt_steering");
            String rearAirConditionigTxt = request.getParameter("rear_air_conditioning");
            String cruiseCtlTxt = request.getParameter("cruise_ctl");
            String rearDefrostTxt = request.getParameter("rear_defrost");
            String sideAirBagsTxt = request.getParameter("side_air_bags");
            String dualAirBagsTxt = request.getParameter("dual_air_bags");
            String fogLampsTxt = request.getParameter("fog_lamps");
            String tripCounterTxt = request.getParameter("trip_counter");
            String luggageRackTxt = request.getParameter("luggage_rack");
            String thirdRowSeatTxt = request.getParameter("third_row_seat");
            String xenonHeadlightsTxt = request.getParameter("xenon_headlights");
            String cdPlayerTxt = request.getParameter("cd_player");
            String cdChangerTxt = request.getParameter("cd_changer");
            String rearWiperTxt = request.getParameter("rear_wiper");
            String floorMatsTxt = request.getParameter("floor_mats");
            String securitySystemTxt = request.getParameter("security_system");
            String tractionControlTxt = request.getParameter("traction_control");
            String rearTailTxt = request.getParameter("rear_tail");
            String liftGateTxt = request.getParameter("lift_gate");
            String heatedSeatsTxt = request.getParameter("heated_seats");
            String tintedWindowsTxt = request.getParameter("tinted_windows");
            String homelinkTxt = request.getParameter("homelink");
            String tirePressureMonitorSystemTxt = request.getParameter("tire_pressure_monitor_system");
            String steeringWheelAudioControlTxt = request.getParameter("steering_wheel_audio_control");
            String spoilerTxt = request.getParameter("spoiler");
            String bugDeflectorTxt = request.getParameter("bug_deflector");
            String keyLessEntryTxt = request.getParameter("keyless_entry");
            String handToolKitTxt = request.getParameter("hand_tool_kit");
            String dualSlidingSideDoorsTxt = request.getParameter("dual_sliding_side_doors");
            String powerVentWindowsTxt = request.getParameter("power_vent_windows");
            String frontCenterConsoleTxt = request.getParameter("front_center_console");
            String stowAndGoTxt = request.getParameter("stow_and_go");
            String stowAndGo3rdRowOnlyTxt = request.getParameter("stow_and_go_3rd_row_only");
            String woodTrimDashTxt = request.getParameter("wood_trim_dash");
            String woodgrainInteriorPackageTxt = request.getParameter("woodgrain_interior_package");
//            String salesDuration = request.getParameter("sales_duration");

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


            Options options = new Options();
            if (airConditioningTxt != null) {
                options.setAirConditioning(Byte.valueOf(airConditioningTxt));
                options.setBugDeflector(Byte.valueOf(bugDeflectorTxt));
                options.setCdChanger(Byte.valueOf(cdChangerTxt));
                options.setCdPlayer(Byte.valueOf(cdPlayerTxt));
                options.setCruiseCtrl(Byte.valueOf(cruiseCtlTxt));
                options.setDualAirBags(Byte.valueOf(dualAirBagsTxt));
                options.setDualSlidingSideDoors(Byte.valueOf(dualSlidingSideDoorsTxt));
                options.setFloorMats(Byte.valueOf(floorMatsTxt));
                options.setFogLamps(Byte.valueOf(fogLampsTxt));
                options.setFrontCenterConsole(Byte.valueOf(frontCenterConsoleTxt));
                options.setHandToolKit(Byte.valueOf(handToolKitTxt));
                options.setHeatedSeats(Byte.valueOf(heatedSeatsTxt));
                options.setHomeLink(Byte.valueOf(homelinkTxt));
                options.setKeylessEntry(Byte.valueOf(keyLessEntryTxt));
                options.setLiftGate(Byte.valueOf(liftGateTxt));
                options.setLuggageRack(Byte.valueOf(luggageRackTxt));
                options.setPowerVentWindows(Byte.valueOf(powerVentWindowsTxt));
                options.setPwrLocks(Byte.valueOf(pwrLocksTxt));
                options.setPwrMirrors(Byte.valueOf(pwrMirrorsTxt));
                options.setPwrSeats(Byte.valueOf(pwrSeatsTxt));
                options.setPwrSeatsDriversOnly(Byte.valueOf(pwrSeatsDriverOnlyTxt));
                options.setPwrSteering(Byte.valueOf(pwrSteeringTxt));
                options.setPwrWindows(Byte.valueOf(pwrWindowsTxt));
                options.setRearAirConditioning(Byte.valueOf(rearAirConditionigTxt));
                options.setRearDefrost(Byte.valueOf(rearDefrostTxt));
                options.setRearTail(Byte.valueOf(rearTailTxt));
                options.setRearWiper(Byte.valueOf(rearWiperTxt));
                options.setSecuritySystem(Byte.valueOf(securitySystemTxt));
                options.setSideAirBags(Byte.valueOf(sideAirBagsTxt));
                options.setSpoiler(Byte.valueOf(spoilerTxt));
                options.setSteeringWheelAudioControl(Byte.valueOf(steeringWheelAudioControlTxt));
                options.setStowAndGo(Byte.valueOf(stowAndGoTxt));
                options.setStowAndGoThirdRowOnly(Byte.valueOf(stowAndGo3rdRowOnlyTxt));
                options.setThirdRowSeat(Byte.valueOf(thirdRowSeatTxt));
                options.setTiltSteering(Byte.valueOf(tiltSteeringTxt));
                options.setTintedWindows(Byte.valueOf(tintedWindowsTxt));
                options.setTirePressureMonitorSystem(Byte.valueOf(tirePressureMonitorSystemTxt));
                options.setTractionControl(Byte.valueOf(tractionControlTxt));
                options.setTripCounter(Byte.valueOf(tripCounterTxt));
                options.setWoodTrimDash(Byte.valueOf(woodTrimDashTxt));
                options.setWoodgrainInteriorPackage(Byte.valueOf(woodgrainInteriorPackageTxt));
                options.setXenonHeadlights(Byte.valueOf(xenonHeadlightsTxt));
            } else {
                options.setAirConditioning(Byte.valueOf("0"));
                options.setBugDeflector(Byte.valueOf("0"));
                options.setCdChanger(Byte.valueOf("0"));
                options.setCdPlayer(Byte.valueOf("0"));
                options.setCruiseCtrl(Byte.valueOf("0"));
                options.setDualAirBags(Byte.valueOf("0"));
                options.setDualSlidingSideDoors(Byte.valueOf("0"));
                options.setFloorMats(Byte.valueOf("0"));
                options.setFogLamps(Byte.valueOf("0"));
                options.setFrontCenterConsole(Byte.valueOf("0"));
                options.setHandToolKit(Byte.valueOf("0"));
                options.setHeatedSeats(Byte.valueOf("0"));
                options.setHomeLink(Byte.valueOf("0"));
                options.setKeylessEntry(Byte.valueOf("0"));
                options.setLiftGate(Byte.valueOf("0"));
                options.setLuggageRack(Byte.valueOf("0"));
                options.setPowerVentWindows(Byte.valueOf("0"));
                options.setPwrLocks(Byte.valueOf("0"));
                options.setPwrMirrors(Byte.valueOf("0"));
                options.setPwrSeats(Byte.valueOf("0"));
                options.setPwrSeatsDriversOnly(Byte.valueOf("0"));
                options.setPwrSteering(Byte.valueOf("0"));
                options.setPwrWindows(Byte.valueOf("0"));
                options.setRearAirConditioning(Byte.valueOf("0"));
                options.setRearDefrost(Byte.valueOf("0"));
                options.setRearTail(Byte.valueOf("0"));
                options.setRearWiper(Byte.valueOf("0"));
                options.setSecuritySystem(Byte.valueOf("0"));
                options.setSideAirBags(Byte.valueOf("0"));
                options.setSpoiler(Byte.valueOf("0"));
                options.setSteeringWheelAudioControl(Byte.valueOf("0"));
                options.setStowAndGo(Byte.valueOf("0"));
                options.setStowAndGoThirdRowOnly(Byte.valueOf("0"));
                options.setThirdRowSeat(Byte.valueOf("0"));
                options.setTiltSteering(Byte.valueOf("0"));
                options.setTintedWindows(Byte.valueOf("0"));
                options.setTirePressureMonitorSystem(Byte.valueOf("0"));
                options.setTractionControl(Byte.valueOf("0"));
                options.setTripCounter(Byte.valueOf("0"));
                options.setWoodTrimDash(Byte.valueOf("0"));
                options.setWoodgrainInteriorPackage(Byte.valueOf("0"));
                options.setXenonHeadlights(Byte.valueOf("0"));
            }

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

            /////////////////////////////проверка!!!!!!!!!!!!!!!!!!


            BodyStyle bodyStyle = new BodyStyle();
            bodyStyle.setId(Integer.valueOf(bodyStyleTxt));
            DriveTrain driveTrain = new DriveTrain();
            driveTrain.setId(Integer.valueOf(driveTrainTxt));
            ExteriorColor exteriorColor = new ExteriorColor();
            exteriorColor.setId(Integer.valueOf(exteriorColorTxt));
            Fuel fuel = new Fuel();
            fuel.setId(Integer.valueOf(fuelTxt));
            InteriorColor interiorColor = new InteriorColor();
            interiorColor.setId(Integer.valueOf(interiorColorTxt));
            Tires tires = new Tires();
            tires.setId(Integer.valueOf(tiresTxt));
            TopType topType = new TopType();
            topType.setId(Integer.valueOf(topTypeTxt));
            Wheels wheels = new Wheels();
            wheels.setId(Integer.valueOf(wheelsTxt));
            Door door = new Door();
            door.setId(Integer.valueOf(doorTxt));
            Engine engine = new Engine();
            engine.setId(Integer.valueOf(engineTxt));
            Transmission transmission = new Transmission();
            transmission.setId(Integer.valueOf(transmissionTxt));
            InteriorType interiorType = new InteriorType();
            interiorType.setId(Integer.valueOf(interiorTypeTxt));
            Trim trim = new Trim();
            trim.setId(Integer.valueOf(trimTxt));




            Auto auto = new Auto();
            auto.setIdBodyStyle(bodyStyle);
            auto.setIdDriveTrain(driveTrain);
            auto.setIdExteriorColor(exteriorColor);
            auto.setIdFuel(fuel);
            auto.setIdInteriorColor(interiorColor);

            Characteristics characteristics = new Characteristics();

            characteristics.setMake(makeTxt);
            characteristics.setModel(modelTxt);
            characteristics.setModification(modificationTxt);

            CharacteristicsDAO characteristicsDAO = new CharacteristicsDAO();
            Characteristics character = characteristicsDAO.findByMarkModelModufication(characteristics);
            auto.setCharacteristics(character);

            auto.setIdOptions(options);
            auto.setIdTires(tires);
            auto.setIdTopType(topType);
            auto.setIdWheels(wheels);
            auto.setDoors(door);
            auto.setEngine(engine);
            auto.setInteriorType(interiorType);
            auto.setTransmission(transmission);
            auto.setOdometer(Integer.valueOf(odometerTxt));
            auto.setVin(vinTxt);
            auto.setYear(Integer.valueOf(yearTxt));
            auto.setTrim(trim);
            auto.setDamage(damage);
            AddLotCommand addLotCommand = new AddLotCommand();
            Integer lotId = addLotCommand.addLot(request, response);
            auto.setId(lotId);
            AutoDAO autoDAO = new AutoDAO();
//            if (!autoDAO.isVINExist(vinTxt)) {
            Integer autoId = autoDAO.add(auto);
            characteristicsDAO.increment(character.getId(), makeTxt, modelTxt);
            // MAKE IMAGE DIRECTORY
            FileIOSet fio = new FileIOSet();
            fio.mkDir(autoId.toString());

            // CREATE BIDHISTORY FILE
//		String fileName = autoId+"/bidhistory.txt";
//		fio.appendStringToFile(fileName, "" + millisSaleClosing);
//		fio.createNewEmptyFile(fileName);

            MakeDAO makeDAO = new MakeDAO();
            ModelDAO modelDAO = new ModelDAO();
            ModificationDAO modificationDAO = new ModificationDAO();
            response.sendRedirect("./addImage.jsp?id=" + autoId);


        ///  makeDAO.increment(Integer.valueOf(makeTxt));
        ///   modelDAO.increment(Integer.valueOf(modelTxt));
        ///   modificationDAO.increment(Integer.valueOf(modificationTxt));
        //           } else {
        //             response.sendRedirect("./addAuto.jsp");
        //       }
        } catch (NumberFormatException ex) {
            response.sendRedirect("./addAuto.jsp");
        //request.getRequestDispatcher("Controller?command=SHOW_SEARCH").forward(request, response);
        }
    }
}