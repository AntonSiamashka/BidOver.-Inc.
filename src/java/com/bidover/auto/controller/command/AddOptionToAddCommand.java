
package com.bidover.auto.controller.command;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jedai
 */
public class AddOptionToAddCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public AddOptionToAddCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void execute() throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String outputText = "";
        outputText += "<table>";
        outputText += createRadioButton("pwr_locks");
        outputText += createRadioButton("pwr_windows");
        outputText += createRadioButton("pwr_steering");
        outputText += createRadioButton("pwr_mirrors");
        outputText += createRadioButton("pwr_seats");
        outputText += createRadioButton("pwr_seats_drivers_only");
        outputText += createRadioButton("tilt_steering");
        outputText += createRadioButton("air_conditioning");
        outputText += createRadioButton("rear_air_conditioning");
        outputText += createRadioButton("cruise_ctl");
        outputText += createRadioButton("rear_defrost");
        outputText += createRadioButton("side_air_bags");
        outputText += createRadioButton("dual_air_bags");
        outputText += createRadioButton("fog_lamps");
        outputText += createRadioButton("trip_counter");
        outputText += createRadioButton("luggage_rack");
        outputText += createRadioButton("third_row_seat");
        outputText += createRadioButton("xenon_headlights");
        outputText += createRadioButton("cd_player");
        outputText += createRadioButton("cd_changer");
        outputText += createRadioButton("rear_wiper");
        outputText += createRadioButton("floor_mats");
        outputText += createRadioButton("security_system");
        outputText += createRadioButton("traction_control");
        outputText += createRadioButton("rear_tail");
        outputText += createRadioButton("lift_gate");
        outputText += createRadioButton("heated_seats");
        outputText += createRadioButton("tinted_windows");
        outputText += createRadioButton("homelink");
        outputText += createRadioButton("tire_pressure_monitor_system");
        outputText += createRadioButton("steering_wheel_audio_control");
        outputText += createRadioButton("spoiler");
        outputText += createRadioButton("bug_deflector");
        outputText += createRadioButton("keyless_entry");
        outputText += createRadioButton("hand_tool_kit");
        outputText += createRadioButton("dual_sliding_side_doors");
        outputText += createRadioButton("power_vent_windows");
        outputText += createRadioButton("front_center_console");
        outputText += createRadioButton("stow_and_go");
        outputText += createRadioButton("stow_and_go_3rd_row_only");
        outputText += createRadioButton("wood_trim_dash");
        outputText += createRadioButton("woodgrain_interior_package");
        outputText += "</table>";
        try {
            out.print(outputText);
        } finally {
            out.close();
        }

    }

    private String createRadioButton(String name) {

        String outputText = "";
        outputText += "<tr>";
        outputText += "<td>" + name + "</td><td><select name=\"" + name + "\" id=\"" + name + "\">";
        outputText += "<option value=\"" + 0 + "\">Не указано</option>";
        outputText += "<option value=\"" + 1 + "\">Да</option>";
        outputText += "<option value=\"" + 2 + "\">Нет</option>";
        outputText += "</td></tr>";
        return outputText;

    }
}
