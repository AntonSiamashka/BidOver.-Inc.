
package com.bidover.auto.controller.command;

import com.bidover.auto.model.bean.Location;
import com.bidover.auto.database.dao.LocationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ChangeLocationCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public ChangeLocationCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void execute() throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String select = "";
        String country_code = request.getParameter("country_code");
        String atd_code = request.getParameter("atd_code");
        if (atd_code != null && !"".equals(atd_code)) {
            LocationDAO locationDAO = new LocationDAO();
            List<Location> locations = locationDAO.findAllLocations(country_code, atd_code);
            select += "<select name=\"location_code\" id=\"location_code\" onchange=\"\">";
            for (Location location : locations) {
                select += "<option value=\"" + location.getLocationCode() + "\">" + location.getLocation() + "</option>";
            }
            select += "</select>";
        }
        try {
            out.print(select);
        } finally {
            out.close();
        }
    }
}
