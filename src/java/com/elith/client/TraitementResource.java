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
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
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
@Path("traitement")
@Secured({Role.SUPER, Role.ADMIN, Role.THERAPEUTE})
public class TraitementResource {

    @Context
    private UriInfo context;
    
    private TraitementDAO traitementDAO;
    private TypeXDAO typeXDAO ;
    private ErrorResponse error = new ErrorResponse(200, Response.Status.OK) ;


    /**
     * Creates a new instance of TraitementResource
     */
    public TraitementResource() {
    }

    /**
     * Retrieves representation of an instance of com.elith.client.TraitementResource
     * @param id
     * @param securityContext
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

    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ItemTraitementJSON postJson(@Context SecurityContext securityContext, Traitement traitement) {
        int idClinique = UtilitaireAPI.extractIdClinique(securityContext);
        int idUser = UtilitaireAPI.extractIdUser(securityContext);
        TraitementAbstract traitementMaJ = new TraitementNull(); 
               
        try{
            
            if(traitement != null){
                
                System.out.println("traitement  raison : "+ traitement.getRaison());
                System.out.println("traitement  traitement : "+ traitement.getTraitement());
                System.out.println("traitement  conseils : "+ traitement.getConseils());
                System.out.println("traitement  commentaires : "+ traitement.getCommentaires());
                System.out.println("traitement  datevisite : "+ traitement.getDateVisiteString());
                System.out.println("traitement  domicile : "+ traitement.getDomicile());
                System.out.println("traitement  fraiskm : "+ traitement.getFraisKm());
                System.out.println("traitement  km : "+ traitement.getKm());
                System.out.println("traitement  domicile : "+ traitement.getDomicile());
                System.out.println("traitement  tarif : "+ traitement.getTarif());
            
                System.out.println("traitement  tps : "+ traitement.getTps());
                System.out.println("traitement  tvq : "+ traitement.getTvq());
                System.out.println("traitement  certif : "+ traitement.getNumCertificat());
                System.out.println("traitement  archive : "+ traitement.getArchive());
                System.out.println("traitement  idclient : "+ traitement.getIdClient());
                System.out.println("traitement  mode de paiement : "+ traitement.getModePaiement());
                System.out.println("traitement  prix : "+ traitement.getPrix());
                
                
                          
                 traitementDAO = new TraitementDAO();
                      
                // ajout des informations complémentaires
                traitement.setIdClinique(idClinique);
                traitement.setLogin(idUser);
                
          
                // ajout du traitement
                traitementDAO.create(traitement);
                System.out.println("traitement créé");
                int cle = traitementDAO.maxCle();
                traitement.setId(cle);
                traitementMaJ = getTraitement(String.valueOf(traitement.getId()), idClinique);
                if(traitementMaJ.isNil())error = new ErrorResponse(403, Response.Status.FORBIDDEN);                
                
            }          
          
        } catch (DAOException e) {
            error = new ErrorResponse(404, Response.Status.NOT_FOUND);   
        } 
       
        return traitementToJSON(traitementMaJ, error);
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
                    
                    traitement.setModePaiement(this.obtenirIdTypeX("paiement", traitement.getModePaiement(), idClinique));                    
                    traitement.setTarif(this.obtenirIdTypeX("tarif", traitement.getTarif(), idClinique));
                    
                    traitementDAO.updateTraitement(traitement);           
                    traitementMaJ = getTraitement(String.valueOf(traitement.getId()), idClinique);
                    System.out.println("traiement màj : " + traitement.getId());
                    if(traitementMaJ.isNil())error = new ErrorResponse(403, Response.Status.FORBIDDEN);
                                
                }else{                    
                    error = new ErrorResponse(204, Response.Status.NO_CONTENT); 
                }          
            }              
          
        } catch (DAOException e) {
            error = new ErrorResponse(404, Response.Status.NOT_FOUND);   
        }  
       
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
    
    private String obtenirIdTypeX(String type, String label, int idClinique){
        List<TypeX> listeTypes = null ; 
        String res = "1" ;
        
        if(type != null){
                           
                listeTypes = this.obtenirListeTypeX(type, idClinique);                
                if(listeTypes != null){
                boolean trouve = false;
                int i = 0 ;
                    while(!trouve && i < listeTypes.size()){
                        String element = listeTypes.get(i).getIntitule();
                        if(element.equals(label)){
                            trouve = true ;
                            res = String.valueOf(listeTypes.get(i).getIdType());
                        }
                        i++;
                    }
                }
        }   
  
       return res ;
    }
        
        
    private List<TypeX> obtenirListeTypeX(String type, int idClinique){
        List<TypeX> listeTypes ; 
        
         try{           
            if(type != null){
                if(type.equals("paiement")){
                    typeXDAO = new TypeXDAO("idtypepaiement", "paiement", "typepaiement", "ordre", idClinique);
                }else if(type.equals("tarif")){
                    typeXDAO = new TypeXDAO("idtypetarif", "tarif", "typetarif", "ordre", idClinique);
                }
            }             
            listeTypes = typeXDAO.getAllTypeX();            
                 
        }catch(DAOException e){
            listeTypes = null ;
        }
    return listeTypes ;
    }
}
