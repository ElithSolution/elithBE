/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elith.authentification;

import com.elith.connexion.DAOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author ambk3201
 */
@Path("utilisateurs")
public class UtilisateursResource {

    private UtilisateurDAO utilisateurDAO;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UtilisateursResource
     */
    public UtilisateursResource() {
    }

    /**
     * Retrieves representation of an instance of com.elith.authentification.UtilisateursResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UtilisateurAbstract> getJson(@Context UriInfo info) {
        
        String tri = info.getQueryParameters().getFirst("tri");
        System.out.println("  TRI= " + tri);	
        
        //TODO return proper representation object
        Map<Integer, UtilisateurAbstract> res = new LinkedHashMap<>();
        List<UtilisateurAbstract> resultat = new ArrayList<>();                               
              
            try {                
                utilisateurDAO = new UtilisateurDAO();
                if (tri != null && tri.equals("actif"))
                    res = utilisateurDAO.countUtilisateurActif();
                else 
                    res = utilisateurDAO.countTousUtilisateur();
                
            }catch(DAOException e){
                   System.out.println("erreur");
            } 

            if (res != null){
                Iterator<Integer> it = res.keySet().iterator();                 
                while (it.hasNext()) {  
                     // id du user
                    UtilisateurAbstract util = res.get(it.next());
                    resultat.add(util);                    
                 
                }             
            }
            return resultat;

    }

    /**
     * PUT method for updating or creating an instance of UtilisateursResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
