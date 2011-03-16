package com.bidover.auto.database.dao;

import com.bidover.common.database.dao.LotDAO;
import com.bidover.auto.model.bean.Auto;
import com.bidover.auto.model.bean.Door;
import com.bidover.auto.model.bean.Engine;
import com.bidover.auto.model.bean.InteriorType;
import com.bidover.auto.model.bean.Tires;
import com.bidover.auto.model.bean.Transmission;
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
public class AutoDAO {

    private Connection connection;
    private PreparedStatement peparedStatement;
    private ConnectionPool connectionPool;

    public AutoDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
    }

    public Integer add(Auto auto) {
        Integer addFlag = null;
        OptionDAO optionDAO = new OptionDAO();
        int idOption = optionDAO.add(auto.getIdOptions());
        DamageDAO damageDAO = new DamageDAO();
        int idDamage = damageDAO.add(auto.getDamage());
        int idLot = auto.getId();
        if (idOption != -1 && idLot != -1 && idDamage != -1) {
            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement(
                        "INSERT INTO bidover_db.auto(" +
                        "VIN, " + 
                        "id, " +
                        "doors, " +
                        "engine, " +
                        "odometer," +
                        "year," +
                        "id_options," +
                        "id_body_style, " +
                        "id_top_type," +
                        "id_fuel," +
                        "transmission," +
                        "id_drive_train," +
                        "id_exterior_color," +
                        "id_interior_color," +
                        "interior_type," +
                        "id_wheels," +
                        "id_tires," +
                        "id_trim," +
                        "id_lot," +
                        "id_damage," +
                        "id_characteristics" +
                        ") " +
                        "VALUES('" +
                        auto.getVin() +
                        "','" +
                        idLot +
                        "','" +
                        auto.getDoors().getId() +
                        "','" +
                        auto.getEngine().getId() +
                        "','" +
                        auto.getOdometer() +
                        "','" +
                        auto.getYear() +
                        "','" +
                        idOption +
                        "','" +
                        auto.getIdBodyStyle().getId() +
                        "','" +
                        auto.getIdTopType().getId() +
                        "','" +
                        auto.getIdFuel().getId() +
                        "','" +
                        auto.getTransmission().getId() +
                        "','" +
                        auto.getIdDriveTrain().getId() +
                        "','" +
                        auto.getIdExteriorColor().getId() +
                        "','" +
                        auto.getIdInteriorColor().getId() +
                        "','" +
                        auto.getInteriorType().getId() +
                        "','" +
                        auto.getIdWheels().getId() +
                        "','" +
                        auto.getIdTires().getId() +
                        "','" +
                        auto.getTrim().getId() +
                        "','" +
                        idLot +
                        "','" +
                        idDamage +
                        "','" +
                        auto.getCharacteristics().getId() +
                        "')");

                peparedStatement.execute();
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
                addFlag = idLot;
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return addFlag;
    }

    /*    public boolean update(Auto curAuto, Auto newAuto) {
    boolean updFlag = false;
    String dbRequest = createDBRequest(curAuto);
    try {
    executeRequest("UPDATE bidover_db.auto .........");
    updFlag = true;
    } catch (SQLException ex) {
    Logger.log(ex);
    }
    return updFlag;
    }*/
    public Auto findById(Integer id) {
        Auto auto = null;
        if (id != null) {
            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.auto " +
                        "INNER JOIN bidover_db.options ON auto.id_options=options.id " +
                        "INNER JOIN bidover_db.characteristics ON auto.id_characteristics=characteristics.id " +
                        "INNER JOIN bidover_db.body_style ON auto.id_body_style=body_style.id " +
                        "INNER JOIN bidover_db.damage ON auto.id_damage=damage.id " +
                        "INNER JOIN bidover_db.drive_train ON auto.id_drive_train=drive_train.id " +
                        "INNER JOIN bidover_db.exterior_color ON auto.id_exterior_color=exterior_color.id " +
                        "INNER JOIN bidover_db.fuel ON auto.id_fuel=fuel.id " +
                        "INNER JOIN bidover_db.interior_color ON auto.id_interior_color=interior_color.id " +
                        "INNER JOIN bidover_db.top_type ON auto.id_top_type=top_type.id " +
                        "INNER JOIN bidover_db.trim ON auto.id_trim=trim.id " +
                        "INNER JOIN bidover_db.wheels ON auto.id_wheels=wheels.id " +
                        "INNER JOIN bidover_db.lot ON auto.id_lot=lot.id " +
                        "INNER JOIN bidover_db.condition ON lot.condition_code=condition.id WHERE auto.id='" + id + "'");
                ResultSet resultSet = peparedStatement.executeQuery();
                if (resultSet.next()) {
                    auto = createAuto(resultSet);
                }
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return auto;
    }

    public Integer findLotIdByAutoId(Integer autoId) {
        Integer lotId = null;
        if (autoId != null) {
            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT auto.id_lot FROM bidover_db.auto " +
                        "INNER JOIN bidover_db.lot ON auto.id_lot=lot.id WHERE auto.id='" + autoId + "'");
                ResultSet resultSet = peparedStatement.executeQuery();
                if (resultSet.next()) {
                    lotId = resultSet.getInt("auto.id_lot");
                }
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return lotId;
    }

    public Integer findAutoIdByVIN(String vin) {
        Integer autoId = null;
        if (vin != null) {
            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT auto.id FROM bidover_db.auto " +
                        " WHERE auto.VIN='" + vin + "'");
                ResultSet resultSet = peparedStatement.executeQuery();
                if (resultSet.next()) {
                    autoId = resultSet.getInt("auto.id");
                }
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return autoId;
    }

    public List<Auto> findPreview(Auto auto) {
        List<Auto> autos = null;
        String dbRequest = createDBRequest(auto);
        if (!dbRequest.isEmpty()) {
            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.auto " +
                        "INNER JOIN bidover_db.characteristics ON auto.id_characteristics=characteristics.id " +
                        "INNER JOIN bidover_db.lot ON auto.id=lot.id WHERE " + dbRequest);
                ResultSet resultSet = peparedStatement.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.isFirst()) {
                        autos = new ArrayList<Auto>();
                    }
                    autos.add(createPreviewAuto(resultSet));
                }
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return autos;
    }

    public Auto findPreviewByLotId(Integer lotId) {
        Auto auto = null;
        if (lotId != null) {
            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.auto " +
                        "INNER JOIN bidover_db.characteristics ON auto.id_characteristics=characteristics.id " +
                        "INNER JOIN bidover_db.lot ON auto.id_lot=lot.id WHERE lot.id='" + lotId + "'");
                ResultSet resultSet = peparedStatement.executeQuery();
                if (resultSet.next()) {
                    auto = createPreviewAuto(resultSet);
                }
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return auto;
    }

    public List<Auto> findPreview(Auto auto, Integer yearBegin, Integer yearEnd) {
        List<Auto> autos = null;
        String dbRequest = createDBRequest(auto);
        if (!dbRequest.isEmpty()) {
            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.auto " +
                        "INNER JOIN bidover_db.characteristics ON auto.id_characteristics=characteristics.id " +
                        "INNER JOIN bidover_db.lot ON auto.id_lot=lot.id "+
                         "WHERE " + dbRequest + " AND auto.year<='" + yearEnd + "' AND auto.year>='" + yearBegin + "'");
                ResultSet resultSet = peparedStatement.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.isFirst()) {
                        autos = new ArrayList<Auto>();
                    }
                    autos.add(createPreviewAuto(resultSet));
                }
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        } else {
            autos = findAllPreview();
        }
        return autos;
    }

    public List<Auto> findAllPreview() {
        List<Auto> autos = null;
        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.auto " +
                    "INNER JOIN bidover_db.options ON auto.id_options=options.id " +
                    "INNER JOIN bidover_db.characteristics ON auto.id_characteristics=characteristics.id " +
                    "INNER JOIN bidover_db.lot ON auto.id_lot=lot.id ");
            ResultSet resultSet = peparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.isFirst()) {
                    autos = new ArrayList<Auto>();
                }
                autos.add(createPreviewAuto(resultSet));
            }
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.log(ex);
        }
        return autos;
    }

    public boolean isVINExist(String vin) {
        boolean existsUser = false;
        try {
            connection = connectionPool.getConnection();
            peparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM bidover_db.auto WHERE VIN='" + vin + "'");
            ResultSet resultSet = peparedStatement.executeQuery();
            existsUser = resultSet.next() & resultSet.getInt(1) > 0 ? true : false;
            resultSet.close();
            connectionPool.releaseConnection(connection);

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(AutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existsUser;
    }

    public void delete(Auto auto) {
        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("");

            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.log(ex);
        }
    }

    private Auto createAuto(ResultSet resultSet) throws SQLException {
        Auto auto = new Auto();
        String id = resultSet.getString("auto.id");
        auto.setId(Integer.valueOf(id));

        CharacteristicsDAO characteristicsDAO = new CharacteristicsDAO();
        auto.setCharacteristics(characteristicsDAO.createCharacteristics(resultSet));

        OptionDAO optionDAO = new OptionDAO();
        auto.setIdOptions(optionDAO.createOption(resultSet));

        BodyStyleDAO bodyStyleDAO = new BodyStyleDAO();
        auto.setIdBodyStyle(bodyStyleDAO.createBody(resultSet));

        DamageDAO damageDAO = new DamageDAO();
        auto.setDamage(damageDAO.createDamage(resultSet));

        DriveTrainDAO driveTrainDAO = new DriveTrainDAO();
        auto.setIdDriveTrain(driveTrainDAO.createDriveTrain(resultSet));

        ExteriorColorDAO exteriorColorDAO = new ExteriorColorDAO();
        auto.setIdExteriorColor(exteriorColorDAO.createExteriorColor(resultSet));

        FuelDAO fuelDAO = new FuelDAO();
        auto.setIdFuel(fuelDAO.createFuel(resultSet));

        InteriorColorDAO interiorColorDAO = new InteriorColorDAO();
        auto.setIdInteriorColor(interiorColorDAO.createInteriorColor(resultSet));

        TopTypeDAO topTypeDAO = new TopTypeDAO();
        auto.setIdTopType(topTypeDAO.createTopType(resultSet));

        TrimDAO trimDAO = new TrimDAO();
        auto.setTrim(trimDAO.createTrim(resultSet));

        WheelsDAO wheelsDAO = new WheelsDAO();
        auto.setIdWheels(wheelsDAO.createWheels(resultSet));

        String doorsTxt = resultSet.getString("auto.doors");
        DoorDAO doorDAO = new DoorDAO();
        Door door = new Door();
        door.setId(Integer.valueOf(doorsTxt));
        auto.setDoors(doorDAO.find(door));

        String engineTxt = resultSet.getString("auto.engine");
        EngineDAO engineDAO = new EngineDAO();
        Engine engine = new Engine();
        engine.setId(Integer.valueOf(engineTxt));
        auto.setEngine(engineDAO.find(engine));

        String transmissionTxt = resultSet.getString("auto.transmission");
        TransmissionDAO transmissionDAO = new TransmissionDAO();
        Transmission transmission = new Transmission();
        transmission.setId(Integer.valueOf(transmissionTxt));
        auto.setTransmission(transmissionDAO.find(transmission));

        String interiorTypeTxt = resultSet.getString("auto.interior_type");
        InteriorTypeDAO interiorTypeDAO = new InteriorTypeDAO();
        InteriorType interiorType = new InteriorType();
        interiorType.setId(Integer.valueOf(interiorTypeTxt));
        auto.setInteriorType(interiorTypeDAO.find(interiorType));

        String tiresTxt = resultSet.getString("auto.id_tires");
        TiresDAO tiresDAO = new TiresDAO();
        Tires tires = new Tires();
        tires.setId(Integer.valueOf(tiresTxt));
        auto.setIdTires(tiresDAO.find(tires));

        String year = resultSet.getString("auto.year");
        auto.setYear(Integer.valueOf(year));

        String odometer = resultSet.getString("auto.odometer");
        auto.setOdometer(Integer.valueOf(odometer));

        String VIN = resultSet.getString("auto.VIN");
        auto.setVin(VIN);

        LotDAO lotDAO = new LotDAO();
        auto.setLot(lotDAO.createLot(resultSet));

        return auto;
    }

    private Auto createPreviewAuto(ResultSet resultSet) throws SQLException {
        Auto auto = new Auto();
        String id = resultSet.getString("auto.id");
        auto.setId(Integer.valueOf(id));

        CharacteristicsDAO characteristicsDAO = new CharacteristicsDAO();
        auto.setCharacteristics(characteristicsDAO.createCharacteristics(resultSet));

        String doorsTxt = resultSet.getString("auto.doors");
        DoorDAO doorDAO = new DoorDAO();
        Door door = new Door();
        door.setId(Integer.valueOf(doorsTxt));
        auto.setDoors(doorDAO.find(door));

        String engineTxt = resultSet.getString("auto.engine");
        EngineDAO engineDAO = new EngineDAO();
        Engine engine = new Engine();
        engine.setId(Integer.valueOf(engineTxt));
        auto.setEngine(engineDAO.find(engine));

        String transmissionTxt = resultSet.getString("auto.transmission");
        TransmissionDAO transmissionDAO = new TransmissionDAO();
        Transmission transmission = new Transmission();
        transmission.setId(Integer.valueOf(transmissionTxt));
        auto.setTransmission(transmissionDAO.find(transmission));

        String interiorTypeTxt = resultSet.getString("auto.interior_type");
        InteriorTypeDAO interiorTypeDAO = new InteriorTypeDAO();
        InteriorType interiorType = new InteriorType();
        interiorType.setId(Integer.valueOf(interiorTypeTxt));
        auto.setInteriorType(interiorTypeDAO.find(interiorType));

        String tiresTxt = resultSet.getString("auto.id_tires");
        TiresDAO tiresDAO = new TiresDAO();
        Tires tires = new Tires();
        tires.setId(Integer.valueOf(tiresTxt));
        auto.setIdTires(tiresDAO.find(tires));

        String year = resultSet.getString("auto.year");
        auto.setYear(Integer.valueOf(year));

        String odometer = resultSet.getString("auto.odometer");
        auto.setOdometer(Integer.valueOf(odometer));

        String VIN = resultSet.getString("auto.VIN");
        auto.setVin(VIN);

        // ---------------
        LotDAO lotDAO = new LotDAO();
        auto.setLot(lotDAO.createLotPreview(resultSet));
        // ---------------

        return auto;
    }

    private String createDBRequest(Auto auto) {
        String dbRequest = "";
        if (auto != null) {
            if (auto.getId() != null) {
                dbRequest += "auto.id='" + auto.getId() + "' ";
            }
            if (auto.getDoors() != null && auto.getDoors().getId() != null) {

                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "auto.doors='" + auto.getDoors().getId() + "' ";

            }
            if (auto.getOdometer() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "auto.odometer<='" + auto.getOdometer() + "' ";
            }
            if (auto.getYear() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "auto.year='" + auto.getYear() + "' ";
            }
///////////////////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11
/*            if (auto.getIdOptions() != null) {
            if (!dbRequest.isEmpty()) {
            dbRequest += " AND ";
            }
            OptionDAO optionDAO = new OptionDAO();
            dbRequest += optionDAO.createDBRequest(auto.getIdOptions());
            }
             */
            if (auto.getLot() != null && auto.getLot().getId() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "auto.id_lot='" + auto.getLot().getId() + "' ";
            }
            if (auto.getCharacteristics()!= null && auto.getCharacteristics().getMake() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "characteristics.manufacturer='" + auto.getCharacteristics().getMake() + "' ";
            }
            if (auto.getIdBodyStyle() != null && auto.getIdBodyStyle().getId() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "auto.id_body_style='" + auto.getIdBodyStyle().getId() + "' ";
            }
            if (auto.getIdTopType() != null && auto.getIdTopType().getId() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "auto.id_top_type='" + auto.getIdTopType().getId() + "' ";
            }
            if (auto.getEngine() != null && auto.getEngine().getId() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "auto.engine='" + auto.getEngine().getId() + "' ";
            }
            if (auto.getIdFuel() != null && auto.getIdFuel().getId() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "auto.id_fuel='" + auto.getIdFuel().getId() + "' ";
            }
            if (auto.getTransmission() != null && auto.getTransmission().getId() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "auto.transmission='" + auto.getTransmission().getId() + "' ";
            }
            if (auto.getIdDriveTrain() != null && auto.getIdDriveTrain().getId() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "auto.id_drive_train='" + auto.getIdDriveTrain().getId() + "' ";
            }
            if (auto.getIdExteriorColor() != null && auto.getIdExteriorColor().getId() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "auto.id_exterior_color='" + auto.getIdExteriorColor().getId() + "' ";
            }
            if (auto.getIdInteriorColor() != null && auto.getIdInteriorColor().getId() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "auto.id_interior_color='" + auto.getIdInteriorColor().getId() + "' ";
            }
            if (auto.getIdWheels() != null && auto.getIdWheels().getId() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "auto.id_wheels='" + auto.getIdWheels().getId() + "' ";
            }
            if (auto.getIdTires() != null && auto.getIdTires().getId() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "auto.id_tires='" + auto.getIdTires().getId() + "' ";
            }
            if (auto.getInteriorType() != null && auto.getInteriorType().getId() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "auto.interior_type='" + auto.getInteriorType().getId() + "' ";
            }
            if (auto.getTrim() != null && auto.getTrim().getId() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "auto.id_trim='" + auto.getTrim().getId() + "' ";
            }
            if (auto.getVin() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "auto.VIN='" + auto.getVin() + "' ";
            }
            if (auto.getDamage() != null && auto.getDamage().getId() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "auto.id_damage='" + auto.getDamage().getId() + "' ";
            }
            if (auto.getLot() != null && auto.getLot().getId() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "auto.id='" + auto.getLot().getId() + "' ";
            }
        }
        return dbRequest;
    }

    private void executeRequest(String request) throws SQLException {
        connection = connectionPool.getConnection();
        peparedStatement = (PreparedStatement) connection.prepareStatement(request);
        peparedStatement.execute();
        peparedStatement.close();
        connectionPool.releaseConnection(connection);
    }
}
