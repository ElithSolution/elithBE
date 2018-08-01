/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elith.API;

import com.elith.authentification.Token;
import com.elith.authentification.Utilisateur;
import com.elith.authentification.UtilisateurDAO;
import com.elith.clinique.ItemAbstract;
import com.elith.clinique.ItemJSON;
import com.elith.erreur.ErrorResponse;
import java.security.Principal;
import java.util.List;
import javax.ws.rs.core.SecurityContext;

/**
 *
 * @author ambk3201
 */
public class UtilitaireAPI {
    
       
    public static int extractIdClinique(SecurityContext securityContext){
        int idClinique;
        Principal principal = securityContext.getUserPrincipal();
        Token monToken = new Token(principal.getName());
        idClinique = monToken.getIdCliniqueFromPrincipal();
        return idClinique ;        
    }
    
    /*
    private static String extractLogin(SecurityContext securityContext){
        Principal principal = securityContext.getUserPrincipal();
        Token monToken = new Token(principal.getName());        
       return monToken.getUsernameFromPrincipal();        
    }
*/
    
    public static int extractIdUser(SecurityContext securityContext){
        int idUser;
        Principal principal = securityContext.getUserPrincipal();
        Token monToken = new Token(principal.getName());
        idUser = monToken.getIdUserFromPrincipal();
        System.out.println("idUser " + idUser);
        return idUser ;        
    }
    
    public static ItemJSON itemToJSON(ItemJSON itemJSON, List<ItemAbstract> listeItems, ErrorResponse erreur){  
        itemJSON.setErrorResponse(erreur);
        //itemJSON.setItems(listeItems);        
        return itemJSON ;
    }
}
