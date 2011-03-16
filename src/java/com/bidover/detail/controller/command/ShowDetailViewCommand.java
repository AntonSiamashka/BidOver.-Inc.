/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.detail.controller.command;

import com.bidover.detail.model.bean.Detail;
import com.bidover.detail.properties.PathProperties;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bidover.detail.model.logic.SearchDetailsLogic;

/**
 *
 * @author Jedai
 */
public class ShowDetailViewCommand implements ICommand {

    private SearchDetailsLogic searchDetailLogic = new SearchDetailsLogic();
    private final static String DETAIL_ATTRIBUTE = "detail";
    private final static String DETAIL_ID_ATTRIBUTE = "detailID";

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter(DETAIL_ID_ATTRIBUTE);
        if (idStr != null) {
            Integer id = Integer.parseInt(idStr);
            Detail detail = searchDetailLogic.findDetailById(id);
            response.setContentType("application/xml");
            request.setAttribute(DETAIL_ATTRIBUTE, detail);
        }
        return PathProperties.createPathProperties().getProperty(PathProperties.DETAIL_VIEW_PAGE);
    }
}
