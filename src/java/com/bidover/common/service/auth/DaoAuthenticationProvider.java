package com.bidover.common.service.auth;

import com.Ostermiller.util.RandPass;
import com.bidover.common.database.dao.UserDAO;
import com.bidover.common.mail.MailSender;
import com.bidover.common.model.bean.User;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class DaoAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private UserDAO userDAO;
    /**
     * Password encoder.
     */
    private PasswordEncoder passwordEncoder = new PlaintextPasswordEncoder();

    
    
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
            UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        if (userDetails == null) {
            throw new BadCredentialsException("bad credentials");
        }
    }

//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication,
//            UserDetails user) {
//        // Get user authorities.
//        Collection<GrantedAuthority> authorities = userDAO.loadUserByUsername(user.getUsername()).getAuthorities();
//        ((User) user).setAuthorities(authorities);
//        return super.createSuccessAuthentication(principal, authentication, user);
//    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        User userBean = getUserByEmailAndPassword(username, authentication.getCredentials().toString());
        if (userBean == null) {
            throw new UsernameNotFoundException(username);
        }
        return userBean;
    }

    public User getUserByEmailAndPassword(String username, String password) {
        String passwordHash = passwordEncoder.encodePassword(password, null);
        User userBean = null;
        try {
            userBean = userDAO.getUserByEmailAndPassword(username, passwordHash);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoAuthenticationProvider.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DaoAuthenticationProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userBean;
    }
    
     public User getUserById(Integer userId) {
        return userDAO.getUserById(userId);
    }

    public boolean isMailExist(String email) {
        boolean result = true;
        try {
            result = userDAO.isMailExist(email);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoAuthenticationProvider.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DaoAuthenticationProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public Integer addUser(String email) {
        Integer userId = null;
        String password = null;
        String encodedPassword = null;
        password = new RandPass(RandPass.NUMBERS_AND_LETTERS_ALPHABET).getPass(8);
        encodedPassword = passwordEncoder.encodePassword(password, null);
        System.out.println(password);
        try {
            userId = userDAO.addUser(email, encodedPassword);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoAuthenticationProvider.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DaoAuthenticationProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        sendMail(email, password);
        return userId;
    }

    private void sendMail(String email, String password) {
        try {
            String mailMessage = "Your password: " + password;
            mailMessage += "/n";
            mailMessage += "<a href=\"http://localhost:8084/BidiverLast/login.jsp\">Login</a>";
            System.out.println(mailMessage);
            MailSender mailSender = new MailSender();
            mailSender.sendMail(email, "123", "Bidover.com", "Bidover registration", mailMessage);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoAuthenticationProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Sets the PasswordEncoder instance to be used to encode and validate passwords.
     * If not set, {@link PlaintextPasswordEncoder} will be used by default.
     *
     * @param passwordEncoder The passwordEncoder to use
     */
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Get password encoder.
     *
     * @return passwordEncoder.
     */
    protected PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
