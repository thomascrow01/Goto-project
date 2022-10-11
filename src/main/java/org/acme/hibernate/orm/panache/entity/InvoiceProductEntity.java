package org.acme.hibernate.orm.panache.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Cacheable
public class InvoiceProductEntity extends PanacheEntity {

    @Column(length = 40, unique = true)
    public int invoiceID;
    public int productID;
    public int quantity;

    public InvoiceProductEntity(){
        
    }

    public InvoiceProductEntity(int quantity, int productID, int invoiceID) {
        this.quantity = quantity;
        this.productID = productID;
        this.invoiceID = invoiceID;
    }

    public InvoiceProductEntity(int quantity){
        this.quantity = quantity;
    }

}
