/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.common.database.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import com.bidover.common.logger.Logger;
import java.util.ResourceBundle;

/**
 *
 * @author Администратор
 */
public class ConnectionPool {

    private static ConnectionPool mc;
    private int maxConn = 10;
    private Connection[] ConnArray = new Connection[maxConn];
    private int withdrewed = 0;
    private Properties properties = new Properties();

    private ConnectionPool() {
        
        for (int i = 0; i < maxConn; i++) {
            try {
                ConnArray[i] = openOneConnection();
            } catch (ClassNotFoundException ex) {
                Logger.log(ex);
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }

    }

    static public ConnectionPool getConnectionPool() {
        ConnectionPool mcc;
        mcc = (mc == null ? new ConnectionPool() : mc);
        mc = mcc;
        return mcc;
    }

    synchronized public Connection getConnection() {
        Connection conn = null;
        while (withdrewed == 10) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.log(ex);
            }
        }

        withdrewed++;

        for (int i = 0; i < maxConn; i++) {
            if (ConnArray[i] != null) {
                conn = ConnArray[i];
                ConnArray[i] = null;
                break;
            }
        }

        notify();
        return conn;
    }

    synchronized public void releaseConnection(Connection conn) throws SQLException {
        withdrewed--;
        for (int i = 0; i < maxConn; i++) {
            if (ConnArray[i] == null) {
                ConnArray[i] = conn;
                break;
            }
        }
        notify();
    }

    synchronized public void closeAll() {
        for (int i = 0; i < maxConn; i++) {
            if (ConnArray[i] != null) {
                try {
                    ConnArray[i].close();
                } catch (Exception ex) {
                    Logger.log(ex);
                }
            }
        }
        ConnArray = null;
    }

    private Connection openOneConnection() throws ClassNotFoundException, SQLException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
        Properties prop = new Properties();
        Class.forName(resourceBundle.getString("JDBC_DRIVER"));
        prop.setProperty("user", resourceBundle.getString("user"));
        prop.setProperty("password", resourceBundle.getString("password"));
        prop.setProperty("useUnicode", resourceBundle.getString("useUnicode"));
        prop.setProperty("characterEncoding", resourceBundle.getString("characterEncoding"));
        return DriverManager.getConnection(resourceBundle.getString("ADDRESS_DB"), prop);
    }
}
