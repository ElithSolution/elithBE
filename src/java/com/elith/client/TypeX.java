/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elith.client;

/**
 *
 * @author ambk3201
 */
public class TypeX {
    
    private int idType ;
    private String intitule ;
    private int ordre ;
    private int idClinique ;
    
    // constructeur
    public TypeX(){
    }
    
    public TypeX(int idType, String intitule, int ordre, int idClinique){
        this.idType = idType ;
        this.intitule = intitule ;
        this.ordre = ordre ;
        this.idClinique = idClinique ;
    }
    
    // getters
    public int getIdType(){return idType;}
    public String getIntitule(){return intitule;}
    public int getOrdre(){return ordre ;}
    public int getIdClinique(){return idClinique ;}
    
    // setters
    public void setIdType(int idType){this.idType = idType;}
    public void setIntitule(String intitule){this.intitule = intitule;}
    public void setOrdre(int ordre){this.ordre = ordre;}
    public void setIdClinique(int idClinique){this.idClinique = idClinique;}
     
}
