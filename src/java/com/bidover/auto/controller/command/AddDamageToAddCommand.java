package com.bidover.auto.controller.command;

import com.bidover.auto.model.bean.DamageCondition;
import com.bidover.auto.database.dao.DamageConditionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jedai
 */
public class AddDamageToAddCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public AddDamageToAddCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void execute() throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/xml");
        PrintWriter out = response.getWriter();
        DamageConditionDAO damageConditionDAO = new DamageConditionDAO();
        List<DamageCondition> damageConditionList = damageConditionDAO.findAll();
        String outputText = "";
        DamageCondition damageCondition = new DamageCondition();
        damageCondition.setId(0);
        damageCondition.setCondition("unknown");
        damageConditionList.add(damageCondition);
        outputText = transformDamageToXML("damage-condition", damageConditionList);
        try {
            out.write(outputText);
        } finally {
            out.close();
        }

    }

    private String transformDamageToXML(String name, List<DamageCondition> list) {
        StringBuilder xml = new StringBuilder(100);
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xml.append("<" + name + "-list>");
        for (DamageCondition element : list) {
            xml.append(element.toXMLString());
        }
        xml.append("</" + name + "-list>");
        return xml.toString();
    }
}
