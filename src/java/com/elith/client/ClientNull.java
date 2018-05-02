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
public class ClientNull extends ClientAbstract implements Serializable {
    

    /** Creates a new instance of Utilisateur */
    public ClientNull() {
    }

    @Override
    public int getId() { return 0;}
    @Override
    public String getNom() { return "";}
    @Override
    public String getPrenom() { return "";}
    @Override
    public String getAdresse() { return "";}
    @Override
    public String getVille() { return "";}
    @Override
    public String getCodePostal() { return "";}
    @Override
    public String getTelephone() { return "";}
    @Override
    public String getEmail() { return "";}
    @Override
    public Date getDateNaissance() { return null;}
    @Override
    public java.sql.Date getDateNaissanceSql() { return null;}
    @Override
    public int getEnfants() { return 0;}
    @Override
    public String getEmploi() { return "";}
    @Override
    public String getLoisirs() {return "";}
    @Override
    public String getRefere() { return  "";}
    @Override
    public String getAutresTherapies() { return "";}
    @Override
    public String getMedicaments() { return "";}
    @Override
    public String getComplements() { return "";}
    @Override
    public boolean getCardiaque() { return false;}
    @Override
    public boolean getHypoglycemie() {return false;}
    @Override
    public boolean getAsthme() { return false;}
    @Override
    public boolean getDiabete() { return false;}
    @Override
    public String getAllergies() { return ""; }
    @Override
    public String getAutre() { return ""; }
    @Override
    public String getHistoOperations() { return "";}
    @Override
    public String getHistoDouleur() { return "";}
    @Override
    public String getConstatations() { return "";}
    @Override
    public String getAttentions() { return ""; }
    @Override
    public String getPasMasser() { return ""; }
    @Override
    public boolean getFumeur(){return false;}
    @Override
    public boolean getPeau(){return false;}
    @Override
    public boolean getArthrose(){return false;}
    @Override
    public boolean getOsteoporose(){return false;}
    @Override
    public String getNote(){return "";}
    @Override
    public String getConstipation(){return "";}
    @Override
    public String getNomComplet(){return "";}
    @Override
    public String getDateNaissanceString(){return "";}
   
     @Override
    public List<Traitement> getTraitements(){return null;}
    
    @Override
    public int getIdClinique(){return 0;}
    

    @Override
    public void setId(int id) {}
    @Override
    public void setNom(String nom) {}
    @Override
    public void setPrenom(String prenom) {}    
    @Override
    public void setAdresse(String adresse) {}
    @Override
    public void setVille(String ville) {}
    @Override
    public void setCodePostal(String codePostal) {}
    @Override
    public void setTelephone(String telephone) {}
    @Override
    public void setEmail(String email) {}
    @Override
    public void setDateNaissance(Date dateNaissance) {}
    @Override
    public void setEnfants(int enfants) {}
    @Override
    public void setEmploi(String emploi) {}
    @Override
    public void setLoisirs(String loisirs) {}
    @Override
    public void setRefere(String refere) {}
    @Override
    public void setAutresTherapies(String autresTherapies) {}
    @Override
    public void setMedicaments(String medicaments) {}
    @Override
    public void setComplements(String complements) {}
    @Override
    public void setCardiaque(boolean cardiaque) {}
    @Override
    public void setHypoglycemie(boolean hypoglycemie) {}
    @Override
    public void setAsthme(boolean asthme) {}
    @Override
    public void setDiabete(boolean diabete) {}
    @Override
    public void setAllergies(String allergies) {}
    @Override
    public void setAutre(String autre) {}
    @Override
    public void setHistoOperations(String histoOperations) {}
    @Override
    public void setHistoDouleur(String histoDouleur) {}
    @Override
    public void setConstatations(String constatations) {}
    @Override
    public void setAttentions(String attentions) {}
    @Override
    public void setPasMasser(String pasMasser) {}
    @Override
    public void setFumeur(boolean fumeur){}
    @Override
    public void setPeau(boolean peau){}
    @Override
    public void setArthrose(boolean arthrose){}
    @Override
    public void setOsteoporose(boolean osteoporose){}
    @Override
    public void setNote(String note){}
    @Override
    public void setConstipation(String constipation){}  
    @Override
    public void setNomComplet(String nomComplet){}
    @Override
    public void setDateNaissanceString(String dateNaissanceString){}
   
    
    @Override
    public void setIdClinique(int idClinique){}
   
 
    @Override
    public void setTraitements(List traitements){}
    
    
    
    
    @Override
    public boolean isNil() {
        return true;
    }
    
}
