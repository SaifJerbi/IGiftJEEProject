/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unice.miage.igift.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Root
 */
@Entity
public class PurchaseOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany
    private Collection<LineItem> lineItems;
    @ManyToOne
    private Customer customer;
    private Timestamp creationDate;
    private double totalPrice;

    public Collection<LineItem> getLineItems() {
        return lineItems;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public void setLineItems(Collection<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchaseOrder)) {
            return false;
        }
        PurchaseOrder other = (PurchaseOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unice.miage.igift.entity.PurchaseOrder[id=" + id + "]";
    }

    public void addPurchase(LineItem item, int quantity, double price) {
        if (lineItems == null) {
            lineItems = new ArrayList<LineItem>();
        }
        //item.setOrder(this);
        item.setQuantity(item.getQuantity() + quantity);
        item.setSubTotal(item.getSubTotal() + (quantity * price));
        totalPrice += item.getSubTotal() + (quantity * price);
    }

    public void updatePurchaseAndUpdateLineItem(LineItem item, int quantity, double price) {
        if (lineItems == null) {
            lineItems = new ArrayList<LineItem>();
        }
        int oldQuantity = item.getQuantity();
        item.setQuantity(quantity);
        item.setSubTotal(quantity * price);
        totalPrice = totalPrice - (oldQuantity * price);
        totalPrice += quantity * price;
    }
    Long itemId = new Long(0);

    public LineItem addPurchaseAndLineItem(Product product, int quantity, double price) {

        if (lineItems == null || lineItems.isEmpty()) {
            lineItems = new ArrayList<LineItem>();
        }
        LineItem item = new LineItem();
        item.setId(itemId);
        item.setProduct(product);
        item.setQuantity(quantity);
        item.setSubTotal(quantity * price);
        lineItems.add(item);
        totalPrice += quantity * price;
        itemId = itemId + 1;
        return item;
    }
}
