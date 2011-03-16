package com.bidover.util;

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 

public class SessionSnoop extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession session;
	
	public SessionSnoop (HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}

	public void setLanguage(String LNG) throws ServletException, IOException {
		session.setAttribute("LNG", LNG);
	}
	
	// FOR USERS
	public boolean isUser() throws ServletException, IOException {
		boolean user = false;
		if ( session != null & request.isRequestedSessionIdValid() & session.getAttribute("userAccess") != null ) {
			String access = session.getAttribute("userAccess").toString();
			if (access.equals("granted")) user = true;
		}
		return user;
	}

	public void setUser(int UID) throws ServletException, IOException {
		session.setAttribute("userAccess", "granted");
		session.setAttribute("UID", UID);
	}
	
	public int getUserID() throws ServletException, IOException {
		String UID = session.getAttribute("UID").toString();
		return (UID != null ? Integer.parseInt(UID) : 0);
	}

	public void closeUserSession() throws ServletException, IOException {
		session.removeAttribute("userAccess");
		session.removeAttribute("UID");
	}

	// FOR AFFILIATES
	public boolean isAft() throws ServletException, IOException {
		boolean aft = false;
		if ( session != null & request.isRequestedSessionIdValid() & session.getAttribute("aftAccess") != null ) {
			String access = (String) session.getAttribute("aftAccess");
			if (access.equals("granted")) aft = true;
		}
		return aft;
	}

	public void setAft(int AID) throws ServletException, IOException {
		session.setAttribute("aftAccess", "granted");
		session.setAttribute("AID", AID);
	}
	
	public int getAftID() throws ServletException, IOException {
		String AID = session.getAttribute("AID").toString();
		return (AID != null ? Integer.parseInt(AID) : 0);
	}

	public void closeAftSession() throws ServletException, IOException {
		session.removeAttribute("aftAccess");
		session.removeAttribute("AID");
	}
	
}