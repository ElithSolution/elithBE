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
public class ItemTypeXJSON extends ItemJSON implements Serializable {   
   
   
   public ItemTypeXJSON() {
    }
    
    public ItemTypeXJSON(ErrorResponse erreur, List<ItemTypeX> items){
        this.erreur = erreur ;
        this.items = items ;       
    }
    
    
   @Override
    public List<ItemTypeX> getItems(){return this.items ; }
    public void setItems(List<ItemTypeX> items){ this.items = items ; }   
        

}
