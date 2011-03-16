
package com.bidover.auto.controller.command;

import com.bidover.auto.model.bean.Currency;
import com.bidover.auto.model.bean.Country;
import com.bidover.auto.model.bean.Location;
import com.bidover.auto.database.dao.CurrencyDAO;
import com.bidover.auto.database.dao.CountryDAO;
import com.bidover.auto.database.dao.LocationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddSalesDetailsToAddCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public AddSalesDetailsToAddCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void execute() throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String country_code = "US";
        String atd_code = "";
        String currency_code = "USD";
        
        String sales_duration_list = createSalesDurationList();
        String fee_floor_price = setFeeFloorPrice();
        String country_list = createCountryList(country_code);
        String atd_list = createATDList(country_code, atd_code);
        String currency_list = createCurrencyList(currency_code);
 
        try {
            out.write("{'country_list':'"+country_list+"'," +
                      " 'atd_list':'"+atd_list+"'," +
                      " 'currency_list':'"+currency_list+"'," +
                      " 'fee_floor_price':'"+fee_floor_price+"'," +
                      " 'sales_duration_list':'"+sales_duration_list+"'}");
        }catch(Exception ex){
            ex.printStackTrace();
        } finally {
            out.close();
        }
    }


    private String createSalesDurationList() {
        String select = "<select id=\"sales_duration\" name=\"sales_duration\">" +
                "<option value=\"3\">3</option>" +
                "<option value=\"5\">5</option>" +
                "<option value=\"7\">7</option>" +
                "<option value=\"10\">10</option>" +
                "<option value=\"14\">14</option>" +
                "<option value=\"21\">21</option>" +
                "<option value=\"28\">28</option>" +
                "</select>";
        return select;
    }

        private String setFeeFloorPrice() {
        String fee = "1";
        return fee;
    }

    private String createCurrencyList(String currency_code) {
        String select = "";
        CurrencyDAO currencyDAO = new CurrencyDAO();
        List<Currency> currencies = currencyDAO.findAll();
        select += "<select name=\"currency_code\" id=\"currency_code\" onchange=\"\">";
        for (Currency currency : currencies) {
            if (currency.getCurrencyCode().equals(currency_code)) select += "<option selected value=\"" + currency.getCurrencyCode() + "\">" + currency.getCurrencyCode() + "</option>";
            else select += "<option value=\"" + currency.getCurrencyCode() + "\">" + currency.getCurrencyCode() + "</option>";
//            select += "<option value=\"" + currency.getCurrencyCode() + "\">" + currency.getCurrencyCode() + " (" + currency.getPlaceUsed() + ")</option>";
        }
        select += "</select>";
        return select;
    }

    private String createCountryList(String iso_cc2) {
        String select = "";
        CountryDAO countryDAO = new CountryDAO();
        List<Country> countries = countryDAO.findAll();
        select += "<select name=\"country_code\" id=\"country_code\" onchange=\"processATD();\">";
        for (Country country : countries) {
            if (country.getISO_CC2().equals(iso_cc2)) select += "<option selected value=\"" + country.getISO_CC2() + "\">" + country.getCountry() + "</option>";
            else select += "<option value=\"" + country.getISO_CC2() + "\">" + country.getCountry() + "</option>";
        }
        select += "</select>";
        return select;
    }

    private String createATDList(String country_code, String atd_code) {
        String select = "";
        LocationDAO locationDAO = new LocationDAO();
        List<Location> locations = locationDAO.findAllATDs(country_code);
        select += "<select name=\"atd_code\" id=\"atd_code\" onchange=\"processLocation();\">";
        for (Location location : locations) {
            if (location.getATDCode().equals(atd_code)) select += "<option selected value=\"" + location.getATDCode() + "\">" + location.getATD() + "</option>";
            else select += "<option value=\"" + location.getATDCode() + "\">" + location.getATD() + "</option>";
        }
        select += "</select>";
        return select;
    }
}
