package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.hibernate.orm.panache.entity.MemberEntity;

import io.quarkus.panache.common.Sort;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String hello() {

        MemberEntity.listAll(Sort.by("name"));

        return "<h1>Hello</h1> from RESTEasy Reactive"  ;
    }
}