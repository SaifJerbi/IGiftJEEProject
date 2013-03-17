/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unice.miage.igift.dao;

import com.unice.miage.igift.entity.Product;
import com.unice.miage.igift.enumeration.CategoryEnum;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Root
 */
@Stateless
public class ProductDaoBean implements ProductDaoLocal {

    @EJB
    private CategoryDaoLocal categoryDaoBean;
    @PersistenceContext
    private EntityManager em;
    Product product;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")
    public Product getProductByProductId(Long productId) {
        return (Product) em.createNamedQuery("Product.findByProductId").setParameter("productId", productId).getSingleResult();
    }

    public Collection<Product> getProductList() {
        return (Collection<Product>) em.createNamedQuery("Product.findAll").getResultList();
    }

    public void addProduct(Product product) {
        em.persist(product);
    }

    public Collection<Product> getProductListByCategoryId(Long catgoryId) {
        return (Collection<Product>) em.createNamedQuery("Product.findByCategoryId").setParameter("catgoryId", catgoryId).getResultList();
    }

    public void persist(Object object) {
        em.persist(object);
    }
}
