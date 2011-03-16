/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.tires.properties;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Jedai
 * Класс считывает информацию из файла пропертис, который содержит информацию о
 * сообщенияхпользователю
 */
public class MessagesProperties {

    private ResourceBundle resourceBundle = null;
    private static MessagesProperties messagesProperties = null;
    public static final String WRONG_PASS = "msg.wrongPass";
    public static final String WRONG_REPEAT_PASS = "msg.wrongRepeatPass";
    public static final String WRONG_LOGIN = "msg.wrongLogin";
    public static final String WRONG_FIRST_NAME = "msg.wrongFirstName";
    public static final String WRONG_SECOND_NAME = "msg.wrongSecondName";
    public static final String LOGIN_EXIST = "msg.loginExist";
    public static final String ERROR_LOGIN = "msg.errorLogin";
    public static final String WAIT_DEMAND = "msg.waitDemand";
    public static final String ADD_SERVICE_FAIL = "msg.errorAddingService";
    public static final String ADD_TARIF_FAIL = "msg.errorAddingTarification";
    public static final String CALCULATE_SUCCESSFUL = "msg.calcBills";
    public static final String ADDING_SERVICE_SUCCESSFUL = "msg.addingService";
    public static final String ADDING_TARIFICATION_SUCCESSFUL = "msg.addingTarification";
    public static final String BILL_PAID_SUCCESSFUL = "msg.billPaidSuccessful";
    public static final String BILL_PAID_FAIL = "msg.billPaidFail";
    public static final String REGISTER_SUCCESSFUL = "msg.registerSuccessful";
    public static final String CHANGE_ABONENT_INFO_SUCCESSFUL = "msg.changeAbonentInfoSuccessful";
    public static final String CHANGE_ADMIN_INFO_SUCCESSFUL = "msg.changeAdminInfoSuccessful";

    private MessagesProperties() {
        resourceBundle = ResourceBundle.getBundle("tires.messages");
    }

    public static MessagesProperties createPathProperties() {
        if (messagesProperties == null) {
            messagesProperties = new MessagesProperties();
        }
        return messagesProperties;
    }

    public String getProperties(String key, String language) {
        Locale locale = new Locale(language);
        resourceBundle = ResourceBundle.getBundle("messages", locale);
        return resourceBundle.getString(key);
    }
}

