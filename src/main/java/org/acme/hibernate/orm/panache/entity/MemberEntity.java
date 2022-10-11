package org.acme.hibernate.orm.panache.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Cacheable
public class MemberEntity extends PanacheEntity {

    public String name;
    public String email;
    public String mobile;
    public String address;

    public MemberEntity() {
    }

    
}
