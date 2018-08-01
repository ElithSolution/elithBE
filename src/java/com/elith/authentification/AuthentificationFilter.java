/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elith.authentification;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.elith.connexion.Constantes;
import com.elith.erreur.ErrorResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import javax.annotation.Priority;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;


/**
 *
 * @author ambk3201
 */

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthentificationFilter implements ContainerRequestFilter {


    private String username ;
    private final ErrorResponse error = new ErrorResponse(401,Response.Status.UNAUTHORIZED);
      
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        final SecurityContext currentSecurityContext = requestContext.getSecurityContext();
        requestContext.setSecurityContext(new SecurityContext() {        
            @Override
            public Principal getUserPrincipal() {
                return new Principal() {
                    
                    @Override
                    public String getName() {
                        return username;
                    }                     
            
            };
          }

            @Override
            public boolean isUserInRole(String role) {
                return true;
            }

            @Override
            public boolean isSecure() {
                return currentSecurityContext.isSecure();
            }

            @Override
            public String getAuthenticationScheme() {
                return "Bearer";
            }
        });
               
       
        // Get the HTTP Authorization header from the request
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);  
        System.out.println("authorizationHeader : " + authorizationHeader);
 
        // Check if the HTTP Authorization header is present and formatted correctly 
        if (mauvaisHeader(authorizationHeader)) {
            error.sendErrorJson(requestContext);
        }else{
     
            try {
                // Validate the token
                username = validateTokenHMAC256(Constantes.TOKEN_SECRET, extractToken(authorizationHeader)) ;                     

            } catch (UnsupportedEncodingException | JWTVerificationException exception){                     
                error.sendErrorJson(requestContext);             
            }
        }
        
    }

    /**************************************************************************/
       /**
     * Validate a HMAC256 Token
     * @param token
     *          Token you need to validate
     * @param secret
     *          Secret used to generate the token
     * @return
     *          Returns `true` if token is valid.
     * @throws UnsupportedEncodingException
     *          UTF-8 encoding not supported
     * @throws JWTVerificationException 
     *          Invalid Signing configuration / Couldn't convert Claims.
     */
    private String validateTokenHMAC256(String secret, String token) throws UnsupportedEncodingException, JWTVerificationException {       
            String resultat = "" ;
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                                    .withIssuer("auth0")
                                    .build();         
            DecodedJWT jwt = verifier.verify(token);
            Claim usernameClaim = jwt.getClaim("username");
            Claim idCliniqueClaim = jwt.getClaim("idClinique");
            Claim roleClaim = jwt.getClaim("role");
            Claim idUserClaim = jwt.getClaim("idUser");
            Claim prenomClaim = jwt.getClaim("prenom");
             
            if(usernameClaim != null && idCliniqueClaim != null && roleClaim != null)
                resultat = UsernameIdCliniqueRoleToJSONString(usernameClaim.asString(), 
                                                                idCliniqueClaim.asInt(), 
                                                                roleClaim.asString(),
                                                                idUserClaim.asInt(),
                                                                prenomClaim.asString());
            return resultat ;

    }
    
    private String UsernameIdCliniqueRoleToJSONString(String username, int idClinique, String role, int idUser, String prenom){
        String resultat = "" ;       
        JsonObject model = Json.createObjectBuilder()
                            .add("username", username)
                            .add("idClinique", idClinique)
                            .add("role", role)
                            .add("idUser", idUser)
                            .add("prenom", prenom)
                            .build();
        if (model != null) resultat = model.toString() ;
        System.out.println("Personne logguee : " + resultat) ;
        
        return resultat ;
    }
    
    private boolean mauvaisHeader(String authorizationHeader){
        return (authorizationHeader == null || !authorizationHeader.startsWith("Bearer "));
    }
    
    private String extractToken(String authorizationHeader){
        return authorizationHeader.substring("Bearer".length()).trim();
    }
    
}
