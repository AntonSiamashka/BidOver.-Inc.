/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.common.database.dao;

import com.bidover.common.model.bean.Timezone;
import com.bidover.auto.database.connectionpool.ConnectionPool;
import java.sql.PreparedStatement;
import com.bidover.common.encrypters.HashEncrypter;
import com.bidover.common.model.bean.User;
import com.Ostermiller.util.RandPass;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Администратор
 */
public class UserDAO {

    private Connection c;
    private static PreparedStatement st;
    private ConnectionPool cp;
    public static int START_STATUS = 0;
    public static int PRE_REG_STATUS = 1;
    public static int TOTAL_REG_STATUS = 2;

    public UserDAO() throws SQLException {
        cp = ConnectionPool.getConnectionPool();
        c = cp.getConnection();
        st = c.prepareStatement("SELECT @key:=?");
        st.setString(1, "l0");
        st.executeQuery();
        st.close();
        cp.releaseConnection(c);
    }

    public void addUser(User user) throws ClassNotFoundException, SQLException, SQLException {
        c = cp.getConnection();
        st = c.prepareStatement("INSERT INTO bidover_db.user(lastName, firstName, nickName, password,timezone_id, email) VALUES(?,?,?,?,?,AES_ENCRYPT(?, @key))");
        String enc_pass = null;
        try {
            enc_pass = HashEncrypter.encrypt(user.getPassword());
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        st.setString(1, user.getLastName());
        st.setString(2, user.getFirstName());
        st.setString(3, user.getNickName());
        st.setString(4, enc_pass);
        st.setInt(5, user.getTimezone().getId());
        st.setString(6, user.getEmail());
        st.executeUpdate();
        st.close();
        cp.releaseConnection(c);
    }

    public boolean setUserInfo(User user) {
        boolean flag = false;
        String lastName = user.getLastName();
        String firstName = user.getFirstName();
        String nickName = user.getNickName();
        Timezone timezone = user.getTimezone();
        String userData = user.getUserData();
        String userCountry = user.getUserCountry();
        String userPhone = user.getUserPhone();
        String userAddress = user.getUserAddress();
        if (lastName != null && firstName != null && nickName != null && timezone != null && timezone.getId() != null && userCountry != null && userAddress != null) {
            c = cp.getConnection();
            try {
                st = c.prepareStatement("UPDATE bidover_db.user SET user.lastName=? , user.firstName=? , user.nickName=? , user.timezone_id=? , user.user_data=? , user.user_country=? , user.user_phone=? , user.user_address=? , user.user_status=? WHERE user.id=?");
                st.setString(1, lastName);
                st.setString(2, firstName);
                st.setString(3, nickName);
                st.setInt(4, timezone.getId());
                st.setString(5, userData);
                st.setString(6, userCountry);
                st.setString(7, userPhone);
                st.setString(8, userAddress);
                st.setInt(9, TOTAL_REG_STATUS);
                st.setInt(10, user.getId());
                st.executeUpdate();
                st.close();
                cp.releaseConnection(c);
                flag = true;
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return flag;
    }

    public boolean setUserStatus(int userStatus, int userId) {
        boolean flag = false;
        c = cp.getConnection();
        try {
            st = c.prepareStatement("UPDATE bidover_db.user SET user_status=? WHERE Id=?");
            st.setInt(1, userStatus);
            st.setInt(2, userId);
            st.executeUpdate();
            st.close();
            cp.releaseConnection(c);
            flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public String addUser(String email)
           throws ClassNotFoundException, SQLException, SQLException {
        String password = null;
        try {
            c = cp.getConnection();
            st = c.prepareStatement("INSERT INTO bidover_db.user(password,user_status,user_reg_date,email) VALUES(?,?,?,AES_ENCRYPT(?, @key))");
            String enc_pass = null;
            password = new RandPass(RandPass.NUMBERS_AND_LETTERS_ALPHABET).getPass(8);
            enc_pass = HashEncrypter.encrypt(password);
            st.setString(1, enc_pass);
            st.setInt(2, START_STATUS);
            st.setLong(3, System.currentTimeMillis());
            st.setString(4, email);
            st.executeUpdate();
            st.close();
            cp.releaseConnection(c);
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return password;
    }

    public User getUserByEmailAndPassword(String email, String pass)
           throws ClassNotFoundException, SQLException {
        String passEncr = null;
        try {
            passEncr = HashEncrypter.encrypt(pass);
            c = cp.getConnection();
            st = c.prepareStatement("SELECT * FROM bidover_db.user where email=AES_ENCRYPT('" + email + "',@key) AND password='" + passEncr + "'");
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                String id = resultSet.getString("Id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String nickName = resultSet.getString("nickName");
                String userData = resultSet.getString("user_data");
                String userCountry = resultSet.getString("user_country");
                String userAddress = resultSet.getString("user_address");
                String userPhone = resultSet.getString("user_phone");
                String timezoneId = resultSet.getString("timezone_id");
                Integer userStatus = resultSet.getInt("user_status");
                TimezoneDAO timezoneDAO = new TimezoneDAO();
                Timezone timezone = null;
                if (timezoneId != null) {
                    timezone = timezoneDAO.find(Integer.valueOf(timezoneId));
                }
                resultSet.close();
                st.close();
                cp.releaseConnection(c);
                User user = new User(Integer.valueOf(id), firstName, lastName, nickName, email, pass, timezone, userStatus);
                user.setUserData(userData);
                user.setUserAddress(userAddress);
                user.setUserCountry(userCountry);
                user.setUserPhone(userPhone);
                return user;
            }
            resultSet.close();
            st.close();
            cp.releaseConnection(c);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Integer getUserIdByEmail(String email)
           throws ClassNotFoundException, SQLException {
        Integer userId = null;
        c = cp.getConnection();
        st = c.prepareStatement("SELECT * FROM bidover_db.user where email=AES_ENCRYPT('" + email + "',@key)");
        ResultSet resultSet = st.executeQuery();
        if (resultSet.next()) {
            userId = resultSet.getInt("Id");
        }
        resultSet.close();
        st.close();
        cp.releaseConnection(c);
        return userId;
    }

    public String changePassword(int userId) {
        String password = null;
        try {
            String enc_pass;
            password = new RandPass(RandPass.NUMBERS_AND_LETTERS_ALPHABET).getPass(8);
            enc_pass = HashEncrypter.encrypt(password);
            if (!setPassword(enc_pass, userId)) {
                password = null;
            }
        } catch (NoSuchAlgorithmException ex) {
            password = null;
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return password;
    }

    public String changePassword(String password, int userId) {
        String enc_pass = null;
        try {
            enc_pass = HashEncrypter.encrypt(password);
            if (!setPassword(enc_pass, userId)) {
                enc_pass = null;
            }
        } catch (NoSuchAlgorithmException ex) {
            enc_pass = null;
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return enc_pass;
    }

    public boolean setPassword(String password, int userId) {
        boolean flag = false;
        try {
/*            c = cp.getConnection();
            st = c.prepareStatement("UPDATE bidover_db.user SET user.password=? WHERE user.id=?");
            st.setString(1, password);
            st.setInt(2, userId);
            st.executeUpdate();
            st.close();
            cp.releaseConnection(c);*/
            executeRequest("UPDATE bidover_db.user SET user.password='"+password+"' WHERE user.id="+userId);
            flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public boolean setUserBalance(Double amount, int userId) {
        boolean flag = false;
        try {
            executeRequest("UPDATE bidover_db.user SET user.balance="+amount+" WHERE user.id="+userId);
            flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public Double getUserBalance(int userId)
           throws ClassNotFoundException, SQLException {
        Double userBalance = null;
        c = cp.getConnection();
        st = c.prepareStatement("SELECT user.balance FROM bidover_db.user WHERE user.id="+userId);
        ResultSet resultSet = st.executeQuery();
        if (resultSet.next()) {
            userBalance = resultSet.getDouble(1);
        }
        resultSet.close();
        st.close();
        cp.releaseConnection(c);
        return userBalance;
    }

    public boolean isNickNameExist(String nickName) throws ClassNotFoundException, SQLException {
        c = cp.getConnection();
        st = c.prepareStatement("SELECT COUNT(*) FROM bidover_db.user WHERE nickName='" + nickName + "'");
        ResultSet resultSet = st.executeQuery();
        boolean existsUser = resultSet.next() & resultSet.getInt(1) > 0 ? true : false;
        resultSet.close();
        cp.releaseConnection(c);
        return existsUser;
    }

    public boolean isMailExist(String email) throws ClassNotFoundException, SQLException {
        c = cp.getConnection();
        st = c.prepareStatement("SELECT COUNT(*) FROM bidover_db.user WHERE email=AES_ENCRYPT('" + email + "',@key)");
        ResultSet resultSet = st.executeQuery();
        boolean existsUser = resultSet.next() & resultSet.getInt(1) > 0 ? true : false;
        resultSet.close();
        cp.releaseConnection(c);
        return existsUser;
    }

        private void executeRequest(String request) throws SQLException {
        c = cp.getConnection();
        st = (PreparedStatement) c.prepareStatement(request);
        st.execute();
        st.close();
        cp.releaseConnection(c);
    }
}
