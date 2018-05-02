/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elith.client;

import com.elith.API.UtilitaireAPI;
import com.elith.authentification.Role;
import com.elith.authentification.Secured;
import com.elith.connexion.DAOException;
import com.elith.erreur.ErrorResponse;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
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
@Path("client")
@Secured({Role.SUPER, Role.ADMIN, Role.THERAPEUTE})
public class ClientResource {

    @Context
    private UriInfo context;
       
    private ClientDAO clientDAO;
    private TraitementDAO traitementDAO;
    private ErrorResponse error = new ErrorResponse(200, Response.Status.OK) ;
    

    /**
     * Creates a new instance of ClientResource
     */
    public ClientResource() {
    }

    /**
     * Retrieves representation of an instance of com.elith.client.ClientResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ItemClientJSON getJson(@PathParam("id") String id,@Context SecurityContext securityContext) {        
        int idClinique = UtilitaireAPI.extractIdClinique(securityContext);
        ClientAbstract client = new ClientNull();
        
        try{
            clientDAO = new ClientDAO();
            // le client existe bien
            if(clientDAO.existeClientById(Integer.parseInt(id))){                    
                client = getClient(id, idClinique, null);
                if(client.isNil()) error = new ErrorResponse(403, Response.Status.FORBIDDEN); 
            // pas de client
            }else{                    
                error = new ErrorResponse(204, Response.Status.NO_CONTENT); 
            } 
        } catch (DAOException e) {
            error = new ErrorResponse(404, Response.Status.NOT_FOUND);   
        }
             
        return clientToJSON(client, error);
    }

    
      /**
     * PUT method for updating or creating an instance of ClientsResource
     * @param securityContext
     * @param client
     * @param content representation for the resource
     * @return 
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ItemClientJSON putJson(@Context SecurityContext securityContext, Client client) {
        int idClinique = UtilitaireAPI.extractIdClinique(securityContext);
        ClientAbstract clientMaJ = new ClientNull(); 
        
        try{
            
            if(client != null){
                clientDAO = new ClientDAO();
                // le client existe bien
                if(clientDAO.existeClientById(client.getId())){                    
                    clientDAO.updateClient(client); 
                    clientMaJ = getClient(String.valueOf(client.getId()), idClinique, client.getTraitements());
                    if(clientMaJ.isNil())error = new ErrorResponse(403, Response.Status.FORBIDDEN);
                // pas de client
                }else{                    
                    error = new ErrorResponse(204, Response.Status.NO_CONTENT); 
                }    
            }          
          
        } catch (DAOException e) {
            error = new ErrorResponse(404, Response.Status.NOT_FOUND);   
        } 
       
        return clientToJSON(clientMaJ, error);
    }
    
 
    
    @DELETE
    @Path("/{id}")
    public ErrorResponse deleteClient(@Context SecurityContext securityContext, @PathParam("id") String id) {  
        System.out.println("Delete client " + id);
        int idClinique = UtilitaireAPI.extractIdClinique(securityContext);
        boolean ok ;
        try{
            if (id != null) {
                clientDAO = new ClientDAO();
                ok = clientDAO.deleteClient(id, idClinique);
                if(ok) clientDAO.deleteTraitementClient(id);                
            }
                 
        }catch(DAOException e){
                error = new ErrorResponse(400,Response.Status.BAD_REQUEST);              
        }
        return error ;
    }
    
    
    /***************************************************************************/
    
    
    private ItemClientJSON clientToJSON(ClientAbstract client, ErrorResponse erreur){
        ItemClientJSON itemJSON ;
        List<ItemClient> listeItemsClient = new ArrayList<>();
         
        ItemClient itemClient = new ItemClient(client);          
        listeItemsClient.add(itemClient);
        itemJSON = new ItemClientJSON(erreur, listeItemsClient);
        
        return itemJSON ;
    }
    
    private ClientAbstract getClient(String idClient, int idClinique, List<Traitement> listeTraitements){
        ClientAbstract client ;          
        try{
            clientDAO = new ClientDAO();
            traitementDAO = new TraitementDAO();            
            client = clientDAO.findById(Integer.parseInt(idClient), idClinique);
            if(listeTraitements == null) listeTraitements = traitementDAO.getAllTraitementClient(Integer.parseInt(idClient), idClinique);
            client.setTraitements(listeTraitements);
                 
        }catch(NumberFormatException | DAOException e){
            client = new ClientNull();     
        }
        return client ;
    }
    
}
