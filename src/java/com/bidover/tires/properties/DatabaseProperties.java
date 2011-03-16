/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.tires.properties;

import java.util.ResourceBundle;

/**
 *
 * @author Jedai
 *
 * Класс считывает информацию из файла пропертис, который содержит информацию о
 * базе данных и запросах к ней.
 */
public class DatabaseProperties {

    private ResourceBundle resourceBundle = null;
    private static DatabaseProperties databaseProperties = null;
    public static final String ADD_TIRES_REQUEST = "ADD_TIRES_REQUEST";
    public static final String FIND_TIRES_BY_ID_REQUEST = "FIND_TIRES_BY_ID_REQUEST";
    public static final String FIND_ALL_TIRES_REQUEST = "FIND_ALL_TIRES_REQUEST";
    public static final String FIND_OTHER_TIRES_REQUEST = "FIND_OTHER_TIRES_REQUEST";
    public static final String ADD_WHEELS_REQUEST = "ADD_WHEELS_REQUEST";
    public static final String FIND_WHEELS_BY_ID_REQUEST = "FIND_WHEELS_BY_ID_REQUEST";
    public static final String FIND_ALL_WHEELS_REQUEST = "FIND_ALL_WHEELS_REQUEST";
    public static final String FIND_OTHER_WHEELS_REQUEST = "FIND_OTHER_WHEELS_REQUEST";
    public static final String LOG_PATH = "LOG_PATH";
    public static final String ADD_TIRES_WIDTH_REQUEST = "ADD_TIRES_WIDTH_REQUEST";
    public static final String FIND_TIRES_WIDTH_BY_ID_REQUEST = "FIND_TIRES_WIDTH_BY_ID_REQUEST";
    public static final String FIND_ALL_TIRES_WIDTH_REQUEST = "FIND_ALL_TIRES_WIDTH_REQUEST";
    public static final String ADD_TIRES_DIAMETER_REQUEST = "ADD_TIRES_DIAMETER_REQUEST";
    public static final String FIND_TIRES_DIAMETER_BY_ID_REQUEST = "FIND_TIRES_DIAMETER_BY_ID_REQUEST";
    public static final String FIND_ALL_TIRES_DIAMETER_REQUEST = "FIND_ALL_TIRES_DIAMETER_REQUEST";
    public static final String ADD_TIRES_CONTOUR_REQUEST = "ADD_TIRES_CONTOUR_REQUEST";
    public static final String FIND_TIRES_CONTOUR_BY_ID_REQUEST = "FIND_TIRES_CONTOUR_BY_ID_REQUEST";
    public static final String FIND_ALL_TIRES_CONTOUR_REQUEST = "FIND_ALL_TIRES_CONTOUR_REQUEST";
    public static final String ADD_TIRES_MAKE_REQUEST = "ADD_TIRES_MAKE_REQUEST";
    public static final String FIND_TIRES_MAKE_BY_ID_REQUEST = "FIND_TIRES_MAKE_BY_ID_REQUEST";
    public static final String FIND_ALL_TIRES_MAKE_REQUEST = "FIND_ALL_TIRES_MAKE_REQUEST";
    public static final String ADD_TIRES_SEASON_REQUEST = "ADD_TIRES_SEASON_REQUEST";
    public static final String FIND_TIRES_SEASON_BY_ID_REQUEST = "FIND_TIRES_SEASON_BY_ID_REQUEST";
    public static final String FIND_ALL_TIRES_SEASON_REQUEST = "FIND_ALL_TIRES_SEASON_REQUEST";
    public static final String ADD_WHEELS_WIDTH_REQUEST = "ADD_WHEELS_WIDTH_REQUEST";
    public static final String FIND_WHEELS_WIDTH_BY_ID_REQUEST = "FIND_WHEELS_WIDTH_BY_ID_REQUEST";
    public static final String FIND_ALL_WHEELS_WIDTH_REQUEST = "FIND_ALL_WHEELS_WIDTH_REQUEST";
    public static final String ADD_WHEELS_DIAMETER_REQUEST = "ADD_WHEELS_DIAMETER_REQUEST";
    public static final String FIND_WHEELS_DIAMETER_BY_ID_REQUEST = "FIND_WHEELS_DIAMETER_BY_ID_REQUEST";
    public static final String FIND_ALL_WHEELS_DIAMETER_REQUEST = "FIND_ALL_WHEELS_DIAMETER_REQUEST";
    public static final String ADD_WHEELS_BCD_REQUEST = "ADD_WHEELS_BCD_REQUEST";
    public static final String FIND_WHEELS_BCD_BY_ID_REQUEST = "FIND_WHEELS_BCD_BY_ID_REQUEST";
    public static final String FIND_ALL_WHEELS_BCD_REQUEST = "FIND_ALL_WHEELS_BCD_REQUEST";
    public static final String ADD_WHEELS_ED_REQUEST = "ADD_WHEELS_ED_REQUEST";
    public static final String FIND_WHEELS_ED_BY_ID_REQUEST = "FIND_WHEELS_ED_BY_ID_REQUEST";
    public static final String FIND_ALL_WHEELS_ED_REQUEST = "FIND_ALL_WHEELS_ED_REQUEST";

    private DatabaseProperties() {
        resourceBundle = ResourceBundle.getBundle("tires.properties");
    }

    public static DatabaseProperties createDatabaseProperties() {
        if (databaseProperties == null) {
            databaseProperties = new DatabaseProperties();
        }
        return databaseProperties;
    }

    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
