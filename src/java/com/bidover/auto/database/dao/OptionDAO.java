/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.database.dao;

import com.bidover.auto.model.bean.Options;
import com.bidover.auto.database.connectionpool.ConnectionPool;
import java.sql.Connection;
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
public class OptionDAO {

    private Connection connection;
    private PreparedStatement peparedStatement;
    private ConnectionPool connectionPool;

    public OptionDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
    }

    public int add(Options option) {
        int id = -1;
        try {
            List<Options> options = find(option);
            if (options == null) {

                String s = "INSERT INTO bidover_db.options(" +
                        "air_conditioning," +
                        "bug_deflector," +
                        "cd_changer," +
                        "cd_player," +
                        "cruise_ctrl," +
                        "dual_air_bags," +
                        "dual_sliding_side_doors," +
                        "floor_mats," +
                        "fog_lamps," +
                        "front_center_console," +
                        "hand_tool_kit," +
                        "heated_seats," +
                        "home_link," +
                        "keyless_entry," +
                        "lift_gate," +
                        "luggage_rack," +
                        "power_vent_windows," +
                        "pwr_locks," +
                        "pwr_mirrors," +
                        "pwr_seats," +
                        "pwr_seats_driver," +
                        "pwr_steering," +
                        "pwr_windows," +
                        "rear_air_conditioning," +
                        "rear_defrost," +
                        "rear_tail," +
                        "rear_wiper," +
                        "security_system," +
                        "side_air_bags," +
                        "spoiler," +
                        "steering_wheel_audio_ctrl," +
                        "stow_go," +
                        "stow_go_third_row_only," +
                        "third_row_seat," +
                        "tilt_steering," +
                        "tinted_windows," +
                        "tire_pressure_monitor_system," +
                        "traction_ctrl," +
                        "trip_counter," +
                        "wood_trim_dash," +
                        "woodgrain_interior_package," +
                        "xenon_headlights" +
                        ") VALUES('" +
                        option.getAirConditioning() + "','" +
                        option.getBugDeflector() + "','" +
                        option.getCdChanger() + "','" +
                        option.getCdPlayer() + "','" +
                        option.getCruiseCtrl() + "','" +
                        option.getDualAirBags() + "','" +
                        option.getDualSlidingSideDoors() + "','" +
                        option.getFloorMats() + "','" +
                        option.getFogLamps() + "','" +
                        option.getFrontCenterConsole() + "','" +
                        option.getHandToolKit() + "','" +
                        option.getHeatedSeats() + "','" +
                        option.getHomeLink() + "','" +
                        option.getKeylessEntry() + "','" +
                        option.getLiftGate() + "','" +
                        option.getLuggageRack() + "','" +
                        option.getPowerVentWindows() + "','" +
                        option.getPwrLocks() + "','" +
                        option.getPwrMirrors() + "','" +
                        option.getPwrSeats() + "','" +
                        option.getPwrSeatsDriversOnly() + "','" +
                        option.getPwrSteering() + "','" +
                        option.getPwrWindows() + "','" +
                        option.getRearAirConditioning() + "','" +
                        option.getRearDefrost() + "','" +
                        option.getRearTail() + "','" +
                        option.getRearWiper() + "','" +
                        option.getSecuritySystem() + "','" +
                        option.getSideAirBags() + "','" +
                        option.getSpoiler() + "','" +
                        option.getSteeringWheelAudioControl() + "','" +
                        option.getStowAndGo() + "','" +
                        option.getStowAndGoThirdRowOnly() + "','" +
                        option.getThirdRowSeat() + "','" +
                        option.getTiltSteering() + "','" +
                        option.getTintedWindows() + "','" +
                        option.getTirePressureMonitorSystem() + "','" +
                        option.getTractionControl() + "','" +
                        option.getTripCounter() + "','" +
                        option.getWoodTrimDash() + "','" +
                        option.getWoodgrainInteriorPackage() + "','" +
                        option.getXenonHeadlights() + "')";

                executeRequest(s);
                options = find(option);
                id = options.get(0).getId();
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
        String dbRequest = createDBRequest(option);
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
        String dbRequest = createDBRequest(curOption);
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

    private void executeRequest(String request) throws SQLException {
        connection = connectionPool.getConnection();
        peparedStatement = (PreparedStatement) connection.prepareStatement(request);
        peparedStatement.execute();
        peparedStatement.close();
        connectionPool.releaseConnection(connection);
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
            option.setAirConditioning(Byte.valueOf(currentString));
        } else {
            option.setAirConditioning(null);
        }

        currentString = resultSet.getString("options.bug_deflector");
        if (currentString != null) {
            option.setBugDeflector(Byte.valueOf(currentString));
        } else {
            option.setBugDeflector(null);
        }

        currentString = resultSet.getString("options.cd_changer");
        if (currentString != null) {
            option.setCdChanger(Byte.valueOf(currentString));
        } else {
            option.setCdChanger(null);
        }

        currentString = resultSet.getString("options.cd_player");
        if (currentString != null) {
            option.setCdPlayer(Byte.valueOf(currentString));
        } else {
            option.setCdPlayer(null);
        }

        currentString = resultSet.getString("options.cruise_ctrl");
        if (currentString != null) {
            option.setCruiseCtrl(Byte.valueOf(currentString));
        } else {
            option.setCruiseCtrl(null);
        }

        currentString = resultSet.getString("options.dual_air_bags");
        if (currentString != null) {
            option.setDualAirBags(Byte.valueOf(currentString));
        } else {
            option.setDualAirBags(null);
        }

        currentString = resultSet.getString("options.dual_sliding_side_doors");
        if (currentString != null) {
            option.setDualSlidingSideDoors(Byte.valueOf(currentString));
        } else {
            option.setDualSlidingSideDoors(null);
        }

        currentString = resultSet.getString("options.floor_mats");
        if (currentString != null) {
            option.setFloorMats(Byte.valueOf(currentString));
        } else {
            option.setFloorMats(null);
        }
        currentString = resultSet.getString("options.fog_lamps");
        if (currentString != null) {
            option.setFogLamps(Byte.valueOf(currentString));
        } else {
            option.setFogLamps(null);
        }
        currentString = resultSet.getString("options.front_center_console");
        if (currentString != null) {
            option.setFrontCenterConsole(Byte.valueOf(currentString));
        } else {
            option.setFrontCenterConsole(null);
        }
        currentString = resultSet.getString("options.hand_tool_kit");
        if (currentString != null) {
            option.setHandToolKit(Byte.valueOf(currentString));
        } else {
            option.setHandToolKit(null);
        }

        currentString = resultSet.getString("options.heated_seats");
        if (currentString != null) {
            option.setHeatedSeats(Byte.valueOf(currentString));
        } else {
            option.setHeatedSeats(null);
        }

        currentString = resultSet.getString("options.home_link");
        if (currentString != null) {
            option.setHomeLink(Byte.valueOf(currentString));
        } else {
            option.setHomeLink(null);
        }

        currentString = resultSet.getString("options.keyless_entry");
        if (currentString != null) {
            option.setKeylessEntry(Byte.valueOf(currentString));
        } else {
            option.setKeylessEntry(null);
        }

        currentString = resultSet.getString("options.lift_gate");
        if (currentString != null) {
            option.setLiftGate(Byte.valueOf(currentString));
        } else {
            option.setLiftGate(null);
        }

        currentString = resultSet.getString("options.luggage_rack");
        if (currentString != null) {
            option.setLuggageRack(Byte.valueOf(currentString));
        } else {
            option.setLuggageRack(null);
        }

        currentString = resultSet.getString("options.power_vent_windows");
        if (currentString != null) {
            option.setPowerVentWindows(Byte.valueOf(currentString));
        } else {
            option.setPowerVentWindows(null);
        }

        currentString = resultSet.getString("options.pwr_locks");
        if (currentString != null) {
            option.setPwrLocks(Byte.valueOf(currentString));
        } else {
            option.setPwrLocks(null);
        }

        currentString = resultSet.getString("options.pwr_mirrors");
        if (currentString != null) {
            option.setPwrMirrors(Byte.valueOf(currentString));
        } else {
            option.setPwrMirrors(null);
        }

        currentString = resultSet.getString("options.pwr_seats");
        if (currentString != null) {
            option.setPwrSeats(Byte.valueOf(currentString));
        } else {
            option.setPwrSeats(null);
        }

        currentString = resultSet.getString("options.pwr_seats_driver");
        if (currentString != null) {
            option.setPwrSeatsDriversOnly(Byte.valueOf(currentString));
        } else {
            option.setPwrSeatsDriversOnly(null);
        }

        currentString = resultSet.getString("options.pwr_steering");
        if (currentString != null) {
            option.setPwrSteering(Byte.valueOf(currentString));
        } else {
            option.setPwrSteering(null);
        }

        currentString = resultSet.getString("options.pwr_windows");
        if (currentString != null) {
            option.setPwrWindows(Byte.valueOf(currentString));
        } else {
            option.setPwrWindows(null);
        }

        currentString = resultSet.getString("options.rear_air_conditioning");
        if (currentString != null) {
            option.setRearAirConditioning(Byte.valueOf(currentString));
        } else {
            option.setRearAirConditioning(null);
        }

        currentString = resultSet.getString("options.rear_defrost");
        if (currentString != null) {
            option.setRearDefrost(Byte.valueOf(currentString));
        } else {
            option.setRearDefrost(null);
        }

        currentString = resultSet.getString("options.rear_tail");
        if (currentString != null) {
            option.setRearTail(Byte.valueOf(currentString));
        } else {
            option.setRearTail(null);
        }

        currentString = resultSet.getString("options.rear_wiper");
        if (currentString != null) {
            option.setRearWiper(Byte.valueOf(currentString));
        } else {
            option.setRearWiper(null);
        }
        currentString = resultSet.getString("options.security_system");
        if (currentString != null) {
            option.setSecuritySystem(Byte.valueOf(currentString));
        } else {
            option.setSecuritySystem(null);
        }

        currentString = resultSet.getString("options.side_air_bags");
        if (currentString != null) {
            option.setSideAirBags(Byte.valueOf(currentString));
        } else {
            option.setSideAirBags(null);
        }

        currentString = resultSet.getString("options.spoiler");
        if (currentString != null) {
            option.setSpoiler(Byte.valueOf(currentString));
        } else {
            option.setSpoiler(null);
        }

        currentString = resultSet.getString("options.steering_wheel_audio_ctrl");
        if (currentString != null) {
            option.setSteeringWheelAudioControl(Byte.valueOf(currentString));
        } else {
            option.setSteeringWheelAudioControl(null);
        }

        currentString = resultSet.getString("options.stow_go");
        if (currentString != null) {
            option.setStowAndGo(Byte.valueOf(currentString));
        } else {
            option.setStowAndGo(null);
        }

        currentString = resultSet.getString("options.stow_go_third_row_only");
        if (currentString != null) {
            option.setStowAndGoThirdRowOnly(Byte.valueOf(currentString));
        } else {
            option.setStowAndGoThirdRowOnly(null);
        }

        currentString = resultSet.getString("options.third_row_seat");
        if (currentString != null) {
            option.setThirdRowSeat(Byte.valueOf(currentString));
        } else {
            option.setThirdRowSeat(null);
        }

        currentString = resultSet.getString("options.tilt_steering");
        if (currentString != null) {
            option.setTiltSteering(Byte.valueOf(currentString));
        } else {
            option.setTiltSteering(null);
        }

        currentString = resultSet.getString("options.tinted_windows");
        if (currentString != null) {
            option.setTintedWindows(Byte.valueOf(currentString));
        } else {
            option.setTintedWindows(null);
        }

        currentString = resultSet.getString("options.tire_pressure_monitor_system");
        if (currentString != null) {
            option.setTirePressureMonitorSystem(Byte.valueOf(currentString));
        } else {
            option.setTirePressureMonitorSystem(null);
        }

        currentString = resultSet.getString("options.traction_ctrl");
        if (currentString != null) {
            option.setTractionControl(Byte.valueOf(currentString));
        } else {
            option.setTractionControl(null);
        }

        currentString = resultSet.getString("options.trip_counter");
        if (currentString != null) {
            option.setTripCounter(Byte.valueOf(currentString));
        } else {
            option.setTripCounter(null);
        }

        currentString = resultSet.getString("options.wood_trim_dash");
        if (currentString != null) {
            option.setWoodTrimDash(Byte.valueOf(currentString));
        } else {
            option.setWoodTrimDash(null);
        }
        currentString = resultSet.getString("options.woodgrain_interior_package");
        if (currentString != null) {
            option.setWoodgrainInteriorPackage(Byte.valueOf(currentString));
        } else {
            option.setWoodgrainInteriorPackage(null);
        }
        currentString = resultSet.getString("options.xenon_headlights");
        if (currentString != null) {
            option.setWoodgrainInteriorPackage(Byte.valueOf(currentString));
        } else {
            option.setWoodgrainInteriorPackage(null);
        }
        return option;
    }

    public String createDBRequest(Options option) {
        String dbRequest = "";
        if (option != null) {
            if (option.getAirConditioning() != null) {
                dbRequest += "options.air_conditioning='" + option.getAirConditioning() + "' ";
            }
            if (option.getBugDeflector() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.bug_deflector='" + option.getBugDeflector() + "' ";
            }
            if (option.getCdChanger() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.cd_changer='" + option.getCdChanger() + "' ";
            }
            if (option.getCdPlayer() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.cd_player='" + option.getCdPlayer() + "' ";
            }
            if (option.getCruiseCtrl() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.cruise_ctrl='" + option.getCruiseCtrl() + "' ";
            }
            if (option.getDualAirBags() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.dual_air_bags='" + option.getDualAirBags() + "' ";
            }
            if (option.getDualSlidingSideDoors() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.dual_sliding_side_doors='" + option.getDualSlidingSideDoors() + "' ";
            }
            if (option.getFloorMats() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.floor_mats='" + option.getFloorMats() + "' ";
            }
            if (option.getFogLamps() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.fog_lamps='" + option.getFogLamps() + "' ";
            }
            if (option.getFrontCenterConsole() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.front_center_console='" + option.getFrontCenterConsole() + "' ";
            }
            if (option.getHandToolKit() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.hand_tool_kit='" + option.getHandToolKit() + "' ";
            }
            if (option.getHeatedSeats() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.heated_seats='" + option.getHeatedSeats() + "' ";
            }
            if (option.getHomeLink() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.home_link='" + option.getHomeLink() + "' ";
            }
            if (option.getKeylessEntry() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.keyless_entry='" + option.getKeylessEntry() + "' ";
            }
            if (option.getLiftGate() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.lift_gate='" + option.getLiftGate() + "' ";
            }
            if (option.getLuggageRack() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.luggage_rack='" + option.getLuggageRack() + "' ";
            }
            if (option.getPowerVentWindows() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.power_vent_windows='" + option.getPowerVentWindows() + "' ";
            }
            if (option.getPwrLocks() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.pwr_locks='" + option.getPwrLocks() + "' ";
            }
            if (option.getPwrMirrors() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.pwr_mirrors='" + option.getPwrMirrors() + "' ";
            }
            if (option.getPwrSeats() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.pwr_seats='" + option.getPwrSeats() + "' ";
            }
            if (option.getPwrSeatsDriversOnly() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.pwr_seats_driver='" + option.getPwrSeatsDriversOnly() + "' ";
            }
            if (option.getPwrSteering() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.pwr_steering='" + option.getPwrSteering() + "' ";
            }
            if (option.getPwrWindows() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.pwr_windows='" + option.getPwrWindows() + "' ";
            }
            if (option.getRearAirConditioning() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.rear_air_conditioning='" + option.getRearAirConditioning() + "' ";
            }
            if (option.getRearDefrost() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.rear_defrost='" + option.getRearDefrost() + "' ";
            }
            if (option.getRearTail() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.rear_tail='" + option.getRearTail() + "' ";
            }
            if (option.getRearWiper() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.rear_wiper='" + option.getRearWiper() + "' ";
            }
            if (option.getSecuritySystem() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.security_system='" + option.getSecuritySystem() + "' ";
            }
            if (option.getSideAirBags() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.side_air_bags='" + option.getSideAirBags() + "' ";
            }
            if (option.getSpoiler() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.spoiler='" + option.getSpoiler() + "' ";
            }
            if (option.getSteeringWheelAudioControl() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.steering_wheel_audio_ctrl='" + option.getSteeringWheelAudioControl() + "' ";
            }
            if (option.getStowAndGo() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.stow_go='" + option.getStowAndGo() + "' ";
            }
            if (option.getStowAndGoThirdRowOnly() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.stow_go_third_row_only='" + option.getStowAndGoThirdRowOnly() + "' ";
            }
            if (option.getThirdRowSeat() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.third_row_seat='" + option.getThirdRowSeat() + "' ";
            }
            if (option.getTiltSteering() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.tilt_steering='" + option.getTiltSteering() + "' ";
            }
            if (option.getTintedWindows() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.tinted_windows='" + option.getTintedWindows() + "' ";
            }
            if (option.getTirePressureMonitorSystem() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.tire_pressure_monitor_system='" + option.getTirePressureMonitorSystem() + "' ";
            }
            if (option.getTractionControl() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.traction_ctrl='" + option.getTractionControl() + "' ";
            }
            if (option.getTripCounter() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.trip_counter='" + option.getTripCounter() + "' ";
            }
            if (option.getWoodTrimDash() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.wood_trim_dash='" + option.getWoodTrimDash() + "' ";
            }
            if (option.getWoodgrainInteriorPackage() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.woodgrain_interior_package='" + option.getWoodgrainInteriorPackage() + "' ";
            }
            if (option.getXenonHeadlights() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "options.xenon_headlights='" + option.getXenonHeadlights() + "' ";
            }
        }
        return dbRequest;
    }
}
