/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.detail.controller.command;

import com.bidover.common.controller.command.AddLotCommand;
import com.bidover.detail.model.bean.Category;
import com.bidover.detail.model.bean.Detail;
import com.bidover.detail.model.bean.Item;
import com.bidover.detail.model.bean.Make;
import com.bidover.detail.model.bean.Model;
import com.bidover.detail.model.bean.SubCategory;
import com.bidover.detail.properties.MessagesProperties;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bidover.detail.properties.PathProperties;
import com.bidover.detail.model.logic.AddingDetailLogic;

/**
 *
 * @author Jedai
 */
public class AddingDetailCommand implements ICommand {

    private AddingDetailLogic addingDetailLogic = new AddingDetailLogic();
    private final static String CATEGORY_ATTRIBUTE = "category-select";
    private final static String SUBCATEGORY_ATTRIBUTE = "sub-category-select";
    private final static String ITEM_ATTRIBUTE = "item-select";
    private final static String MAKE_ATTRIBUTE = "make-select";
    private final static String MODEL_ATTRIBUTE = "model-select";
    private final static String YEAR_BEGIN_ATTRIBUTE = "year-begin-select";
    private final static String YEAR_END_ATTRIBUTE = "year-end-select";

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryStr = request.getParameter(CATEGORY_ATTRIBUTE);
        String subCategoryStr = request.getParameter(SUBCATEGORY_ATTRIBUTE);
        String itemStr = request.getParameter(ITEM_ATTRIBUTE);
        String makeStr = request.getParameter(MAKE_ATTRIBUTE);
        String modelStr = request.getParameter(MODEL_ATTRIBUTE);
        String yearBeginStr = request.getParameter(YEAR_BEGIN_ATTRIBUTE);
        String yearEndStr = request.getParameter(YEAR_END_ATTRIBUTE);
        int categoryId = Integer.parseInt(categoryStr);
        int subCategoryId = Integer.parseInt(subCategoryStr);
        Integer itemId = null;
        if (itemStr != null) {
            itemId = Integer.parseInt(itemStr);
        }
        int makeId = Integer.parseInt(makeStr);
        int modelId = Integer.parseInt(modelStr);
        int yearBeginId = Integer.parseInt(yearBeginStr);
        int yearEndId = Integer.parseInt(yearEndStr);
        Detail detail = new Detail();
        detail.setBeginYear(yearBeginId);
        detail.setEndYear(yearEndId);
        detail.setCategory(new Category(categoryId));
        detail.setSubCategory(new SubCategory(subCategoryId));
        detail.setItem(new Item(itemId));
        detail.setMake(new Make(makeId));
        detail.setModel(new Model(modelId));
        AddLotCommand addLotCommand = new AddLotCommand();
        //Integer lotId = addLotCommand.addLot(request, response);
        //detail.setId(lotId);
        addingDetailLogic.addDetail(detail);
        return PathProperties.createPathProperties().getProperty(PathProperties.ADD_DETAIL_ESCAPE);
    }
}
