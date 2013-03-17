/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unice.miage.igift.tools;

/**
 *
 * @author Root
 */
public class HelperFunctions {

    public static boolean isSet(String str) {
        return str != null && str.trim().length() > 0;
    }

    public static boolean isIntegerSet(String quantityStr) {
        try {
            Integer.parseInt(quantityStr);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
