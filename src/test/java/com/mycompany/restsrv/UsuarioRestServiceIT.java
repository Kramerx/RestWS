/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.restsrv;

import com.mycompany.entidad.User;
import com.mycompany.entidad.Users;
import java.net.URI;
import java.util.List;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.JAXBException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jose
 */
public class UsuarioRestServiceIT {
    private static URI uri = UriBuilder.fromUri("http://localhost/RestWS/app/user").port(20282).build();
    private static Client client = ClientBuilder.newClient();

    @Test
    public void noCrearNullUser() throws JAXBException {
        Response response = client.target(uri).request().post(Entity.entity(null, MediaType.APPLICATION_XML));
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }
    
    @Test
    public void noBuscaIdUser() throws JAXBException {
        Response response = client.target(uri).path("unknowId").request().get();
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }
    
    @Test
    public void creacionEliminacionUser() {
        User u = new User("TestName","TestLastname");
        Response response = client.target(uri).request().post(Entity.entity(u,MediaType.APPLICATION_XML));
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        List<User> users = client.target(uri).request().get(Users.class).getUsers();
        u=users.get(users.size()-1);
//        URI userURI = response.getLocation();
        response = client.target(uri).path(""+u.getIdusuario()).request().delete();
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }
}
