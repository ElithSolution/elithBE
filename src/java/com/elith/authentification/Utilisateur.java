/*
 * Utilisateur.java
 *
 * Created on 6 avril 2008, 20:23
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.elith.authentification;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kat
 */
@XmlRootElement(name = "utilisateur")
public class Utilisateur extends UtilisateurAbstract implements Serializable  {

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


    /** Creates a new instance of Utilisateur */
    public Utilisateur() {
    }

    // full constructeur
    public Utilisateur(String nom,
            String prenom,
            String email,
            String login,
            String motPasse,
            boolean actif,
            int idClinique,
            Role role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.login = login;
        this.motPasse = motPasse;
        this.actif = actif ;
        this.idClinique = idClinique ;
        this.role = role ;
    }

    
    // getters
    @Override
    public int getId() { return id; }
    @Override
    public String getNom() { return nom; }
    @Override
    public String getPrenom() {return prenom;}
    @Override
    public String getEmail() { return email; }
    @Override
    public String getLogin() { return login; }
    @Override
    public String getMotPasse() { return motPasse; }
    @Override
    public List<String> getListeModules(){ return this.listeModules ; }
    @Override
    public boolean getActif(){return this.actif ;}  
    @Override
    public int getIdClinique() { return idClinique ; }  
    @Override
    public Role getRole(){return role ;}

    // setters
    @Override
    public void setId(int id) { this.id = id; }
    @Override
    public void setNom(String nom) { this.nom = nom; }
    @Override
    public void setPrenom(String prenom) { this.prenom = prenom; }
    @Override
    public void setEmail(String email) { this.email = email; }
    @Override
    public void setLogin(String login) { this.login = login; }
    @Override
    public void setMotPasse(String motPasse) { this.motPasse = motPasse; }
    @Override
    public void setListeModules(List<String> listeModules) { this.listeModules = listeModules ; }
    @Override
    public void setActif(boolean actif){ this.actif = actif ;}
    @Override
    public void setIdClinique(int idClinique){ this.idClinique = idClinique ; }
    @Override
    public void setRole(Role role){this.role = role ; }
    
   
    @Override
    public boolean isNil() {
        return false;
    }
}
