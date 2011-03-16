/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.detail.controller.command;

import com.bidover.detail.model.bean.Category;
import com.bidover.detail.model.bean.Make;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bidover.detail.properties.PathProperties;
import com.bidover.detail.model.logic.AddingDetailLogic;

/**
 *
 * @author Jedai
 */
public class AddDetailCommand implements ICommand {

    private AddingDetailLogic addingDetailLogic = new AddingDetailLogic();
    private final static String CATEGORY_ATTRIBUTE = "categoryList";
    private final static String MAKE_ATTRIBUTE = "makeList";

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categoryList = addingDetailLogic.findAllCategory();
        List<Make> makeList = addingDetailLogic.findAllMakes();
        request.setAttribute(CATEGORY_ATTRIBUTE, categoryList);
        request.setAttribute(MAKE_ATTRIBUTE, makeList);
        return PathProperties.createPathProperties().getProperty(PathProperties.ADD_DETAIL_PAGE);
    }

}
