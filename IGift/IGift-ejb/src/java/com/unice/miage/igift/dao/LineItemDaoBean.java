/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unice.miage.igift.dao;

import com.unice.miage.igift.entity.LineItem;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Root
 */
@Stateless
public class LineItemDaoBean implements LineItemDaoLocal {

    @PersistenceContext
    private EntityManager em;

    public void persistLineItem(LineItem item) {
        em.persist(item);
    }

    public void persist(Object object) {
        em.persist(object);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")
}
