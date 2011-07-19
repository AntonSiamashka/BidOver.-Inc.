/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.common.database.dao;

import com.bidover.common.model.bean.Timezone;
import com.bidover.common.encrypters.HashEncrypter;
import com.bidover.common.model.bean.User;
import com.Ostermiller.util.RandPass;
import com.bidover.common.model.bean.Role;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author Администратор
 */
public class UserDAO extends BaseDAO implements UserDetailsService {

    private final String BASE = "user";
    
    public static int PRE_REG_STATUS = 0;
    public static int TOTAL_REG_STATUS = 1;

    public UserDAO() {
        super();
        connection = connectionPool.getConnection();
        try {
            peparedStatement = connection.prepareStatement("SELECT @key:=?");
            peparedStatement.setString(1, "l0");
            peparedStatement.executeQuery();
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addUser(User user) throws ClassNotFoundException, SQLException, SQLException {
        connection = connectionPool.getConnection();
        peparedStatement = connection.prepareStatement("INSERT INTO bidover_db.user(lastName, firstName, nickName, password,timezone_id, email) VALUES(?,?,?,?,?,AES_ENCRYPT(?, @key))");
        String enc_pass = null;
        System.out.println(user.getPassword());
        try {
            enc_pass = HashEncrypter.encrypt(user.getPassword());
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        peparedStatement.setString(1, user.getLastName());
        peparedStatement.setString(2, user.getFirstName());
        peparedStatement.setString(3, user.getNickName());
        peparedStatement.setString(4, enc_pass);
        peparedStatement.setInt(5, user.getTimezone().getId());
        peparedStatement.setString(6, user.getEmail());
        peparedStatement.executeUpdate();
        peparedStatement.close();
        connectionPool.releaseConnection(connection);
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
            connection = connectionPool.getConnection();
            try {
                peparedStatement = connection.prepareStatement("UPDATE bidover_db.user SET user.lastName=? , user.firstName=? , user.nickName=? , user.timezone_id=? , user.user_data=? , user.user_country=? , user.user_phone=? , user.user_address=? , user.user_status=?, user.role_id=? WHERE user.id=?");
                peparedStatement.setString(1, lastName);
                peparedStatement.setString(2, firstName);
                peparedStatement.setString(3, nickName);
                peparedStatement.setInt(4, timezone.getId());
                peparedStatement.setString(5, userData);
                peparedStatement.setString(6, userCountry);
                peparedStatement.setString(7, userPhone);
                peparedStatement.setString(8, userAddress);
                peparedStatement.setInt(9, TOTAL_REG_STATUS);
                peparedStatement.setInt(10, TOTAL_REG_STATUS);
                peparedStatement.setInt(11, user.getId());
                peparedStatement.executeUpdate();
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
                flag = true;
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return flag;
    }

    public boolean setUserStatus(int userStatus, int userId) {
        boolean flag = false;
        connection = connectionPool.getConnection();
        try {
            peparedStatement = connection.prepareStatement("UPDATE bidover_db.user SET user_status=?, role_id=? WHERE Id=?");
            peparedStatement.setInt(1, userStatus);
            peparedStatement.setInt(2, userStatus);
            peparedStatement.setInt(3, userId);
            peparedStatement.executeUpdate();
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
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
            connection = connectionPool.getConnection();
            peparedStatement = connection.prepareStatement("INSERT INTO bidover_db.user(password,user_status,user_reg_date,email,role_id) VALUES(?,?,?,AES_ENCRYPT(?, @key),?)");
            String enc_pass = null;
            password = new RandPass(RandPass.NUMBERS_AND_LETTERS_ALPHABET).getPass(8);
            enc_pass = HashEncrypter.encrypt(password);
            peparedStatement.setString(1, enc_pass);
            peparedStatement.setInt(2, PRE_REG_STATUS);
            peparedStatement.setLong(3, System.currentTimeMillis());
            peparedStatement.setString(4, email);
            peparedStatement.setInt(5, 0);
            peparedStatement.executeUpdate();
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
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
            connection = connectionPool.getConnection();
            peparedStatement = connection.prepareStatement("SELECT * FROM bidover_db.user where email=AES_ENCRYPT('" + email + "',@key) AND password='" + passEncr + "'");
            ResultSet resultSet = peparedStatement.executeQuery();
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
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
                User user = new User(Integer.valueOf(id), firstName, lastName, nickName, email, pass, timezone, userStatus);
                user.setUserData(userData);
                user.setUserAddress(userAddress);
                user.setUserCountry(userCountry);
                user.setUserPhone(userPhone);
                user.setAuthorities(getRolesByUserId(user.getId()));
                return user;
            }
            resultSet.close();
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Integer getUserIdByEmail(String email)
            throws ClassNotFoundException, SQLException {
        Integer userId = null;
        connection = connectionPool.getConnection();
        peparedStatement = connection.prepareStatement("SELECT * FROM bidover_db.user where email=AES_ENCRYPT('" + email + "',@key)");
        ResultSet resultSet = peparedStatement.executeQuery();
        if (resultSet.next()) {
            userId = resultSet.getInt("Id");
        }
        resultSet.close();
        peparedStatement.close();
        connectionPool.releaseConnection(connection);
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
            executeRequest("UPDATE bidover_db.user SET user.password='" + password + "' WHERE user.id=" + userId);
            flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public boolean setUserBalance(Double amount, int userId) {
        boolean flag = false;
        try {
            executeRequest("UPDATE bidover_db.user SET user.balance=" + amount + " WHERE user.id=" + userId);
            flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public Double getUserBalance(int userId)
            throws ClassNotFoundException, SQLException {
        Double userBalance = null;
        connection = connectionPool.getConnection();
        peparedStatement = connection.prepareStatement("SELECT user.balance FROM bidover_db.user WHERE user.id=" + userId);
        ResultSet resultSet = peparedStatement.executeQuery();
        if (resultSet.next()) {
            userBalance = resultSet.getDouble(1);
        }
        resultSet.close();
        peparedStatement.close();
        connectionPool.releaseConnection(connection);
        return userBalance;
    }

    public boolean isNickNameExist(String nickName) throws ClassNotFoundException, SQLException {
        connection = connectionPool.getConnection();
        peparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM bidover_db.user WHERE nickName='" + nickName + "'");
        ResultSet resultSet = peparedStatement.executeQuery();
        boolean existsUser = resultSet.next() & resultSet.getInt(1) > 0 ? true : false;
        resultSet.close();
        connectionPool.releaseConnection(connection);
        return existsUser;
    }

    public boolean isMailExist(String email) throws ClassNotFoundException, SQLException {
        connection = connectionPool.getConnection();
        peparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM bidover_db.user WHERE email=AES_ENCRYPT('" + email + "',@key)");
        ResultSet resultSet = peparedStatement.executeQuery();
        boolean existsUser = resultSet.next() & resultSet.getInt(1) > 0 ? true : false;
        resultSet.close();
        connectionPool.releaseConnection(connection);
        return existsUser;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        User user = getUserByEmail(email);
        user.setAuthorities(getRolesByUserId(user.getId()));
        return user;
    }

    public User getUserByEmail(String email) {
        User user = new User();
        try {
            connection = connectionPool.getConnection();
            peparedStatement = connection.prepareStatement("SELECT * FROM bidover_db.user where email=AES_ENCRYPT('" + email + "',@key)");
            ResultSet resultSet = peparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("Id"));
                user.setEmail(email);
                user.setPassword(resultSet.getString("password"));
                user.setAuthorities(getRolesByUserId(user.getId()));
            }
            resultSet.close();
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public List<GrantedAuthority> getRolesByUserId(Integer id) {
        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        try {
            connection = connectionPool.getConnection();
            peparedStatement = connection.prepareStatement("SELECT r.title FROM bidover_db.user u inner join bidover_db.roles r on u.role_id=r.role_id where u.id='" + id + "'");
            ResultSet resultSet = peparedStatement.executeQuery();
            while (resultSet.next()) {
                roles.add(new Role(resultSet.getString("title")));
            }
            resultSet.close();
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return roles;
    }
    
    public User createBean(ResultSet resultSet) throws SQLException {
        User bean = createNewInstance();
        return populateBean(bean, resultSet);
    }
    
    public User createNewInstance() {
        return new User();
    }
    
    public User populateBean(User bean, ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt(BASE+".Id");
        String firstName = resultSet.getString(BASE+".firstName");
        String lastName = resultSet.getString(BASE+".lastName");
        String nickName = resultSet.getString(BASE+".nickName");
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setNickName(nickName);
        return bean;
    }
    
}
