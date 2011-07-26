/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.common.service.auth;

import com.bidover.common.model.bean.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 *
 * @author Jedai
 */
public class BidoverAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private String defaultTargetUrl;
    
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication a) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User user = (User) a.getPrincipal();
        session.setAttribute("profile", user);
        session.setAttribute("status", user.getStatus());
        String result = "{'status':" + user.getStatus() + "}";
        response.getWriter().write(result);
    }

    public String getDefaultTargetUrl() {
        return defaultTargetUrl;
    }

    public void setDefaultTargetUrl(String defaultTargetUrl) {
        this.defaultTargetUrl = defaultTargetUrl;
    }
    
    
    
}
