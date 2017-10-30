/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.restsrv;

import com.mycompany.entidad.User;
import java.net.URI;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jose
 */
public class UsuarioRestServiceTest {
    private static URI uri = UriBuilder.fromUri("http://localhost/RestWS/app/user").port(20282).build();
    private static Client client = ClientBuilder.newClient();
    
    public UsuarioRestServiceTest() {
    }
    
    /**
     * Test of getUsuarios method, of class UsuarioRestService.
     */
    @Test
    public void validarUrl(){
        String url= "http://localhost/RestWS/app";
        URI uri = UriBuilder.fromUri(url).port(20282).path("user").path("1").build();
        assertEquals("http://localhost:20282/RestWS/app/user/1",uri.toString());

        uri=UriBuilder.fromUri(url).port(20282).path("user").build();
        assertEquals("http://localhost:20282/RestWS/app/user",uri.toString());
    }

    /**
     * Test of getUsuarios method, of class UsuarioRestService.
     */
    @Test
    public void testIngreso(){
        User u = null;//new User("TestName","TestLastname");
//        Users urs = client.target(uri).request().get(Users.class);
//        for (User a: urs.getUsers()){
//            System.out.println(a.getNombre());
//        }
        Response response = client.target(uri).request().post(Entity.entity(u,MediaType.APPLICATION_XML));
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
//        response=client.target(response.getLocation()).request().get();
//        u=response.readEntity(User.class);
//        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
//        assertEquals("TestName", u.getNombre());
    }
    
    /**
     * Test of getUsuarios method, of class UsuarioRestService.
     */
    @Test
    public void testBuscar(){
        int id=1;       
        Response response = client.target(uri).path(""+id).request().get();
//        User u = response.readEntity(User.class);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}
