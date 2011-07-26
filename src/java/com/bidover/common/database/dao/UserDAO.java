/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.common.database.dao;

import com.bidover.common.model.bean.Timezone;
import com.bidover.common.model.bean.User;
import com.Ostermiller.util.RandPass;
import com.bidover.common.model.bean.Role;
import com.bidover.util.IntegerUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
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
    private Md5PasswordEncoder encoder = new Md5PasswordEncoder();

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

    public Integer addUser(String email, String password)
            throws ClassNotFoundException, SQLException {
        Integer userId = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();
            peparedStatement = connection.prepareStatement("INSERT INTO bidover_db.user(password,user_status,user_reg_date,email,role_id) VALUES(?,?,?,AES_ENCRYPT(?, @key),?)", PreparedStatement.RETURN_GENERATED_KEYS);
            peparedStatement.setString(1, password);
            peparedStatement.setInt(2, PRE_REG_STATUS);
            peparedStatement.setLong(3, System.currentTimeMillis());
            peparedStatement.setString(4, email);
            peparedStatement.setInt(5, PRE_REG_STATUS);
            peparedStatement.executeUpdate();
            resultSet = peparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                userId = resultSet.getInt(1);
            }
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userId;
    }

    public User getUserByEmailAndPassword(String email, String pass)
            throws ClassNotFoundException, SQLException {
        String passEncr = pass;
        //passEncr = encoder.encodePassword(pass, null);
        connection = connectionPool.getConnection();
        peparedStatement = connection.prepareStatement("SELECT @key:=?");
        peparedStatement.setString(1, "l0");
        peparedStatement.executeQuery();
        peparedStatement = connection.prepareStatement("SELECT * FROM bidover_db.user where email=AES_ENCRYPT('" + email + "',@key) AND password='" + passEncr + "'");
        ResultSet resultSet = peparedStatement.executeQuery();
        if (resultSet.next()) {
            User user = createBean(resultSet);
            resultSet.close();
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
            return user;
        }
        resultSet.close();
        peparedStatement.close();
        connectionPool.releaseConnection(connection);
        return null;
    }

    public Integer getUserIdByEmail(String email)
            throws ClassNotFoundException, SQLException {
        Integer userId = null;
        connection = connectionPool.getConnection();
        peparedStatement = connection.prepareStatement("SELECT @key:=?");
        peparedStatement.setString(1, "l0");
        peparedStatement.executeQuery();
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
        String enc_pass;
        password = new RandPass(RandPass.NUMBERS_AND_LETTERS_ALPHABET).getPass(8);
        enc_pass = encoder.encodePassword(password, null);
        if (!setPassword(enc_pass, userId)) {
            password = null;
        }
        return password;
    }

    public String changePassword(String password, int userId) {
        String enc_pass = null;

        enc_pass = encoder.encodePassword(password, null);
        if (!setPassword(enc_pass, userId)) {
            enc_pass = null;
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

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = getUserByEmail(email);
        user.setAuthorities(getRolesByUserId(user.getId()));
        return user;
    }

    public User getUserByEmail(String email) {
        User user = new User();
        try {
            connection = connectionPool.getConnection();
            peparedStatement = connection.prepareStatement("SELECT @key:=?");
            peparedStatement.setString(1, "l0");
            peparedStatement.executeQuery();
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

    public User getUserById(Integer userId) {
        User user = null;
        try {
            connection = connectionPool.getConnection();
            peparedStatement = connection.prepareStatement("SELECT @key:=?");
            peparedStatement.setString(1, "l0");
            peparedStatement.executeQuery();
            peparedStatement = connection.prepareStatement("SELECT * FROM bidover_db.user where id=?");
            peparedStatement.setInt(1, userId);
            ResultSet resultSet = peparedStatement.executeQuery();
            if (resultSet.next()) {
                user = createBean(resultSet);
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

    public List<GrantedAuthority> getRolesById(Integer id) {
        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        try {
            connection = connectionPool.getConnection();
            peparedStatement = connection.prepareStatement("SELECT r.title FROM bidover_db.roles r where r.role_id='" + id + "'");
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

        String id = resultSet.getString(BASE + ".id");
        String email = resultSet.getString(BASE + ".email");
        String firstName = resultSet.getString(BASE + ".firstName");
        String lastName = resultSet.getString(BASE + ".lastName");
        String nickName = resultSet.getString(BASE + ".nickName");
        String userData = resultSet.getString(BASE + ".user_data");
        String userCountry = resultSet.getString(BASE + ".user_country");
        String userAddress = resultSet.getString(BASE + ".user_address");
        String userPhone = resultSet.getString(BASE + ".user_phone");
        String timezoneId = resultSet.getString(BASE + ".timezone_id");
        Integer userStatus = resultSet.getInt(BASE + ".user_status");
        Integer role = resultSet.getInt(BASE + ".role_id");
        TimezoneDAO timezoneDAO = new TimezoneDAO();
        Timezone timezone = null;
        if (timezoneId != null) {
            timezone = timezoneDAO.find(IntegerUtil.parseString(timezoneId));
        }
        bean.setId(IntegerUtil.parseString(id));
        bean.setFirstName(firstName);
        bean.setLastName(lastName);
        bean.setNickName(nickName);
        bean.setEmail(email);
        bean.setStatus(userStatus);
        bean.setAuthorities(getRolesById(role));
        bean.setUserData(userData);
        bean.setUserAddress(userAddress);
        bean.setUserCountry(userCountry);
        bean.setUserPhone(userPhone);
        bean.setTimezone(timezone);
        return bean;
    }
}
