/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unice.miage.igift.dao;

import com.unice.miage.igift.entity.Category;
import com.unice.miage.igift.enumeration.CategoryEnum;
import java.util.Collection;
import java.util.Iterator;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Root
 */
@Stateless
public class CategoryDaoBean implements CategoryDaoLocal {

    @EJB
    private ProductDaoLocal productDaoBean;
    @PersistenceContext
    private EntityManager em;
    Category category;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")
    public void deleteAll() {
        for (Iterator it = getCategoryList().iterator(); it.hasNext();) {
            Object object = it.next();
            em.remove(object);

        }
        for (Iterator it = productDaoBean.getProductList().iterator(); it.hasNext();) {
            Object object = it.next();
            em.remove(object);
        }
    }

    public Collection<Category> getCategoryList() {
        return (Collection<Category>) em.createNamedQuery("Category.findAll").getResultList();
    }

    public Category getCategoryByName(String categoryName) {
        return (Category) em.createNamedQuery("Category.findByCategoryName").setParameter("categoryName", categoryName).getSingleResult();
    }

    public Category addCategory(String name) {
        category = new Category();
        category.setName(name);
        em.persist(category);
        return category;
    }

    public void createCategory() {
        addCategory(CategoryEnum.COSMETIC.getCategoryText());
        addCategory(CategoryEnum.GASTRONOMY.getCategoryText());
        addCategory(CategoryEnum.JEWEL.getCategoryText());
    }
}
