/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.detail.model.bean;

/**
 *
 * @author Jedai
 */
public class SubCategory {

    private Integer id = null;
    private String name = null;
    private Category category = null;

    public SubCategory() {
    }

    public SubCategory(Integer id) {
        this.id = id;
    }

    public SubCategory(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
