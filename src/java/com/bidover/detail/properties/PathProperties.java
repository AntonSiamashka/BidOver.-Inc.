/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.detail.properties;


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
    public static final String ADD_DETAIL_ESCAPE = "ADD_DETAIL_ESCAPE";
    public static final String ADD_DETAIL_PAGE = "ADD_DETAIL_PAGE";
    public static final String SEARCH_DETAIL_PAGE = "SEARCH_DETAIL_PAGE";
    public static final String SEARCH_DETAIL_RESULT_PAGE = "SEARCH_DETAIL_RESULT_PAGE";
    public static final String INDEX_PAGE = "INDEX_PAGE";
    public static final String DETAIL_VIEW_PAGE="DETAIL_VIEW_PAGE";

    private PathProperties()  {

       resourceBundle = ResourceBundle.getBundle("detail.address");
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
