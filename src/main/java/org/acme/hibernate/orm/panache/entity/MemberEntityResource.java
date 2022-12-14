package org.acme.hibernate.orm.panache.entity;

import java.util.List;
import java.util.regex.Pattern;

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

@Path("entity/member")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class MemberEntityResource {

    private static final Logger LOGGER = Logger.getLogger(MemberEntityResource.class.getName());

    @GET
    public List<MemberEntity> get() {
        return MemberEntity.listAll(Sort.by("name"));
    }

    @GET
    @Path("id={id}")
    public MemberEntity getSingle(Long id) {
        MemberEntity entity = MemberEntity.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Member with id of " + id + " does not exist.", 404);
        }
        return entity;
    }
    public static boolean isName(String in){
        return Pattern.matches("[a-zA-Z]+", in);
    }
 
    @GET
    @Path("email={input}")
    public List<MemberEntity> filterEmail(String input){
        
        return MemberEntity.list("email", Sort.by("name").and("id"), input);
    }

    @GET
    @Path("name={input}")
    public List<MemberEntity> filterName(String input){
        
        return MemberEntity.list("name", Sort.by("name").and("id"), input);
    }

    @GET
    @Path("mobile={input}")
    public List<MemberEntity> filterMobile(String input){
        
        return MemberEntity.list("mobile", Sort.by("name").and("id"), input);
    }


    @POST
    @Transactional
    public Response create(MemberEntity member) {



        if (member.id != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }

        if (!Pattern.matches("[a-zA-Z\\s]+", member.name)){
            throw new WebApplicationException("Invalid name.", 422);
        }

        if (member.email.length() > 0 && !Pattern.matches("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$", member.email)){

            throw new WebApplicationException("Invalid email.", 422);
        }
       


        

        member.persist();
        return Response.ok(member).status(201).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public MemberEntity update(Long id, MemberEntity member) {
        if (member.name == null) {
            throw new WebApplicationException("Member Name was not set on request.", 422);
        }

        MemberEntity entity = MemberEntity.findById(id);

        if (entity == null) {
            throw new WebApplicationException("Member with id of " + id + " does not exist.", 404);
        }

        if (!Pattern.matches("[a-zA-Z]+", member.name)){
            throw new WebApplicationException("Invalid name.", 422);
        }

        if (member.email.length() > 0 && !Pattern.matches("[a-z0-9._%+-]+@[a-z0-9.-]+[a-z]{2,}$", member.email)){

            throw new WebApplicationException("Invalid email.", 422);
        }
       


        entity.name = member.name;
        entity.email = member.email;
        entity.mobile = member.mobile;
        entity.address = member.address;

        return entity;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(Long id) {
        MemberEntity entity = MemberEntity.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Member with id of " + id + " does not exist.", 404);
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
