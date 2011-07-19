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
import com.bidover.auto.model.bean.Wheels;
import com.bidover.auto.database.dao.AutoDAO;
import com.bidover.auto.database.dao.CharacteristicsDAO;
import com.bidover.auto.database.dao.MakeDAO;
import com.bidover.auto.database.dao.ModelDAO;
import com.bidover.auto.database.dao.ModificationDAO;
import com.bidover.auto.model.bean.Characteristics;
import com.bidover.auto.model.bean.CountryAssembly;
import com.bidover.common.controller.command.AddLotCommand;
import com.bidover.common.upload.FileIOSet;
import com.bidover.util.IntegerUtil;
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
            String displacementTxt = request.getParameter("displacement");
            String salvageTxt = request.getParameter("salvage");
            String countryAssemblyTxt = request.getParameter("country_assembly");
            //String trimTxt = request.getParameter("trim");
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
            if("on".equals(airConditioningTxt)) {
                options.setAirConditioning(true);
            } else {
                options.setAirConditioning(false);
            }
            if("on".equals(bugDeflectorTxt)) {
                options.setBugDeflector(true);
            } else {
                options.setBugDeflector(false);
            }
            if("on".equals(cdChangerTxt)) {
                options.setCdChanger(true);
            } else {
                options.setCdChanger(false);
            }
            if("on".equals(cdPlayerTxt)) {
                options.setCdPlayer(true);
            } else {
                options.setCdPlayer(false);
            }
            if("on".equals(cruiseCtlTxt)) {
                options.setCruiseCtrl(true);
            } else {
                options.setCruiseCtrl(false);
            }
            if("on".equals(dualAirBagsTxt)) {
                options.setDualAirBags(true);
            } else {
                options.setDualAirBags(false);
            }
            if("on".equals(dualSlidingSideDoorsTxt)) {
                options.setDualSlidingSideDoors(true);
            } else {
                options.setDualSlidingSideDoors(false);
            }
            if("on".equals(floorMatsTxt)) {
                options.setFloorMats(true);
            } else {
                options.setFloorMats(false);
            }
            if("on".equals(frontCenterConsoleTxt)) {
                options.setFrontCenterConsole(true);
            } else {
                options.setFrontCenterConsole(false);
            }
            if("on".equals(handToolKitTxt)) {
                options.setHandToolKit(true);
            } else {
                options.setHandToolKit(false);
            }
            if("on".equals(heatedSeatsTxt)) {
                options.setHeatedSeats(true);
            } else {
                options.setHeatedSeats(false);
            }
            if("on".equals(homelinkTxt)) {
                options.setHomeLink(true);
            } else {
                options.setHomeLink(false);
            }
            if("on".equals(keyLessEntryTxt)) {
                options.setKeylessEntry(true);
            } else {
                options.setKeylessEntry(false);
            }
            if("on".equals(liftGateTxt)) {
                options.setLiftGate(true);
            } else {
                options.setLiftGate(false);
            }
            if("on".equals(luggageRackTxt)) {
                options.setLuggageRack(true);
            } else {
                options.setLuggageRack(false);
            }
            if("on".equals(powerVentWindowsTxt)) {
                options.setPowerVentWindows(true);
            } else {
                options.setPowerVentWindows(false);
            }
            if("on".equals(pwrLocksTxt)) {
                options.setPwrLocks(true);
            } else {
                options.setPwrLocks(false);
            }
            if("on".equals(pwrMirrorsTxt)) {
                options.setPwrMirrors(true);
            } else {
                options.setPwrMirrors(false);
            }
            if("on".equals(pwrSeatsTxt)) {
                options.setPwrSeats(true);
            } else {
                options.setPwrSeats(false);
            }
            if("on".equals(pwrSeatsDriverOnlyTxt)) {
                options.setPwrSeatsDriversOnly(true);
            } else {
                options.setPwrSeatsDriversOnly(false);
            }
            if("on".equals(pwrSteeringTxt)) {
                options.setPwrSteering(true);
            } else {
                options.setPwrSteering(false);
            }
            if("on".equals(pwrWindowsTxt)) {
                options.setPwrWindows(true);
            } else {
                options.setPwrWindows(false);
            }
            if("on".equals(rearAirConditionigTxt)) {
                options.setRearAirConditioning(true);
            } else {
                options.setRearAirConditioning(false);
            }
            if("on".equals(rearDefrostTxt)) {
                options.setRearDefrost(true);
            } else {
                options.setRearDefrost(false);
            }
            if("on".equals(rearTailTxt)) {
                options.setRearTail(true);
            } else {
                options.setRearTail(false);
            }
            if("on".equals(rearWiperTxt)) {
                options.setRearWiper(true);
            } else {
                options.setRearWiper(false);
            }
            if("on".equals(securitySystemTxt)) {
                options.setSecuritySystem(true);
            } else {
                options.setSecuritySystem(false);
            }
            if("on".equals(sideAirBagsTxt)) {
                options.setSideAirBags(true);
            } else {
                options.setSideAirBags(false);
            }
            if("on".equals(spoilerTxt)) {
                options.setSpoiler(true);
            } else {
                options.setSpoiler(false);
            }
            if("on".equals(steeringWheelAudioControlTxt)) {
                options.setSteeringWheelAudioControl(true);
            } else {
                options.setSteeringWheelAudioControl(false);
            }
            if("on".equals(stowAndGoTxt)) {
                options.setStowAndGo(true);
            } else {
                options.setStowAndGo(false);
            }
            if("on".equals(stowAndGo3rdRowOnlyTxt)) {
                options.setStowAndGoThirdRowOnly(true);
            } else {
                options.setStowAndGoThirdRowOnly(false);
            }
            if("on".equals(thirdRowSeatTxt)) {
                options.setThirdRowSeat(true);
            } else {
                options.setThirdRowSeat(false);
            }
            if("on".equals(tiltSteeringTxt)) {
                options.setTiltSteering(true);
            } else {
                options.setTiltSteering(false);
            }
            if("on".equals(tintedWindowsTxt)) {
                options.setTintedWindows(true);
            } else {
                options.setTintedWindows(false);
            }
            if("on".equals(tirePressureMonitorSystemTxt)) {
                options.setTirePressureMonitorSystem(true);
            } else {
                options.setTirePressureMonitorSystem(false);
            }
            if("on".equals(tractionControlTxt)) {
                options.setTractionControl(true);
            } else {
                options.setTractionControl(false);
            }
            if("on".equals(tripCounterTxt)) {
                options.setTripCounter(true);
            } else {
                options.setTripCounter(false);
            }
            if("on".equals(woodTrimDashTxt)) {
                options.setWoodTrimDash(true);
            } else {
                options.setWoodTrimDash(false);
            }
            if("on".equals(woodgrainInteriorPackageTxt)) {
                options.setWoodgrainInteriorPackage(true);
            } else {
                options.setWoodgrainInteriorPackage(false);
            }
            if("on".equals(xenonHeadlightsTxt)) {
                options.setXenonHeadlights(true);
            } else {
                options.setXenonHeadlights(false);
            }
            if("on".equals(fogLampsTxt)) {
                options.setFogLamps(true);
            } else {
                options.setFogLamps(false);
            }
            Damage damage = new Damage();
            if (deckLidTxt != null) {
                if(!deckLidTxt.isEmpty()){
                    damage.setDeckLid(IntegerUtil.parseString(deckLidTxt));
                } else {
                    damage.setDeckLid(null);
                }
                if(!frontBumperTxt.isEmpty()){
                    damage.setFrontBumper(IntegerUtil.parseString(frontBumperTxt));
                } else {
                    damage.setFrontBumper(null);
                }
                if(!hoodTxt.isEmpty()){
                    damage.setHood(IntegerUtil.parseString(hoodTxt));
                } else {
                    damage.setHood(null);
                }
                if(!lQtrPanelTxt.isEmpty()){
                    damage.setDeckLid(IntegerUtil.parseString(lQtrPanelTxt));
                } else {
                    damage.setDeckLid(null);
                }
                if(!lfDoorTxt.isEmpty()){
                    damage.setLfDoor(IntegerUtil.parseString(lfDoorTxt));
                } else {
                    damage.setLfDoor(null);
                }
                if(!lfFenderTxt.isEmpty()){
                    damage.setLfFender(IntegerUtil.parseString(lfFenderTxt));
                } else {
                    damage.setLfFender(null);
                }
                if(!lrDoorTxt.isEmpty()){
                    damage.setLrDoor(IntegerUtil.parseString(lrDoorTxt));
                } else {
                    damage.setLrDoor(null);
                }
                if(!overallVehicleTxt.isEmpty()){
                    damage.setOverallVehicle(IntegerUtil.parseString(overallVehicleTxt));
                } else {
                    damage.setOverallVehicle(null);
                }
                if(!rQtrPanelTxt.isEmpty()){
                    damage.setRQtrPanel(IntegerUtil.parseString(rQtrPanelTxt));
                } else {
                    damage.setRQtrPanel(null);
                }
                if(!rfFenderTxt.isEmpty()){
                    damage.setRfFender(IntegerUtil.parseString(rfFenderTxt));
                } else {
                    damage.setRfFender(null);
                }
                if(!rearBumperTxt.isEmpty()){
                    damage.setRearBumper(IntegerUtil.parseString(rearBumperTxt));
                } else {
                    damage.setRearBumper(null);
                }
                if(!rfDoorTxt.isEmpty()){
                    damage.setRfDoor(IntegerUtil.parseString(rfDoorTxt));
                } else {
                    damage.setRfDoor(null);
                }
                if(!roofTxt.isEmpty()){
                    damage.setRoof(IntegerUtil.parseString(roofTxt));
                } else {
                    damage.setRoof(null);
                }
                if(!rrDoorTxt.isEmpty()){
                    damage.setRrDoor(IntegerUtil.parseString(rrDoorTxt));
                } else {
                    damage.setRrDoor(null);
                }
                if(!seatsTxt.isEmpty()){
                    damage.setSeats(IntegerUtil.parseString(seatsTxt));
                } else {
                    damage.setSeats(null);
                }
                if(!windshieldTxt.isEmpty()){
                    damage.setWindshield(IntegerUtil.parseString(windshieldTxt));
                } else {
                    damage.setWindshield(null);
                }
            }

            /////////////////////////////проверка!!!!!!!!!!!!!!!!!!


            BodyStyle bodyStyle = new BodyStyle();
            bodyStyle.setId(IntegerUtil.parseString(bodyStyleTxt));
            DriveTrain driveTrain = new DriveTrain();
            driveTrain.setId(IntegerUtil.parseString(driveTrainTxt));
            ExteriorColor exteriorColor = new ExteriorColor();
            exteriorColor.setId(IntegerUtil.parseString(exteriorColorTxt));
            Fuel fuel = new Fuel();
            fuel.setId(IntegerUtil.parseString(fuelTxt));
            InteriorColor interiorColor = new InteriorColor();
            interiorColor.setId(IntegerUtil.parseString(interiorColorTxt));
            CountryAssembly countryAssembley = new CountryAssembly();
            countryAssembley.setId(IntegerUtil.parseString(countryAssemblyTxt));
            Tires tires = new Tires();
            tires.setId(IntegerUtil.parseString(tiresTxt));
            TopType topType = new TopType();
            topType.setId(IntegerUtil.parseString(topTypeTxt));
            Wheels wheels = new Wheels();
            wheels.setId(IntegerUtil.parseString(wheelsTxt));
            Door door = new Door();
            door.setId(IntegerUtil.parseString(doorTxt));
            Engine engine = new Engine();
            engine.setId(IntegerUtil.parseString(engineTxt));
            Transmission transmission = new Transmission();
            transmission.setId(IntegerUtil.parseString(transmissionTxt));
            InteriorType interiorType = new InteriorType();
            interiorType.setId(IntegerUtil.parseString(interiorTypeTxt));
//            Trim trim = new Trim();
//            trim.setId(IntegerUtil.parseString(trimTxt));




            Auto auto = new Auto();
            auto.setIdBodyStyle(bodyStyle);
            auto.setIdDriveTrain(driveTrain);
            auto.setIdExteriorColor(exteriorColor);
            auto.setIdFuel(fuel);
            auto.setIdInteriorColor(interiorColor);
            auto.setCountryAssembly(countryAssembley);
            if("on".equals(salvageTxt)) {
                auto.setSalvage(true);
            } else {
                auto.setSalvage(false);
            }
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
            auto.setOdometer(IntegerUtil.parseString(odometerTxt));
            auto.setDisplacement(IntegerUtil.parseString(displacementTxt));
            auto.setVin(vinTxt);
            auto.setYear(IntegerUtil.parseString(yearTxt));
            //auto.setTrim(trim);
            auto.setDamage(damage);
            AddLotCommand addLotCommand = new AddLotCommand();
            addLotCommand.addLot(request, response, auto);
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


        ///  makeDAO.increment(IntegerUtil.parseString(makeTxt));
        ///   modelDAO.increment(IntegerUtil.parseString(modelTxt));
        ///   modificationDAO.increment(IntegerUtil.parseString(modificationTxt));
        //           } else {
        //             response.sendRedirect("./addAuto.jsp");
        //       }
        } catch (NumberFormatException ex) {
            response.sendRedirect("./addAuto.jsp");
        //request.getRequestDispatcher("Controller?command=SHOW_SEARCH").forward(request, response);
        }
    }
}