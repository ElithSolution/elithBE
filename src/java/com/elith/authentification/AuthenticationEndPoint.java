package com.elith.authentification;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.elith.connexion.Constantes;
import com.elith.connexion.DAOException;
import com.elith.erreur.ErrorResponse;
import com.elith.util.ConversionType;
import java.io.UnsupportedEncodingException;
import java.time.ZonedDateTime;
import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/authentication")
public class AuthenticationEndPoint {
    

    private UtilisateurDAO utilisateurDAO;
    private final ErrorResponse error = new ErrorResponse(401,Response.Status.UNAUTHORIZED);

                     
 
    @POST
    @Produces(value = {"application/json", "text/plain"})
    //@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUser(Credentials credentials) {
        String token ;
        TokenJSON tokenJSON ;
        int idClinique ;
        int idUser ;
        Role role ;
        UtilisateurAbstract user ;       
        
        try {

            String username = credentials.getLogin();
            String password = credentials.getMotPasse();         

            // Authenticate the user using the credentials provided
            user = authenticate(username, password) ;  
     
            if(user.isNil()){
                return Response.status(Response.Status.UNAUTHORIZED).entity(error).build();
                
            }else{     
                // Issue a token for the user
                idClinique = user.getIdClinique();                
                role = user.getRole();
                idUser = user.getId(); // id du therapeuthe
                
                token = issueTokenHMAC256(Constantes.TOKEN_SECRET, username, idClinique, role.toString(), idUser) ;
                tokenJSON = new TokenJSON(token);
                System.out.println("  TOKENJSON : " + tokenJSON) ;
                // Return the token on the response
                return Response.ok(tokenJSON).build();
            }

        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(error).build();
        }      
    }

    /**************************************************************************/
    
    private UtilisateurAbstract authenticate(String username, String password) throws Exception {
         UtilisateurAbstract util ;
         String encodedPwd = ConversionType.getEncodedPassword(password);
         try {
                utilisateurDAO = new UtilisateurDAO();
                util = utilisateurDAO.findByLoginPwd(username, encodedPwd);
                                      
         }catch(DAOException e){
               util = new UtilisateurNull();
         }
         return (util);            
                    
    }
    
    
       /**
     * Generate the HMAC256 Token
     * @param secret
     *          Secret to generate the token
     * @return 
     *      Token as a String
     * @throws UnsupportedEncodingException
     *      UTF-8 encoding not supported
     * @throws JWTVerificationException 
     *      Invalid Signing configuration / Couldn't convert Claims.
     */
    public String issueTokenHMAC256(String secret, String username, int idClinique, String role, int idUser) 
            throws UnsupportedEncodingException, JWTCreationException {

        String token="";           
        Algorithm algorithm = Algorithm.HMAC256(secret);
        Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(5000).toInstant());
        System.out.println("Expiration du token : " + expirationDate);
        token = JWT.create()
                    .withExpiresAt(expirationDate)
                        .withClaim("username", username)
                        .withClaim("idClinique", idClinique)
                        .withClaim("role", role)
                        .withClaim("idUser", idUser)
                        .withIssuer("auth0")
                        .sign(algorithm);
        return token;
    }

    
}