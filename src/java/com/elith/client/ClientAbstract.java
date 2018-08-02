/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elith.client;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ambk3201
 */
public abstract class ClientAbstract implements Serializable  {
    
    protected int id;
    protected String nom;
    protected String prenom;
    protected String adresse;
    protected String ville;
    protected String codePostal;
    protected String telephone;
    protected String email;
    protected Date dateNaissance;
    protected int enfants;
    protected String emploi;
    protected String loisirs;
    protected String refere;
    protected String autresTherapies;
    protected String medicaments;
    protected String complements;
    protected boolean cardiaque;
    protected boolean hypoglycemie;
    protected boolean asthme;
    protected boolean diabete;
    protected String allergies;
    protected String autre;
    protected String histoOperations;
    protected String histoDouleur;
    protected String constatations;
    protected String attentions;
    protected String pasMasser;
    protected boolean fumeur ;
    protected boolean peau ;
    protected boolean arthrose ;
    protected boolean osteoporose ;
    protected String note ;
    protected String constipation ;
    
    // ajout du nom complet pour la recherche
    protected String nomComplet;
    protected String telephoneChiffre;
    
    // ajout d'un champ pour faciliter la gestion de la date de naissance
    protected String dateNaissanceString;
        
    // clinique
    protected int idClinique ;
    
    // liste des traitements
    protected List<Traitement> traitements;
    
    // getters
    public abstract int getId() ;
    public abstract String getNom();
    public abstract String getPrenom();
    public abstract String getAdresse();
    public abstract String getVille();
    public abstract String getCodePostal();
    public abstract String getTelephone();
    public abstract String getEmail();
    public abstract Date getDateNaissance();
    public abstract java.sql.Date getDateNaissanceSql();
    public abstract int getEnfants();
    public abstract String getEmploi();
    public abstract String getLoisirs();
    public abstract String getRefere() ;
    public abstract String getAutresTherapies();
    public abstract String getMedicaments();
    public abstract String getComplements();
    public abstract boolean getCardiaque();
    public abstract boolean getHypoglycemie();
    public abstract boolean getAsthme();
    public abstract boolean getDiabete();
    public abstract String getAllergies();
    public abstract String getAutre();
    public abstract String getHistoOperations();
    public abstract String getHistoDouleur();
    public abstract String getConstatations();
    public abstract String getAttentions();
    public abstract String getPasMasser();
    public abstract boolean getFumeur();
    public abstract boolean getPeau();
    public abstract boolean getArthrose();
    public abstract boolean getOsteoporose();
    public abstract String getNote();
    public abstract String getConstipation();
    public abstract String getNomComplet();
    public abstract String getTelephoneChiffre();
    public abstract String getDateNaissanceString();
    
    public abstract int getIdClinique();
    
    public abstract List<Traitement> getTraitements();
    
    public abstract void setId(int id) ;
    public abstract void setNom(String nom);
    public abstract void setPrenom(String prenom);
    public abstract void setAdresse(String adresse);
    public abstract void setVille(String ville);
    public abstract void setCodePostal(String codePostal);
    public abstract void setTelephone(String telephone);
    public abstract void setEmail(String email);
    public abstract void setDateNaissance(Date dateNaissance);
    public abstract void setEnfants(int enfants);
    public abstract void setEmploi(String emploi);
    public abstract void setLoisirs(String loisirs);
    public abstract void setRefere(String refere);
    public abstract void setAutresTherapies(String autresTherapies);
    public abstract void setMedicaments(String medicaments);
    public abstract void setComplements(String complements);
    public abstract void setCardiaque(boolean cardiaque);
    public abstract void setHypoglycemie(boolean hypoglycemie) ;
    public abstract void setAsthme(boolean asthme) ;
    public abstract void setDiabete(boolean diabete);
    public abstract void setAllergies(String allergies);
    public abstract void setAutre(String autre);
    public abstract void setHistoOperations(String histoOperations) ;
    public abstract void setHistoDouleur(String histoDouleur);
    public abstract void setConstatations(String constatations);
    public abstract void setAttentions(String attentions);
    public abstract void setPasMasser(String pasMasser);
    public abstract void setFumeur(boolean fumeur);
    public abstract void setPeau(boolean peau);
    public abstract void setArthrose(boolean arthrose);
    public abstract void setOsteoporose(boolean osteoporose);
    public abstract void setNote(String note);
    public abstract void setConstipation(String constipation);  
    public abstract void setNomComplet(String nomComplet);
    public abstract void setTelephoneChiffre(String telephoneChiffre);
    public abstract void setDateNaissanceString(String dateNaissanceString);
    
    public abstract void setIdClinique(int idClinique);
    
    public abstract void setTraitements(List traitements);
   
    public abstract boolean isNil();
    
   
}
