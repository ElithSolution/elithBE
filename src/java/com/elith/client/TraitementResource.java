/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elith.client;

import com.elith.API.UtilitaireAPI;
import com.elith.authentification.Role;
import com.elith.authentification.Secured;
import com.elith.clinique.ItemAbstract;
import com.elith.clinique.ItemJSON;
import com.elith.connexion.DAOException;
import com.elith.erreur.ErrorResponse;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
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
@Path("traitement")
@Secured({Role.SUPER, Role.ADMIN, Role.THERAPEUTE})
public class TraitementResource {

    @Context
    private UriInfo context;
    
    private TraitementDAO traitementDAO;
    private ErrorResponse error = new ErrorResponse(200, Response.Status.OK) ;


    /**
     * Creates a new instance of TraitementResource
     */
    public TraitementResource() {
    }

    /**
     * Retrieves representation of an instance of com.elith.client.TraitementResource
     * @return an instance of java.lang.String
     */  
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TraitementAbstract getJson(@PathParam("id") String id,@Context SecurityContext securityContext) {
        System.out.println("traitement : " + id);
        TraitementAbstract traitement ;       
        int idClinique = UtilitaireAPI.extractIdClinique(securityContext);
        
        try{
            traitementDAO = new TraitementDAO();
            traitement = traitementDAO.findById(id, idClinique);
                 
        }catch(DAOException e){
            traitement = new TraitementNull(); 
        }
        return traitement ;
    }

    /**
     * PUT method for updating or creating an instance of TraitementResource
     * @param securityContext
     * @param traitement
     * @return 
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public ItemTraitementJSON putJson(@Context SecurityContext securityContext, Traitement traitement) {
        int idClinique = UtilitaireAPI.extractIdClinique(securityContext);
        TraitementAbstract traitementMaJ = new TraitementNull() ; 
                
        try{
            
            if(traitement != null){
                traitementDAO = new TraitementDAO();
                
                if(traitementDAO.existeTraitementById(traitement.getId())){                    
                    traitementDAO.updateTraitement(traitement);
                    traitementMaJ = getTraitement(String.valueOf(traitement.getId()), idClinique);
                    if(traitementMaJ.isNil())error = new ErrorResponse(403, Response.Status.FORBIDDEN);
                // pas de client
                
                }else{                    
                    error = new ErrorResponse(204, Response.Status.NO_CONTENT); 
                } 
                
            }              
          
        } catch (DAOException e) {
            error = new ErrorResponse(404, Response.Status.NOT_FOUND);   
        }  
       
        System.out.println("itemJSON " +traitementToJSON(traitementMaJ, error));
        return traitementToJSON(traitementMaJ, error);
    }
    
    
    @DELETE
    @Path("/{id}")
    public ErrorResponse deleteTraitement(@Context SecurityContext securityContext,@PathParam("id") String id) {  
        System.out.println("Delete traitement " + id);
        int idClinique = UtilitaireAPI.extractIdClinique(securityContext);
        try{
            if (id != null) {
                traitementDAO = new TraitementDAO();
                traitementDAO.deleteTraitement(id, idClinique);
            }
                 
        }catch(DAOException e){
                error = new ErrorResponse(400,Response.Status.BAD_REQUEST);              
        }
        return error ;
    }
    
    /***************************************************************************/
   
    private ItemTraitementJSON traitementToJSON(TraitementAbstract traitement, ErrorResponse erreur){
        ItemTraitementJSON itemJSON ;
        List<ItemTraitement> listeItemsTraitement = new ArrayList<>();
         
        ItemTraitement itemTraitement = new ItemTraitement(traitement);          
        listeItemsTraitement.add(itemTraitement);
        itemJSON = new ItemTraitementJSON(erreur, listeItemsTraitement);
        
        return itemJSON ;
    }

    
    /*
    private ItemJSON traitementToJSON(ItemAbstract itemTraitement, ErrorResponse erreur){
        ItemJSON itemJSON = new ItemTraitementJSON();
        List<ItemAbstract> listeItemsTraitement = new ArrayList<>();         
        listeItemsTraitement.add(itemTraitement);
        return UtilitaireAPI.itemToJSON(itemJSON, listeItemsTraitement, erreur);
    }
    */
    private TraitementAbstract getTraitement(String idTraitement, int idClinique){
        TraitementAbstract traitement ;          
        try{
            traitementDAO = new TraitementDAO();           
            traitement = traitementDAO.findById(idTraitement, idClinique);
                  
        }catch(NumberFormatException | DAOException e){
            traitement = new TraitementNull();     
        }
        return traitement ;
    }
}
