/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.detail.properties;

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
    public static final String ADD_CATEGORY_REQUEST = "ADD_CATEGORY_REQUEST";
    public static final String FIND_CATEGORY_BY_ID_REQUEST = "FIND_CATEGORY_BY_ID_REQUEST";
    public static final String FIND_ALL_CATEGORY_REQUEST = "FIND_ALL_CATEGORY_REQUEST";

    public static final String ADD_SUBCATEGORY_REQUEST = "ADD_SUBCATEGORY_REQUEST";
    public static final String FIND_SUBCATEGORY_BY_ID_REQUEST = "FIND_SUBCATEGORY_BY_ID_REQUEST";
    public static final String FIND_SUBCATEGORY_BY_CATEGORYID_REQUEST = "FIND_SUBCATEGORY_BY_CATEGORYID_REQUEST";
    public static final String FIND_ALL_SUBCATEGORY_REQUEST = "FIND_ALL_SUBCATEGORY_REQUEST";

    public static final String ADD_ITEM_REQUEST = "ADD_ITEM_REQUEST";
    public static final String FIND_ITEM_BY_ID_REQUEST = "FIND_ITEM_BY_ID_REQUEST";
    public static final String FIND_ITEM_BY_SUBCATEGORYID_REQUEST = "FIND_ITEM_BY_SUBCATEGORYID_REQUEST";
    public static final String FIND_ALL_ITEM_REQUEST = "FIND_ALL_ITEM_REQUEST";

    public static final String ADD_MAKE_REQUEST = "ADD_MAKE_REQUEST";
    public static final String FIND_MAKE_BY_ID_REQUEST = "FIND_MAKE_BY_ID_REQUEST";
    public static final String FIND_ALL_MAKES_REQUEST = "FIND_ALL_MAKES_REQUEST";
    public static final String INCREMENT_MAKE_REQUEST = "INCREMENT_MAKE_REQUEST";
    public static final String DECREMENT_MAKE_REQUEST = "DECREMENT_MAKE_REQUEST";

    public static final String ADD_MODEL_REQUEST = "ADD_MODEL_REQUEST";
    public static final String FIND_MODEL_BY_ID_REQUEST = "FIND_MODEL_BY_ID_REQUEST";
    public static final String FIND_MODEL_BY_MAKEID_REQUEST = "FIND_MODEL_BY_MAKEID_REQUEST";
    public static final String FIND_ALL_MODELS_REQUEST = "FIND_ALL_MODELS_REQUEST";
    public static final String INCREMENT_MODEL_REQUEST = "INCREMENT_MODEL_REQUEST";
    public static final String DECREMENT_MODEL_REQUEST = "DECREMENT_MODEL_REQUEST";

    public static final String ADD_DETAIL_REQUEST = "ADD_DETAIL_REQUEST";
    public static final String FIND_DETAIL_BY_ID_REQUEST = "FIND_DETAIL_BY_ID_REQUEST";
    public static final String FIND_ALL_DETAIL_REQUEST = "FIND_ALL_DETAIL_REQUEST";
    public static final String FIND_OTHER_REQUEST = "FIND_OTHER_REQUEST";

    public static final String LOG_PATH = "LOG_PATH";

    private DatabaseProperties() {
        resourceBundle = ResourceBundle.getBundle("detail.properties");
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
