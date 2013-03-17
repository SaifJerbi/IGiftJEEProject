/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unice.miage.igift.session;

import com.unice.miage.igift.dao.CategoryDaoLocal;
import com.unice.miage.igift.dao.ProductDaoLocal;
import com.unice.miage.igift.entity.Product;
import com.unice.miage.igift.enumeration.CategoryEnum;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Root
 */
@Stateless
public class ProductProcessBean implements ProductProcessLocal {

    @EJB
    private CategoryDaoLocal categoryDaoBean;
    @EJB
    private ProductDaoLocal productDaoBean;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")
    public void updateProductQuantity(Long id, int quantity) {
        Product product = productDaoBean.getProductByProductId(id);
        int remainQuantity = product.getQuantity() - quantity;
        if (remainQuantity > 0) {
            product.setQuantity(remainQuantity);
        } else {
            product.setQuantity(0);
            product.setAvailability(false);
        }
    }

    public void createProduct() {
        productDaoBean.addProduct(new Product("Chance", "Chanel 50ml", "chanel-chance.bmp", 20, categoryDaoBean.getCategoryByName(CategoryEnum.COSMETIC.getCategoryText()), "Perfume", 70, true));
        productDaoBean.addProduct(new Product("Very irresistible", "Givenchy 50ml", "Givenchy-VERY_IRRESISTIBLE.bmp", 20, categoryDaoBean.getCategoryByName(CategoryEnum.COSMETIC.getCategoryText()), "Perfume", 60, true));
        productDaoBean.addProduct(new Product("J'adore", "Dior 50ml", "dior-jadore.bmp", 20, categoryDaoBean.getCategoryByName(CategoryEnum.COSMETIC.getCategoryText()), "Perfume", 70, true));
        productDaoBean.addProduct(new Product("Ring", "Gold ring", "ring-gold.bmp", 20, categoryDaoBean.getCategoryByName(CategoryEnum.JEWEL.getCategoryText()), "ring", 150, true));
        productDaoBean.addProduct(new Product("Bracelet tiffany", "Or blanc", "bracelet-tiffany.bmp", 20, categoryDaoBean.getCategoryByName(CategoryEnum.JEWEL.getCategoryText()), "bracelet", 100, true));
        productDaoBean.addProduct(new Product("Collier fantaisie", "Or 750", "collier-fantaisie.bmp", 20, categoryDaoBean.getCategoryByName(CategoryEnum.JEWEL.getCategoryText()), "collier", 386, true));
        productDaoBean.addProduct(new Product("Coffret epice", "Varieté 900g", "coffret-epice.bmp", 20, categoryDaoBean.getCategoryByName(CategoryEnum.GASTRONOMY.getCategoryText()), "epice", 60, true));
        productDaoBean.addProduct(new Product("Coffret chocolat", "Varieté 200g", "coffret-chocolat.bmp", 20, categoryDaoBean.getCategoryByName(CategoryEnum.GASTRONOMY.getCategoryText()), "chocolat", 35, true));
        productDaoBean.addProduct(new Product("Ferrero", "Ferrero 100g", "ferrero-rocher.bmp", 20, categoryDaoBean.getCategoryByName(CategoryEnum.GASTRONOMY.getCategoryText()), "chocolat", 15, true));
    }
}
