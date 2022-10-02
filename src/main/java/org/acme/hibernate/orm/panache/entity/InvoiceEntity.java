package org.acme.hibernate.orm.panache.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Cacheable
public class InvoiceEntity extends PanacheEntity {

    public int memberID;
    public int productID;

    public InvoiceEntity(){
        
    }

    public InvoiceEntity(int memberID, int productID) {
        this.memberID = memberID;
        this.productID = productID;
    }

}
