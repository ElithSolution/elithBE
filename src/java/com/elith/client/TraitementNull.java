/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elith.client;

import com.elith.erreur.ErrorResponse;
import java.io.Serializable;
import java.util.Date;
import javax.ws.rs.core.Response;

/**
 *
 * @author ambk3201
 */
    public class TraitementNull extends TraitementAbstract implements Serializable {
    
    /** Creates a new instance of Utilisateur */
    public TraitementNull() {
    }

    
    // getters  
    @Override
    public int getId() { return 0;}
    @Override
    public int getIdClient() { return 0;}
    @Override
    public Date getDateVisite() {return null;}
    @Override
    public java.sql.Date getDateVisiteSql() { return null;}
    @Override
    public String getRaison() { return "";}
    @Override
    public String getTraitement() { return "";}
    @Override
    public String getConseils() { return "";}
    @Override
    public String getCommentaires() { return "";}
    @Override
    public int getLogin() { return 0;}
    @Override
    public float getPrix() { return 0;}
    @Override
    public String getModePaiement() { return "";}
    @Override
    public int getKm() { return 0;}
    @Override
    public boolean getDomicile() { return false; }
    @Override
    public String getTarif(){ return "";}
    @Override
    public float getFraisKm(){ return 0;}
    @Override
    public float getTps(){return 0;}
    @Override
    public float getTvq(){return 0;}
    @Override
    public String getNumCertificat(){return "";}
    @Override
    public boolean getArchive(){return false;}
    @Override
    public String getNomTherapeute(){return "";}
    @Override
    public String getDateVisiteString(){return "";}
    
    @Override
    public int getIdClinique(){return 0;}
    

    // setters
    @Override
    public void setId(int id) {}
    @Override
    public void setIdClient(int idClient) {}
    @Override
    public void setDateVisite(Date dateVisite) {}
    @Override
    public void setRaison(String raison) {}
    @Override
    public void setTraitement(String traitement) {}
    @Override
    public void setConseils(String conseils) {}
    @Override
    public void setCommentaires(String commentaires) {}
    @Override
    public void setLogin(int login) {}
    @Override
    public void setPrix(float prix) {}
    @Override
    public void setModePaiement(String modePaiement) {}
    @Override
    public void setKm(int km) {}
    @Override
    public void setDomicile(boolean domicile) {}
    @Override
    public void setTarif(String tarif){}
    @Override
    public void setFraisKm(float fraisKm){}
    @Override
    public void setTps(float tps){}
    @Override
    public void setTvq(float tvq){}
    @Override
    public void setNumCertificat(String numCertificat){}
    @Override
    public void setArchive(boolean archive){}
    @Override
    public void setNomTherapeute(String nomTherapeute){}
    @Override
    public void setDateVisiteString(String dateVisiteString){}
    
    
    @Override
    public void setIdClinique(int idClinique){}
    
    
    @Override
    public boolean isNil() {
        return true;
    }
    
}
