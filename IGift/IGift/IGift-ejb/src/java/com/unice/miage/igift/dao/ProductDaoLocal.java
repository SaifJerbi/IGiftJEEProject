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
public interface ProductDaoLocal {

    public com.unice.miage.igift.entity.Product getProductByProductId(java.lang.Long productId);

    public void persist(java.lang.Object object);

    public java.util.Collection<com.unice.miage.igift.entity.Product> getProductList();

    public void addProduct(com.unice.miage.igift.entity.Product product);

    public java.util.Collection<com.unice.miage.igift.entity.Product> getProductListByCategoryId(java.lang.Long catgoryId);
    
}
