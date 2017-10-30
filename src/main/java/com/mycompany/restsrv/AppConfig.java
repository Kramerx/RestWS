/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.restsrv;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;

/**
 *
 * @author Jose
 */
@ApplicationPath("app")
public class AppConfig extends Application{
    private final Set<Class<?>> classes;
    
    public AppConfig(){
        HashSet<Class<?>> c = new HashSet<>();
        c.add(UsuarioRestService.class);
        c.add(MOXyJsonProvider.class);
        classes = Collections.unmodifiableSet(c);
    }
    
    @Override
    public Set<Class<?>> getClasses(){
        return classes;
    }
}