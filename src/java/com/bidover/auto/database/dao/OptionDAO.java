/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.database.dao;

import com.bidover.auto.model.bean.Options;
import com.bidover.common.database.dao.BaseDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import com.bidover.common.logger.Logger;

/**
 *
 * @author Jedai
 */
public class OptionDAO extends BaseDAO {

    public OptionDAO() {
        super();
    }

    public int add(Options option) {
        int id = -1;
        ResultSet resultSet = null;
        try {
            List<Options> options = findByAllOption(option);
            if (options == null) {

                String s = "INSERT INTO bidover_db.options("
                        + "air_conditioning,"
                        + "bug_deflector,"
                        + "cd_changer,"
                        + "cd_player,"
                        + "cruise_ctrl,"
                        + "dual_air_bags,"
                        + "dual_sliding_side_doors,"
                        + "floor_mats,"
                        + "fog_lamps,"
                        + "front_center_console,"
                        + "hand_tool_kit,"
                        + "heated_seats,"
                        + "home_link,"
                        + "keyless_entry,"
                        + "lift_gate,"
                        + "luggage_rack,"
                        + "power_vent_windows,"
                        + "pwr_locks,"
                        + "pwr_mirrors,"
                        + "pwr_seats,"
                        + "pwr_seats_driver,"
                        + "pwr_steering,"
                        + "pwr_windows,"
                        + "rear_air_conditioning,"
                        + "rear_defrost,"
                        + "rear_tail,"
                        + "rear_wiper,"
                        + "security_system,"
                        + "side_air_bags,"
                        + "spoiler,"
                        + "steering_wheel_audio_ctrl,"
                        + "stow_go,"
                        + "stow_go_third_row_only,"
                        + "third_row_seat,"
                        + "tilt_steering,"
                        + "tinted_windows,"
                        + "tire_pressure_monitor_system,"
                        + "traction_ctrl,"
                        + "trip_counter,"
                        + "wood_trim_dash,"
                        + "woodgrain_interior_package,"
                        + "xenon_headlights"
                        + ") VALUES(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?)";

                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement(s, PreparedStatement.RETURN_GENERATED_KEYS);
                peparedStatement.setBoolean(1, option.getAirConditioning());
                peparedStatement.setBoolean(1, option.getBugDeflector());
                peparedStatement.setBoolean(2, option.getCdChanger());
                peparedStatement.setBoolean(3, option.getCdPlayer());
                peparedStatement.setBoolean(4, option.getCruiseCtrl());
                peparedStatement.setBoolean(5, option.getDualAirBags());
                peparedStatement.setBoolean(6, option.getDualSlidingSideDoors());
                peparedStatement.setBoolean(7, option.getFloorMats());
                peparedStatement.setBoolean(8, option.getFogLamps());
                peparedStatement.setBoolean(9, option.getFrontCenterConsole());
                peparedStatement.setBoolean(10, option.getHandToolKit());
                peparedStatement.setBoolean(11, option.getHeatedSeats());
                peparedStatement.setBoolean(12, option.getHomeLink());
                peparedStatement.setBoolean(13, option.getKeylessEntry());
                peparedStatement.setBoolean(14, option.getLiftGate());
                peparedStatement.setBoolean(15, option.getLuggageRack());
                peparedStatement.setBoolean(16, option.getPowerVentWindows());
                peparedStatement.setBoolean(17, option.getPwrLocks());
                peparedStatement.setBoolean(18, option.getPwrMirrors());
                peparedStatement.setBoolean(19, option.getPwrSeats());
                peparedStatement.setBoolean(20, option.getPwrSeatsDriversOnly());
                peparedStatement.setBoolean(21, option.getPwrSteering());
                peparedStatement.setBoolean(22, option.getPwrWindows());
                peparedStatement.setBoolean(23, option.getRearAirConditioning());
                peparedStatement.setBoolean(24, option.getRearDefrost());
                peparedStatement.setBoolean(25, option.getRearTail());
                peparedStatement.setBoolean(26, option.getRearWiper());
                peparedStatement.setBoolean(27, option.getSecuritySystem());
                peparedStatement.setBoolean(28, option.getSideAirBags());
                peparedStatement.setBoolean(29, option.getSpoiler());
                peparedStatement.setBoolean(30, option.getSteeringWheelAudioControl());
                peparedStatement.setBoolean(31, option.getStowAndGo());
                peparedStatement.setBoolean(32, option.getStowAndGoThirdRowOnly());
                peparedStatement.setBoolean(33, option.getThirdRowSeat());
                peparedStatement.setBoolean(34, option.getTiltSteering());
                peparedStatement.setBoolean(35, option.getTintedWindows());
                peparedStatement.setBoolean(36, option.getTirePressureMonitorSystem());
                peparedStatement.setBoolean(37, option.getTractionControl());
                peparedStatement.setBoolean(38, option.getTripCounter());
                peparedStatement.setBoolean(39, option.getWoodTrimDash());
                peparedStatement.setBoolean(40, option.getWoodgrainInteriorPackage());
                peparedStatement.setBoolean(41, option.getXenonHeadlights());
                peparedStatement.execute();
                resultSet = peparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }
                closeAll(resultSet, peparedStatement, connection);
            } else {
                id = options.get(0).getId();
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(OptionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;
    }

    public List<Options> find(Options option) {
        List<Options> options = null;
        String dbRequest = createDBRequest(option, false);
        if (!dbRequest.isEmpty()) {
            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.options WHERE " + dbRequest);
                ResultSet resultSet = peparedStatement.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.isFirst()) {
                        options = new ArrayList<Options>();
                    }
                    options.add(createOption(resultSet));
                }
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return options;
    }

    public List<Options> findByAllOption(Options option) {
        List<Options> options = null;
        String dbRequest = createDBRequest(option, true);
        if (!dbRequest.isEmpty()) {
            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.options WHERE " + dbRequest);
                ResultSet resultSet = peparedStatement.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.isFirst()) {
                        options = new ArrayList<Options>();
                    }
                    options.add(createOption(resultSet));
                }
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return options;
    }

    public boolean update(Options curOption, Options newOption) {
        boolean updFlag = false;
        String dbRequest = createDBRequest(curOption, false);
        /*if (newOption != null && newOption.getTitle() != null && !dbRequest.isEmpty()) {
        try {
        executeRequest("UPDATE bidover_db.make SET title='" + newOption.getTitle() + "' WHERE " + dbRequest);
        updFlag = true;
        } catch (SQLException ex) {
        Logger.log(ex);
        }
        }*/
        return updFlag;
    }

    public void delete(Options option) {
        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("");
            peparedStatement.executeQuery();

            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.log(ex);
        }
    }

    public Options createOption(ResultSet resultSet) throws SQLException {
        Options option = new Options();

        String currentString = resultSet.getString("options.id");
        if (currentString != null) {
            option.setId(Integer.valueOf(currentString));
        } else {
            option.setId(null);
        }
        currentString = resultSet.getString("options.air_conditioning");
        if (currentString != null) {
            option.setAirConditioning(Boolean.valueOf(currentString));
        } else {
            option.setAirConditioning(null);
        }

        currentString = resultSet.getString("options.bug_deflector");
        if (currentString != null) {
            option.setBugDeflector(Boolean.valueOf(currentString));
        } else {
            option.setBugDeflector(null);
        }

        currentString = resultSet.getString("options.cd_changer");
        if (currentString != null) {
            option.setCdChanger(Boolean.valueOf(currentString));
        } else {
            option.setCdChanger(null);
        }

        currentString = resultSet.getString("options.cd_player");
        if (currentString != null) {
            option.setCdPlayer(Boolean.valueOf(currentString));
        } else {
            option.setCdPlayer(null);
        }

        currentString = resultSet.getString("options.cruise_ctrl");
        if (currentString != null) {
            option.setCruiseCtrl(Boolean.valueOf(currentString));
        } else {
            option.setCruiseCtrl(null);
        }

        currentString = resultSet.getString("options.dual_air_bags");
        if (currentString != null) {
            option.setDualAirBags(Boolean.valueOf(currentString));
        } else {
            option.setDualAirBags(null);
        }

        currentString = resultSet.getString("options.dual_sliding_side_doors");
        if (currentString != null) {
            option.setDualSlidingSideDoors(Boolean.valueOf(currentString));
        } else {
            option.setDualSlidingSideDoors(null);
        }

        currentString = resultSet.getString("options.floor_mats");
        if (currentString != null) {
            option.setFloorMats(Boolean.valueOf(currentString));
        } else {
            option.setFloorMats(null);
        }
        currentString = resultSet.getString("options.fog_lamps");
        if (currentString != null) {
            option.setFogLamps(Boolean.valueOf(currentString));
        } else {
            option.setFogLamps(null);
        }
        currentString = resultSet.getString("options.front_center_console");
        if (currentString != null) {
            option.setFrontCenterConsole(Boolean.valueOf(currentString));
        } else {
            option.setFrontCenterConsole(null);
        }
        currentString = resultSet.getString("options.hand_tool_kit");
        if (currentString != null) {
            option.setHandToolKit(Boolean.valueOf(currentString));
        } else {
            option.setHandToolKit(null);
        }

        currentString = resultSet.getString("options.heated_seats");
        if (currentString != null) {
            option.setHeatedSeats(Boolean.valueOf(currentString));
        } else {
            option.setHeatedSeats(null);
        }

        currentString = resultSet.getString("options.home_link");
        if (currentString != null) {
            option.setHomeLink(Boolean.valueOf(currentString));
        } else {
            option.setHomeLink(null);
        }

        currentString = resultSet.getString("options.keyless_entry");
        if (currentString != null) {
            option.setKeylessEntry(Boolean.valueOf(currentString));
        } else {
            option.setKeylessEntry(null);
        }

        currentString = resultSet.getString("options.lift_gate");
        if (currentString != null) {
            option.setLiftGate(Boolean.valueOf(currentString));
        } else {
            option.setLiftGate(null);
        }

        currentString = resultSet.getString("options.luggage_rack");
        if (currentString != null) {
            option.setLuggageRack(Boolean.valueOf(currentString));
        } else {
            option.setLuggageRack(null);
        }

        currentString = resultSet.getString("options.power_vent_windows");
        if (currentString != null) {
            option.setPowerVentWindows(Boolean.valueOf(currentString));
        } else {
            option.setPowerVentWindows(null);
        }

        currentString = resultSet.getString("options.pwr_locks");
        if (currentString != null) {
            option.setPwrLocks(Boolean.valueOf(currentString));
        } else {
            option.setPwrLocks(null);
        }

        currentString = resultSet.getString("options.pwr_mirrors");
        if (currentString != null) {
            option.setPwrMirrors(Boolean.valueOf(currentString));
        } else {
            option.setPwrMirrors(null);
        }

        currentString = resultSet.getString("options.pwr_seats");
        if (currentString != null) {
            option.setPwrSeats(Boolean.valueOf(currentString));
        } else {
            option.setPwrSeats(null);
        }

        currentString = resultSet.getString("options.pwr_seats_driver");
        if (currentString != null) {
            option.setPwrSeatsDriversOnly(Boolean.valueOf(currentString));
        } else {
            option.setPwrSeatsDriversOnly(null);
        }

        currentString = resultSet.getString("options.pwr_steering");
        if (currentString != null) {
            option.setPwrSteering(Boolean.valueOf(currentString));
        } else {
            option.setPwrSteering(null);
        }

        currentString = resultSet.getString("options.pwr_windows");
        if (currentString != null) {
            option.setPwrWindows(Boolean.valueOf(currentString));
        } else {
            option.setPwrWindows(null);
        }

        currentString = resultSet.getString("options.rear_air_conditioning");
        if (currentString != null) {
            option.setRearAirConditioning(Boolean.valueOf(currentString));
        } else {
            option.setRearAirConditioning(null);
        }

        currentString = resultSet.getString("options.rear_defrost");
        if (currentString != null) {
            option.setRearDefrost(Boolean.valueOf(currentString));
        } else {
            option.setRearDefrost(null);
        }

        currentString = resultSet.getString("options.rear_tail");
        if (currentString != null) {
            option.setRearTail(Boolean.valueOf(currentString));
        } else {
            option.setRearTail(null);
        }

        currentString = resultSet.getString("options.rear_wiper");
        if (currentString != null) {
            option.setRearWiper(Boolean.valueOf(currentString));
        } else {
            option.setRearWiper(null);
        }
        currentString = resultSet.getString("options.security_system");
        if (currentString != null) {
            option.setSecuritySystem(Boolean.valueOf(currentString));
        } else {
            option.setSecuritySystem(null);
        }

        currentString = resultSet.getString("options.side_air_bags");
        if (currentString != null) {
            option.setSideAirBags(Boolean.valueOf(currentString));
        } else {
            option.setSideAirBags(null);
        }

        currentString = resultSet.getString("options.spoiler");
        if (currentString != null) {
            option.setSpoiler(Boolean.valueOf(currentString));
        } else {
            option.setSpoiler(null);
        }

        currentString = resultSet.getString("options.steering_wheel_audio_ctrl");
        if (currentString != null) {
            option.setSteeringWheelAudioControl(Boolean.valueOf(currentString));
        } else {
            option.setSteeringWheelAudioControl(null);
        }

        currentString = resultSet.getString("options.stow_go");
        if (currentString != null) {
            option.setStowAndGo(Boolean.valueOf(currentString));
        } else {
            option.setStowAndGo(null);
        }

        currentString = resultSet.getString("options.stow_go_third_row_only");
        if (currentString != null) {
            option.setStowAndGoThirdRowOnly(Boolean.valueOf(currentString));
        } else {
            option.setStowAndGoThirdRowOnly(null);
        }

        currentString = resultSet.getString("options.third_row_seat");
        if (currentString != null) {
            option.setThirdRowSeat(Boolean.valueOf(currentString));
        } else {
            option.setThirdRowSeat(null);
        }

        currentString = resultSet.getString("options.tilt_steering");
        if (currentString != null) {
            option.setTiltSteering(Boolean.valueOf(currentString));
        } else {
            option.setTiltSteering(null);
        }

        currentString = resultSet.getString("options.tinted_windows");
        if (currentString != null) {
            option.setTintedWindows(Boolean.valueOf(currentString));
        } else {
            option.setTintedWindows(null);
        }

        currentString = resultSet.getString("options.tire_pressure_monitor_system");
        if (currentString != null) {
            option.setTirePressureMonitorSystem(Boolean.valueOf(currentString));
        } else {
            option.setTirePressureMonitorSystem(null);
        }

        currentString = resultSet.getString("options.traction_ctrl");
        if (currentString != null) {
            option.setTractionControl(Boolean.valueOf(currentString));
        } else {
            option.setTractionControl(null);
        }

        currentString = resultSet.getString("options.trip_counter");
        if (currentString != null) {
            option.setTripCounter(Boolean.valueOf(currentString));
        } else {
            option.setTripCounter(null);
        }

        currentString = resultSet.getString("options.wood_trim_dash");
        if (currentString != null) {
            option.setWoodTrimDash(Boolean.valueOf(currentString));
        } else {
            option.setWoodTrimDash(null);
        }
        currentString = resultSet.getString("options.woodgrain_interior_package");
        if (currentString != null) {
            option.setWoodgrainInteriorPackage(Boolean.valueOf(currentString));
        } else {
            option.setWoodgrainInteriorPackage(null);
        }
        currentString = resultSet.getString("options.xenon_headlights");
        if (currentString != null) {
            option.setWoodgrainInteriorPackage(Boolean.valueOf(currentString));
        } else {
            option.setWoodgrainInteriorPackage(null);
        }
        return option;
    }

    public String createDBRequest(Options option, boolean checkFalse) {
        String dbRequest = "";
        if (option != null) {
            if (option.getAirConditioning() != null) {
                if (option.getAirConditioning() && (option.getAirConditioning() || checkFalse)) {
                    dbRequest += "options.air_conditioning='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.air_conditioning='0' ";
                }
            }
            if (option.getBugDeflector() != null) {
                if (!dbRequest.isEmpty() && (option.getBugDeflector() || checkFalse)) {
                    dbRequest += " AND ";
                }
                if (option.getBugDeflector()) {
                    dbRequest += "options.bug_deflector='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.bug_deflector='0' ";
                }
            }
            if (option.getCdChanger() != null && (option.getCdChanger() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getCdChanger()) {
                    dbRequest += "options.cd_changer='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.cd_changer='0' ";
                }
            }
            if (option.getCdPlayer() != null && (option.getCdPlayer() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getCdPlayer()) {
                    dbRequest += "options.cd_player='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.cd_player='0' ";
                }
            }
            if (option.getCruiseCtrl() != null && (option.getCruiseCtrl() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getCruiseCtrl()) {
                    dbRequest += "options.cruise_ctrl='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.cruise_ctrl='0' ";
                }
            }
            if (option.getDualAirBags() != null && (option.getDualAirBags() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getDualAirBags()) {
                    dbRequest += "options.dual_air_bags='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.dual_air_bags='0' ";
                }
            }
            if (option.getDualSlidingSideDoors() != null && (option.getDualSlidingSideDoors() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getDualSlidingSideDoors()) {
                    dbRequest += "options.dual_sliding_side_doors='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.dual_sliding_side_doors='0' ";
                }
            }
            if (option.getFloorMats() != null && (option.getFloorMats() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getFloorMats()) {
                    dbRequest += "options.floor_mats='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.floor_mats='0' ";
                }
            }
            if (option.getFogLamps() != null && (option.getFogLamps() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getFogLamps()) {
                    dbRequest += "options.fog_lamps='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.fog_lamps='0' ";
                }
            }
            if (option.getFrontCenterConsole() != null && (option.getFrontCenterConsole() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getFrontCenterConsole()) {
                    dbRequest += "options.front_center_console='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.front_center_console='0' ";
                }
            }
            if (option.getHandToolKit() != null && (option.getHandToolKit() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getHandToolKit()) {
                    dbRequest += "options.hand_tool_kit='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.hand_tool_kit='0' ";
                }
            }
            if (option.getHeatedSeats() != null && (option.getHeatedSeats() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getHeatedSeats()) {
                    dbRequest += "options.heated_seats='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.heated_seats='0' ";
                }
            }
            if (option.getHomeLink() != null && (option.getHomeLink() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getHomeLink()) {
                    dbRequest += "options.home_link='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.home_link='0' ";
                }
            }
            if (option.getKeylessEntry() != null && (option.getKeylessEntry() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getKeylessEntry()) {
                    dbRequest += "options.keyless_entry='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.keyless_entry='0' ";
                }
            }
            if (option.getLiftGate() != null && (option.getLiftGate() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getLiftGate()) {
                    dbRequest += "options.lift_gate='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.lift_gate='0' ";
                }
            }
            if (option.getLuggageRack() != null && (option.getLuggageRack() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getLuggageRack()) {
                    dbRequest += "options.luggage_rack='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.luggage_rack='0' ";
                }
            }
            if (option.getPowerVentWindows() != null && (option.getPowerVentWindows() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getPowerVentWindows()) {
                    dbRequest += "options.power_vent_windows='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.power_vent_windows='0' ";
                }
            }
            if (option.getPwrLocks() != null && (option.getPwrLocks() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getPwrLocks()) {
                    dbRequest += "options.pwr_locks='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.pwr_locks='0' ";
                }
            }
            if (option.getPwrMirrors() != null && (option.getPwrMirrors() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getPwrMirrors()) {
                    dbRequest += "options.pwr_mirrors='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.pwr_mirrors='0' ";
                }
            }
            if (option.getPwrSeats() != null && (option.getPwrSeats() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getPwrSeats()) {
                    dbRequest += "options.pwr_seats='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.pwr_seats='0' ";
                }
            }
            if (option.getPwrSeatsDriversOnly() != null && (option.getPwrSeatsDriversOnly() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getPwrSeatsDriversOnly()) {
                    dbRequest += "options.pwr_seats_driver='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.pwr_seats_driver='0' ";
                }
            }
            if (option.getPwrSteering() != null && (option.getPwrSteering() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getPwrSteering()) {
                    dbRequest += "options.pwr_steering='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.pwr_steering='0' ";
                }
            }
            if (option.getPwrWindows() != null && (option.getPwrWindows() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getPwrWindows()) {
                    dbRequest += "options.pwr_windows='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.pwr_windows='0' ";
                }
            }
            if (option.getRearAirConditioning() != null && (option.getRearAirConditioning() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getRearAirConditioning()) {
                    dbRequest += "options.rear_air_conditioning='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.rear_air_conditioning='0' ";
                }
            }
            if (option.getRearDefrost() != null && (option.getRearDefrost() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getRearDefrost()) {
                    dbRequest += "options.rear_defrost='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.rear_defrost='0' ";
                }
            }
            if (option.getRearTail() != null && (option.getRearTail() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getRearTail()) {
                    dbRequest += "options.rear_tail='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.rear_tail='0' ";
                }
            }
            if (option.getRearWiper() != null && (option.getRearWiper() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getRearWiper()) {
                    dbRequest += "options.rear_wiper='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.rear_wiper='0' ";
                }
            }
            if (option.getSecuritySystem() != null && (option.getSecuritySystem() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getSecuritySystem()) {
                    dbRequest += "options.security_system='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.security_system='0' ";
                }
            }
            if (option.getSideAirBags() != null && (option.getSideAirBags() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getSideAirBags()) {
                    dbRequest += "options.side_air_bags='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.side_air_bags='0' ";
                }
            }
            if (option.getSpoiler() != null && (option.getSpoiler() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getSpoiler()) {
                    dbRequest += "options.spoiler='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.spoiler='0' ";
                }
            }
            if (option.getSteeringWheelAudioControl() != null && (option.getSteeringWheelAudioControl() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getSteeringWheelAudioControl()) {
                    dbRequest += "options.steering_wheel_audio_ctrl='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.steering_wheel_audio_ctrl='0' ";
                }
            }
            if (option.getStowAndGo() != null && (option.getStowAndGo() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getStowAndGo()) {
                    dbRequest += "options.stow_go='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.stow_go='0' ";
                }
            }
            if (option.getStowAndGoThirdRowOnly() != null && (option.getStowAndGoThirdRowOnly() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getStowAndGoThirdRowOnly()) {
                    dbRequest += "options.stow_go_third_row_only='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.stow_go_third_row_only='0' ";
                }
            }
            if (option.getThirdRowSeat() != null && (option.getThirdRowSeat() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getThirdRowSeat()) {
                    dbRequest += "options.third_row_seat='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.third_row_seat='0' ";
                }
            }
            if (option.getTiltSteering() != null && (option.getTiltSteering() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getTiltSteering()) {
                    dbRequest += "options.tilt_steering='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.tilt_steering='0' ";
                }
            }
            if (option.getTintedWindows() != null && (option.getTintedWindows() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getTintedWindows()) {
                    dbRequest += "options.tinted_windows='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.tinted_windows='0' ";
                }
            }
            if (option.getTirePressureMonitorSystem() != null && (option.getTirePressureMonitorSystem() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getTirePressureMonitorSystem()) {
                    dbRequest += "options.tire_pressure_monitor_system='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.tire_pressure_monitor_system='0' ";
                }
            }
            if (option.getTractionControl() != null && (option.getTractionControl() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getTractionControl()) {
                    dbRequest += "options.traction_ctrl='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.traction_ctrl='0' ";
                }
            }
            if (option.getTripCounter() != null && (option.getTripCounter() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getTripCounter()) {
                    dbRequest += "options.trip_counter='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.trip_counter='0' ";
                }
            }
            if (option.getWoodTrimDash() != null && (option.getWoodTrimDash() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getWoodTrimDash()) {
                    dbRequest += "options.wood_trim_dash='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.wood_trim_dash='0' ";
                }
            }
            if (option.getWoodgrainInteriorPackage() != null && (option.getWoodgrainInteriorPackage() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getWoodgrainInteriorPackage()) {
                    dbRequest += "options.woodgrain_interior_package='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.woodgrain_interior_package='0' ";
                }
            }
            if (option.getXenonHeadlights() != null && (option.getXenonHeadlights() || checkFalse)) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                if (option.getXenonHeadlights()) {
                    dbRequest += "options.xenon_headlights='1' ";
                } else if (checkFalse) {
                    dbRequest += "options.xenon_headlights='0' ";
                }
            }
        }
        return dbRequest;
    }

}
