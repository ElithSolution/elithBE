/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elith.clinique;

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
public class CliniqueDAO {
    
    private static final String CLINIQUE_FIND_ALL = "SELECT idclinique, nom, siteweb FROM clinique";
    private static final String CLINIQUE_FINDBY_ID = "SELECT nom, siteweb FROM clinique WHERE idclinique = ?";
    
    
    private CnxPool cnxPool;
    
    /** Creates a new instance of CliniqueDAO */
    public CliniqueDAO() throws DAOException {
        cnxPool = CnxPool.getInstance();
    }
    
    public List<Clinique> findAll() throws DAOException {
        List<Clinique> liste = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement prepStmt = null;
        ResultSet result = null;
        Clinique clinique = null;
        try {
            prepStmt = con.prepareStatement(CLINIQUE_FIND_ALL);
            result = prepStmt.executeQuery();
            while (result.next()) {
                clinique = new Clinique(result.getInt("idclinique"),
                        result.getString("nom"),                    
                        result.getString("siteweb"));
                liste.add(clinique);
            }
        } catch (SQLException e) {
            throw new DAOException("Liste des cliniques impossible a obtenir!" + e);
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
    
    public Clinique findById(int id) throws DAOException {
        Connection con = getConnection();
        PreparedStatement prepStmt = null;
        ResultSet result = null;
        Clinique clinique = null;
        try {
            prepStmt = con.prepareStatement(CLINIQUE_FINDBY_ID);
            prepStmt.setInt(1, id);

            result = prepStmt.executeQuery();
            if (result.next()) {
                               
                clinique = new Clinique(id,
                        result.getString("nom"),
                        result.getString("siteweb"));
            }
        } catch (SQLException e) {
            throw new DAOException("Clinique pour l'id " + id + " impossible a obtenir!" + e);
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
        return clinique;
    }

    public Connection getConnection() throws DAOException {
        return cnxPool.getConnection();
    }
    
}
