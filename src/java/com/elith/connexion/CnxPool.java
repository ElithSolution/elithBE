/*
 * CnxPool.java
 *
 * Created on 2 fevrier 2008, 19:27
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.elith.connexion;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Kat
 */
public class CnxPool {

    private DataSource dataSource;
    private static CnxPool cnxPool;

    private CnxPool() throws DAOException {
        try {
            InitialContext context = new InitialContext();
            String JNDIDataSource = ApplicationProperties.getDatasourceJndiName();
            dataSource = (DataSource) context.lookup(JNDIDataSource);
        } catch (NamingException e) {
            throw new DAOException("localisation DataSource impossible !" + e);
        }
    }

    public static synchronized CnxPool getInstance() throws DAOException {
        if (cnxPool == null) {
            cnxPool = new CnxPool();
        }
        return cnxPool;
    }

    public Connection getConnection() throws DAOException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }
}
