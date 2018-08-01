/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elith.authentification;

import com.elith.connexion.DAOException;
import java.security.Principal;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 * REST Web Service
 *
 * @author ambk3201
 */
@Path("utilisateur")
@Secured({Role.SUPER, Role.ADMIN, Role.THERAPEUTE})
public class UtilisateurResource {
    
    private UtilisateurDAO utilisateurDAO;
    private Token monToken ;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UtilisateurResource
     */
    public UtilisateurResource() {
    }

    
        
    /**
     * Retrieves representation of an instance of com.elith.authentification.UtilisateurResource
     * @return an instance of java.lang.String
     */ 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public UtilisateurAbstract getJson(@PathParam("id") String id, @Context SecurityContext securityContext) {
        UtilisateurAbstract utilisateur = new Utilisateur() ;  
        
        Principal principal = securityContext.getUserPrincipal();
        monToken = new Token(principal.getName());
        String username = monToken.getUsernameFromPrincipal();
        int idClinique = monToken.getIdCliniqueFromPrincipal();
        System.out.println("username :" + username);
        System.out.println("idclinique : " + idClinique);
        
        //TODO return proper representation object                              
              
            try {
                utilisateurDAO = new UtilisateurDAO();
                if(id != null)
                    utilisateur = utilisateurDAO.findById(Integer.parseInt(id), idClinique) ;
            }catch(DAOException e){
                   System.out.println("DAOException " + e) ;
            } 

            if(utilisateur == null)                 
                utilisateur = new UtilisateurNull();

            return utilisateur;

    }

    /**
     * PUT method for updating or creating an instance of UtilisateurResource
     * @param util
     * @return 
     */
    @PUT
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putJson(Utilisateur util) {
          
        System.out.println("email : " + util.getEmail()); 
        System.out.println("id : " + util.getId()); 
        
        try{
             utilisateurDAO = new UtilisateurDAO();
             System.out.println("creation");
             utilisateurDAO.create(util);
        }catch(DAOException e){
             System.out.println("erreur");
        }
       
        String output = util.toString();
        return Response.status(200).entity(output).build();
    }
    
   
    
    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteUtilisateur(@PathParam("id") String id) {    
        try{
             utilisateurDAO = new UtilisateurDAO();
             if (id != null)
                utilisateurDAO.deleteUtilisateur(Integer.parseInt(id));
        }catch(DAOException e){
                  System.out.println("erreur");
        }
       

    }
}
