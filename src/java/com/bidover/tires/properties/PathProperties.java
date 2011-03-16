/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.tires.properties;

import java.util.ResourceBundle;

/**
 *
 * @author Jedai
 * Класс считывает информацию из файла пропертис, который содержит информацию о
 * путях к страницам
 */
public class PathProperties {

    private ResourceBundle resourceBundle = null;
    private static PathProperties pathProperties = null;
    public static final String ADD_TIRES_PAGE = "ADD_TIRES_PAGE";
    public static final String SEARCH_TIRES_PAGE = "SEARCH_TIRES_PAGE";
    public static final String SEARCH_TIRES_RESULT_PAGE = "SEARCH_TIRES_RESULT_PAGE";
    public static final String TIRES_VIEW_PAGE = "TIRES_VIEW_PAGE";
    public static final String ADD_WHEELS_PAGE = "ADD_WHEELS_PAGE";
    public static final String SEARCH_WHEELS_PAGE = "SEARCH_WHEELS_PAGE";
    public static final String SEARCH_WHEELS_RESULT_PAGE = "SEARCH_WHEELS_RESULT_PAGE";
    public static final String WHEELS_VIEW_PAGE = "WHEELS_VIEW_PAGE";
    public static final String INDEX_PAGE = "INDEX_PAGE";

    private PathProperties() {

        resourceBundle = ResourceBundle.getBundle("tires.address");
    }

    public static PathProperties createPathProperties() {
        if (pathProperties == null) {
            pathProperties = new PathProperties();
        }
        return pathProperties;
    }

    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
