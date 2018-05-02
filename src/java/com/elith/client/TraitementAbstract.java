/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elith.client;

import com.elith.erreur.ErrorResponse;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ambk3201
 */
public abstract class TraitementAbstract implements Serializable  {
    
    protected int id;
    protected int idClient;
    protected Date dateVisite;
    protected String raison;
    protected String traitement;
    protected String conseils;
    protected String commentaires;
    protected int login;
    protected float prix; // TTC
    protected String modePaiement;
    protected int km;
    protected boolean domicile ;
    protected String tarif ;
    protected float fraisKm ;
    protected float tps ;
    protected float tvq ;
    protected String numCertificat ;
    protected boolean archive ;
    
    protected String nomTherapeute;
    protected String dateVisiteString;

    // clinique
    protected int idClinique ;
    
    // getters    
    public abstract int getId();
    public abstract int getIdClient();
    public abstract Date getDateVisite();
    public abstract java.sql.Date getDateVisiteSql();
    public abstract String getRaison();
    public abstract String getTraitement();
    public abstract String getConseils();
    public abstract String getCommentaires();
    public abstract int getLogin();
    public abstract float getPrix();
    public abstract String getModePaiement();
    public abstract int getKm();
    public abstract boolean getDomicile();
    public abstract String getTarif();
    public abstract float getFraisKm();
    public abstract float getTps();
    public abstract float getTvq();
    public abstract String getNumCertificat();
    public abstract boolean getArchive();
    public abstract String getNomTherapeute();
    public abstract String getDateVisiteString();
    
    public abstract int getIdClinique();
    
    // setters    
    public abstract void setId(int id);
    public abstract void setIdClient(int idClient);
    public abstract void setDateVisite(Date dateVisite);
    public abstract void setRaison(String raison);
    public abstract void setTraitement(String traitement);
    public abstract void setConseils(String conseils);
    public abstract void setCommentaires(String commentaires);
    public abstract void setLogin(int login);
    public abstract void setPrix(float prix);
    public abstract void setModePaiement(String modePaiement);
    public abstract void setKm(int km);
    public abstract void setDomicile(boolean domicile);
    public abstract void setTarif(String tarif);
    public abstract void setFraisKm(float fraisKm);
    public abstract void setTps(float tps);
    public abstract void setTvq(float tvq);
    public abstract void setNumCertificat(String numCertificat);
    public abstract void setArchive(boolean archive);
    public abstract void setNomTherapeute(String nomTherapeute);
    public abstract void setDateVisiteString(String dateVisiteString);

    public abstract void setIdClinique(int idClinique);
    
    public abstract boolean isNil();
   
}
