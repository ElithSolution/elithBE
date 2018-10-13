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
@Path("typeX")
@Secured({Role.SUPER, Role.ADMIN, Role.THERAPEUTE})
public class TypeXResource {

    @Context
    private UriInfo context;
    
    private TypeXDAO typeXDAO ;
     //typeTarifDAO = new TypeXDAO("idtypetarif", "tarif", "typetarif", "ordre");
    private ErrorResponse error = new ErrorResponse(200, Response.Status.OK) ;

    /**
     * Creates a new instance of TypeXResource
     */
    public TypeXResource() {
    }

    /**
     * Retrieves representation of an instance of com.elith.client.TypeXResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    
    
    @GET
    @Path("/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public  ItemTypeXJSON getJson(@PathParam("type") String type,@Context SecurityContext securityContext) {
        List<TypeX> listeTypes = new ArrayList<>();        
        ItemTypeXJSON itemJSON ;
        ItemTypeX itemTypeX ;
        List<ItemTypeX> listeItemsTypeX = new ArrayList<>();
        
        int idClinique = UtilitaireAPI.extractIdClinique(securityContext);
        
        try{           
            if(type != null){
                if(type.equals("paiement")){
                    System.out.println(" GET Liste paiements ");
                    typeXDAO = new TypeXDAO("idtypepaiement", "paiement", "typepaiement", "ordre", idClinique);
                }else if(type.equals("tarif")){
                    System.out.println(" GET Liste tarif ");
                    typeXDAO = new TypeXDAO("idtypetarif", "tarif", "typetarif", "ordre", idClinique);
                }
            }
             
            listeTypes = typeXDAO.getAllTypeX();            
                 
        }catch(DAOException e){
            error = new ErrorResponse(404, Response.Status.NOT_FOUND);  
        }
        
        for(int  i = 0 ; i < listeTypes.size(); i++){
            itemTypeX = new ItemTypeX(listeTypes.get(i));
            listeItemsTypeX.add(itemTypeX);
        }
        
        itemJSON = new ItemTypeXJSON(error, listeItemsTypeX);
        
        return itemJSON ;
        
    }

    /**
     * PUT method for updating or creating an instance of TypeXResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
