/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elith.clinique;

import com.elith.client.ItemTraitement;
import com.elith.erreur.ErrorResponse;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ambk3201
 * @param <Item>
 */
public abstract class ItemJSON<Item> implements Serializable {
    
   protected ErrorResponse erreur ;
   protected List<Item> items;
   
   public ErrorResponse getErrorResponse(){return this.erreur; }   
   public void setErrorResponse(ErrorResponse erreur){ this.erreur = erreur ; }
   
   public abstract List<Item> getItems();
      
      
}
