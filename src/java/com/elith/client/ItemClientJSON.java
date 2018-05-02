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
public class ItemClientJSON extends ItemJSON implements Serializable {   
   
   
   public ItemClientJSON() {
    }
    
    public ItemClientJSON(ErrorResponse erreur, List<ItemClient> items){
        this.erreur = erreur ;
        this.items = items ;       
    }
    
    
    public List<ItemClient> getItems(){return this.items ; }
    public void setItems(List<ItemClient> items){ this.items = items ; }   
        
}

