/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elith.client;

import com.elith.connexion.CnxPool;
import com.elith.connexion.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ambk3201
 */
public class TypeXDAO {
    
    private final CnxPool cnxPool;
    private final String champId ;
    private final String champIntitule ;
    private final String table ;
    private final String champOrdre ;
    private final int idClinique ;
     
    
    /** Creates a new instance of TypeXDAO
     * @param champId
     * @param champIntitule
     * @param table
     * @param champOrdre
     * @param idClinique
     * @throws com.elith.connexion.DAOException */
    public TypeXDAO(String champId, String champIntitule, String table, String champOrdre, int idClinique) throws DAOException {
        cnxPool = CnxPool.getInstance();
        this.champId = champId ;
        this.champIntitule = champIntitule ;
        this.champOrdre = champOrdre ;
        this.table = table ;
        this.idClinique = idClinique ;
    }
    
        
    
     public List<TypeX> getAllTypeX() throws DAOException {
         
       String TYPEX_FIND_ALL = "SELECT " + this.champId + ", " + this.champIntitule + ", " + this.champOrdre
                               + " FROM " + this.table 
                               + " WHERE idclinique = " + this.idClinique
                               + " ORDER BY " + this.champOrdre ;  
   
         
        List<TypeX> liste = new ArrayList();
        Connection con = getConnection();
        PreparedStatement prepStmt = null;
        ResultSet result = null;
        TypeX typeX ;
        try {
            prepStmt = con.prepareStatement(TYPEX_FIND_ALL);
            result = prepStmt.executeQuery();
            
            while (result.next()) {
                typeX = new TypeX(result.getInt(1), result.getString(2), result.getInt(3), this.idClinique);                           
                liste.add(typeX);
            }
            
        } catch (SQLException e) {
            throw new DAOException("Liste des types impossible a obtenir!" + e);
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Probleme a la fermeture du ResultSet " + e);
            }
            try {
                if (prepStmt != null) {
                    prepStmt.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Probleme a la fermeture du PreparedStatement " + e);
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Probleme a la fermeture de la connexion " + e);
            }
        }

        return liste;
    }
    
    
    
    public Connection getConnection() throws DAOException {
        return cnxPool.getConnection();
    }
    
}


