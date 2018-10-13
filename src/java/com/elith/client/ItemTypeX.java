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
public class ItemTypeX implements Serializable, ItemAbstract {
    
    private TypeX typeX ;
    
    public ItemTypeX(){
    }
    
    public ItemTypeX(TypeX typeX){
        this.typeX = typeX ;
    }
    

    public TypeX getTypeX(){return this.typeX; }
    public void setTypeX(TypeX typeX){this.typeX = typeX;}

}

