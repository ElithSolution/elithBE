/*
 * ApplicationProperties.java
 *
 * Created on 2 fevrier 2008, 19:31
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.elith.connexion;


import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;

/**
 *
 * @author Kat
 */
public class ApplicationProperties implements Constantes {

    private static PropertyResourceBundle properties = null;
    public static String datasourceJndiName = null;
    public static String datasourceSchema = null;
    public static String datasourceUserId = null;
    public static String datasourcePassword = null;
    public static String[] datasourceTables = null;
    

        
   



    static {
        try {
            // fichier de configuration
            properties = (PropertyResourceBundle) PropertyResourceBundle.getBundle("application");
            datasourceJndiName = properties.getString("clinique.datasource.name");
            datasourceSchema = properties.getString("clinique.datasource.schema");
            datasourceUserId = properties.getString("clinique.datasource.userid");
            datasourcePassword = properties.getString("clinique.datasource.password");
            datasourceTables = properties.getString("clinique.datasource.tables").split(";"); 
            
            
                               

        } catch (MissingResourceException e) {
            System.out.println("ApplicationProperties (MissingResourceException)" + e);

            datasourceJndiName = DATASOURCE_JNDI_NAME;
            datasourceSchema = DATASOURCE_SCHEMA;
            datasourceUserId = DATASOURCE_USERID;
            datasourcePassword = DATASOURCE_PASSWORD;
        }
    }

    public static String getDatasourceJndiName() { return datasourceJndiName; }
    public static String getDatasourceSchema() { return datasourceSchema; }
    public static String getDatasourceUserId() { return datasourceUserId; }
    public static String getDatasourcePassword() { return datasourcePassword; }
    public static String[] getDatasourceTables() { return datasourceTables; }
    

    
}


