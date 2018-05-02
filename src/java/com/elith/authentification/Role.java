/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elith.authentification;

/**
 *
 * @author ambk3201
 */
public enum Role {
    SUPER("SUPER"),
    ADMIN("ADMIN"),
    THERAPEUTE("THERAPEUTE") ;
    
    private String name = "" ;
    
    Role(String name){
        this.name = name ;
    }
    
    public String toString(){
        return name ;
    }
}

