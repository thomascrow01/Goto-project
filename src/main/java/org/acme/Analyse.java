package org.acme;

import org.acme.hibernate.orm.panache.entity.MemberEntity;
public class Analyse {
    public int memberID;
    public String memberName;
    public long totalMoneySpent;

    public int favouriteProductID;
    public String favouriteProductName;

    // public Analyse(int memberID, String favouriteProduct) {
    //     this.memberID = memberID;
    //     this.favouriteProduct = favouriteProduct;
    // }

    public Analyse(Long memberID) {
        this.memberID = memberID.intValue();

        MemberEntity memberEntity = MemberEntity.findById(memberID);
        memberName = memberEntity.name;

    }

    public Analyse(){

    }

}
