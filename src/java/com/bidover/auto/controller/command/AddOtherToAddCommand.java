package com.bidover.auto.controller.command;

import com.bidover.auto.database.dao.CharacteristicsDAO;
import com.bidover.auto.model.bean.Characteristics;
import com.bidover.auto.model.bean.Modification;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddOtherToAddCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public AddOtherToAddCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void execute() throws ServletException, IOException {
        StringBuilder specification = new StringBuilder(500);
        String modificationStr = request.getParameter("modification");
        Modification modifictaion = new Modification();
        modifictaion.setTitle(modificationStr);

        specification.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        if (modificationStr != null && !"".equals(modificationStr)) {
            specification.append("<other-list>");
            specification.append(transformYearToXML(modificationStr));
            specification.append("</other-list>");
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/xml");
        response.getWriter().write(specification.toString());
    }

    private String transformYearToXML(String modification) {
        StringBuilder xml = new StringBuilder(100);
        xml.append("<year-list>");
        CharacteristicsDAO characteristicsDAO = new CharacteristicsDAO();
        List<Characteristics> characteristics = characteristicsDAO.findAllByModificationTitle(modification);
        int begYear = characteristics.get(0).getBegYear();
        int endYear = characteristics.get(0).getEndYear();
            int time = endYear - begYear;
            for (int i = 0; i <= time; i++) {
                int currentYear = begYear + i;
                xml.append("<year>");
                xml.append("<id>");
                xml.append(currentYear);
                xml.append("</id>");
                xml.append("<title>");
                xml.append(currentYear);
                xml.append("</title>");
                xml.append("</year>");
            }
        
        xml.append("</year-list>");
        return xml.toString();
    }

}
