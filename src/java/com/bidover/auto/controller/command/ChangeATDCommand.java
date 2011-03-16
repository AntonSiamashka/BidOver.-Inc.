
package com.bidover.auto.controller.command;

import com.bidover.auto.model.bean.Location;
import com.bidover.auto.database.dao.LocationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ChangeATDCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public ChangeATDCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void execute() throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String select = "";
        String country_code = request.getParameter("country_code");
        if (country_code != null && !"".equals(country_code)) {
            LocationDAO locationDAO = new LocationDAO();
            List<Location> locations = locationDAO.findAllATDs(country_code);
            select += "<select name=\"atd_code\" id=\"atd_code\" onchange=\"processLocation();\">";
            for (Location location : locations) {
                select += "<option value=\"" + location.getATDCode() + "\">" + location.getATD() + "</option>";
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
