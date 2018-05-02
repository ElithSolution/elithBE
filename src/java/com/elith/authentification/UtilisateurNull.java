/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elith.authentification;

import com.elith.erreur.ErrorResponse;
import java.io.Serializable;
import javax.ws.rs.core.Response;

/**
 *
 * @author ambk3201
 */

public class UtilisateurNull extends UtilisateurAbstract implements Serializable {
    
    
    private ErrorResponse erreur ;

    /** Creates a new instance of Utilisateur */
    public UtilisateurNull() {
        erreur = new ErrorResponse(204, Response.Status.NO_CONTENT);
    }

 
    
    // getter   
    @Override
    public ErrorResponse getError(){return erreur ; }

    // setters   
    @Override
    public void setError(ErrorResponse erreur){this.erreur = erreur ; }
   
    @Override
    public boolean isNil() {
        return true;
    }
}
