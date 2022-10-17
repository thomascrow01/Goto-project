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
    public Date date;
    public int productID;

    public InvoiceEntity(){
        
    }

    public InvoiceEntity(Date date, int memberID) {
        this.date = date;
        this.memberID = memberID;
    }

    public InvoiceEntity(Date date){
        this.date = date;
    }

}
