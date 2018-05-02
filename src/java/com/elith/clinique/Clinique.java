/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elith.clinique;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ambk3201
 */
@XmlRootElement(name = "clinique")
public class Clinique implements Serializable {
    

    private int id;
    private String nom;
    private String siteWeb;


    /** Creates a new instance of Clinique */
    public Clinique() {
    }

    // full constructeur
    public Clinique(int id,
            String nom,
            String siteWeb) {
        this.id = id;
        this.nom = nom;
        this.siteWeb = siteWeb;
    }

    // getters
    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getSiteWeb() {return siteWeb;}


    // setters
    public void setId(int id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setSiteWeb(String siteWeb) { this.siteWeb = siteWeb; }
   
}