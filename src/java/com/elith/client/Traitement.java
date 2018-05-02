/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elith.client;

import com.elith.util.ConversionType;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ambk3201
 */

@XmlRootElement(name = "traitement")
public class Traitement extends TraitementAbstract implements Serializable  {
    
   
    /**
     * Creates a new instance of Traitement
     */
    public Traitement() {
    }

    // full constructeur
    public Traitement(int idClient,
            Date dateVisite,
            String raison,
            String traitement,
            String conseils,
            String commentaires,
            int login,
            float prix,
            String modePaiement,
            int km,
            boolean domicile,
            String tarif,
            float fraisKm,
            float tps,
            float tvq,
            String numCertificat,
            boolean archive,
            String nomTherapeute,
            int idClinique) {
        this.idClient = idClient;
        this.dateVisite = dateVisite;
        this.raison = raison;
        this.traitement = traitement;
        this.conseils = conseils;
        this.commentaires = commentaires;
        this.login = login;
        this.prix = prix;
        this.modePaiement = modePaiement;
        this.km = km;
        this.domicile = domicile;
        this.tarif = tarif ;
        this.fraisKm = fraisKm ;
        this.tps = tps ;
        this.tvq = tvq ;
        this.numCertificat = numCertificat ;
        this.archive = archive ;
        this.nomTherapeute = nomTherapeute;
        this.dateVisiteString = ConversionType.DateToString(new java.sql.Date(dateVisite.getTime()));
        this.idClinique = idClinique ;
    }

    // getters    
    @Override
    public int getId() { return this.id; }
    @Override
    public int getIdClient() { return this.idClient; }
    @Override
    public Date getDateVisite() {return this.dateVisite;}
    @Override
    public java.sql.Date getDateVisiteSql() { return new java.sql.Date(dateVisite.getTime());}
    @Override
    public String getRaison() { return this.raison;}
    @Override
    public String getTraitement() { return this.traitement;}
    @Override
    public String getConseils() { return this.conseils;}
    @Override
    public String getCommentaires() { return this.commentaires; }
    @Override
    public int getLogin() { return this.login; }
    @Override
    public float getPrix() { return this.prix; }
    @Override
    public String getModePaiement() { return this.modePaiement;}
    @Override
    public int getKm() { return this.km;}
    @Override
    public boolean getDomicile() { return this.domicile; }
    @Override
    public String getTarif(){ return this.tarif ;}
    @Override
    public float getFraisKm(){ return this.fraisKm ; }
    @Override
    public float getTps(){return this.tps ;}
    @Override
    public float getTvq(){return this.tvq ;}
    @Override
    public String getNumCertificat(){return this.numCertificat;}
    @Override
    public boolean getArchive(){return this.archive ;}
    @Override
    public String getNomTherapeute(){return this.nomTherapeute;}
    @Override
    public String getDateVisiteString(){return this.dateVisiteString;}
    
    @Override
    public int getIdClinique(){return idClinique;}
    

    // setters
    @Override
    public void setId(int id) { this.id = id; }
    @Override
    public void setIdClient(int idClient) { this.idClient = idClient;}
    @Override
    public void setDateVisite(Date dateVisite) { this.dateVisite = dateVisite;}
    @Override
    public void setRaison(String raison) { this.raison = raison;}
    @Override
    public void setTraitement(String traitement) { this.traitement = traitement; }
    @Override
    public void setConseils(String conseils) { this.conseils = conseils; }
    @Override
    public void setCommentaires(String commentaires) { this.commentaires = commentaires; }
    @Override
    public void setLogin(int login) { this.login = login; }
    @Override
    public void setPrix(float prix) { this.prix = prix; }
    @Override
    public void setModePaiement(String modePaiement) { this.modePaiement = modePaiement; }
    @Override
    public void setKm(int km) { this.km = km; }
    @Override
    public void setDomicile(boolean domicile) { this.domicile = domicile; }
    @Override
    public void setTarif(String tarif){this.tarif = tarif ; }
    @Override
    public void setFraisKm(float fraisKm){ this.fraisKm = fraisKm ; }
    @Override
    public void setTps(float tps){this.tps = tps ;}
    @Override
    public void setTvq(float tvq){this.tvq = tvq ;}
    @Override
    public void setNumCertificat(String numCertificat){this.numCertificat = numCertificat ;}
    @Override
    public void setArchive(boolean archive){this.archive = archive;}
    @Override
    public void setNomTherapeute(String nomTherapeute){this.nomTherapeute = nomTherapeute ;}
    @Override
    public void setDateVisiteString(String dateVisiteString){this.dateVisiteString = dateVisiteString ;}
    
    
    @Override
    public void setIdClinique(int idClinique){this.idClinique = idClinique;}
    
     @Override
    public boolean isNil() { return false;  }

}

