/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elith.clinique;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author ambk3201
 */
public class CliniqueResource {

    private String id;

    /**
     * Creates a new instance of CliniqueResource
     */
    private CliniqueResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the CliniqueResource
     */
    public static CliniqueResource getInstance(String id) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of CliniqueResource class.
        return new CliniqueResource(id);
    }

    /**
     * Retrieves representation of an instance of com.elith.clinique.CliniqueResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of CliniqueResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    /**
     * DELETE method for resource CliniqueResource
     */
    @DELETE
    public void delete() {
    }
}
