/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elith.erreur;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;



/**
 *
 * @author ambk3201
 */
public class ErrorResponse {
    
    private int code ;
    private Status message ;
    
   
    public ErrorResponse() {
    }
    
    public ErrorResponse(int code, Status message){
        this.code = code ;
        this.message = message ;
    }

    public int getCode(){return this.code; }
    public Status getMessage(){ return this.message ;}
    
    public void setCode(int code){ this.code = code ; }
    public void setMessage(Status message){ this.message = message ; }    
    
    public void sendErrorJson(ContainerRequestContext requestContext){ 
        
        requestContext.abortWith(Response.status(message).
                entity(this)
                .type(MediaType.APPLICATION_JSON)
                .build()); 
    }
    
    /*
    public String errorToJSON(){
        String res = "{\"code\": " + this.code + ","
                + "\"message\": \"" + this.message 
                + "\"}";
        return res ;
    }
    */
    
}
