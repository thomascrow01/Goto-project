package org.acme;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;


import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;

import javax.ws.rs.Produces;

import org.acme.hibernate.orm.panache.entity.InvoiceEntity;
import org.acme.hibernate.orm.panache.entity.ProductEntity;
import org.acme.hibernate.orm.panache.entity.InvoiceProductEntity;



import io.quarkus.panache.common.Sort;

@Path("analyse")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class AnalyseResource {

    @GET
    @Path("{id}")
    public Analyse[] get(Long id) {

        Analyse[] output = new Analyse[1];
        output[0] = new Analyse(id);
        
        HashMap<Integer, Integer> tally = new HashMap<Integer, Integer>();
        int currentMax = -1; // hoping this doesnt break the csv thing if this runs without any invoices or anything

        long totalMoneySpent = 0;

        List<InvoiceEntity> invoices = InvoiceEntity.list("memberID", Sort.by("id"), id.intValue());

        for(InvoiceEntity invoice : invoices){

            List<InvoiceProductEntity> products = InvoiceProductEntity.list("invoiceID", Sort.by("id").and("quantity"), invoice.id.intValue());

            for(InvoiceProductEntity invoiceProduct : products){

                ProductEntity product = ProductEntity.findById(Long.valueOf(invoiceProduct.productID));

                totalMoneySpent += product.price * invoiceProduct.quantity;

                // should just cache here, maybe ill do it later
                tally.put(invoiceProduct.productID, tally.get(invoiceProduct.productID) != null ? tally.get(invoiceProduct.productID) + 1 : 0 );

                currentMax = tally.get(currentMax) == null || tally.get(invoiceProduct.productID) > tally.get(currentMax) ? tally.get(invoiceProduct.productID) : tally.get(currentMax);

            }

        }

        output[0].totalMoneySpent = totalMoneySpent;
        output[0].favouriteProductID = currentMax;
        //ProductEntity product = ProductEntity.findById(Long.valueOf(currentMax));
        //output.favouriteProductName = product.name;

        return output;

    }
}