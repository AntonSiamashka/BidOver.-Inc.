/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bidover.auto.database.dao;
import com.bidover.auto.model.bean.TimeEntity;
import com.bidover.auto.database.connectionpool.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Jedai
 */
public class TimeDAO {

    private Connection connection;
    private PreparedStatement st;
    private ConnectionPool pool;

    public TimeDAO() {
        pool = ConnectionPool.getConnectionPool();
    }

    public TimeEntity getNextTime() throws ClassNotFoundException, SQLException, SQLException {
        int id = 0;
        long time = 0;
        TimeEntity time_entity = null;
        connection = pool.getConnection();
        st = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.timetable ORDER BY time");
        ResultSet resultSet = st.executeQuery();
        if (resultSet.next()) {
            id = resultSet.getInt("id");
            time = resultSet.getLong("time");
           time_entity = new TimeEntity(id, time);
        }
        resultSet.close();
        st.close();
        pool.releaseConnection(connection);
        return time_entity;
    }

    public void deleteField(TimeEntity time) throws ClassNotFoundException, SQLException {
        connection = pool.getConnection();
        st = (PreparedStatement) connection.prepareStatement("Delete from bidover_db.timetable where time="+time.getTime());
        st.executeUpdate();
        st.close();
        pool.releaseConnection(connection);
    }

    public void addField(long time,Integer lotId) {
        try {
            connection = pool.getConnection();
            st = (PreparedStatement) connection.prepareStatement("INSERT INTO bidover_db.timetable(time,lot_id) VALUES(?,?)");
            st.setLong(1, time);
            st.setInt(2, lotId);
            st.executeUpdate();
            st.close();
            pool.releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(TimeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

