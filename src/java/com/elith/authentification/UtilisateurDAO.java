/*
 * UtilisateurDAO.java
 *
 * Created on 6 avril 2008, 20:23
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.elith.authentification;

import com.elith.connexion.CnxPool;
import com.elith.connexion.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kat
 */
public class UtilisateurDAO {

   
    private static final String UTILISATEUR_FIND_ALL = "SELECT idutilisateur, nom, prenom, email, login, motpasse, actif, idclinique FROM utilisateur";
    private static final String UTILISATEUR_FINDBY_NOM = "SELECT idutilisateur, prenom, email, login, motpasse, actif, idclinique FROM utilisateur WHERE upper(nom) like ?";
    
    private static final String UTILISATEUR_UPDATE = "UPDATE utilisateur set nom = ?, prenom = ?, email = ?, login = ?, actif = ?, idclinique = ? WHERE idutilisateur = ?";
    private static final String UTILISATEUR_UPDATE_PWD = "UPDATE utilisateur set motPasse = ? where login = ? and motPasse = ?";
    private static final String UTILISATEUR_DELETE = "DELETE FROM utilisateur WHERE idutilisateur = ?";
    private static final String UTILISATEUR_MAXCLE = "SELECT MAX(idutilisateur) as MaxId FROM utilisateur";
    private static final String UTILISATEUR_DROITS = "SELECT nommodule FROM module m LEFT JOIN droit d on m.idmodule = d.idmodule WHERE iduser = ? " ;
    private static final String UTILISATEUR_IDUSED = "SELECT distinct(login) FROM traitement " +
            "UNION " +
            "SELECT distinct(login) FROM produitachete" ;
    private static final String UTILISATEUR_MODULE = "SELECT idmodule FROM module WHERE lower(nommodule) like ? " ;
    private static final String UTILISATEUR_INSERT_DROIT = "INSERT INTO droit(iduser, idmodule) VALUES(?, ?) " ;
    private static final String UTILISATEUR_DELETE_DROIT = "DELETE FROM droit WHERE iduser = ? " ;
    
    
    /****************************************************************************************************************************************************************/
    private static final String UTILISATEUR_FINDBY_ID = "SELECT nom, prenom, email, login, motpasse, actif, idclinique, role FROM utilisateur WHERE idutilisateur = ?";
    private static final String UTILISATEUR_FINDBY_LOGIN_PWD = "SELECT idutilisateur, nom, prenom, email, actif, idclinique, role FROM utilisateur WHERE login like ? and motpasse like ?";
    private static final String UTILISATEUR_COUNT_ACTIF = "SELECT idutilisateur, nom, prenom, email, login, motpasse, actif, idclinique, role FROM utilisateur WHERE (actif = true) ORDER BY nom";
    private static final String UTILISATEUR_COUNT = "SELECT idutilisateur, nom, prenom, email, login, motpasse, actif, idclinique, role FROM utilisateur ORDER BY nom";
    private static final String UTILISATEUR_INSERT = "INSERT INTO utilisateur (idutilisateur, nom, prenom, email, login, motpasse, actif, idclinique, role) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    // AVEC VALIDATION DE LA CLINIQUE COMME SECURITÉ
    private static final String UTILISATEUR_FINDBY_ID_SECURED = "SELECT nom, prenom, email, login, motpasse, actif, idclinique, role FROM utilisateur "
                                                                    + "WHERE idutilisateur = ? AND idclinique = ?";
 
    
    private CnxPool cnxPool;

    /** Creates a new instance of UtilisateurDAO */
    public UtilisateurDAO() throws DAOException {
        cnxPool = CnxPool.getInstance();
    }

    public void insertDroit(int iduser, int module) throws DAOException {
       Connection con = getConnection();
        PreparedStatement prepStmt = null;

        try {
                         
            prepStmt = con.prepareStatement(UTILISATEUR_INSERT_DROIT);
            int n = 1;
            prepStmt.setInt(n++, iduser);
            prepStmt.setInt(n++, module);        
            
            prepStmt.executeUpdate();
            
        }catch (SQLException e) {
            throw new DAOException("creation du droit impossible !" + e);
        } finally {
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
    }
    
    public int getIdModule(String module) throws DAOException {
        int res = 0 ;
        Connection con = getConnection();
        PreparedStatement prepStmt = null;
        ResultSet result = null;
        try {
            prepStmt = con.prepareStatement(UTILISATEUR_MODULE);
            prepStmt.setString(1, module != null ? module.toLowerCase() : "");
             
            result = prepStmt.executeQuery();
            if (result.next()) {
                res = result.getInt("idmodule");
            }

        } catch (SQLException e) {
            throw new DAOException("Recuperation de la cle impossible !" + e);

        } finally {
            try {
                if (prepStmt != null) {
                    prepStmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Probleme a la fermeture de la connexion" + e);
            }
        }
        return res ;
    }
    
      public int maxCle() throws DAOException {
        int res = 0;
        Connection con = getConnection();
        PreparedStatement prepStmt = null;
        ResultSet result = null;

        try {
            prepStmt = con.prepareStatement(UTILISATEUR_MAXCLE);
            result = prepStmt.executeQuery();
            if (result.next()) {
                res = result.getInt("MaxId");
            }

        } catch (SQLException e) {
            throw new DAOException("Recuperation de la cle impossible !" + e);

        } finally {
            try {
                if (prepStmt != null) {
                    prepStmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Probleme a la fermeture de la connexion" + e);
            }
        }
        return res;
    }
    
    public void create(UtilisateurAbstract utilisateur) throws DAOException {
        Connection con = getConnection();
        PreparedStatement prepStmt = null;

        try {
            
            int id = this.maxCle() + 1 ;
            System.out.println("prochain id : " + id);
             
            prepStmt = con.prepareStatement(UTILISATEUR_INSERT);
            int n = 1;

            prepStmt.setInt(n++, id);
            prepStmt.setString(n++, utilisateur.getNom());
            prepStmt.setString(n++, utilisateur.getPrenom());
            prepStmt.setString(n++, utilisateur.getEmail());
            prepStmt.setString(n++, utilisateur.getLogin());
            prepStmt.setString(n++, utilisateur.getMotPasse());
            prepStmt.setBoolean(n++, utilisateur.getActif());  
            prepStmt.setInt(n++, utilisateur.getIdClinique());
            prepStmt.setString(n++, utilisateur.getRole().toString());
            
            prepStmt.executeUpdate();
            
        }catch (SQLException e) {
            throw new DAOException("creation de l'utilisateur impossible !" + e);
        } finally {
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
    }
    
    public boolean deleteDroitsUtilisateur(int iduser) throws DAOException {
        Connection con = getConnection();
        PreparedStatement prepStmt = null;
        int n = 0;
        
        try {
            prepStmt = con.prepareStatement(UTILISATEUR_DELETE_DROIT);           
            prepStmt.setInt(1, iduser);
            n = prepStmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Suppression des droits de l'utilisateur " + iduser + " impossible !" + e);
        } finally {
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
        return n == 1 ? true : false;
    }
    
    public boolean deleteUtilisateur(int id) throws DAOException {
        Connection con = getConnection();
        PreparedStatement prepStmt = null;
        int n = 0;
        
        try {
            prepStmt = con.prepareStatement(UTILISATEUR_DELETE);           
            prepStmt.setInt(1, id);
            n = prepStmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Suppression de l'utilisateur " + id + " impossible !" + e);
        } finally {
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
        return n == 1 ? true : false;
    }

    
    public List<Integer> findAllIdUtilisateursUsed() throws DAOException {
        List<Integer> res = new ArrayList<Integer>();
        Connection con = getConnection();
        PreparedStatement prepStmt = null;
        ResultSet result = null;
        try {
            prepStmt = con.prepareStatement(UTILISATEUR_IDUSED);
            result = prepStmt.executeQuery();
            while (result.next()) {
                res.add(result.getInt("login"));
            }
        } catch (SQLException e) {
            throw new DAOException("Liste des Id des utilisateurs impossible a obtenir!" + e);
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
        return res;
    }
    
    
    public List<String> getDroitsUtilisateur(int iduser) throws DAOException {
        List<String> listeDroits = new ArrayList<String>();
        Connection con = getConnection();
        PreparedStatement prepStmt = null;
        ResultSet result = null;
        
        try {
            prepStmt = con.prepareStatement(UTILISATEUR_DROITS);
            prepStmt.setInt(1, iduser) ;
            result = prepStmt.executeQuery();
            while (result.next()) {
               String droit = result.getString("nommodule") ;                
               listeDroits.add(droit);
            }
        } catch (SQLException e) {
            throw new DAOException("Recuperation des droits impossible !" + e);

        } finally {
            try {
                if (prepStmt != null) {
                    prepStmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Probleme a la fermeture de la connexion" + e);
            }
        }
        return listeDroits ;
    }
    
    public Map<Integer, UtilisateurAbstract> countUtilisateurActif() throws DAOException {
            Map<Integer, UtilisateurAbstract> res = new LinkedHashMap<Integer, UtilisateurAbstract>();
            res = countUtilisateur(true);
        return res;
    }
    
     public Map<Integer, UtilisateurAbstract> countTousUtilisateur() throws DAOException {
            Map<Integer, UtilisateurAbstract> res = new LinkedHashMap<Integer, UtilisateurAbstract>();
            res = countUtilisateur(false);
        return res;
    }
      
    public Map<Integer, UtilisateurAbstract> countUtilisateur(boolean actif) throws DAOException {
        Map<Integer, UtilisateurAbstract> res = new LinkedHashMap<Integer, UtilisateurAbstract>();
        Utilisateur utilisateur = null;
        Connection con = getConnection();
        PreparedStatement prepStmt = null;
        ResultSet result = null;

        try {
            if(actif)
                prepStmt = con.prepareStatement(UTILISATEUR_COUNT_ACTIF);
            else
                prepStmt = con.prepareStatement(UTILISATEUR_COUNT);
            result = prepStmt.executeQuery();
            while (result.next()) {
                utilisateur = new Utilisateur(result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("email"),
                        result.getString("login"),
                        result.getString("motpasse"),
                        result.getBoolean("actif"),
                        result.getInt("idclinique"),
                        Role.valueOf(result.getString("role").toUpperCase()));
                utilisateur.setId(result.getInt("idutilisateur"));
                res.put(utilisateur.getId(), utilisateur);
            }
        } catch (SQLException e) {
            throw new DAOException("Recuperation de la cle impossible !" + e);

        } finally {
            try {
                if (prepStmt != null) {
                    prepStmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Probleme a la fermeture de la connexion" + e);
            }
        }
        return res;
    }
    
  

    
    public boolean updateUtilisateur(Utilisateur utilisateur) throws DAOException {
       Connection con = getConnection();
       PreparedStatement prepStmt = null;
       int k = 0;
       int idUser = 0 ;
       if(utilisateur != null) idUser = utilisateur.getId() ;
       
        try {
            prepStmt = con.prepareStatement(UTILISATEUR_UPDATE);
            int n = 1;
            prepStmt.setString(n++, utilisateur.getNom());
            prepStmt.setString(n++, utilisateur.getPrenom());
            prepStmt.setString(n++, utilisateur.getEmail());
            prepStmt.setString(n++, utilisateur.getLogin());
            prepStmt.setBoolean(n++, utilisateur.getActif());
            prepStmt.setInt(n++, utilisateur.getIdClinique());
            prepStmt.setInt(n++, idUser);
            k = prepStmt.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Mise a jour de l'utilisateur " + idUser + " impossible !" + e);
        } finally {
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
        return k == 1 ? true : false; 
    }
    
    public boolean updatePwdUtilisateur(String login, String nvPwd, String ancienPwd) throws DAOException {
        Connection con = getConnection();
        PreparedStatement prepStmt = null;
        int k = 0;

        try {
            prepStmt = con.prepareStatement(UTILISATEUR_UPDATE_PWD);
            int n = 1;
            prepStmt.setString(n++, nvPwd);
            prepStmt.setString(n++, login);
            prepStmt.setString(n++, ancienPwd);
            k = prepStmt.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Mise a jour du mot de passe de " + login + " impossible !" + e);
        } finally {
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
        return k == 1 ? true : false;
    }

    public UtilisateurAbstract findByLoginPwd(String login, String pwd) throws DAOException {
        Connection con = getConnection();
        PreparedStatement prepStmt = null;
        ResultSet result = null;
        UtilisateurAbstract utilisateur = new UtilisateurNull();
              
        try {
            prepStmt = con.prepareStatement(UTILISATEUR_FINDBY_LOGIN_PWD);
            prepStmt.setString(1, login);
            prepStmt.setString(2, pwd);
            
                    
            result = prepStmt.executeQuery();
            if (result.next()) {
                               
                utilisateur = new Utilisateur(result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("email"),
                        login,
                        pwd,
                        result.getBoolean("actif"),
                        result.getInt("idclinique"),
                        Role.valueOf(result.getString("role").toUpperCase()));
                utilisateur.setId(result.getInt("idutilisateur"));
                
            }
        } catch (SQLException e) {
            throw new DAOException("Utilisateur pour le login " + login + " impossible a obtenir!" + e);
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

        return utilisateur;
    }
    
    
     public Utilisateur findById(int id, int idclinique) throws DAOException {
        Connection con = getConnection();
        PreparedStatement prepStmt = null;
        ResultSet result = null;
        Utilisateur utilisateur = null;
        try {
            
             if(idclinique != -1){
                prepStmt = con.prepareStatement(UTILISATEUR_FINDBY_ID_SECURED);
                prepStmt.setInt(1, id);
                prepStmt.setInt(2, idclinique);
            }else{
                prepStmt = con.prepareStatement(UTILISATEUR_FINDBY_ID);
                prepStmt.setInt(1, id); 
             }

            result = prepStmt.executeQuery();
            if (result.next()) {
                            
                utilisateur = new Utilisateur(result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("email"),
                        result.getString("login"),
                        result.getString("motpasse"),
                        result.getBoolean("actif"),
                        result.getInt("idclinique"),
                        Role.valueOf(result.getString("role").toUpperCase()));
                utilisateur.setId(id);
            }
        } catch (SQLException e) {
            throw new DAOException("Utilisateur pour l'id " + id + " impossible a obtenir!" + e);
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
        return utilisateur;
    }

    public Connection getConnection() throws DAOException {
        return cnxPool.getConnection();
    }
}
