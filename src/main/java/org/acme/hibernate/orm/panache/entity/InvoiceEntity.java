package org.acme.hibernate.orm.panache.entity;

import java.sql.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Cacheable
public class InvoiceEntity extends PanacheEntity {

    @Column(length = 40, unique = true)
    public int memberID;
    public int productID;
    public Date date;
    public int quantity;

    public InvoiceEntity(){
        
    }

    public InvoiceEntity(Date date, int quantity, int memberID, int productID) {
        this.date = date;
        this.quantity = quantity;
        this.memberID = memberID;
        this.productID = productID;
    }

    public InvoiceEntity(Date date, int quantity){
        this.date = date;
        this.quantity = quantity;
    }

    public InvoiceEntity(Date date){
        this.date = date;
    }

}
