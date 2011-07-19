package com.bidover.auto.database.dao;

import com.bidover.auto.model.bean.Auto;
import com.bidover.auto.model.bean.Door;
import com.bidover.auto.model.bean.Engine;
import com.bidover.auto.model.bean.InteriorType;
import com.bidover.auto.model.bean.Tires;
import com.bidover.auto.model.bean.Transmission;
import com.bidover.common.database.dao.BaseLotDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import com.bidover.common.logger.Logger;
import com.bidover.util.IntegerUtil;

/**
 *
 * @author Jedai
 */
public class AutoDAO extends BaseLotDAO<Auto> {

    public AutoDAO() {
        super();
    }

    public Integer addSpecificInfo(Auto auto) {
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
                        "INSERT INTO bidover_db.auto("
                        + "VIN, "
                        + "id, "
                        + "doors, "
                        + "salvage, "
                        + "assembly_id, "
                        + "engine, "
                        + "odometer,"
                        + "year,"
                        + "id_options,"
                        + "id_body_style, "
                        + "id_top_type,"
                        + "id_fuel,"
                        + "transmission,"
                        + "id_drive_train,"
                        + "id_exterior_color,"
                        + "id_interior_color,"
                        + "interior_type,"
                        + "id_wheels,"
                        + "id_tires,"
                        + "id_lot,"
                        + "id_damage,"
                        + "id_characteristics,"
                        + "displacement"
                        + ") VALUES(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?)");


                peparedStatement.setString(1, auto.getVin());
                peparedStatement.setObject(2, idLot);
                peparedStatement.setObject(3, auto.getDoors().getId());
                peparedStatement.setBoolean(4, auto.isSalvage());
                peparedStatement.setObject(5, auto.getCountryAssembly().getId());
                peparedStatement.setObject(6, auto.getEngine().getId());
                peparedStatement.setObject(7, auto.getOdometer());
                peparedStatement.setObject(8, auto.getYear());
                peparedStatement.setObject(9, idOption);
                peparedStatement.setObject(10, auto.getIdBodyStyle().getId());
                peparedStatement.setObject(11, auto.getIdTopType().getId());
                peparedStatement.setObject(12, auto.getIdFuel().getId());
                peparedStatement.setObject(13, auto.getTransmission().getId());
                peparedStatement.setObject(14, auto.getIdDriveTrain().getId());
                peparedStatement.setObject(15, auto.getIdExteriorColor().getId());
                peparedStatement.setObject(16, auto.getIdInteriorColor().getId());
                peparedStatement.setObject(17, auto.getInteriorType().getId());
                peparedStatement.setObject(18, auto.getIdWheels().getId());
                peparedStatement.setObject(19, auto.getIdTires().getId());
//                peparedStatement.setObject(20, auto.getTrim().getId());
                peparedStatement.setObject(20, idLot);
                peparedStatement.setObject(21, idDamage);
                peparedStatement.setObject(22, auto.getCharacteristics().getId());
                peparedStatement.setObject(23, auto.getDisplacement());

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
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.auto "
                        + "LEFT JOIN bidover_db.options ON auto.id_options=options.id "
                        + "LEFT JOIN bidover_db.characteristics ON auto.id_characteristics=characteristics.id "
                        + "LEFT JOIN bidover_db.body_style ON auto.id_body_style=body_style.id "
                        + "LEFT JOIN bidover_db.damage ON auto.id_damage=damage.id "
                        + "LEFT JOIN bidover_db.drive_train ON auto.id_drive_train=drive_train.id "
                        + "LEFT JOIN bidover_db.exterior_color ON auto.id_exterior_color=exterior_color.id "
                        + "LEFT JOIN bidover_db.fuel ON auto.id_fuel=fuel.id "
                        + "LEFT JOIN bidover_db.interior_color ON auto.id_interior_color=interior_color.id "
                        + "LEFT JOIN bidover_db.top_type ON auto.id_top_type=top_type.id "
                        + "LEFT JOIN bidover_db.trim ON auto.id_trim=trim.id "
                        + "LEFT JOIN bidover_db.wheels ON auto.id_wheels=wheels.id "
                        + "LEFT JOIN bidover_db.assembley ON auto.assembly_id=assembley.id "
                        + "INNER JOIN bidover_db.lot ON auto.id_lot=lot.id "
                        + "WHERE auto.id='" + id + "'");
                ResultSet resultSet = peparedStatement.executeQuery();
                if (resultSet.next()) {
                    auto = createLot(resultSet);
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
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT auto.id_lot FROM bidover_db.auto "
                        + "INNER JOIN bidover_db.lot ON auto.id_lot=lot.id WHERE auto.id='" + autoId + "'");
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
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT auto.id FROM bidover_db.auto "
                        + " WHERE auto.VIN='" + vin + "'");
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
        if (dbRequest.isEmpty()) {
            dbRequest = "1=1";
        }
        System.out.println(dbRequest);
        if (!dbRequest.isEmpty()) {
            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.auto "
                        + "INNER JOIN bidover_db.characteristics ON auto.id_characteristics=characteristics.id "
                        + "INNER JOIN bidover_db.lot ON auto.id=lot.id WHERE " + dbRequest);
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
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.auto "
                        + "INNER JOIN bidover_db.characteristics ON auto.id_characteristics=characteristics.id "
                        + "INNER JOIN bidover_db.lot ON auto.id_lot=lot.id WHERE lot.id='" + lotId + "'");
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
        if (dbRequest.isEmpty()) {
            dbRequest = "1=1";
        }
        System.out.println(dbRequest);
        if (!dbRequest.isEmpty()) {
            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.auto "
                        + "INNER JOIN bidover_db.characteristics ON auto.id_characteristics=characteristics.id "
                        + "INNER JOIN bidover_db.lot ON auto.id_lot=lot.id "
                        + "WHERE " + dbRequest + " AND auto.year<='" + yearEnd + "' AND auto.year>='" + yearBegin + "'");
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
            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.auto "
                    + "INNER JOIN bidover_db.options ON auto.id_options=options.id "
                    + "INNER JOIN bidover_db.characteristics ON auto.id_characteristics=characteristics.id "
                    + "INNER JOIN bidover_db.lot ON auto.id_lot=lot.id ");
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

    public void populateSpecificInfo(Auto auto, ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("auto.id");
        auto.setId(IntegerUtil.parseString(id));

        CharacteristicsDAO characteristicsDAO = new CharacteristicsDAO();
        auto.setCharacteristics(characteristicsDAO.createCharacteristics(resultSet));

        OptionDAO optionDAO = new OptionDAO();
        auto.setIdOptions(optionDAO.createOption(resultSet));

        BodyStyleDAO bodyStyleDAO = new BodyStyleDAO();
        auto.setIdBodyStyle(bodyStyleDAO.createBean(resultSet));

        DamageDAO damageDAO = new DamageDAO();
        auto.setDamage(damageDAO.createDamage(resultSet));

        DriveTrainDAO driveTrainDAO = new DriveTrainDAO();
        auto.setIdDriveTrain(driveTrainDAO.createBean(resultSet));

        ExteriorColorDAO exteriorColorDAO = new ExteriorColorDAO();
        auto.setIdExteriorColor(exteriorColorDAO.createBean(resultSet));

        FuelDAO fuelDAO = new FuelDAO();
        auto.setIdFuel(fuelDAO.createBean(resultSet));

        InteriorColorDAO interiorColorDAO = new InteriorColorDAO();
        auto.setIdInteriorColor(interiorColorDAO.createBean(resultSet));

        TopTypeDAO topTypeDAO = new TopTypeDAO();
        auto.setIdTopType(topTypeDAO.createBean(resultSet));

        TrimDAO trimDAO = new TrimDAO();
        auto.setTrim(trimDAO.createBean(resultSet));

        WheelsDAO wheelsDAO = new WheelsDAO();
        auto.setIdWheels(wheelsDAO.createBean(resultSet));

        CountryAssemblyDAO countryAssembleyDAO = new CountryAssemblyDAO();
        auto.setCountryAssembly(countryAssembleyDAO.createBean(resultSet));

        String doorsTxt = resultSet.getString("auto.doors");
        DoorDAO doorDAO = new DoorDAO();
        Door door = new Door();
        door.setId(IntegerUtil.parseString(doorsTxt));
        auto.setDoors(doorDAO.find(door));

        String engineTxt = resultSet.getString("auto.engine");
        EngineDAO engineDAO = new EngineDAO();
        Engine engine = new Engine();
        engine.setId(IntegerUtil.parseString(engineTxt));
        auto.setEngine(engineDAO.find(engine));

        String transmissionTxt = resultSet.getString("auto.transmission");
        TransmissionDAO transmissionDAO = new TransmissionDAO();
        Transmission transmission = new Transmission();
        transmission.setId(IntegerUtil.parseString(transmissionTxt));
        auto.setTransmission(transmissionDAO.find(transmission));

        String interiorTypeTxt = resultSet.getString("auto.interior_type");
        InteriorTypeDAO interiorTypeDAO = new InteriorTypeDAO();
        InteriorType interiorType = new InteriorType();
        interiorType.setId(IntegerUtil.parseString(interiorTypeTxt));
        auto.setInteriorType(interiorTypeDAO.find(interiorType));

        String tiresTxt = resultSet.getString("auto.id_tires");
        TiresDAO tiresDAO = new TiresDAO();
        Tires tires = new Tires();
        tires.setId(IntegerUtil.parseString(tiresTxt));
        auto.setIdTires(tiresDAO.find(tires));

        String year = resultSet.getString("auto.year");
        auto.setYear(IntegerUtil.parseString(year));

        Boolean salvage = resultSet.getBoolean("auto.salvage");
        auto.setSalvage(salvage);

        String odometer = resultSet.getString("auto.odometer");
        auto.setOdometer(IntegerUtil.parseString(odometer));
        
        String displacement = resultSet.getString("auto.displacement");
        auto.setDisplacement(IntegerUtil.parseString(displacement));

        String VIN = resultSet.getString("auto.VIN");
        auto.setVin(VIN);
    }

    private Auto createPreviewAuto(ResultSet resultSet) throws SQLException {
        Auto auto = new Auto();
        String id = resultSet.getString("auto.id");
        auto.setId(IntegerUtil.parseString(id));

        CharacteristicsDAO characteristicsDAO = new CharacteristicsDAO();
        auto.setCharacteristics(characteristicsDAO.createCharacteristics(resultSet));

        String doorsTxt = resultSet.getString("auto.doors");
        DoorDAO doorDAO = new DoorDAO();
        Door door = new Door();
        door.setId(IntegerUtil.parseString(doorsTxt));
        auto.setDoors(doorDAO.find(door));

        String engineTxt = resultSet.getString("auto.engine");
        EngineDAO engineDAO = new EngineDAO();
        Engine engine = new Engine();
        engine.setId(IntegerUtil.parseString(engineTxt));
        auto.setEngine(engineDAO.find(engine));

        String transmissionTxt = resultSet.getString("auto.transmission");
        TransmissionDAO transmissionDAO = new TransmissionDAO();
        Transmission transmission = new Transmission();
        transmission.setId(IntegerUtil.parseString(transmissionTxt));
        auto.setTransmission(transmissionDAO.find(transmission));

        String interiorTypeTxt = resultSet.getString("auto.interior_type");
        InteriorTypeDAO interiorTypeDAO = new InteriorTypeDAO();
        InteriorType interiorType = new InteriorType();
        interiorType.setId(IntegerUtil.parseString(interiorTypeTxt));
        auto.setInteriorType(interiorTypeDAO.find(interiorType));

        String tiresTxt = resultSet.getString("auto.id_tires");
        TiresDAO tiresDAO = new TiresDAO();
        Tires tires = new Tires();
        tires.setId(IntegerUtil.parseString(tiresTxt));
        auto.setIdTires(tiresDAO.find(tires));

        String year = resultSet.getString("auto.year");
        auto.setYear(IntegerUtil.parseString(year));

        Boolean salvage = resultSet.getBoolean("auto.salvage");
        auto.setSalvage(salvage);

        String odometer = resultSet.getString("auto.odometer");
        auto.setOdometer(IntegerUtil.parseString(odometer));

        String VIN = resultSet.getString("auto.VIN");
        auto.setVin(VIN);

//        // ---------------
//        LotDAO lotDAO = new LotDAO();
//        auto.setLot(lotDAO.createLotPreview(resultSet));
//        // ---------------

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
//            if (auto.getLot() != null && auto.getLot().getId() != null) {
//                if (!dbRequest.isEmpty()) {
//                    dbRequest += " AND ";
//                }
//                dbRequest += "auto.id_lot='" + auto.getLot().getId() + "' ";
//            }
            if (auto.getCharacteristics() != null && auto.getCharacteristics().getMake() != null && !auto.getCharacteristics().getMake().isEmpty()) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "characteristics.manufacturer='" + auto.getCharacteristics().getMake() + "' ";
            }
            if (auto.getCharacteristics() != null && auto.getCharacteristics().getModel() != null && !auto.getCharacteristics().getModel().isEmpty()) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "characteristics.model='" + auto.getCharacteristics().getModel() + "' ";
            }
            if (auto.getCharacteristics() != null && auto.getCharacteristics().getModification() != null && !auto.getCharacteristics().getModification().isEmpty()) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "characteristics.modification='" + auto.getCharacteristics().getModification() + "' ";
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
//            if (auto.getLot() != null && auto.getLot().getId() != null) {
//                if (!dbRequest.isEmpty()) {
//                    dbRequest += " AND ";
//                }
//                dbRequest += "auto.id='" + auto.getLot().getId() + "' ";
//            }
            if (auto.isSalvage() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "auto.salvage='" + auto.isSalvage() + "' ";
            }
            if (auto.getCountryAssembly() != null && auto.getCountryAssembly().getId() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "auto.assembly_id='" + auto.getCountryAssembly().getId() + "' ";
            }
        }
        return dbRequest;
    }

    @Override
    public Auto createNewInstance() {
        return new Auto();
    }

}
