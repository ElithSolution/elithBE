/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elith.clinique;

import com.elith.authentification.Utilisateur;
import com.elith.connexion.DAOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author ambk3201
 */
@Path("cliniques")
public class CliniquesResource {
    
    private CliniqueDAO cliniqueDAO;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CliniquesResource
     */
    public CliniquesResource() {
    }

    /**
     * Retrieves representation of an instance of com.elith.clinique.CliniquesResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Clinique> getJson() {
        
        List<Clinique> resultat = new ArrayList<>();                               

        try {                
            cliniqueDAO = new CliniqueDAO();
            resultat = cliniqueDAO.findAll();

        }catch(DAOException e){
               System.out.println("erreur");
        } 


        return resultat;

    }

    /**
     * POST method for creating an instance of CliniqueResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postJson(String content) {
        //TODO
        return Response.created(context.getAbsolutePath()).build();
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public CliniqueResource getCliniqueResource(@PathParam("id") String id) {
        return CliniqueResource.getInstance(id);
    }
}
