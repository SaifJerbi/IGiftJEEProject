/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unice.miage.igift.enumeration;

/**
 *
 * @author Root
 */
public enum BookEnum {
    BOOK("BOOK"),
    MAGAZINE("MAGAZINE");

    private String bookText;

    BookEnum(String bookText){
        this.bookText = bookText;
    }

    public String getBookText() {
        return bookText;
    }

    public void setBookText(String bookText) {
        this.bookText = bookText;
    }

}
