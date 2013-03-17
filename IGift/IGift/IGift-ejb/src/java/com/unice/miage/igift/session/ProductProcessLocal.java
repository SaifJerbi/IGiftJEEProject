/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unice.miage.igift.session;

import javax.ejb.Local;

/**
 *
 * @author Root
 */
@Local
public interface ProductProcessLocal {

    public void updateProductQuantity(Long id, int quantity);

    public void createProduct();
    
}
