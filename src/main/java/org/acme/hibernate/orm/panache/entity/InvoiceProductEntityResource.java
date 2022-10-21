package org.acme.hibernate.orm.panache.entity;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.quarkus.panache.common.Sort;

@Path("entity/invoiceproduct")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class InvoiceProductEntityResource {

    private static final Logger LOGGER = Logger.getLogger(InvoiceProductEntityResource.class.getName());

    @GET
    public List<InvoiceProductEntity> get() {
        return InvoiceProductEntity.listAll(Sort.by("date"));
    }

    @GET
    @Path("{id}")
    public InvoiceProductEntity getSingle(Long id) {
        InvoiceProductEntity entity = InvoiceProductEntity.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Invoice with id of " + id + " does not exist.", 404);
        }
        return entity;
    }

    @GET
    @Path("invoiceID={id}")
    public List<InvoiceProductEntity> getInvoice(Long id) {

        return InvoiceProductEntity.list("invoiceID", Sort.by("id").and("quantity"), id);
    }

    @POST
    @Transactional
    public Response create(InvoiceProductEntity invoice) {
        if (invoice.id != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }

        invoice.persist();
        return Response.ok(invoice).status(201).build();
    }

    @POST
    @Transactional
    public Response createArray(InvoiceProductEntity[] invoices) {
        
        for(InvoiceProductEntity invoice : invoices){

            if (invoice.id != null) {
                throw new WebApplicationException("Id was invalidly set on request.", 422);
            }

            invoice.persist();
            
        }
        
        return Response.ok(invoices[0]).status(201).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public InvoiceProductEntity update(Long id, InvoiceProductEntity invoiceproduct) {
        InvoiceProductEntity entity = InvoiceProductEntity.findById(id);

        if (entity == null) {
            throw new WebApplicationException("Invoice with id of " + id + " does not exist.", 404);
        }

        entity.invoiceID = invoiceproduct.invoiceID;
        entity.productID = invoiceproduct.productID;
        entity.quantity = invoiceproduct.quantity;

        return entity;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(Long id) {
        InvoiceProductEntity entity = InvoiceProductEntity.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Invoice with id of " + id + " does not exist.", 404);
        }
        entity.delete();
        return Response.status(204).build();
    }

    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {

        @Inject
        ObjectMapper objectMapper;

        @Override
        public Response toResponse(Exception exception) {
            LOGGER.error("Failed to handle request", exception);

            int code = 500;
            if (exception instanceof WebApplicationException) {
                code = ((WebApplicationException) exception).getResponse().getStatus();
            }

            ObjectNode exceptionJson = objectMapper.createObjectNode();
            exceptionJson.put("exceptionType", exception.getClass().getName());
            exceptionJson.put("code", code);

            if (exception.getMessage() != null) {
                exceptionJson.put("error", exception.getMessage());
            }

            return Response.status(code)
                .entity(exceptionJson)
                .build();
        }

    }
}
