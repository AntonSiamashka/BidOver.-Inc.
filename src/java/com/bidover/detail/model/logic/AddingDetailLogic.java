/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.detail.model.logic;

import com.bidover.detail.database.dao.CategoryDAO;
import com.bidover.detail.database.dao.DetailDAO;
import com.bidover.detail.database.dao.ItemDAO;
import com.bidover.detail.database.dao.MakeDAO;
import com.bidover.detail.database.dao.ModelDAO;
import com.bidover.detail.database.dao.SubCategoryDAO;
import com.bidover.detail.model.bean.Category;
import com.bidover.detail.model.bean.Detail;
import com.bidover.detail.model.bean.Item;
import com.bidover.detail.model.bean.Make;
import com.bidover.detail.model.bean.Model;
import com.bidover.detail.model.bean.SubCategory;
import java.util.List;

/**
 *
 * @author Jedai
 */
public class AddingDetailLogic {

    private CategoryDAO categoryDAO = new CategoryDAO();
    private SubCategoryDAO subCategoryDAO = new SubCategoryDAO();
    private ItemDAO itemDAO = new ItemDAO();
    private MakeDAO makeDAO = new MakeDAO();
    private ModelDAO modelDAO = new ModelDAO();
    private DetailDAO detailDAO = new DetailDAO();

    public List<Category> findAllCategory() {
        return categoryDAO.findAllCategory();
    }

    public List<SubCategory> findSubCategoryByCategoryId(int id) {
        return subCategoryDAO.findSubCategoryByCategoryId(id);
    }

    public List<Item> findItemBySubCategoryId(int id) {
        return itemDAO.findItemsBySubCategoryId(id);
    }

    public List<Make> findAllMakes() {
        return makeDAO.findAll();
    }

    public List<Model> findModelByMakeId(int id) {
        return modelDAO.findModelByMakeId(id);
    }

    public Model findModelById(int id) {
        return modelDAO.findModelById(id);
    }

    public boolean addDetail(Detail detail) {
        boolean flag = false;
        Integer index = detailDAO.addDetail(detail);
        if (index != null) {
            flag = true;
        }
        return flag;
    }
}
