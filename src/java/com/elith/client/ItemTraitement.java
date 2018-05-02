/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elith.client;

import com.elith.clinique.ItemAbstract;
import java.io.Serializable;

/**
 *
 * @author ambk3201
 */
public class ItemTraitement implements Serializable, ItemAbstract {
    
    private TraitementAbstract traitement ;
    
    public ItemTraitement(){
    }
    
    public ItemTraitement(TraitementAbstract traitement){
        this.traitement = traitement ;
    }
    

    public TraitementAbstract getTraitement(){return this.traitement; }
    public void setTraitement(TraitementAbstract traitement){this.traitement = traitement;}


}
