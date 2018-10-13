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
     * @param id
     * @param securityContext
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ItemClientJSON postJson(@Context SecurityContext securityContext, Client client) {
        int idClinique = UtilitaireAPI.extractIdClinique(securityContext);
        ClientAbstract clientMaJ = new ClientNull(); 
        int existeDeja ;
        
        try{
            
            if(client != null){
                
                System.out.println("client  nom : "+ client.getNom());
                System.out.println("client  prénom : "+ client.getPrenom());
                System.out.println("client  adresse : "+ client.getAdresse());
                System.out.println("client  ville : "+ client.getVille());
                System.out.println("client  code postal : "+ client.getCodePostal());
                System.out.println("client  tel : "+ client.getTelephone());
                System.out.println("client  email : "+ client.getEmail());
                System.out.println("client  dateNaissance : "+ client.getDateNaissanceString());
                System.out.println("client  enfant : "+ client.getEnfants());
                System.out.println("client  emploi : "+ client.getEmploi());
                System.out.println("client  loisirs : "+ client.getLoisirs());
                System.out.println("client  referé : "+ client.getRefere());
                System.out.println("client  autres : "+ client.getAutresTherapies());
                System.out.println("client  medicaments : "+ client.getMedicaments());
                System.out.println("client  complement : "+ client.getComplements());
                System.out.println("client  cardiaque : "+ client.getCardiaque());
                System.out.println("client  hypo : "+ client.getHypoglycemie());
                System.out.println("client  asthme : "+ client.getAsthme());
                System.out.println("client  diabete : "+ client.getDiabete());
                System.out.println("client  allergie : "+ client.getAllergies());
                System.out.println("client  autre : "+ client.getAutre());
                System.out.println("client  histoOp : "+ client.getHistoOperations());
                System.out.println("client  histoDouleur : "+ client.getHistoDouleur());
                System.out.println("client  consta : "+ client.getConstatations());
                System.out.println("client  attentions : "+ client.getAttentions());
                System.out.println("client  pas masser : "+ client.getPasMasser());
                System.out.println("client  fumeur : "+ client.getFumeur());
                System.out.println("client  peau : "+ client.getPeau());
                System.out.println("client  arthrose : "+ client.getArthrose());
                System.out.println("client  osteoporose : "+ client.getOsteoporose());
                System.out.println("client  note : "+ client.getNote());
                System.out.println("client  constipation : "+ client.getConstipation());
        
                               
                 clientDAO = new ClientDAO();
                 
                // on verifie que le client n'existe pas deja
                
                existeDeja = clientDAO.existeClient(client.getNom().toLowerCase(), client.getPrenom().toLowerCase(), client.getDateNaissanceString(), idClinique); 
                if(existeDeja != -1) {
                    error = new ErrorResponse(405, Response.Status.METHOD_NOT_ALLOWED);
                    clientMaJ = getClient(String.valueOf(existeDeja), idClinique, null);
                }else {
             
                    // ajout du client
                    client.setIdClinique(idClinique);
                    clientDAO.create(client);
                    int cle = clientDAO.maxCle();
                    client.setId(cle);

                    clientMaJ = getClient(String.valueOf(client.getId()), idClinique, client.getTraitements());
                    if(clientMaJ.isNil())error = new ErrorResponse(403, Response.Status.FORBIDDEN);
                }
                
            }          
          
        } catch (DAOException e) {
            error = new ErrorResponse(404, Response.Status.NOT_FOUND);   
        } 
       
        return clientToJSON(clientMaJ, error);
    }
    
     /**
     * PUT method for updating or creating an instance of ClientsResource
     * @param securityContext
     * @param client
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
