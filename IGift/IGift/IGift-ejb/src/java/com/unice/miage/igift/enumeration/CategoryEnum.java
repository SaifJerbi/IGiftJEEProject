/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unice.miage.igift.enumeration;

/**
 *
 * @author Root
 */
public enum CategoryEnum {
    BOOK("LIVRE"),
    COSMETIC("COSMETIQUE"),
    CLOTHES("VETEMENT"),
    JEWEL("BIJOU"),
    HOUSE("MAISON"),
    GASTRONOMY("GASTRONOMIE");

    private String categoryText;

    CategoryEnum(String categoryText) {
        this.categoryText = categoryText;
    }

    public String getCategoryText() {
        return categoryText;
    }

    public void setCategoryText(String categoryText) {
        this.categoryText = categoryText;
    }


}
