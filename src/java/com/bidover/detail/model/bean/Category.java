/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bidover.detail.model.bean;

/**
 *
 * @author Jedai
 */
public class Category {
    private Integer id=null;
    private String name = null;

    public Category(){
    }

    public Category(Integer id){
        this.id = id;
    }


    public Category(Integer id,String name){
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
}
