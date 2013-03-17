/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unice.miage.igift.dao;

import com.unice.miage.igift.entity.LineItem;
import javax.ejb.Local;

/**
 *
 * @author Root
 */
@Local
public interface LineItemDaoLocal {

    public void persistLineItem(LineItem item);

    public void persist(java.lang.Object object);
    
}
