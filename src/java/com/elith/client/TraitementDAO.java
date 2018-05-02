/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elith.client;

import com.elith.connexion.CnxPool;
import com.elith.connexion.DAOException;
import com.elith.util.ConversionType;
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

public class TraitementDAO {

    private static final String TRAITEMENT_INSERT = "INSERT INTO traitement (idtraitement, idclient, date, raison, traitement, " +
            "conseils, commentaires, login, prix, modepaiement, km, domicile, tarif, fraiskm, tps, tvq, numcertificat, archive) " +
            "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";       
    private static final String TRAITEMENT_UPDATE = "UPDATE traitement set idclient = ?, date = ?, raison = ?, traitement = ?, " +
            "conseils = ?, commentaires = ?, login = ?, prix = ?, modepaiement = ?, km = ?, domicile = ?, tarif = ?, fraiskm = ?, " +
            "tps = ?, tvq = ?, numcertificat = ?, archive = ? " +
            "WHERE idtraitement = ?";
    
    private static final String TRAITEMENT_MAXCLE = "SELECT MAX(idtraitement) as MaxId FROM traitement";
    private static final String TRAITEMENT_COUNT = "SELECT COUNT(idtraitement) as Compte FROM traitement WHERE idclient = ? " ;
    private static final String TRAITEMENT_EXISTE_BYID = "SELECT date FROM traitement WHERE idtraitement = ?" ;
    
    // AVEC VALIDATION DE LA CLINIQUE COMME SECURITÉ
    private static final String TRAITEMENT_FIND_ALL = "SELECT idtraitement, idclient, date, raison, traitement, conseils, " +
            "commentaires, login, prix, modepaiement, km, domicile, tarif, fraiskm, tps, tvq, numcertificat, archive " +
            "FROM traitement " +
            "WHERE idclinique = ?";    
    private static final String TRAITEMENT_FINDBY_ID = "SELECT idclient, date, raison, traitement, conseils, commentaires, t.login, " +
            "prix, modepaiement, km, domicile, tarif, fraiskm, tps, tvq, numcertificat, archive, u.prenom " +
            "FROM traitement t " +
            "LEFT JOIN utilisateur u on t.login= idutilisateur "+
            "WHERE idtraitement = ? AND t.idclinique = ?";     
    private static final String TRAITEMENT_FINDBY_IDCLIENT = "SELECT idtraitement, date, raison, traitement, conseils, " +
            "commentaires, t.login, prix, modepaiement, km, domicile, tarif, fraiskm, tps, tvq, numcertificat, archive, u.prenom " +
            "FROM traitement t " +
            "LEFT JOIN utilisateur u on t.login= idutilisateur "+
            "WHERE idclient = ? AND t.idclinique = ? ORDER BY date desc, idtraitement desc";
    private static final String TRAITEMENT_FINDBY_IDCLIENT_LIMIT = "SELECT idtraitement, date, raison, traitement, conseils, " +
            "commentaires, t.login, prix, modepaiement, km, domicile, tarif, fraiskm, tps, tvq, numcertificat, archive " +
            "FROM traitement t " +
            "LEFT JOIN utilisateur u on t.login= idutilisateur "+
            "WHERE idclient = ? AND t.idclinique = ? ORDER BY date desc, idtraitement desc " +
            "LIMIT ? OFFSET ? " ; 
    private static final String TRAITEMENT_DELETE = "DELETE FROM traitement WHERE idtraitement = ? AND idclinique = ? ";
        
    private CnxPool cnxPool;

    /**
     * Creates a new instance of TraitementDAO
     */
    public TraitementDAO() throws DAOException {
        cnxPool = CnxPool.getInstance();
    }

    
    public boolean existeTraitementById(int idTraitement) throws DAOException {
        Connection con = getConnection();
        PreparedStatement prepStmt = null;
        ResultSet result;
        boolean ok = false;
          
        try {
            prepStmt = con.prepareStatement(TRAITEMENT_EXISTE_BYID);
            int n = 1;
            prepStmt.setInt(n++, idTraitement);
            result = prepStmt.executeQuery();
            if (result.next()) {
                ok = true ;
            }
        }catch(SQLException e) {
            throw new DAOException("Verif de l'existence du traitement " + idTraitement + " impossible !" + e);
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
        return ok ;
    }
	
    public int countTraitement(int idClient) throws DAOException {
       int res = 0;
        Connection con = getConnection();
        PreparedStatement prepStmt = null;
        ResultSet result = null;
        try {
            prepStmt = con.prepareStatement(TRAITEMENT_COUNT);
            int n = 1;
            prepStmt.setInt(n++, idClient);
			
            result = prepStmt.executeQuery();
            if (result.next()) {
                res = result.getInt("Compte");
            }

        } catch (SQLException e) {
            throw new DAOException("Comptage des traitements impossible !" + e);
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
	
    public int maxCle() throws DAOException {
        int res = 0;
        Connection con = getConnection();
        PreparedStatement prepStmt = null;
        ResultSet result = null;

        try {
            prepStmt = con.prepareStatement(TRAITEMENT_MAXCLE);
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

    public void create(Traitement traitement) throws DAOException {
        Connection con = getConnection();
        PreparedStatement prepStmt = null;

        if(traitement != null){
        
                java.util.Date dateVisite = traitement.getDateVisite();
                java.sql.Date dateVisiteSQL = new java.sql.Date(dateVisite.getTime());

                try {

                    int id = this.maxCle() + 1 ;
                    prepStmt = con.prepareStatement(TRAITEMENT_INSERT);
                    int n = 1;
                    prepStmt.setInt(n++, id);
                    prepStmt.setInt(n++, traitement.getIdClient());
                    prepStmt.setDate(n++, dateVisiteSQL);
                    prepStmt.setString(n++, traitement.getRaison());
                    prepStmt.setString(n++, traitement.getTraitement());
                    prepStmt.setString(n++, traitement.getConseils());
                    prepStmt.setString(n++, traitement.getCommentaires());
                    prepStmt.setInt(n++, traitement.getLogin());
                    prepStmt.setFloat(n++, traitement.getPrix());
                    prepStmt.setString(n++, traitement.getModePaiement());
                    prepStmt.setInt(n++, traitement.getKm());
                    prepStmt.setBoolean(n++, traitement.getDomicile());
                    prepStmt.setString(n++, traitement.getTarif());
                    prepStmt.setFloat(n++, traitement.getFraisKm());
                    prepStmt.setFloat(n++, traitement.getTps());
                    prepStmt.setFloat(n++, traitement.getTvq());
                    prepStmt.setString(n++, traitement.getNumCertificat());
                    prepStmt.setBoolean(n++, traitement.getArchive());
                    prepStmt.executeUpdate();

                } catch (SQLException e) {
                    throw new DAOException("Creation du traitement " + traitement + " impossible !" + e);
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
            } // if(traitement != null)
        }

        public boolean updateTraitement(Traitement traitement) throws DAOException {
            Connection con = getConnection();
            PreparedStatement prepStmt = null;
            int k = 0;

            if(traitement != null) {
                java.util.Date dateVisite = ConversionType.StringToDate(traitement.getDateVisiteString());
                java.sql.Date dateVisiteSQL = new java.sql.Date(dateVisite.getTime());

                try {
                    prepStmt = con.prepareStatement(TRAITEMENT_UPDATE);
                    int n = 1;
                    prepStmt.setInt(n++, traitement.getIdClient());
                    prepStmt.setDate(n++, dateVisiteSQL);
                    prepStmt.setString(n++, traitement.getRaison());
                    prepStmt.setString(n++, traitement.getTraitement());
                    prepStmt.setString(n++, traitement.getConseils());
                    prepStmt.setString(n++, traitement.getCommentaires());
                    prepStmt.setInt(n++, traitement.getLogin());
                    prepStmt.setFloat(n++, traitement.getPrix());
                    prepStmt.setString(n++, traitement.getModePaiement());
                    prepStmt.setInt(n++, traitement.getKm());
                    prepStmt.setBoolean(n++, traitement.getDomicile());
                    prepStmt.setString(n++, traitement.getTarif());
                    prepStmt.setFloat(n++, traitement.getFraisKm());
                    prepStmt.setFloat(n++, traitement.getTps());
                    prepStmt.setFloat(n++, traitement.getTvq());
                    prepStmt.setString(n++, traitement.getNumCertificat());
                    prepStmt.setBoolean(n++, traitement.getArchive());
                    prepStmt.setInt(n++, traitement.getId());                    
                    k = prepStmt.executeUpdate();

                } catch (SQLException e) {
                    throw new DAOException("Mise a jour du traitement " + traitement.getId() + " impossible !" + e);
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
        } // if(traitement != null)
        return k == 1 ? true : false;
    }

    public Traitement findById(String id, int idclinique) throws DAOException {
        Connection con = getConnection();
        PreparedStatement prepStmt = null;
        ResultSet result = null;
        Traitement traitement = null ;
        try {
            prepStmt = con.prepareStatement(TRAITEMENT_FINDBY_ID);
            prepStmt.setInt(1, ConversionType.StringToInt(id));
            prepStmt.setInt(2, idclinique);
            result = prepStmt.executeQuery();
            if (result.next()) {
                traitement = new Traitement(result.getInt("idclient"),
                        result.getDate("date"),
                        result.getString("raison"),
                        result.getString("traitement"),
                        result.getString("conseils"),
                        result.getString("commentaires"),
                        result.getInt("login"),
                        result.getFloat("prix"),
                        result.getString("modepaiement"),
                        result.getInt("km"),
                        result.getBoolean("domicile"),
                        result.getString("tarif"),
                        result.getFloat("fraiskm"),
                        result.getFloat("tps"),
                        result.getFloat("tvq"),
                        result.getString("numcertificat"),
                        result.getBoolean("archive"),
                        result.getString("prenom"),
                        idclinique);
                traitement.setId(ConversionType.StringToInt(id));
            }
        } catch (SQLException e) {
            throw new DAOException("Traitement pour l'id " + id + " impossible a obtenir!" + e);
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
        return traitement;
    }

    public boolean deleteTraitement(String id, int idClinique) throws DAOException {
        Connection con = getConnection();
        PreparedStatement prepStmt = null;
        int n = 0;
        try {
            prepStmt = con.prepareStatement(TRAITEMENT_DELETE);
            prepStmt.setInt(1, ConversionType.StringToInt(id));
            prepStmt.setInt(2, idClinique);
            n = prepStmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Suppression du traitement " + id + " impossible !" + e);
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

    public List<Traitement> getAllTraitementClient(int idClient, int idClinique) throws DAOException {
        List<Traitement> liste = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement prepStmt = null;
        ResultSet result = null;
        Traitement traitement ;
   
        try {
            prepStmt = con.prepareStatement(TRAITEMENT_FINDBY_IDCLIENT);
            int n = 1;
            prepStmt.setInt(n++, idClient);
            prepStmt.setInt(n++, idClinique);
            result = prepStmt.executeQuery();
            while (result.next()) {

                traitement = new Traitement(idClient,
                        result.getDate("date"),
                        result.getString("raison"),
                        result.getString("traitement"),
                        result.getString("conseils"),
                        result.getString("commentaires"),
                        result.getInt("login"),
                        result.getFloat("prix"),
                        result.getString("modepaiement"),
                        result.getInt("km"),
                        result.getBoolean("domicile"),
                        result.getString("tarif"),
                        result.getFloat("fraiskm"),
                        result.getFloat("tps"),
                        result.getFloat("tvq"),
                        result.getString("numcertificat"),
                        result.getBoolean("archive"),
                        result.getString("prenom"),
                        idClinique);
                traitement.setId(result.getInt("idtraitement"));
                liste.add(traitement);
            }
        } catch (SQLException e) {
            throw new DAOException("Liste des traitements impossible à obtenir!" + e);
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
	
    public List<TraitementAbstract> getAllTraitementClientLimit(int idClient, int idClinique, int debut, int nb) throws DAOException {
        List<TraitementAbstract> liste = new ArrayList<TraitementAbstract>();
        Connection con = getConnection();
        PreparedStatement prepStmt = null;
        ResultSet result = null;
        TraitementAbstract traitement = new TraitementNull();
        try {
            prepStmt = con.prepareStatement(TRAITEMENT_FINDBY_IDCLIENT_LIMIT);
            int n = 1;
            prepStmt.setInt(n++,idClient);
            prepStmt.setInt(n++,idClinique);
            prepStmt.setInt(n++, nb);
            prepStmt.setInt(n++, debut);
            result = prepStmt.executeQuery();
            while (result.next()) {

                traitement = new Traitement(idClient,
                        result.getDate("date"),
                        result.getString("raison"),
                        result.getString("traitement"),
                        result.getString("conseils"),
                        result.getString("commentaires"),
                        result.getInt("login"),
                        result.getFloat("prix"),
                        result.getString("modepaiement"),
                        result.getInt("km"),
                        result.getBoolean("domicile"),
                        result.getString("tarif"),
                        result.getFloat("fraiskm"),
                        result.getFloat("tps"),
                        result.getFloat("tvq"),
                        result.getString("numcertificat"),
                        result.getBoolean("archive"),
                        result.getString("prenom"),
                        idClinique);
                traitement.setId(result.getInt("idtraitement"));
                liste.add(traitement);
            }
        } catch (SQLException e) {
            throw new DAOException("Liste des traitements limit impossible a obtenir!" + e);
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
