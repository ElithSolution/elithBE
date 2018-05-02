/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elith.authentification;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ambk3201
 */
@XmlRootElement(name = "credentials")
public class Credentials implements Serializable{
    
    private String login;
    private String motPasse;
    
    /** Creates a new instance of Utilisateur */
    public Credentials() {
    }

    // full constructeur
    public Credentials(
            String login,
            String motPasse) {

        this.login = login;
        this.motPasse = motPasse;
    }

    // getters
    public String getLogin() { return login; }
    public String getMotPasse() { return motPasse; }


    // setters
    public void setLogin(String login) { this.login = login; }
    public void setMotPasse(String motPasse) { this.motPasse = motPasse; }

    
}
