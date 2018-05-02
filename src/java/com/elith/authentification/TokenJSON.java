/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elith.authentification;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ambk3201
 */
@XmlRootElement(name = "tokenJSON")
public class TokenJSON implements Serializable{
    
    private String accessToken ;
    
    public TokenJSON(){
        
    }
    
    public TokenJSON(String accessToken){
        this.accessToken = accessToken ;
    }
    
    public String getAccessToken(){return this.accessToken;}
    public void setAccessToken(String accessToken){this.accessToken = accessToken ; }
    
    
    
}
