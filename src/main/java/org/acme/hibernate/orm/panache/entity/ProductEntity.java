package org.acme.hibernate.orm.panache.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Cacheable
public class ProductEntity extends PanacheEntity {

    public String name;
    public String description;
    public float price;

    public ProductEntity() {
    }

    public ProductEntity(String name, String description, float price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public ProductEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ProductEntity(String name) {
        this.name = name;
    }
}
