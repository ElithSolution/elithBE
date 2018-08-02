/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elith.client;

import com.elith.util.ConversionType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ambk3201
 */
@XmlRootElement(name = "client")
public class Client extends ClientAbstract implements Serializable  {

 
    public Client(){}
    
    // full constructeur
    public Client(int id,
            String nom,
            String prenom,
            String adresse,
            String ville,
            String codePostal,
            String telephone,
            String email,
            Date dateNaissance,
            int enfants,
            String emploi,
            String loisirs,
            String refere,
            String autresTherapies,
            String medicaments,
            String complements,
            boolean cardiaque,
            boolean hypoglycemie,
            boolean asthme,
            boolean diabete,
            String allergies,
            String autre,
            String histoOperations,
            String histoDouleur,
            String constatations,
            String attentions,
            String pasMasser,
            boolean fumeur,
            boolean peau,
            boolean arthrose,
            boolean osteoporose,
            String note,
            String constipation,
            int idClinique) {
        
        this.id = id ;     
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.ville = ville;
        this.codePostal = codePostal;
        this.telephone = telephone;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.enfants = enfants;
        this.emploi = emploi;
        this.loisirs = loisirs;
        this.refere = refere;
        this.autresTherapies = autresTherapies;
        this.medicaments = medicaments;
        this.complements = complements;
        this.cardiaque = cardiaque;
        this.hypoglycemie = hypoglycemie;
        this.asthme = asthme;
        this.diabete = diabete;
        this.allergies = allergies;
        this.autre = autre;
        this.histoOperations = histoOperations;
        this.histoDouleur = histoDouleur;
        this.constatations = constatations;
        this.attentions = attentions;
        this.pasMasser = pasMasser;
        this.fumeur = fumeur ;
        this.peau = peau ;
        this.arthrose = arthrose ;
        this.osteoporose = osteoporose ;
        this.note = note ;
        this.constipation = constipation;
        this.nomComplet = this.prenom + " " + this.nom ;
        this.telephoneChiffre = obtenirJusteChiffre(this.telephone);
        this.dateNaissanceString = ConversionType.DateToString(new java.sql.Date(dateNaissance.getTime()));
              
        this.idClinique = idClinique;
                
    }
    
    @Override
    public int getId() { return id;}
    @Override
    public String getNom() { return nom;}
    @Override
    public String getPrenom() { return prenom;}
    @Override
    public String getAdresse() { return (adresse != null ? adresse : "");}
    @Override
    public String getVille() { return (ville != null ? ville : "");}
    @Override
    public String getCodePostal() { return (codePostal != null ? codePostal : ""); }
    @Override
    public String getTelephone() { return (telephone != null ? telephone : "");}
    @Override
    public String getEmail() { return (email != null ? email : "");}
    @Override
    public Date getDateNaissance() { return dateNaissance; }
    @Override
    public java.sql.Date getDateNaissanceSql() { return new java.sql.Date(dateNaissance.getTime());}
    @Override
    public int getEnfants() { return enfants;}
    @Override
    public String getEmploi() { return (emploi != null ? emploi : ""); }
    @Override
    public String getLoisirs() {return (loisirs != null ? loisirs : "");}
    @Override
    public String getRefere() { return (refere != null ? refere : "");}
    @Override
    public String getAutresTherapies() { return (autresTherapies != null ? autresTherapies : "");}
    @Override
    public String getMedicaments() { return (medicaments != null ? medicaments : "");}
    @Override
    public String getComplements() { return (complements != null ? complements : "");}
    @Override
    public boolean getCardiaque() { return cardiaque;}
    @Override
    public boolean getHypoglycemie() {return hypoglycemie;}
    @Override
    public boolean getAsthme() { return asthme;}
    @Override
    public boolean getDiabete() { return diabete;}
    @Override
    public String getAllergies() { return allergies; }
    @Override
    public String getAutre() { return autre; }
    @Override
    public String getHistoOperations() { return (histoOperations != null ? histoOperations : "");}
    @Override
    public String getHistoDouleur() { return (histoDouleur != null ? histoDouleur : "");}
    @Override
    public String getConstatations() { return (constatations != null ? constatations : "");}
    @Override
    public String getAttentions() { return (attentions != null ? attentions : ""); }
    @Override
    public String getPasMasser() { return (pasMasser != null ? pasMasser : ""); }
    @Override
    public boolean getFumeur(){return fumeur;}
    @Override
    public boolean getPeau(){return peau;}
    @Override
    public boolean getArthrose(){return arthrose;}
    @Override
    public boolean getOsteoporose(){return osteoporose;}
    @Override
    public String getNote(){return (note != null ? note : "");}
    @Override
    public String getConstipation(){return (constipation != null ? constipation : "");}
    @Override
    public String getNomComplet(){return nomComplet;}
    @Override
    public String getTelephoneChiffre(){return telephoneChiffre;}
    @Override
    public String getDateNaissanceString(){return dateNaissanceString;}
    
    @Override
    public int getIdClinique(){return idClinique;}
    
   
    @Override
    public List<Traitement> getTraitements(){return traitements; }
    

    @Override
    public void setId(int id) { this.id = id; }
    @Override
    public void setNom(String nom) { this.nom = nom;}
    @Override
    public void setPrenom(String prenom) {this.prenom = prenom;}
    @Override
    public void setAdresse(String adresse) { this.adresse = adresse; }
    @Override
    public void setVille(String ville) { this.ville = ville;}
    @Override
    public void setCodePostal(String codePostal) { this.codePostal = codePostal; }
    @Override
    public void setTelephone(String telephone) { this.telephone = telephone; }
    @Override
    public void setEmail(String email) { this.email = email;}
    @Override
    public void setDateNaissance(Date dateNaissance) { this.dateNaissance = dateNaissance; }
    @Override
    public void setEnfants(int enfants) {this.enfants = enfants;}
    @Override
    public void setEmploi(String emploi) {this.emploi = emploi;}
    @Override
    public void setLoisirs(String loisirs) {this.loisirs = loisirs;}
    @Override
    public void setRefere(String refere) { this.refere = refere;}
    @Override
    public void setAutresTherapies(String autresTherapies) { this.autresTherapies = autresTherapies;}
    @Override
    public void setMedicaments(String medicaments) {this.medicaments = medicaments;}
    @Override
    public void setComplements(String complements) { this.complements = complements;}
    @Override
    public void setCardiaque(boolean cardiaque) { this.cardiaque = cardiaque;}
    @Override
    public void setHypoglycemie(boolean hypoglycemie) { this.hypoglycemie = hypoglycemie;}
    @Override
    public void setAsthme(boolean asthme) {this.asthme = asthme;}
    @Override
    public void setDiabete(boolean diabete) {this.diabete = diabete;}
    @Override
    public void setAllergies(String allergies) {this.allergies = allergies;}
    @Override
    public void setAutre(String autre) { this.autre = autre;}
    @Override
    public void setHistoOperations(String histoOperations) { this.histoOperations = histoOperations;}
    @Override
    public void setHistoDouleur(String histoDouleur) { this.histoDouleur = histoDouleur;}
    @Override
    public void setConstatations(String constatations) { this.constatations = constatations;}
    @Override
    public void setAttentions(String attentions) { this.attentions = attentions;}
    @Override
    public void setPasMasser(String pasMasser) { this.pasMasser = pasMasser; }
    @Override
    public void setFumeur(boolean fumeur){this.fumeur = fumeur;}
    @Override
    public void setPeau(boolean peau){this.peau = peau ;}
    @Override
    public void setArthrose(boolean arthrose){this.arthrose = arthrose;}
    @Override
    public void setOsteoporose(boolean osteoporose){this.osteoporose = osteoporose;}
    @Override
    public void setNote(String note){this.note = note;}
    @Override
    public void setConstipation(String constipation){this.constipation = constipation;}  
    @Override
    public void setNomComplet(String nomComplet){this.nomComplet = nomComplet;}
    @Override
    public void setTelephoneChiffre(String telephoneChiffre){this.telephoneChiffre = telephoneChiffre;}
    @Override
    public void setDateNaissanceString(String dateNaissanceString){this.dateNaissanceString = dateNaissanceString;}
   
    
    @Override
    public void setIdClinique(int idClinique){this.idClinique = idClinique;}
   
 
    @Override
    public void setTraitements(List traitements) {this.traitements = traitements ;}
    
    
    @Override
    public boolean isNil() { return false;  }
    
    private String obtenirJusteChiffre(String telephone){
        String telChiffre = "" ;
        
        try {
            if(telephone != null){
                Pattern p = Pattern.compile("[0-9]");
                Matcher m = p.matcher(telephone);
                while(m.find()){
                    telChiffre += telephone.substring(m.start(), m.end());
                }  
            }
            
        }catch(PatternSyntaxException e){         
        }

        return telChiffre ;
    }
}
