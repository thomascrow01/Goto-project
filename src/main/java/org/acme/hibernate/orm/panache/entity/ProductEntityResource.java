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

@Path("entity/product")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class ProductEntityResource {

    private static final Logger LOGGER = Logger.getLogger(ProductEntityResource.class.getName());

    @GET
    public List<ProductEntity> get() {
        return ProductEntity.listAll(Sort.by("name"));
    }

    @GET
    @Path("{id}")
    public ProductEntity getSingle(Long id) {
        ProductEntity entity = ProductEntity.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Product with id of " + id + " does not exist.", 404);
        }
        return entity;
    }

    @GET
    @Path("name={input}")
    public List<ProductEntity> filterName(String input){
        
        return ProductEntity.list("name", Sort.by("name").and("id"), input);
    }

    @POST
    @Transactional
    public Response create(ProductEntity Product) {
        if (Product.id != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }

        Product.persist();
        return Response.ok(Product).status(201).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public ProductEntity update(Long id, ProductEntity Product) {
        if (Product.name == null) {
            throw new WebApplicationException("Product Name was not set on request.", 422);
        }

        ProductEntity entity = ProductEntity.findById(id);

        if (entity == null) {
            throw new WebApplicationException("Product with id of " + id + " does not exist.", 404);
        }

        entity.name = Product.name;
        entity.description = Product.description;
        entity.price = Product.price;

        return entity;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(Long id) {
        ProductEntity entity = ProductEntity.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Product with id of " + id + " does not exist.", 404);
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
