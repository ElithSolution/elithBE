/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elith.client;

import com.elith.API.UtilitaireAPI;
import com.elith.authentification.Role;
import com.elith.authentification.Secured;
import com.elith.authentification.Token;
import com.elith.connexion.DAOException;
import com.elith.erreur.ErrorResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 * REST Web Service
 *
 * @author ambk3201
 */
@Path("clients")
@Secured({Role.SUPER, Role.ADMIN, Role.THERAPEUTE})
public class ClientsResource {

    @Context
    private UriInfo context;
    private SecurityContext security;
    
    private ClientDAO clientDAO;
    private ErrorResponse error = new ErrorResponse(200, Response.Status.OK) ;

    /**
     * Creates a new instance of ClientsResource
     */
    public ClientsResource() {
    }

    /**
     * Retrieves representation of an instance of com.elith.client.ClientsResource
     * @param securityContext
     * @return an instance of java.lang.String
     */
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ItemClientJSON getJson(@Context SecurityContext securityContext) {           
        String search = context.getQueryParameters().getFirst("search");        
        int idClinique = UtilitaireAPI.extractIdClinique(securityContext);
        
        /*********************************************/
        ItemClientJSON itemJSON ;
        ItemClient itemClient ;
        List<ItemClient> listeItemsClient = new ArrayList<>();
        /*********************************************/              
        List<Client> resultat = new ArrayList<>();                               
              
            try {  
                clientDAO = new ClientDAO();
                if (search != null)
                    resultat = clientDAO.findByNom(search, idClinique);
                else 
                    resultat = clientDAO.findAll(idClinique);               
                
            }catch(DAOException e){
                   error = new ErrorResponse(204, Response.Status.NO_CONTENT); 
            } 
             
        for(int  i = 0 ; i < resultat.size(); i++){
            itemClient = new ItemClient(resultat.get(i));
            listeItemsClient.add(itemClient);
        }
                
        itemJSON = new ItemClientJSON(error, listeItemsClient);
        return itemJSON ;    
    }

    /**
     * PUT method for updating or creating an instance of ClientsResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson() {
    }
    
}
