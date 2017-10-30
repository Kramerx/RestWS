/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entidad;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author Jose
 */
@XmlRootElement 
@XmlSeeAlso(User.class)
public class Users extends ArrayList<User>{
    
    public Users(){
        super();
    }
    public Users(Collection<? extends User> c) {
        super(c);
    }
    
    @XmlElement(name="user")
    public List<User> getUsers(){
        return this;
    }
    
    public void setUsers(List<User> users){
        this.addAll(users);
    }
}
