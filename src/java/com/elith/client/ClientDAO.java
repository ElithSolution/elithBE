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
public class ClientDAO {
    
    
   
   
    private static final String CLIENT_UPDATE = "UPDATE client set nom = ?, prenom = ?, adresse = ?, ville = ?, codepostal = ?, " +
            "telephone = ?, email = ?, datenaissance = ?, enfants = ?, emploi = ?, loisirs = ?, refere = ?, autrestherapies = ?, " +
            "medicaments = ?, complements = ?, cardiaque = ?, hypoglycemie = ?, asthme = ?, diabete = ?, allergies = ?, " +
            "autre = ?, histooperations = ?, histodouleur = ?, constatations = ?, attentions = ?, pasmasser = ?," +
            "fumeur = ?, peau = ?, arthrose = ?, osteoporose = ?, note = ?, constipation = ? where idclient = ?";
    private static final String CLIENT_TRAITEMENT_DELETE = "DELETE FROM traitement WHERE idclient = ?";
    private static final String CLIENT_DOCJOINT_DELETE = "DELETE FROM documentjoint WHERE idclient = ?";
    private static final String CLIENT_PRODUITACHETE_DELETE = "DELETE FROM produitachete WHERE idclient = ?";    
    private static final String CLIENT_EXISTE = "SELECT idclient FROM client WHERE lower(nom) like ? AND lower(prenom) like ? " +
            "AND datenaissance = ? AND idclinique = ?";
    private static final String CLIENT_EXISTE_BYID = "SELECT nom FROM client WHERE idclient = ?" ;
                
    
    /****************************************************************************************************************************************************************/
    private static final String CLIENT_MAXCLE = "SELECT MAX(idclient) as MaxId FROM client";
    private static final String CLIENT_INSERT = "INSERT INTO client (idclient, nom, prenom, adresse, ville, codepostal, telephone, " +
           "email, datenaissance, enfants, emploi, loisirs, refere, autrestherapies, medicaments, complements," +
            "cardiaque, hypoglycemie, asthme, diabete, allergies, autre, histooperations, " +
            "histodouleur, constatations, attentions, pasmasser, fumeur, peau, arthrose, osteoporose, note, constipation, idclinique) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
    // AVEC VALIDATION DE LA CLINIQUE COMME SECURITÉ
    private static final String CLIENT_FIND_ALL = "SELECT idclient, nom, prenom, adresse, ville, codepostal, telephone, " +
            "email, datenaissance, enfants, emploi, loisirs, refere, autrestherapies, medicaments, complements, " +
            "cardiaque, hypoglycemie, asthme, diabete, allergies, autre, histooperations, " +
            "histodouleur, constatations, attentions, pasmasser, fumeur, peau, arthrose, osteoporose, note, constipation " +
            "FROM client WHERE idclinique = ? ";
    private static final String CLIENT_FINDBY_ID = "SELECT idclient, nom, prenom, adresse, ville, codepostal, telephone, " +
            "email, datenaissance, enfants, emploi, loisirs, refere, autrestherapies, medicaments, complements, " +
            "cardiaque, hypoglycemie, asthme, diabete, allergies, autre, histooperations, " +
            "histodouleur, constatations, attentions, pasmasser, fumeur, peau, arthrose, osteoporose, note, constipation, idclinique " +
            "FROM client WHERE idclient = ? and idclinique = ? ";
    private static final String CLIENT_FINDBY_NOM = "SELECT idclient, nom, prenom, adresse, ville, codepostal, telephone, " +
            "email, datenaissance, enfants, emploi, loisirs, refere, autrestherapies, medicaments, complements, " +
            "cardiaque, hypoglycemie, asthme, diabete, allergies, autre, histooperations, " +
            "histodouleur, constatations, attentions, pasmasser, fumeur, peau, arthrose, osteoporose, note, constipation " +
            "FROM client WHERE ((upper(nom) like ? OR upper(prenom) like ?) AND idclinique = ? )" +
            "ORDER by nom, prenom";
    private static final String CLIENT_FINDBY_TELEPHONE = "SELECT idclient, nom, prenom, adresse, ville, codepostal, telephone, " +
            "email, datenaissance, enfants, emploi, loisirs, refere, autrestherapies, medicaments, complements, " +
            "cardiaque, hypoglycemie, asthme, diabete, allergies, autre, histooperations, " +
            "histodouleur, constatations, attentions, pasmasser, fumeur, peau, arthrose, osteoporose, note, constipation " +
            "FROM client WHERE (telephone like ? AND idclinique = ? )" +
            "ORDER by nom, prenom";    
    private static final String CLIENT_DELETE = "DELETE FROM client WHERE idclient = ? and idclinique = ?"; 

    
    private CnxPool cnxPool;

    /** Creates a new instance of ClientDAO
     * @throws com.elith.connexion.DAOException */
    public ClientDAO() throws DAOException {
        cnxPool = CnxPool.getInstance();
    }

     public ClientAbstract findById(int id, int idclinique) throws DAOException {
        Connection con = getConnection();
        PreparedStatement prepStmt = null;
        ResultSet result = null;
        ClientAbstract client = new ClientNull() ;
        try {
            prepStmt = con.prepareStatement(CLIENT_FINDBY_ID);
            prepStmt.setInt(1, id);
            prepStmt.setInt(2, idclinique);
            result = prepStmt.executeQuery();
            if (result.next()) {
                client = new Client(id,
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("adresse"),
                        result.getString("ville"),
                        result.getString("codepostal"),
                        result.getString("telephone"),
                        result.getString("email"),
                        result.getDate("datenaissance"),
                        result.getInt("enfants"),
                        result.getString("emploi"),
                        result.getString("loisirs"),
                        result.getString("refere"),
                        result.getString("autrestherapies"),
                        result.getString("medicaments"),
                        result.getString("complements"),
                        result.getBoolean("cardiaque"),
                        result.getBoolean("hypoglycemie"),
                        result.getBoolean("asthme"),
                        result.getBoolean("diabete"),
                        result.getString("allergies"),
                        result.getString("autre"),
                        result.getString("histooperations"),
                        result.getString("histodouleur"),
                        result.getString("constatations"),
                        result.getString("attentions"),
                        result.getString("pasmasser"),
                        result.getBoolean("fumeur"),
                        result.getBoolean("peau"),
                        result.getBoolean("arthrose"),
                        result.getBoolean("osteoporose"),
                        result.getString("note"),
                        result.getString("constipation"),
                        result.getInt("idclinique"));
        
            }
        } catch (SQLException e) {
            throw new DAOException("Client pour l'id " + id + " impossible a obtenir!" + e);
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
        return client;
    }
    
    public List<Client> findByNom(String nom, int idclinique) throws DAOException {
        List<Client> liste = new ArrayList();
        Connection con = getConnection();
        PreparedStatement prepStmt = null;
        ResultSet result = null;
        Client client;
        try {
            prepStmt = con.prepareStatement(CLIENT_FINDBY_NOM);
            prepStmt.setString(1, nom.toUpperCase() + "%");
            prepStmt.setString(2, nom.toUpperCase() + "%");
            prepStmt.setInt(3, idclinique);
            result = prepStmt.executeQuery();
            while (result.next()) {
                client = new Client(result.getInt("idclient"),
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("adresse"),
                        result.getString("ville"),
                        result.getString("codepostal"),
                        result.getString("telephone"),
                        result.getString("email"),
                        result.getDate("datenaissance"),
                        result.getInt("enfants"),
                        result.getString("emploi"),
                        result.getString("loisirs"),
                        result.getString("refere"),
                        result.getString("autrestherapies"),
                        result.getString("medicaments"),
                        result.getString("complements"),
                        result.getBoolean("cardiaque"),
                        result.getBoolean("hypoglycemie"),
                        result.getBoolean("asthme"),
                        result.getBoolean("diabete"),
                        result.getString("allergies"),
                        result.getString("autre"),
                        result.getString("histooperations"),
                        result.getString("histodouleur"),
                        result.getString("constatations"),
                        result.getString("attentions"),
                        result.getString("pasmasser"),
                        result.getBoolean("fumeur"),
                        result.getBoolean("peau"),
                        result.getBoolean("arthrose"),
                        result.getBoolean("osteoporose"),
                        result.getString("note"),
                        result.getString("constipation"),
                        idclinique);
                
                liste.add(client);
            }
            
        } catch (SQLException e) {
            throw new DAOException("Liste des clients impossible a obtenir!" + e);
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
    
    public List<Client> findByTelephone(String telephone, int idclinique) throws DAOException {
        List<Client> liste = new ArrayList();
        Connection con = getConnection();
        PreparedStatement prepStmt = null;
        ResultSet result = null;
        Client client;
        try {
            prepStmt = con.prepareStatement(CLIENT_FINDBY_TELEPHONE);
            prepStmt.setString(1, "%" + telephone + "%");
            prepStmt.setInt(2, idclinique);
            result = prepStmt.executeQuery();
            while (result.next()) {
                client = new Client(result.getInt("idclient"),
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("adresse"),
                        result.getString("ville"),
                        result.getString("codepostal"),
                        result.getString("telephone"),
                        result.getString("email"),
                        result.getDate("datenaissance"),
                        result.getInt("enfants"),
                        result.getString("emploi"),
                        result.getString("loisirs"),
                        result.getString("refere"),
                        result.getString("autrestherapies"),
                        result.getString("medicaments"),
                        result.getString("complements"),
                        result.getBoolean("cardiaque"),
                        result.getBoolean("hypoglycemie"),
                        result.getBoolean("asthme"),
                        result.getBoolean("diabete"),
                        result.getString("allergies"),
                        result.getString("autre"),
                        result.getString("histooperations"),
                        result.getString("histodouleur"),
                        result.getString("constatations"),
                        result.getString("attentions"),
                        result.getString("pasmasser"),
                        result.getBoolean("fumeur"),
                        result.getBoolean("peau"),
                        result.getBoolean("arthrose"),
                        result.getBoolean("osteoporose"),
                        result.getString("note"),
                        result.getString("constipation"),
                        idclinique);
                
                liste.add(client);
            }
            
        } catch (SQLException e) {
            throw new DAOException("Liste des clients impossible a obtenir!" + e);
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
    
     public List<Client> findAll(int idclinique) throws DAOException {
        List<Client> liste = new ArrayList();
        Connection con = getConnection();
        PreparedStatement prepStmt = null;
        ResultSet result = null;
        Client client;
        try {
            prepStmt = con.prepareStatement(CLIENT_FIND_ALL);
            prepStmt.setInt(1, idclinique);
            result = prepStmt.executeQuery();
            while (result.next()) {
                client = new Client(result.getInt("idclient"),
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("adresse"),
                        result.getString("ville"),
                        result.getString("codepostal"),
                        result.getString("telephone"),
                        result.getString("email"),
                        result.getDate("datenaissance"),
                        result.getInt("enfants"),
                        result.getString("emploi"),
                        result.getString("loisirs"),
                        result.getString("refere"),
                        result.getString("autrestherapies"),
                        result.getString("medicaments"),
                        result.getString("complements"),
                        result.getBoolean("cardiaque"),
                        result.getBoolean("hypoglycemie"),
                        result.getBoolean("asthme"),
                        result.getBoolean("diabete"),
                        result.getString("allergies"),
                        result.getString("autre"),
                        result.getString("histooperations"),
                        result.getString("histodouleur"),
                        result.getString("constatations"),
                        result.getString("attentions"),
                        result.getString("pasmasser"),
                        result.getBoolean("fumeur"),
                        result.getBoolean("peau"),
                        result.getBoolean("arthrose"),
                        result.getBoolean("osteoporose"),
                        result.getString("note"),
                        result.getString("constipation"),
                        idclinique);
                               
                liste.add(client);
            }
        } catch (SQLException e) {
            throw new DAOException("Liste des clients impossible a obtenir!" + e);
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

    
    public int maxCle() throws DAOException {
        int res = 0;
        Connection con = getConnection();
        PreparedStatement prepStmt = null;
        ResultSet result ;

        try {
            prepStmt = con.prepareStatement(CLIENT_MAXCLE);
            result = prepStmt.executeQuery();
            if (result.next()) {
                res = result.getInt("MaxId");
                System.out.println("Max clé : " + res);
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

    
    public int existeClient(String nom, String prenom, String date, int idClinique) throws DAOException {
        int idClient = -1 ;
        Connection con = getConnection();
        PreparedStatement prepStmt = null;
        ResultSet result;
        
        java.util.Date dateNais = ConversionType.StringToDate(date);
        java.sql.Date dateNaisSQL = new java.sql.Date(dateNais.getTime());
        
        System.out.println("date naissance sql : " + dateNaisSQL);
          
        try {
            prepStmt = con.prepareStatement(CLIENT_EXISTE);
            int n = 1;
            prepStmt.setString(n++, nom);
            prepStmt.setString(n++, prenom);
            prepStmt.setDate(n++, dateNaisSQL);
            prepStmt.setInt(n++, idClinique);
            System.out.println(prepStmt);
            result = prepStmt.executeQuery();
            if (result.next()) {
                idClient = result.getInt("idclient");
            }
        }catch(SQLException e) {
            throw new DAOException("Verif de l'existence du client " + nom + " impossible !" + e);
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
        return idClient ;
    }
    
    public boolean existeClientById(int idClient) throws DAOException {
        Connection con = getConnection();
        PreparedStatement prepStmt = null;
        ResultSet result;
        boolean ok = false;
          
        try {
            prepStmt = con.prepareStatement(CLIENT_EXISTE_BYID);
            int n = 1;
            prepStmt.setInt(n++, idClient);
            result = prepStmt.executeQuery();
            if (result.next()) {
                ok = true ;
            }
        }catch(SQLException e) {
            throw new DAOException("Verif de l'existence du client " + idClient + " impossible !" + e);
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
    
    public void create(Client client) throws DAOException {

        Connection con = getConnection();
        PreparedStatement prepStmt = null;

        java.util.Date dateNais = ConversionType.StringToDate(client.getDateNaissanceString());
        java.sql.Date dateNaisSQL = new java.sql.Date(dateNais.getTime());
        
        try {
            
            int id = this.maxCle() + 1 ;
                        
            prepStmt = con.prepareStatement(CLIENT_INSERT);
            int n = 1;

            prepStmt.setInt(n++, id);
            prepStmt.setString(n++, client.getNom().toUpperCase());
            prepStmt.setString(n++, client.getPrenom());
            prepStmt.setString(n++, client.getAdresse());
            prepStmt.setString(n++, client.getVille());
            prepStmt.setString(n++, client.getCodePostal());
            prepStmt.setString(n++, client.getTelephone());
            prepStmt.setString(n++, client.getEmail());
            prepStmt.setDate(n++, dateNaisSQL);
            prepStmt.setInt(n++, client.getEnfants());
            prepStmt.setString(n++, client.getEmploi());
            prepStmt.setString(n++, client.getLoisirs());
            prepStmt.setString(n++, client.getRefere());
            prepStmt.setString(n++, client.getAutresTherapies());
            prepStmt.setString(n++, client.getMedicaments());
            prepStmt.setString(n++, client.getComplements());
            prepStmt.setBoolean(n++, client.getCardiaque());
            prepStmt.setBoolean(n++, client.getHypoglycemie());
            prepStmt.setBoolean(n++, client.getAsthme());
            prepStmt.setBoolean(n++, client.getDiabete());
            prepStmt.setString(n++, client.getAllergies());
            prepStmt.setString(n++, client.getAutre());
            prepStmt.setString(n++, client.getHistoOperations());
            prepStmt.setString(n++, client.getHistoDouleur());
            prepStmt.setString(n++, client.getConstatations());
            prepStmt.setString(n++, client.getAttentions());
            prepStmt.setString(n++, client.getPasMasser());            
            prepStmt.setBoolean(n++, client.getFumeur());
            prepStmt.setBoolean(n++, client.getPeau());
            prepStmt.setBoolean(n++, client.getArthrose());
            prepStmt.setBoolean(n++, client.getOsteoporose());
            prepStmt.setString(n++, client.getNote());
            prepStmt.setString(n++, client.getConstipation());      
            
            prepStmt.setInt(n++, client.getIdClinique());
            
            System.out.println("Requête : " + prepStmt);
            prepStmt.executeUpdate();

        } catch (SQLException e) {

            throw new DAOException("Creation du client " + client + " impossible !" + e);

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
    }

    public boolean updateClient(Client client) throws DAOException {
        Connection con = getConnection();
        PreparedStatement prepStmt = null;
        int k = 0;
    
        java.util.Date dateNais = ConversionType.StringToDate(client.getDateNaissanceString());
        java.sql.Date dateNaisSQL = new java.sql.Date(dateNais.getTime());

        try {
            prepStmt = con.prepareStatement(CLIENT_UPDATE);
            int n = 1;
            prepStmt.setString(n++, client.getNom());
            prepStmt.setString(n++, client.getPrenom());
            prepStmt.setString(n++, client.getAdresse());
            prepStmt.setString(n++, client.getVille());
            prepStmt.setString(n++, client.getCodePostal());
            prepStmt.setString(n++, client.getTelephone());
            prepStmt.setString(n++, client.getEmail());
            prepStmt.setDate(n++, dateNaisSQL);
            prepStmt.setInt(n++, client.getEnfants());
            prepStmt.setString(n++, client.getEmploi());
            prepStmt.setString(n++, client.getLoisirs());
            prepStmt.setString(n++, client.getRefere());
            prepStmt.setString(n++, client.getAutresTherapies());
            prepStmt.setString(n++, client.getMedicaments());
            prepStmt.setString(n++, client.getComplements());
            prepStmt.setBoolean(n++, client.getCardiaque());
            prepStmt.setBoolean(n++, client.getHypoglycemie());
            prepStmt.setBoolean(n++, client.getAsthme());
            prepStmt.setBoolean(n++, client.getDiabete());
            prepStmt.setString(n++, client.getAllergies());
            prepStmt.setString(n++, client.getAutre());
            prepStmt.setString(n++, client.getHistoOperations());
            prepStmt.setString(n++, client.getHistoDouleur());
            prepStmt.setString(n++, client.getConstatations());
            prepStmt.setString(n++, client.getAttentions());
            prepStmt.setString(n++, client.getPasMasser());            
            prepStmt.setBoolean(n++, client.getFumeur());
            prepStmt.setBoolean(n++, client.getPeau());
            prepStmt.setBoolean(n++, client.getArthrose());
            prepStmt.setBoolean(n++, client.getOsteoporose());
            prepStmt.setString(n++, client.getNote());
            prepStmt.setString(n++, client.getConstipation());            
            prepStmt.setInt(n++, client.getId());
            k = prepStmt.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Mise a jour du client " + client.getId() + " impossible !" + e);
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
        return k == 1;
    }

    
    
   
    

   

    public boolean deleteClient(String id, int idClinique) throws DAOException {
        Connection con = getConnection();
        PreparedStatement prepStmt = null;
        int n = 0;
        int idClient;
        try {
            idClient = Integer.parseInt(id);
        }catch(NumberFormatException e){
            idClient = 0 ;
        }
        
        try {
            prepStmt = con.prepareStatement(CLIENT_DELETE);           
            prepStmt.setInt(1, idClient);
            prepStmt.setInt(2, idClinique);
            n = prepStmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Suppression du client " + id + " impossible !" + e);
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
        return n == 1 ;
    }

    public boolean deleteTraitementClient(String id) throws DAOException {
        Connection con = getConnection();
        PreparedStatement prepStmt = null;
        int n = 0;
        try {
            prepStmt = con.prepareStatement(CLIENT_TRAITEMENT_DELETE);
            prepStmt.setInt(1, ConversionType.StringToInt(id));
            n = prepStmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Suppression des traitements du client " + id + " impossible !" + e);
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
        return n == 1 ;
    }

    public boolean deleteProduitAcheteClient(String id) throws DAOException {
        Connection con = getConnection();
        PreparedStatement prepStmt = null;
        int n = 0;
        try {
            prepStmt = con.prepareStatement(CLIENT_PRODUITACHETE_DELETE);
            prepStmt.setInt(1, ConversionType.StringToInt(id));
            n = prepStmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Suppression des produits achetes du client " + id + " impossible !" + e);
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
        return n == 1 ;
    }

    public boolean deleteDocjointClient(String id) throws DAOException {
        Connection con = getConnection();
        PreparedStatement prepStmt = null;
        int n = 0;
        try {
            prepStmt = con.prepareStatement(CLIENT_DOCJOINT_DELETE);
            prepStmt.setInt(1, ConversionType.StringToInt(id));
            n = prepStmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Suppression des documents joints du client " + id + " impossible !" + e);
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
        return n == 1 ;
    }

    
    public Connection getConnection() throws DAOException {
        return cnxPool.getConnection();
    }
}

    

