/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author ambk3201
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
       
        resources.add(com.elith.authentification.AuthenticationEndPoint.class);
        resources.add(com.elith.authentification.AuthentificationFilter.class);
        resources.add(com.elith.authentification.AuthorizationFilter.class);
        resources.add(com.elith.authentification.UtilisateurResource.class);
        resources.add(com.elith.authentification.UtilisateursResource.class);
        resources.add(com.elith.client.ClientCrossOriginResourceSharingFilter.class);
        resources.add(com.elith.client.ClientResource.class);
        resources.add(com.elith.client.ClientsResource.class);
        resources.add(com.elith.client.TraitementResource.class);
        resources.add(com.elith.clinique.CliniqueResource.class);
        resources.add(com.elith.clinique.CliniquesResource.class);
    }
    
}
