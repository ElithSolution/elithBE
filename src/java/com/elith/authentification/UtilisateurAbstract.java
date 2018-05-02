/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elith.authentification;

import com.elith.erreur.ErrorResponse;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ambk3201
 */


public abstract class UtilisateurAbstract implements Serializable  {

    private ErrorResponse erreur ;
    
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String login;
    private String motPasse;
    private List<String> listeModules ; // les modules auxquels on a acces
    private boolean actif ;
    private int idClinique ;
    private Role role ;

   
   
     // getters
    public ErrorResponse getError(){return erreur ; }
    
    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() {return prenom;}
    public String getEmail() { return email; }
    public String getLogin() { return login; }
    public String getMotPasse() { return motPasse; }
    public List<String> getListeModules(){ return this.listeModules ; }
    public boolean getActif(){return this.actif ;}  
    public int getIdClinique() { return idClinique ; }  
    public Role getRole(){return role ;}

    // setters
    public void setError(ErrorResponse erreur){this.erreur = erreur ; }
    
    public void setId(int id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setEmail(String email) { this.email = email; }
    public void setLogin(String login) { this.login = login; }
    public void setMotPasse(String motPasse) { this.motPasse = motPasse; }
    public void setListeModules(List<String> listeModules) { this.listeModules = listeModules ; }
    public void setActif(boolean actif){ this.actif = actif ;}
    public void setIdClinique(int idClinique){ this.idClinique = idClinique ; }
    public void setRole(Role role){this.role = role ; }
        
    
    
    public abstract boolean isNil();
 

   
}