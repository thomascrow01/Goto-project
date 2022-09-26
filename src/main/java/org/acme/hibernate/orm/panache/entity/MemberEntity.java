package org.acme.hibernate.orm.panache.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Cacheable
public class MemberEntity extends PanacheEntity {

    @Column(length = 40, unique = true)
    public String name;
    public String email;
    public String mobile;
    public String address;

    public MemberEntity() {
    }

    public MemberEntity(String name, String email, String mobile, String address) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
    }

    public MemberEntity(String name, String email, String mobile) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }

    public MemberEntity(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public MemberEntity(String name) {
        this.name = name;
    }
}
