/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.database.dao;

import com.bidover.auto.model.bean.Damage;
import com.bidover.auto.database.connectionpool.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author Jedai
 */
public class DamageDAO {

    private Connection connection;
    private PreparedStatement peparedStatement;
    private ConnectionPool connectionPool;

    public DamageDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
    }

    public int add(Damage damage) {
        int id = -1;
        try {
            String s = "INSERT INTO bidover_db.damage(" +
                    "hood," +
                    "roof," +
                    "windshield," +
                    "lf_door," +
                    "lr_door," +
                    "rf_door," +
                    "rr_door," +
                    "l_qtr_panel," +
                    "r_qtr_panel," +
                    "front_bumper," +
                    "rear_bumper," +
                    "lf_fender," +
                    "rf_fender," +
                    "deck_lid," +
                    "seats," +
                    "overall_vehicle" +
                    ") VALUES('" +
                    damage.getHood() + "','" +
                    damage.getRoof() + "','" +
                    damage.getWindshield() + "','" +
                    damage.getLfDoor() + "','" +
                    damage.getLrDoor() + "','" +
                    damage.getRfDoor() + "','" +
                    damage.getRrDoor() + "','" +
                    damage.getLQtrPanel() + "','" +
                    damage.getRQtrPanel() + "','" +
                    damage.getFrontBumper() + "','" +
                    damage.getRearBumper() + "','" +
                    damage.getLfFender() + "','" +
                    damage.getRfFender() + "','" +
                    damage.getDeckLid() + "','" +
                    damage.getSeats() + "','" +
                    damage.getOverallVehicle() + "')";

            executeRequest(s);
            id = find(damage).get(0).getId();

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(OptionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;
    }

    public List<Damage> find(Damage damage) {
        List<Damage> damages = null;
        String dbRequest = createDBRequest(damage);
        if (!dbRequest.isEmpty()) {
            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.damage WHERE " + dbRequest);
                ResultSet resultSet = peparedStatement.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.isFirst()) {
                        damages = new ArrayList<Damage>();
                    }
                    damages.add(createDamage(resultSet));
                }
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(OptionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return damages;
    }

    public Damage createDamage(ResultSet resultSet) throws SQLException {
        Damage damage = new Damage();
        String currentString = resultSet.getString("damage.id");
        if (currentString != null) {
            damage.setId(Integer.valueOf(currentString));
        } else {
            damage.setId(null);
        }
        currentString = resultSet.getString("damage.hood");
        if (currentString != null) {
            damage.setHood(Integer.valueOf(currentString));
        } else {
            damage.setHood(null);
        }
        currentString = resultSet.getString("damage.roof");
        if (currentString != null) {
            damage.setRoof(Integer.valueOf(currentString));
        } else {
            damage.setRoof(null);
        }
        currentString = resultSet.getString("damage.windshield");
        if (currentString != null) {
            damage.setWindshield(Integer.valueOf(currentString));
        } else {
            damage.setWindshield(null);
        }
        currentString = resultSet.getString("damage.lf_door");
        if (currentString != null) {
            damage.setLfDoor(Integer.valueOf(currentString));
        } else {
            damage.setLfDoor(null);
        }
        currentString = resultSet.getString("damage.lr_door");
        if (currentString != null) {
            damage.setLrDoor(Integer.valueOf(currentString));
        } else {
            damage.setLrDoor(null);
        }
        currentString = resultSet.getString("damage.rf_door");
        if (currentString != null) {
            damage.setRfDoor(Integer.valueOf(currentString));
        } else {
            damage.setRfDoor(null);
        }
        currentString = resultSet.getString("damage.rr_door");
        if (currentString != null) {
            damage.setRrDoor(Integer.valueOf(currentString));
        } else {
            damage.setRrDoor(null);
        }
        currentString = resultSet.getString("damage.l_qtr_panel");
        if (currentString != null) {
            damage.setLQtrPanel(Integer.valueOf(currentString));
        } else {
            damage.setLQtrPanel(null);
        }
        currentString = resultSet.getString("damage.r_qtr_panel");
        if (currentString != null) {
            damage.setRQtrPanel(Integer.valueOf(currentString));
        } else {
            damage.setRQtrPanel(null);
        }
        currentString = resultSet.getString("damage.front_bumper");
        if (currentString != null) {
            damage.setFrontBumper(Integer.valueOf(currentString));
        } else {
            damage.setFrontBumper(null);
        }
        currentString = resultSet.getString("damage.rear_bumper");
        if (currentString != null) {
            damage.setRearBumper(Integer.valueOf(currentString));
        } else {
            damage.setRearBumper(null);
        }
        currentString = resultSet.getString("damage.lf_fender");
        if (currentString != null) {
            damage.setLfFender(Integer.valueOf(currentString));
        } else {
            damage.setLfFender(null);
        }
        currentString = resultSet.getString("damage.rf_fender");
        if (currentString != null) {
            damage.setRfFender(Integer.valueOf(currentString));
        } else {
            damage.setRfFender(null);
        }
        currentString = resultSet.getString("damage.deck_lid");
        if (currentString != null) {
            damage.setDeckLid(Integer.valueOf(currentString));
        } else {
            damage.setDeckLid(null);
        }
        currentString = resultSet.getString("damage.seats");
        if (currentString != null) {
            damage.setSeats(Integer.valueOf(currentString));
        } else {
            damage.setSeats(null);
        }
        currentString = resultSet.getString("damage.overall_vehicle");
        if (currentString != null) {
            damage.setOverallVehicle(Integer.valueOf(currentString));
        } else {
            damage.setOverallVehicle(null);
        }
        return damage;
    }

    private void executeRequest(String request) throws SQLException {
        connection = connectionPool.getConnection();
        peparedStatement = (PreparedStatement) connection.prepareStatement(request);
        peparedStatement.execute();
        peparedStatement.close();
        connectionPool.releaseConnection(connection);
    }

    public String createDBRequest(Damage damage) {
        String dbRequest = "";
        if (damage != null) {
            if (damage.getHood() != null) {
                dbRequest += "damage.hood='" + damage.getHood() + "' ";
            }
            if (damage.getId() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "damage.id='" + damage.getId() + "' ";
            }
            if (damage.getRoof() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "damage.roof='" + damage.getRoof() + "' ";
            }
            if (damage.getWindshield() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "damage.windshield='" + damage.getWindshield() + "' ";
            }

            if (damage.getLfDoor() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "damage.lf_door='" + damage.getLfDoor() + "' ";
            }

            if (damage.getLrDoor() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "damage.lr_door='" + damage.getLrDoor() + "' ";
            }

            if (damage.getRfDoor() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "damage.rf_door='" + damage.getRfDoor() + "' ";
            }

            if (damage.getRrDoor() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "damage.rr_door='" + damage.getRrDoor() + "' ";
            }

            if (damage.getLQtrPanel() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "damage.l_qtr_panel='" + damage.getLQtrPanel() + "' ";
            }

            if (damage.getRQtrPanel() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "damage.r_qtr_panel='" + damage.getRQtrPanel() + "' ";
            }

            if (damage.getFrontBumper() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "damage.front_bumper='" + damage.getFrontBumper() + "' ";
            }

            if (damage.getRearBumper() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "damage.rear_bumper='" + damage.getRearBumper() + "' ";
            }

            if (damage.getLfFender() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "damage.lf_fender='" + damage.getLfFender() + "' ";
            }
            if (damage.getRfFender() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "damage.rf_fender='" + damage.getRfFender() + "' ";
            }
            if (damage.getDeckLid() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "damage.deck_lid='" + damage.getDeckLid() + "' ";
            }
            if (damage.getSeats() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "damage.seats='" + damage.getSeats() + "' ";
            }
            if (damage.getOverallVehicle() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "damage.overall_vehicle='" + damage.getOverallVehicle() + "' ";
            }

        }
        return dbRequest;
    }
}
