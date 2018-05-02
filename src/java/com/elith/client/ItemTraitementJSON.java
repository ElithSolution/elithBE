/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elith.client;

import com.elith.clinique.ItemJSON;
import com.elith.erreur.ErrorResponse;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ambk3201
 */
public class ItemTraitementJSON extends ItemJSON implements Serializable { 
    
    public ItemTraitementJSON() {
    }
    
    public ItemTraitementJSON(ErrorResponse erreur, List<ItemTraitement> items){
        this.erreur = erreur ;
        this.items = items ;       
    }
    
    
    @Override
    public List<ItemTraitement> getItems(){return this.items ; }
    public void setItems(List<ItemTraitement> items){ this.items = items ; } 
}
