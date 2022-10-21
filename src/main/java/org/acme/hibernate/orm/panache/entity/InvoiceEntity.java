package org.acme.hibernate.orm.panache.entity;

import java.sql.Date;

import javax.persistence.Cacheable;
import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Cacheable
public class InvoiceEntity extends PanacheEntity {

    public int memberID;
    public String date;

    public InvoiceEntity(){
        
    }

    public InvoiceEntity(String date, int memberID) {
        this.date = date;
        this.memberID = memberID;
    }

    public InvoiceEntity(String date){
        this.date = date;
    }

}
