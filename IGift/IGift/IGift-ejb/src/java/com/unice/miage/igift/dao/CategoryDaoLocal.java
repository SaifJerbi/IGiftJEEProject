/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unice.miage.igift.dao;

import javax.ejb.Local;

/**
 *
 * @author Root
 */
@Local
public interface CategoryDaoLocal {

    public void persist(java.lang.Object object);

    public void deleteAll();

    public java.util.Collection<com.unice.miage.igift.entity.Category> getCategoryList();

    public com.unice.miage.igift.entity.Category getCategoryByName(java.lang.String categoryName);

    public com.unice.miage.igift.entity.Category addCategory(java.lang.String name);

    public void createCategory();
    
}
