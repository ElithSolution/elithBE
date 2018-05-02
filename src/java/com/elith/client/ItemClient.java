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
public class ItemClient implements Serializable, ItemAbstract {
    
    private ClientAbstract client ;
    
    public ItemClient(){
    }
    
    public ItemClient(ClientAbstract client){
        this.client = client ;
    }
    

    public ClientAbstract getClient(){return this.client; }
    public void setClient(ClientAbstract client){this.client = client;}


    
}
