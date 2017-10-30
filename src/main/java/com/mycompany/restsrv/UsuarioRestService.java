/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.restsrv;

import com.mycompany.entidad.User;
import com.mycompany.entidad.Users;
import java.net.URI;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("user")
@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
@Stateless
public class UsuarioRestService {
    @PersistenceContext(unitName = "com.mycompany_mavenproject2_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    @Context
    private UriInfo uriInfo;
    
    @GET
    public Response getUsuarios(){
        TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
        Users users = new Users(query.getResultList());
        return Response.ok(users).build();
    }
     
    @GET
    @Path("{id}")
    public Response getUsuario(@PathParam("id") int id){
        User u= em.find(User.class, id);
        if (u==null)
            throw new NotFoundException();
        return Response.ok(u).build();
    } 

    @POST
    public Response crearUsuario(User u){
        if (u==null)
            throw new BadRequestException();
        em.persist(u);
        URI userUri=uriInfo.getAbsolutePathBuilder().path(u.getIdusuario().toString()).build();
        return Response.created(userUri).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response eliminarUsuario(@PathParam("id") int id) {
        em.remove(em.find(User.class, id));
        return Response.noContent().build();
    }
}

