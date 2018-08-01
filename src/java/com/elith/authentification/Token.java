/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elith.authentification;


import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;




/**
 *
 * @author ambk3201
 */
public class Token {
    
    private String principalUsername ;
    
    public Token(String principalUsername){
        this.principalUsername = principalUsername ;
    }
    
    public String getPrincipalUsername(){ return this.principalUsername ; }
    public void setPrincipalUsername(String principalUsername){ this.principalUsername = principalUsername ; }
    
    /**
     * @return ************************************************************************/
 
    public int getIdCliniqueFromPrincipal(){
        int res = -1 ;            
        JsonObject obj = Json.createReader(new StringReader(principalUsername)).readObject() ;
        if(obj != null) res = Integer.valueOf(obj.get("idClinique").toString());
        return res ;
    }
    
    public int getIdUserFromPrincipal(){
        int res = -1 ;            
        JsonObject obj = Json.createReader(new StringReader(principalUsername)).readObject() ;
        if(obj != null) res = Integer.valueOf(obj.get("idUser").toString());
        return res ;
    }
    
    public String getRoleFromPrincipal(){
        return getStringFromPrincipal("role");
    }
     
    public String getUsernameFromPrincipal(){
        return getStringFromPrincipal("username");
    }
     
    private String getStringFromPrincipal(String objet){
        String res = "" ;            
        JsonObject obj = Json.createReader(new StringReader(principalUsername)).readObject() ;        
        if(obj != null) res = obj.getJsonString(objet).getString();
        return res ;
    }
}