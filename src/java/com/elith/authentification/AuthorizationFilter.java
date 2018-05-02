/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elith.authentification;

import com.elith.erreur.ErrorResponse;
import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author ambk3201
 */

@Secured
@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;
    private Token monToken ;
    private ErrorResponse error ;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // Get the resource class which matches with the requested URL
        // Extract the roles declared by it
        Class<?> resourceClass = resourceInfo.getResourceClass();
        List<Role> classRoles = extractRoles(resourceClass);

        // Get the resource method which matches with the requested URL
        // Extract the roles declared by it
        Method resourceMethod = resourceInfo.getResourceMethod();
        List<Role> methodRoles = extractRoles(resourceMethod);
     
        SecurityContext currentSecurityContext = requestContext.getSecurityContext();
        Principal principal = currentSecurityContext.getUserPrincipal();
        monToken = new Token(principal.getName());
        String role = monToken.getRoleFromPrincipal();
               
        try {
            // Check if the user is allowed to execute the method
            // The method annotations override the class annotations
            if (methodRoles.isEmpty()) {
                checkPermissions(classRoles, role);
            } else {
                checkPermissions(methodRoles, role);
            }
            
        } catch (Exception e) {            
            error = new ErrorResponse(403,Status.FORBIDDEN);
            error.sendErrorJson(requestContext);

        }
    }


    
    // Extract the roles from the annotated element
    private List<Role> extractRoles(AnnotatedElement annotatedElement) {
        if (annotatedElement == null) {
            return new ArrayList<>();
        } else {
            Secured secured = annotatedElement.getAnnotation(Secured.class);
            if (secured == null) {
                return new ArrayList<>();
            } else {
                Role[] allowedRoles = secured.value();
               
                return Arrays.asList(allowedRoles);
            }
        }
    }

    private void checkPermissions(List<Role> allowedRoles, String monRole) throws Exception {
        boolean trouve = false ;
        int nbElt = allowedRoles.size() ;
        int i = 0 ;
        
        while(!trouve && i < nbElt){
            Role rolePermis = allowedRoles.get(i) ;
            if(rolePermis != null)
                trouve = (rolePermis.toString().compareTo(monRole) == 0) ;
                i++ ;            
        }
        
        if(!trouve) throw new Exception() ;

    }
}
