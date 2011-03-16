/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.detail.controller.command;

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
import com.bidover.detail.model.logic.SearchDetailsLogic;
import java.util.List;

/**
 *
 * @author Jedai
 */
public class SearchingDetailCommand implements ICommand {

    private SearchDetailsLogic searchDetailsLogic = new SearchDetailsLogic();
    private final static String CATEGORY_ATTRIBUTE = "category-select";
    private final static String SUBCATEGORY_ATTRIBUTE = "sub-category-select";
    private final static String ITEM_ATTRIBUTE = "item-select";
    private final static String MAKE_ATTRIBUTE = "make-select";
    private final static String MODEL_ATTRIBUTE = "model-select";
    private final static String YEAR_BEGIN_ATTRIBUTE = "year-begin-select";
    private final static String YEAR_END_ATTRIBUTE = "year-end-select";
    private final static String SEARCH_RESULT_ATTRIBUTE = "detailList";

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryStr = request.getParameter(CATEGORY_ATTRIBUTE);
        String subCategoryStr = request.getParameter(SUBCATEGORY_ATTRIBUTE);
        String itemStr = request.getParameter(ITEM_ATTRIBUTE);
        String makeStr = request.getParameter(MAKE_ATTRIBUTE);
        String modelStr = request.getParameter(MODEL_ATTRIBUTE);
        String yearBeginStr = request.getParameter(YEAR_BEGIN_ATTRIBUTE);
        String yearEndStr = request.getParameter(YEAR_END_ATTRIBUTE);
        Integer categoryId = null;
        Integer subCategoryId = null;
        Integer itemId = null;
        Integer makeId = null;
        Integer modelId = null;
        Integer yearBeginId = null;
        Integer yearEndId = null;
        if (categoryStr != null) {
            categoryId = Integer.parseInt(categoryStr);
        }
        if (subCategoryStr != null) {
            subCategoryId = Integer.parseInt(subCategoryStr);
        }
        if (itemStr != null) {
            itemId = Integer.parseInt(itemStr);
        }
        if (makeStr != null) {
            makeId = Integer.parseInt(makeStr);
        }
        if (modelStr != null) {
            modelId = Integer.parseInt(modelStr);
        }
        if (yearBeginStr != null && yearEndStr != null) {
            yearBeginId = Integer.parseInt(yearBeginStr);
            yearEndId = Integer.parseInt(yearEndStr);
        }
        Detail detail = new Detail();
        detail.setBeginYear(yearBeginId);
        detail.setEndYear(yearEndId);
        detail.setCategory(new Category(categoryId));
        detail.setSubCategory(new SubCategory(subCategoryId));
        detail.setItem(new Item(itemId));
        detail.setMake(new Make(makeId));
        detail.setModel(new Model(modelId));
        List<Detail> details = searchDetailsLogic.find(detail);
        request.setAttribute(SEARCH_RESULT_ATTRIBUTE, details);
        return PathProperties.createPathProperties().getProperty(PathProperties.SEARCH_DETAIL_RESULT_PAGE);
    }
}
